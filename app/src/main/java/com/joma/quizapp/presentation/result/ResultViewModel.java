package com.joma.quizapp.presentation.result;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.joma.quizapp.App;
import com.joma.quizapp.data.history.HistoryStorage;
import com.joma.quizapp.model.QuizResult;

public class ResultViewModel extends ViewModel {

    private HistoryStorage historyStorage = App.historyStorage;
    MutableLiveData<QuizResult> quizResult = new MutableLiveData<>();

    void init(int id){
        quizResult.setValue(historyStorage.getQuizResult(id));
    }
}
