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
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.joma.quizapp.App;
import com.joma.quizapp.R;
import com.joma.quizapp.data.IQuizRepository;
import com.joma.quizapp.model.Category;
import com.joma.quizapp.model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QuizActivity extends AppCompatActivity {

    private QuizViewModel quizViewModel;
    private RecyclerView recyclerView;
    private QuizAdapter adapter;
    private ImageView backImage;
    private TextView categoryText;
    private TextView progressText;
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
        quizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);

        loading = findViewById(R.id.quiz_loading_progress);
        categoryText = findViewById(R.id.quiz_category);
        backImage = findViewById(R.id.quiz_back_image);
        backImage.setOnClickListener(view -> finish());


        amount = getIntent().getIntExtra(EXTRA_AMOUNT, 1);

        quizProgress = findViewById(R.id.quiz_progress);
        quizProgress.setMax(amount);

        progressText = findViewById(R.id.quiz_progress_text);

        category = getIntent().getIntExtra(EXTRA_CATEGORY, 0) + 8;
        if (category == 8) category = null;

        difficulty = getIntent().getStringExtra(EXTRA_DIFFICULTY).toLowerCase();
        if (difficulty.equals("any")) difficulty = null;

        getQuestions();

        recyclerView = findViewById(R.id.quiz_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new QuizAdapter(questionList);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int recyclerViewPos = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                categoryText.setText(questionList.get(recyclerViewPos).getCategory());
                quizProgress.setProgress(recyclerViewPos+1);
                progressText.setText(recyclerViewPos+1+"/"+amount);
            }
        });
        new PagerSnapHelper().attachToRecyclerView(recyclerView);
    }

    private void getQuestions() {
        App.quizRepository.getQuiz(amount, category, difficulty, new IQuizRepository.OnQuizCallBack() {
            @Override
            public void onSuccess(List<Question> questions) {
                questionList.addAll(questions);
                categoryText.setText(questionList.get(0).getCategory());
                adapter.notifyDataSetChanged();
                progressText.setText("1/"+amount);
                loading.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("-----------", e.getMessage());
            }
        });


        App.quizRepository.getCategory(new IQuizRepository.OnCategoryCallBack() {
            @Override
            public void onSuccess(List<Category> categories) {
                Log.d("---------", categories + "");
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("-----------", e.getMessage());
            }
        });

        App.quizRepository.getTotalQuestion(new IQuizRepository.OnTotalQuestionCallBack() {
            @Override
            public void onSuccess(TotalQuestion overall, Map<Integer, TotalQuestion> totalQuestions) {
                Log.d("---------", overall + "\n---------" + totalQuestions);
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("-----------", e.getMessage());
            }
        });
    }
}