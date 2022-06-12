package com.adaptionsoft.games.domain;

import java.util.HashSet;
import java.util.Set;

public class Board {

    private Set<Player> penalizedPlayers;

    public Board() {
        penalizedPlayers = new HashSet<>();
    }

    public void moveToPenalizeBox(Player player) {
        penalizedPlayers.add(player);
        System.out.printf("%s was sent to the penalty box%n", player.getName());
    }

    public boolean isInPenaltyBox(Player player) {
        return penalizedPlayers.contains(player);
    }

    public void removeFromPenalizeBox(Player player) {
        penalizedPlayers.remove(player);
    }
}
