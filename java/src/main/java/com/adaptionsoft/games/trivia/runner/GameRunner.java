
package com.adaptionsoft.games.trivia.runner;

import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Game;


public class GameRunner {

    private static boolean hasWon;

    public static void main(String[] args) {
        Game aGame = new Game();

        aGame.addPlayer("Chet");
        aGame.addPlayer("Pat");
        aGame.addPlayer("Sue");


        Random rand = new Random();
        if (args.length > 0) {
            rand = new Random(Long.parseLong(args[0]));
        }

        do {

            aGame.roll(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7) {
                hasWon = aGame.wrongAnswer();
            } else {
                //TODO: Extract winning logic from answering questions
                hasWon = aGame.wasCorrectlyAnswered();
            }

        } while (!hasWon);

    }
}
