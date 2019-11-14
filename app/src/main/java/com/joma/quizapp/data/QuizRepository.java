package com.joma.quizapp.data;

public class QuizRepository implements IQuizRepository {

    private static final String BASE_URL = "https://opentdb.com";

    @Override
    public void getQuiz(OnQuizCallBack callBack) {
        callBack.onFailure(new Exception("Remote data source not initialized"));
        //TODO callBack.onSuccess();
    }
}
