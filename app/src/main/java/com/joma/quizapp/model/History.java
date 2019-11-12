package com.joma.quizapp.model;

public class History {
    private String category;
    private String difficulty;
    private int questions;
    private int correctAnswers;
    private long time;

    public History() {
    }

    public History(String category, String difficulty, int questions, int correctAnswers, long time) {
        this.category = category;
        this.difficulty = difficulty;
        this.questions = questions;
        this.correctAnswers = correctAnswers;
        this.time = time;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getQuestions() {
        return questions;
    }

    public void setQuestions(int questions) {
        this.questions = questions;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
