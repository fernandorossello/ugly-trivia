package com.adaptionsoft.games.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BoardTest {

    Board board;
    Player testPlayer = new Player("TestPlayer");

    @BeforeEach
    void setup() {
        board = new Board();
    }

    @Test
    void testPlayerNotInPenaltyBox() {
        Assertions.assertFalse(board.isInPenaltyBox(testPlayer));
    }

    @Test
    void testPlayerInPenaltyBox() {
        board.moveToPenalizeBox(testPlayer);

        Assertions.assertTrue(board.isInPenaltyBox(testPlayer));
    }

    @Test
    void testPlayerNotInPenaltyBoxAfterBeingRemoved() {
        board.moveToPenalizeBox(testPlayer);

        Assertions.assertTrue(board.isInPenaltyBox(testPlayer));

        board.removeFromPenalizeBox(testPlayer);

        Assertions.assertFalse(board.isInPenaltyBox(testPlayer));
    }

    @Test
    void addPlayerPutsPlayerInTheStartingPosition() {
        board.addPlayer(testPlayer);

        Assertions.assertEquals(0, board.getPlayerPosition(testPlayer));
    }
}
