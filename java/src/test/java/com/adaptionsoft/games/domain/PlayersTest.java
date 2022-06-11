package com.adaptionsoft.games.domain;

import com.adaptionsoft.games.domain.Players;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PlayersTest {
    Players players;

    @BeforeEach
    void setup() {
        players = new Players();
    }

    @Test
    void testHowManyWhenEmpty() {
        Assertions.assertEquals(0, players.howMany());
    }

    @Test
    void testHowManyWhenAddingAPlayer() {
        players.addPlayer("Test player");

        Assertions.assertEquals(1, players.howMany());
    }

    @Test
    void testGiveNextPlayerTurnWhenNoPlayerShouldThrowException() {
        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> players.giveNextPlayerTurn());
        Assertions.assertEquals("No players in the game", exception.getMessage());
    }

    @Test
    void testGetCurrentPlayerWhenHavingTwoPlayersAndNoTurnsHavePassed() {
        players.addPlayer("Test player");
        players.addPlayer("Test player2");

        Assertions.assertEquals("Test player", players.getCurrentPlayer().getName());
    }

    @Test
    void testGiveNextPlayerTurnWhenHavingTwoPlayers() {
        players.addPlayer("Test player");
        players.addPlayer("Test player2");

        players.giveNextPlayerTurn();

        Assertions.assertEquals("Test player2", players.getCurrentPlayer().getName());
    }

    @Test
    void testGiveNextPlayerTurnWhenHavingTwoPlayersAnd3Turns() {
        players.addPlayer("Test player");
        players.addPlayer("Test player2");

        players.giveNextPlayerTurn();
        players.giveNextPlayerTurn();

        Assertions.assertEquals("Test player", players.getCurrentPlayer().getName());
    }

    @Test
    void testGetCurrentPlayerWhenNoPlayerShouldThrowException() {
        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> players.getCurrentPlayer());
        Assertions.assertEquals("No players in the game", exception.getMessage());
    }

}
