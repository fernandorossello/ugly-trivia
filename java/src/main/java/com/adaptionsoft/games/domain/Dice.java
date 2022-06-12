package com.adaptionsoft.games.domain;

import lombok.Builder;

import java.util.Random;

@Builder
public class Dice implements Randomizer {
    Random random;
    int sides;

    @Override
    public int roll() {
        int rolledValue = random.nextInt(sides - 1) + 1;
        System.out.println("They have rolled a " + rolledValue);
        return rolledValue;
    }
}
