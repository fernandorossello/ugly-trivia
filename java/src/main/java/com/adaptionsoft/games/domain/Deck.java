package com.adaptionsoft.games.domain;

import com.adaptionsoft.games.enums.Category;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static com.adaptionsoft.games.enums.Category.values;

public class Deck {

    Map<Category, LinkedList<String>> categoryCards = new HashMap<>();

    public Deck(int numberOfQuestionsByCategory) {
        //TODO: Be able to configure from outside the categories
        for (Category category : values()) {
            categoryCards.put(category, new LinkedList<>());
        }

        for (int i = 0; i < numberOfQuestionsByCategory; i++) {
            for (Category category : values()) {
                categoryCards.get(category).add(createCategoryQuestion(i, category));
            }
        }
    }

    private String createCategoryQuestion(int index, Category category) {
        return String.format("%s Question %d", category.getName(), index);
    }

    public String getNextQuestion(Category category) {
        return categoryCards.get(category).removeFirst();
    }
}
