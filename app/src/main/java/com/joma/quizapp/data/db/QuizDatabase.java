package com.joma.quizapp.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.joma.quizapp.data.history.HistoryDao;
import com.joma.quizapp.model.QuizResult;

@Database(entities = {QuizResult.class}, version = 2, exportSchema = false)
abstract public class QuizDatabase extends RoomDatabase {
    public abstract HistoryDao getHistoryDao();
}
