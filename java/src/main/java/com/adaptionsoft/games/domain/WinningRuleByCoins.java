package com.adaptionsoft.games.domain;

public class WinningRuleByCoins implements WinningRule {

    private final int COINS_TO_WIN = 6;

    public boolean hasWon(Player player) {
        return player.getCoins() == COINS_TO_WIN;
    }
}
