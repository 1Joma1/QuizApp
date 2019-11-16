package com.joma.quizapp.data;

import com.joma.quizapp.model.Question;

import java.util.List;

public interface IQuizRepository {

    void getQuiz(OnQuizCallBack callBack, int amount, Integer category, String difficulty);

    interface OnQuizCallBack{
        void onSuccess(List<Question> questions);
        void onFailure(Exception e);
    }
}
