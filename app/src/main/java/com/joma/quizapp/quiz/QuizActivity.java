package com.joma.quizapp.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.joma.quizapp.R;
import com.joma.quizapp.settings.SettingsViewModel;

public class QuizActivity extends AppCompatActivity {

    private QuizViewModel quizViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        quizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
    }
}
