package com.adaptionsoft.games.trivia.domain;

import com.adaptionsoft.games.domain.Deck;
import com.adaptionsoft.games.enums.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DeckTest {

    Deck deck;

    @BeforeEach
    void setup() {
        deck = new Deck(50);
    }

    @Test
    void testGetRockQuestion() {
        String question = deck.getNextQuestion(Category.ROCK);

        Assertions.assertEquals("Rock Question 0", question);
    }

    @Test
    void testGetPopQuestion() {
        String question = deck.getNextQuestion(Category.POP);

        Assertions.assertEquals("Pop Question 0", question);
    }

    @Test
    void testGetScienceQuestion() {
        String question = deck.getNextQuestion(Category.SCIENCE);

        Assertions.assertEquals("Science Question 0", question);
    }

    @Test
    void testGetSportsQuestion() {
        String question = deck.getNextQuestion(Category.SPORTS);

        Assertions.assertEquals("Sports Question 0", question);
    }

    @Test
    void testGetTwoSportsQuestion() {
        deck.getNextQuestion(Category.SPORTS);
        String question = deck.getNextQuestion(Category.SPORTS);

        Assertions.assertEquals("Sports Question 1", question);
    }
}
