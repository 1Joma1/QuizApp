package com.joma.quizapp.data;

import com.joma.quizapp.model.Category;
import com.joma.quizapp.model.Question;
import com.joma.quizapp.presentation.quiz.TotalQuestion;

import java.util.List;
import java.util.Map;

public interface IQuizRepository {

    void getQuiz(OnQuizCallBack callBack, int amount, Integer category, String difficulty);
    void getCategory(OnCategoryCallBack callBack);
    void getTotalQuestion(OnTotalQuestionCallBack callBack);

    interface OnQuizCallBack{
        void onSuccess(List<Question> questions);
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
