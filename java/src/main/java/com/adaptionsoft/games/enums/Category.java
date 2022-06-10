package com.adaptionsoft.games.enums;

public enum Category {
    POP("Pop"),
    ROCK("Rock"),
    SCIENCE("Science"),
    SPORTS("Sports");

    private String name;

    Category(String categoryName) {
        name = categoryName;
    }


    public String getName() {
        return name;
    }
}
