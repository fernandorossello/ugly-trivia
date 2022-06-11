package com.adaptionsoft.games.domain;

import java.util.ArrayList;

public class Players {
    private final ArrayList<Player> players = new ArrayList<>();
    private int currentPlayerIndex = 0;

    public Players() {
    }

    public void addPlayer(String playerName) {
        players.add(new Player(playerName));

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + this.howMany());
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
