package com.joma.quizapp.presentation.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.joma.quizapp.App;
import com.joma.quizapp.R;
import com.joma.quizapp.data.IQuizRepository;
import com.joma.quizapp.model.Category;
import com.joma.quizapp.model.Question;
import com.joma.quizapp.model.TotalQuestion;
import com.joma.quizapp.presentation.result.ResultActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QuizActivity extends AppCompatActivity implements QuizAdapter.Listener {

    private QuizViewModel quizViewModel;
    private RecyclerView recyclerView;
    private QuizAdapter adapter;
    private ImageView backImage;
    private TextView categoryText;
    private TextView progressText;
    private Button skipButton;
    private List<Question> questionList = new ArrayList<>();
    private int amount;
    private Integer category;
    private String difficulty;
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

        category = getIntent().getIntExtra(EXTRA_CATEGORY, 0) + 8;
        if (category == 8) category = null;
        difficulty = getIntent().getStringExtra(EXTRA_DIFFICULTY).toLowerCase();
        if (difficulty.equals("any")) difficulty = null;
        amount = getIntent().getIntExtra(EXTRA_AMOUNT, 5);

        quizProgress.setMax(amount);

        quizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        quizViewModel.questions.observe(this, questions -> {
            adapter.setQuestions(questions);
            loading.setVisibility(View.GONE);
        });
        quizViewModel.currentQuestionPosition.observe(this, position -> {
            progressText.setText(position + 1 + "/" + amount);
            quizProgress.setProgress(position + 1);
            recyclerView.smoothScrollToPosition(position);
            categoryText.setText(adapter.getQuestions().get(position).getCategory());
            skipButton.setVisibility(View.VISIBLE);
        });
        quizViewModel.finishEvent.observe(this, aVoid -> finish());
        quizViewModel.openResultEvent.observe(this, aVoid -> ResultActivity.start(this));
        quizViewModel.init(amount, category, difficulty);
    }

    private void initView() {

        progressText = findViewById(R.id.quiz_progress_text);
        loading = findViewById(R.id.quiz_loading_progress);
        categoryText = findViewById(R.id.quiz_category);
        quizProgress = findViewById(R.id.quiz_progress);
        backImage = findViewById(R.id.quiz_back_image);
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