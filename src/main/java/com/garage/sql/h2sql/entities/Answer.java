package com.garage.sql.h2sql.entities;

public class Answer {
    private long id;
    private String answer;

    public Answer(long id, String answer) {
        this.id = id;
        this.answer = answer;
    }

    public long getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return String.format("The answer to your question is %s", this.answer);
    }
}
