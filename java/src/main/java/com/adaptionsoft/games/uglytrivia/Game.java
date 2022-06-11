package com.adaptionsoft.games.uglytrivia;

import com.adaptionsoft.games.domain.Deck;
import com.adaptionsoft.games.domain.Player;
import com.adaptionsoft.games.domain.Players;
import com.adaptionsoft.games.enums.Category;

import java.util.ArrayList;

import static com.adaptionsoft.games.enums.Category.*;

public class Game {
    public static final int MAX_QUESTIONS_NUMBER = 50;

    Deck deck; // TODO: Make this more extensible. Use a factory for the Deck

    ArrayList<String> playersOld = new ArrayList<>();
    Players players = new Players();
    int[] places = new int[6];

    boolean[] inPenaltyBox = new boolean[6];

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public Game() {
        deck = new Deck(MAX_QUESTIONS_NUMBER);
    }

    public boolean isPlayable() {
        return (players.howMany() >= 2);
    }

    public void addPlayer(String playerName) {
        playersOld.add(playerName);
        places[players.howMany()] = 0;
        inPenaltyBox[players.howMany()] = false;

        players.addPlayer(playerName);
    }

    public void roll(int roll) {
        System.out.println(playersOld.get(currentPlayer) + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (inPenaltyBox[currentPlayer]) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                System.out.println(playersOld.get(currentPlayer) + " is getting out of the penalty box");
                places[currentPlayer] = places[currentPlayer] + roll;
                if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

                System.out.println(playersOld.get(currentPlayer)
                        + "'s new location is "
                        + places[currentPlayer]);
                System.out.println("The category is " + currentCategory().getName());
                askQuestion();
            } else {
                System.out.println(playersOld.get(currentPlayer) + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {

            places[currentPlayer] = places[currentPlayer] + roll;
            if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

            System.out.println(playersOld.get(currentPlayer)
                    + "'s new location is "
                    + places[currentPlayer]);
            System.out.println("The category is " + currentCategory().getName());
            askQuestion();
        }

    }

    private void askQuestion() {
        String questionExtracted = deck.getNextQuestion(currentCategory());

        System.out.println(questionExtracted);
    }

    private Category currentCategory() {
        // TODO: Esto se podría mover al tablero
        switch (places[currentPlayer] % 4) {
            case 0:
                return POP;
            case 1:
                return SCIENCE;
            case 2:
                return SPORTS;
            case 3:
                return ROCK;
            default:
                throw new IllegalStateException("Angelo's fault");
        }
    }

    public boolean wasCorrectlyAnswered() {
        if (inPenaltyBox[currentPlayer] && !isGettingOutOfPenaltyBox) {
            giveNextPlayerTurn();
            return true;
        } else {
            System.out.println("Answer was correct!!!!");
            incrementGoldCoinsForCurrentPlayer();

            boolean winner = didPlayerWin();
            giveNextPlayerTurn();

            return winner;
        }
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(playersOld.get(currentPlayer) + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;

        giveNextPlayerTurn();
        return true;
    }

    private boolean didPlayerWin() {
        return players.getCurrentPlayer().getCoins() != 6;
    }


    // Players

    private void giveNextPlayerTurn() {
        currentPlayer++;
        if (currentPlayer == playersOld.size()) currentPlayer = 0;

        players.giveNextPlayerTurn();
    }

    private void incrementGoldCoinsForCurrentPlayer() {
        players.getCurrentPlayer().earnCoin();
    }
}
