package com.joma.quizapp.data.history;

import androidx.lifecycle.LiveData;

import com.joma.quizapp.model.QuizResult;

import java.util.List;

public class HistoryStorage {
    private HistoryDao dao;

    public HistoryStorage(HistoryDao dao) {
        this.dao = dao;
    }

    public QuizResult getQuizResult(int id){
        return dao.get(id);
    }

    public int saveQuizResult(QuizResult quizResult){
        return (int) dao.insert(quizResult);
    }

    public LiveData<List<QuizResult>> getAll(){
        return dao.getAll();
    }

}
