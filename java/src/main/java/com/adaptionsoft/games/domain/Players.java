package com.adaptionsoft.games.domain;

import java.util.ArrayList;

public class Players {
    private final ArrayList<Player> players = new ArrayList<>();
    private int currentPlayerIndex = 0;

    public Players() {
    }

    public Player addPlayer(String playerName) {
        Player player = new Player(playerName);
        players.add(player);

        System.out.println(player.getName() + " was added");
        System.out.println("They are player number " + this.howMany());

        return player;
    }

    public int howMany() {
        return players.size();
    }

    public void giveNextPlayerTurn() {
        validatePlayers();

        currentPlayerIndex = ++currentPlayerIndex % this.howMany();
    }

    public Player getCurrentPlayer() {
        validatePlayers();

        return players.get(currentPlayerIndex);
    }

    private void validatePlayers() {
        if (players.isEmpty()) {
            throw new RuntimeException("No players in the game");
        }
    }
}
