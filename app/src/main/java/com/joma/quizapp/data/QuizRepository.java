package com.joma.quizapp.data;

public class QuizRepository implements IQuizRepository{

    @Override
    public void getQuiz(OnQuizCallBack callBack) {
        callBack.onFailure(new Exception("Remote data source not initialized"));
        //TODO callBack.onSuccess();
    }
}
