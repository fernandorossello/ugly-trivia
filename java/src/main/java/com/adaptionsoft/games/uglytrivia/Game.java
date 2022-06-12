package com.adaptionsoft.games.uglytrivia;

import com.adaptionsoft.games.domain.Board;
import com.adaptionsoft.games.domain.Deck;
import com.adaptionsoft.games.domain.Player;
import com.adaptionsoft.games.domain.Players;
import com.adaptionsoft.games.enums.Category;

import java.util.ArrayList;

import static com.adaptionsoft.games.enums.Category.*;

public class Game {
    public static final int MAX_QUESTIONS_NUMBER = 50;
    public static final int COINS_TO_WIN = 6;

    Deck deck; // TODO: Make this more extensible. Use a factory for the Deck
    Board board;// TODO: Make this more extensible. Use a factory for the Board

    ArrayList<String> playersOld = new ArrayList<>();
    Players players = new Players();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public Game() {
        deck = new Deck(MAX_QUESTIONS_NUMBER);
        board = new Board();
    }

    public boolean isPlayable() {
        return (players.howMany() >= 2);
    }

    public void addPlayer(String playerName) {
        playersOld.add(playerName);

        Player player = players.addPlayer(playerName);
        board.addPlayer(player);
    }

    public void roll(int roll) {
        System.out.println(players.getCurrentPlayer().getName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (isCurrentPlayerInPenaltyBox()) {
            if (shouldGoOutFromPenaltyBox(roll)) {
                isGettingOutOfPenaltyBox = true;
                System.out.println(playersOld.get(currentPlayer) + " is getting out of the penalty box");

                moveCurrentPlayer(roll);

                askQuestion();
            } else {
                System.out.println(playersOld.get(currentPlayer) + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {
            moveCurrentPlayer(roll);

            askQuestion();
        }
    }

    public boolean wasCorrectlyAnswered() {
        if (isCurrentPlayerInPenaltyBox() && !isGettingOutOfPenaltyBox) {
            giveNextPlayerTurn();
            return false;
        } else {
            System.out.println("Answer was correct!!!!");
            incrementGoldCoinsForCurrentPlayer();

            boolean winner = didPlayerWin();
            giveNextPlayerTurn();

            return winner;
        }
    }

    private boolean shouldGoOutFromPenaltyBox(int roll) {
        return roll % 2 != 0;
    }

    private void moveCurrentPlayer(int roll) {
        board.movePlayer(players.getCurrentPlayer(), roll);
    }

    private void askQuestion() {
        Category currentCategory = currentCategory();

        String questionExtracted = deck.getNextQuestion(currentCategory);
        System.out.println("The category is " + currentCategory.getName());
        System.out.println(questionExtracted);
    }

    private Category currentCategory() {
        return board.getCategory(players.getCurrentPlayer());
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        moveCurrentPlayerToPenaltyBox();

        giveNextPlayerTurn();
        return false;
    }

    private boolean didPlayerWin() {
        return players.getCurrentPlayer().getCoins() == COINS_TO_WIN;
    }

    private void giveNextPlayerTurn() {
        currentPlayer++;
        if (currentPlayer == playersOld.size()) currentPlayer = 0;

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
}
