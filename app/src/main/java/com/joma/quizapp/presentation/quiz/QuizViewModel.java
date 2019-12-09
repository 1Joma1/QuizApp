package com.joma.quizapp.presentation.quiz;

import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.joma.quizapp.App;
import com.joma.quizapp.core.SingleLiveEvent;
import com.joma.quizapp.data.IQuizRepository;
import com.joma.quizapp.data.history.HistoryStorage;
import com.joma.quizapp.model.Question;
import com.joma.quizapp.model.QuizResult;

import java.util.Date;
import java.util.List;

public class QuizViewModel extends ViewModel {

    private IQuizRepository quizRepository = App.quizRepository;
    private HistoryStorage historyStorage = App.historyStorage;
    private List<Question> mQuestions;

    MutableLiveData<List<Question>> questions = new MutableLiveData<>();
    MutableLiveData<Integer> currentQuestionPosition = new MutableLiveData<>();

    SingleLiveEvent<Void> finishEvent = new SingleLiveEvent<>();
    SingleLiveEvent<Integer> openResultEvent = new SingleLiveEvent<>();
    SingleLiveEvent<Void> loaded = new SingleLiveEvent<>();

    void init(int amount, Integer category, String difficulty) {
        quizRepository.getQuiz(amount, category, difficulty, new IQuizRepository.OnQuizCallBack() {
            @Override
            public void onSuccess(List<Question> result) {
                mQuestions = result;
                questions.setValue(mQuestions);
                currentQuestionPosition.setValue(0);
                loaded.call();
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("Failed", e.getMessage());
            }
        });
    }

    void onAnswerClick(int questionPosition, int answerPosition) {
        if (currentQuestionPosition.getValue() == null || mQuestions == null) return;
        Question question = mQuestions.get(questionPosition);
        question.setSelectedAnswerPosition(answerPosition);
        mQuestions.set(questionPosition, question);
        questions.setValue(mQuestions);
        if (questionPosition == mQuestions.size() - 1) {
            finishQuiz();
        } else {
            new Handler().postDelayed(() -> currentQuestionPosition.setValue(questionPosition + 1), 300);
        }
    }

    private void finishQuiz() {
        QuizResult result = new QuizResult(0, mQuestions, calculateResult(), new Date(), getCategory(), getDifficulty());
        int resultId = historyStorage.saveQuizResult(result);
        finishEvent.call();
        openResultEvent.setValue(resultId);
    }

    void onSkipClick() {
        Integer currentPosition = currentQuestionPosition.getValue();
        if (currentPosition != null) {
            if (mQuestions.get(currentPosition).getSelectedAnswerPosition() == null) {
                onAnswerClick(currentPosition, -1);
            } else {
                onAnswerClick(currentPosition, mQuestions.get(currentPosition).getSelectedAnswerPosition());
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

    private int calculateResult() {
        int correctAns = 0;
        for (Question question : mQuestions) {
            if (question.getSelectedAnswerPosition() != null && question.getSelectedAnswerPosition() >= 0) {
                if (question.getAnswers().get(question.getSelectedAnswerPosition()).equals(question.getCorrectAnswer())) {
                    correctAns++;
                }
            }
        }
        return correctAns;
    }

    private String getCategory() {
        for (Question question : mQuestions) {
            if (!question.getCategory().equals(mQuestions.get(0).getCategory())) {
                return "Mixed";
            }
        }
        return mQuestions.get(0).getCategory();
    }

    private String getDifficulty() {
        for (Question question : mQuestions) {
            if (!question.getCategory().equals(mQuestions.get(0).getCategory())) {
                return "All";
            }
        }
        return mQuestions.get(0).getDifficulty().name();
    }
}
