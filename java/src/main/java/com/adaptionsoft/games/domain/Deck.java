package com.adaptionsoft.games.domain;

import com.adaptionsoft.games.enums.Category;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static com.adaptionsoft.games.enums.Category.*;
import static com.adaptionsoft.games.enums.Category.ROCK;

public class Deck {

    LinkedList<String> popQuestions = new LinkedList<>();
    LinkedList<String> scienceQuestions = new LinkedList<>();
    LinkedList<String> sportsQuestions = new LinkedList<>();
    LinkedList<String> rockQuestions = new LinkedList<>();
    Map<Category,LinkedList<String>> categoryCards = new HashMap<>();

    public Deck(int numberOfQuestionsByCategory) {
        categoryCards.put(POP,new LinkedList<>());
        categoryCards.put(SCIENCE,new LinkedList<>());
        categoryCards.put(SPORTS,new LinkedList<>());
        categoryCards.put(ROCK,new LinkedList<>());

        for (int i = 0; i < numberOfQuestionsByCategory; i++) {
            categoryCards.get(POP).add(createCategoryQuestion(i, POP));
            categoryCards.get(SCIENCE).add(createCategoryQuestion(i, SCIENCE));
            categoryCards.get(SPORTS).add(createCategoryQuestion(i, SPORTS));
            categoryCards.get(ROCK).add(createCategoryQuestion(i, ROCK));

            popQuestions.addLast(createCategoryQuestion(i, POP));
            scienceQuestions.addLast(createCategoryQuestion(i, SCIENCE));
            sportsQuestions.addLast(createCategoryQuestion(i, SPORTS));
            rockQuestions.addLast(createCategoryQuestion(i, ROCK));
        }
    }

    private String createCategoryQuestion(int index, Category category){
        return String.format("%s Question %d", category.getName(), index);
    }

    public String getNextQuestion(Category category) {
        return categoryCards.get(category).removeFirst();
    }
}
