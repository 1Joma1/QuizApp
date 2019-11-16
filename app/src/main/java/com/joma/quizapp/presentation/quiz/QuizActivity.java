package com.joma.quizapp.presentation.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.joma.quizapp.App;
import com.joma.quizapp.R;
import com.joma.quizapp.data.IQuizRepository;
import com.joma.quizapp.model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuizActivity extends AppCompatActivity {

    private QuizViewModel quizViewModel;
    private RecyclerView recyclerView;
    private QuizAdapter adapter;
    private List<Question> questionList = new ArrayList<>();
    private int amount;
    private Integer category;
    private String difficulty;
    private static String EXTRA_AMOUNT = "amount";
    private static String EXTRA_CATEGORY = "category";
    private static String EXTRA_DIFFICULTY = "difficulty";

    public static void start(Context context, int amount, int category, String difficulty) {
        Intent intent = new Intent(context, QuizActivity.class);
        intent.putExtra(EXTRA_AMOUNT, amount);
        intent.putExtra(EXTRA_CATEGORY, category);
        intent.putExtra(EXTRA_DIFFICULTY, difficulty);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        quizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);

        amount = getIntent().getIntExtra(EXTRA_AMOUNT, 1);

        category = getIntent().getIntExtra(EXTRA_CATEGORY, 0) + 8;
        if (category == 8) category = null;

        difficulty = getIntent().getStringExtra(EXTRA_DIFFICULTY);
        if (difficulty.equals("Any")) difficulty = null;

        getQuestions();

        recyclerView = findViewById(R.id.quiz_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new QuizAdapter(questionList);
        recyclerView.setAdapter(adapter);
    }

    private void getQuestions() {
        App.quizRepository.getQuiz(new IQuizRepository.OnQuizCallBack() {
            @Override
            public void onSuccess(List<Question> questions) {
                questionList.addAll(questions);
                adapter.notifyDataSetChanged();
                Log.e("----------", questions + "");
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("-----------", e.getMessage());
            }
        }, amount, category, difficulty.toLowerCase());
        Log.e("------------", "amount: " + amount + "category: " + category + "difficulty: " + difficulty.toLowerCase());
    }
}