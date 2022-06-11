package com.adaptionsoft.games.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PlayerTest {
    Player player;

    @BeforeEach
    void setup() {
        player = new Player("Test player");
    }

    @Test
    void testPlayerStartsWithZeroCoins() {
        Assertions.assertEquals(0, player.getCoins());
    }

    @Test
    void testEarnCoinIncrementsPlayersCoin() {
        player.earnCoin();

        Assertions.assertEquals(1, player.getCoins());
    }
}
