package com.joma.quizapp.presentation.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.joma.quizapp.App;
import com.joma.quizapp.model.ShortQuizResult;

import java.util.List;

public class HistoryViewModel extends ViewModel {

    LiveData<List<ShortQuizResult>> history = App.historyStorage.getAllShort();

}
