package com.joma.quizapp.data.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.joma.quizapp.model.QuizResult;
import com.joma.quizapp.model.ShortQuizResult;

import java.util.ArrayList;
import java.util.List;

public class HistoryStorage {
    private HistoryDao dao;

    public HistoryStorage(HistoryDao dao) {
        this.dao = dao;
    }

    public QuizResult getQuizResult(int id){
        return dao.get(id);
    }

    public void saveQuizResult(QuizResult quizResult){
        dao.insert(quizResult);
    }

    public LiveData<List<QuizResult>> getAll(){
        return dao.getAll();
    }

    public LiveData<List<ShortQuizResult>> getAllShort(){
        return Transformations.map(getAll(), quizResult -> {
            ArrayList<ShortQuizResult> shortQuizResults = new ArrayList<>();
            for (QuizResult result:quizResult){
                shortQuizResults.add(new ShortQuizResult(
                        result.getId(),
                        result.getQuestions().size(),
                        result.getCorrectAnswers(),
                        result.getCreatedAt()));
            }
            return shortQuizResults;
        });
    }
}
