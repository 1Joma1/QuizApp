package com.joma.quizapp.model;

import android.text.Html;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private String category;
    private EType type;
    private EDifficulty difficulty;
    private String question;
    @SerializedName("correct_answer")
    private String correctAnswer;
    @SerializedName("incorrect_answers")
    private List<String> incorrectAnswers;

    private List<String> answers;

    private Integer selectedAnswerPosition;

    public Question(String category, EType type, EDifficulty difficulty, String question, String correctAnswer, List<String> incorrectAnswers, List<String> answers, Integer selectedAnswerPosition) {
        this.category = category;
        this.type = type;
        this.difficulty = difficulty;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
        this.answers = answers;
        this.selectedAnswerPosition = selectedAnswerPosition;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public EType getType() {
        return type;
    }

    public void setType(EType type) {
        this.type = type;
    }

    public EDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(EDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setSelectedAnswerPosition(Integer selectedAnswerPosition) {
        this.selectedAnswerPosition = selectedAnswerPosition;
    }

    public String getQuestion() {
        return Html.fromHtml(question).toString();
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return Html.fromHtml(correctAnswer).toString();
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<String> getIncorrectAnswers() {
        return fromHtml(incorrectAnswers);
    }

    public void setIncorrectAnswers(List<String> incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public Integer getSelectedAnswerPosition() {
        return selectedAnswerPosition;
    }

    public void setSelectedAnswerPosition(int selectedAnswerPosition) {
        this.selectedAnswerPosition = selectedAnswerPosition;
    }

    private List<String> fromHtml(List<String> incorrectAnswers){
        List<String> incorrects = new ArrayList<>();
        for (String s : incorrectAnswers){
            incorrects.add(Html.fromHtml(s).toString());
        }
        return incorrects;
    }
}
