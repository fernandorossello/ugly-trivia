package com.adaptionsoft.games.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class InvalidAnswerQuestionTest {

    InvalidAnswerQuestion question;

    @BeforeEach
    void setup() {
        question = InvalidAnswerQuestion.builder().question("Test question").invalidAnswer(5).build();
    }

    @Test
    void testCorrectlyAnswered_whenCorrectAnswer_shouldReturnTrue() {
        Assertions.assertTrue(question.correctlyAnswered(6));
    }

    @Test
    void testCorrectlyAnswered_whenIncorrectAnswer_shouldReturnFalse() {
        Assertions.assertFalse(question.correctlyAnswered(5));
    }
}
