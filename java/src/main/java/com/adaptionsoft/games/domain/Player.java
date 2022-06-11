package com.adaptionsoft.games.domain;

public class Player {
    private final String name;
    private int coins;
    private int position; // TODO: Pareciera ser ajeno al player. Tablero quizá?

    public Player(String name) {
        this.name = name;
        this.coins = 0;
        this.position = 0;
    }

    public void earnCoin() {
        coins++;
        System.out.printf("%s now has %d Gold Coins.%n", name, coins);
    }

    public String getName() {
        return name;
    }

    public int getCoins() {
        return coins;
    }
}
