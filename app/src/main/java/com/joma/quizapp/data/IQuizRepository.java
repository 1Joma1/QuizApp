package com.joma.quizapp.data;

import com.joma.quizapp.model.Category;
import com.joma.quizapp.model.Question;
import com.joma.quizapp.model.TotalQuestion;

import java.util.List;
import java.util.Map;

public interface IQuizRepository {

    void getQuiz(int amount, Integer category, String difficulty, OnQuizCallBack callBack);
    void getCategory(OnCategoryCallBack callBack);
    void getTotalQuestion(OnTotalQuestionCallBack callBack);

    interface OnQuizCallBack{
        void onSuccess(List<Question> result);
        void onFailure(Exception e);
    }
    interface OnCategoryCallBack{
        void onSuccess(List<Category> categories);
        void onFailure(Exception e);
    }
    interface OnTotalQuestionCallBack{
        void onSuccess(TotalQuestion overall, Map<Integer, TotalQuestion> totalQuestions);
        void onFailure(Exception e);
    }
}
