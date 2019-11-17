package com.joma.quizapp.data.response;

import com.google.gson.annotations.SerializedName;
import com.joma.quizapp.model.Category;

import java.util.List;

public class CategoriesResponse {

    @SerializedName("trivia_categories")
    private List<Category> triviaCategories;

    public CategoriesResponse(List<Category> triviaCategories) {
        this.triviaCategories = triviaCategories;
    }

    public List<Category> getTriviaCategories() {
        return triviaCategories;
    }

    public void setTriviaCategories(List<Category> triviaCategories) {
        this.triviaCategories = triviaCategories;
    }
}
