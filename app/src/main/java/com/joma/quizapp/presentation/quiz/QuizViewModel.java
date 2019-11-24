package com.joma.quizapp.presentation.quiz;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.joma.quizapp.App;
import com.joma.quizapp.core.SingleLiveEvent;
import com.joma.quizapp.data.IQuizRepository;
import com.joma.quizapp.model.Question;

import java.util.List;

public class QuizViewModel extends ViewModel {

    private IQuizRepository quizRepository = App.quizRepository;
    private List<Question> mQuestions;

    MutableLiveData<List<Question>> questions = new MutableLiveData<>();
    MutableLiveData<Integer> currentQuestionPosition = new MutableLiveData<>();

    SingleLiveEvent<Void> finishEvent = new SingleLiveEvent<>();
    SingleLiveEvent<Integer> openResultEvent = new SingleLiveEvent<>();

    void init(int amount, Integer category, String difficulty) {
        quizRepository.getQuiz(amount, category, difficulty, new IQuizRepository.OnQuizCallBack() {
            @Override
            public void onSuccess(List<Question> result) {
                mQuestions = result;
                questions.setValue(mQuestions);
                currentQuestionPosition.setValue(0);
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("-----------", e.getMessage());
            }
        });
    }

    void onAnswerClick(int questionPosition, int answerPosition) {
        mQuestions.get(questionPosition).setSelectedAnswerPosition(answerPosition);
        Log.e("-----", questionPosition+"//"+answerPosition);
    }

    void onSkipClick() {
        Integer currentPosition = currentQuestionPosition.getValue();
        if (currentPosition != null) {
            if (currentPosition == questions.getValue().size() - 1) {
                int correctAns = 0;
                for (int i = 0; i < mQuestions.size(); i++) {
                    if (mQuestions.get(i).getAnswers().get(mQuestions.get(i).getSelectedAnswerPosition()).equals(mQuestions.get(i).getCorrectAnswer())){
                        correctAns++;
                    }
                }
                Log.e("---", "correct answers = " + correctAns);
                openResultEvent.call();
                finishEvent.call();
            } else {
                currentQuestionPosition.setValue(currentPosition + 1);
            }
        }
    }

    void onBackClick() {
        Integer currentPosition = currentQuestionPosition.getValue();
        if (currentPosition != null) {
            if (currentPosition == 0) {
                finishEvent.call();
            } else {
                currentQuestionPosition.setValue(currentPosition - 1);
            }
        }
    }
}
