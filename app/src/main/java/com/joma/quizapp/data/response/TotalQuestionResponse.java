package com.joma.quizapp.data.response;

import com.joma.quizapp.presentation.quiz.TotalQuestion;

import java.util.Map;

public class TotalQuestionResponse {
    private TotalQuestion overall;
    private Map<Integer, TotalQuestion> categories;

    public TotalQuestionResponse(TotalQuestion overall, Map<Integer, TotalQuestion> categories) {
        this.overall = overall;
        this.categories = categories;
    }

    public TotalQuestion getOverall() {
        return overall;
    }

    public void setOverall(TotalQuestion overall) {
        this.overall = overall;
    }

    public Map<Integer, TotalQuestion> getCategories() {
        return categories;
    }

    public void setCategories(Map<Integer, TotalQuestion> categories) {
        this.categories = categories;
    }
}
