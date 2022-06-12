package com.adaptionsoft.games.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Board {

    public static final int MAX_CELLS = 12;
    private Set<Player> penalizedPlayers;
    private Map<Player, Integer> playersPositions;

    public Board() {
        penalizedPlayers = new HashSet<>();
        playersPositions = new HashMap<>();
    }

    public void addPlayer(Player player) {
        playersPositions.put(player, 0);
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

    public int getPlayerPosition(Player player) {
        return playersPositions.get(player);
    }

    public void movePlayer(Player player, int positionsToMove) {
        int nextPosition = (playersPositions.get(player) + positionsToMove) % MAX_CELLS;
        playersPositions.put(player, nextPosition);

        System.out.println(player.getName() + "'s new location is " + nextPosition);
    }
}
