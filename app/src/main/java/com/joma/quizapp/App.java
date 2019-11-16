package com.joma.quizapp;

import android.app.Application;

import com.joma.quizapp.data.IQuizRepository;
import com.joma.quizapp.data.QuizRepository;

public class App extends Application {

    public static IQuizRepository quizRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        quizRepository = new QuizRepository();
    }
}
