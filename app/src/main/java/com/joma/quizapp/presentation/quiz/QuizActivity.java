package com.joma.quizapp.presentation.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.joma.quizapp.R;
import com.joma.quizapp.presentation.result.ResultActivity;

public class QuizActivity extends AppCompatActivity implements QuizAdapter.Listener {

    private QuizViewModel quizViewModel;
    private RecyclerView recyclerView;
    private QuizAdapter adapter;
    private TextView categoryText;
    private TextView progressText;
    private Button skipButton;
    private int amount;
    private ProgressBar loading;
    private ProgressBar quizProgress;
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

        initView();

        Integer category = getIntent().getIntExtra(EXTRA_CATEGORY, 0) + 8;
        if (category == 8) category = null;
        String difficulty = getIntent().getStringExtra(EXTRA_DIFFICULTY).toLowerCase();
        if (difficulty.equals("any")) difficulty = null;
        amount = getIntent().getIntExtra(EXTRA_AMOUNT, 5);

        quizProgress.setMax(amount);

        quizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        quizViewModel.questions.observe(this, questions -> adapter.setQuestions(questions));
        quizViewModel.currentQuestionPosition.observe(this, position -> {
            String progressString = position + 1 + "/" + amount;
            progressText.setText(progressString);
            quizProgress.setProgress(position + 1);
            recyclerView.smoothScrollToPosition(position);
            categoryText.setText(adapter.getQuestions().get(position).getCategory());
            skipButton.setVisibility(View.VISIBLE);
        });
        quizViewModel.finishEvent.observe(this, aVoid -> finish());
        quizViewModel.openResultEvent.observe(this, id -> ResultActivity.start(this, id));
        quizViewModel.init(amount, category, difficulty);
        quizViewModel.loaded.observe(this, aVoid -> loading.setVisibility(View.GONE));
    }

    private void initView() {

        progressText = findViewById(R.id.quiz_progress_text);
        loading = findViewById(R.id.quiz_loading_progress);
        categoryText = findViewById(R.id.quiz_category);
        quizProgress = findViewById(R.id.quiz_progress);
        ImageView backImage = findViewById(R.id.quiz_back_image);
        backImage.setOnClickListener(view -> {
            quizViewModel.onBackClick();
        });

        adapter = new QuizAdapter(this);
        recyclerView = findViewById(R.id.quiz_recycler);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setOnTouchListener((v, event) -> true);

        skipButton = findViewById(R.id.quiz_skip_button);
        skipButton.setOnClickListener(v -> quizViewModel.onSkipClick());
    }

    @Override
    public void onAnswerClick(int questionPosition, int answerPosition) {
        quizViewModel.onAnswerClick(questionPosition, answerPosition);
    }


    @Override
    public void onBackPressed() {
        quizViewModel.onBackClick();
    }
}