
package com.adaptionsoft.games.trivia.runner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.adaptionsoft.games.domain.WinningRule;
import com.adaptionsoft.games.domain.WinningRuleByCoins;
import com.adaptionsoft.games.uglytrivia.Game;


public class GameRunner {

    public static final int COINS_TO_WIN = 6;
    private static boolean hasWon;

    public static void main(String[] args) {
        Game aGame = new Game();

        aGame.addPlayer("Chet");
        aGame.addPlayer("Pat");
        aGame.addPlayer("Sue");

        aGame.addWinningRule(new WinningRuleByCoins(COINS_TO_WIN));

        Random rand = new Random();
        if (args.length > 0) {
            rand = new Random(Long.parseLong(args[0]));
        }

        do {
            aGame.playTurn(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7) {
                aGame.wrongAnswer();
            } else {
                aGame.wasCorrectlyAnswered();
            }

            hasWon = aGame.hasCurrentPlayerWon();

            aGame.giveNextPlayerTurn();

        } while (!hasWon);
    }

}
