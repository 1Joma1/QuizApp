package com.joma.quizapp;

import android.app.Application;

import androidx.room.Room;

import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslator;
import com.joma.quizapp.core.EnglishToRussianFirebaseTranslator;
import com.joma.quizapp.data.IQuizRepository;
import com.joma.quizapp.data.QuizRepository;
import com.joma.quizapp.data.db.QuizDatabase;
import com.joma.quizapp.data.history.HistoryStorage;

public class App extends Application {

    public static IQuizRepository quizRepository;
    public static QuizDatabase quizDatabase;
    public static HistoryStorage historyStorage;
    public static FirebaseTranslator translator;

    @Override
    public void onCreate() {
        super.onCreate();
        quizRepository = new QuizRepository();
        quizDatabase = Room.databaseBuilder(this, QuizDatabase.class, "quiz_db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        historyStorage = new HistoryStorage(quizDatabase.getHistoryDao());
        translator = EnglishToRussianFirebaseTranslator.init();
    }
}
