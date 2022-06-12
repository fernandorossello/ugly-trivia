package com.adaptionsoft.games.domain;

public class WinningRuleByCoins implements WinningRule {

    private int coinsToWin;

    public WinningRuleByCoins(int coinsToWin) {
        this.coinsToWin = coinsToWin;
    }

    public boolean hasWon(Player player) {
        return player.getCoins() == coinsToWin;
    }
}
