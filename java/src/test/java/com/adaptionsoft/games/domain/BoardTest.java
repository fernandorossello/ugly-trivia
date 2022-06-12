package com.adaptionsoft.games.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BoardTest {

    Board board;

    @BeforeEach
    void setup() {
        board = new Board();
    }

    @Test
    void testPlayerNotInPenaltyBox() {
        Player testPlayer = new Player("TestPlayer");

        Assertions.assertFalse(board.isInPenaltyBox(testPlayer));
    }

    @Test
    void testPlayerInPenaltyBox() {
        Player testPlayer = new Player("TestPlayer");

        board.moveToPenalizeBox(testPlayer);

        Assertions.assertTrue(board.isInPenaltyBox(testPlayer));
    }

    @Test
    void testPlayerNotInPenaltyBoxAfterBeingRemoved() {
        Player testPlayer = new Player("TestPlayer");

        board.moveToPenalizeBox(testPlayer);

        Assertions.assertTrue(board.isInPenaltyBox(testPlayer));

        board.removeFromPenalizeBox(testPlayer);

        Assertions.assertFalse(board.isInPenaltyBox(testPlayer));
    }
}
