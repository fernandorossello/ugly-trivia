package com.adaptionsoft.games.domain;

import lombok.Builder;

@Builder
public class InvalidAnswerQuestion implements Question {
    private String question;
    private int invalidAnswer;

    public boolean correctlyAnswered(int answer) {
        return answer != invalidAnswer;
    }

}
