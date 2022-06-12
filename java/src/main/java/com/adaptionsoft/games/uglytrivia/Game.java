package com.adaptionsoft.games.uglytrivia;

import com.adaptionsoft.games.domain.*;
import com.adaptionsoft.games.enums.Category;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public static final int MAX_QUESTIONS_NUMBER = 50;
    public static final int COINS_TO_WIN = 6;

    private Deck deck; // TODO: Make this more extensible. Use a factory for the Deck
    private Board board;// TODO: Make this more extensible. Use a factory for the Board
    private Players players = new Players();
    private  final List<WinningRule> winningRules = new ArrayList<>();

    boolean isGettingOutOfPenaltyBox;

    public Game() {
        deck = new Deck(MAX_QUESTIONS_NUMBER);
        board = new Board();
    }

    public void addWinningRule(WinningRule rule) {
        this.winningRules.add(rule);
    }

    public boolean isPlayable() {
        return (players.howMany() >= 2);
    }

    public void playTurn(int roll) {
        System.out.println(players.getCurrentPlayer().getName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (isCurrentPlayerInPenaltyBox()) {
            if (shouldGoOutFromPenaltyBox(roll)) {
                isGettingOutOfPenaltyBox = true;
                System.out.println(players.getCurrentPlayer().getName() + " is getting out of the penalty box");

                moveCurrentPlayer(roll);

                askQuestion();
            } else {
                System.out.println(players.getCurrentPlayer().getName() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {
            moveCurrentPlayer(roll);

            askQuestion();
        }
    }

    public void wasCorrectlyAnswered() {
        if (!isCurrentPlayerInPenaltyBox() || isGettingOutOfPenaltyBox) {
            System.out.println("Answer was correct!!!!");
            incrementGoldCoinsForCurrentPlayer();
        }
    }

    private void askQuestion() {
        Category currentCategory = currentCategory();

        String questionExtracted = deck.getNextQuestion(currentCategory);
        System.out.println("The category is " + currentCategory.getName());
        System.out.println(questionExtracted);
    }

    public void wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        moveCurrentPlayerToPenaltyBox();
    }

    private boolean shouldGoOutFromPenaltyBox(int roll) {
        return roll % 2 != 0;
    }

    private void moveCurrentPlayer(int roll) {
        board.movePlayer(players.getCurrentPlayer(), roll);
    }

    private Category currentCategory() {
        return board.getCategory(players.getCurrentPlayer());
    }

    public void giveNextPlayerTurn() {
        players.giveNextPlayerTurn();
    }

    private void incrementGoldCoinsForCurrentPlayer() {
        players.getCurrentPlayer().earnCoin();
    }

    private void moveCurrentPlayerToPenaltyBox() {
        board.moveToPenalizeBox(players.getCurrentPlayer());
    }

    private boolean isCurrentPlayerInPenaltyBox() {
        return board.isInPenaltyBox(players.getCurrentPlayer());
    }

    public void addPlayer(String playerName) {
        Player player = players.addPlayer(playerName);
        board.addPlayer(player);
    }

    public boolean hasCurrentPlayerWon() {
        return winningRules.stream().anyMatch(r -> r.hasWon(players.getCurrentPlayer()));
    }
}
