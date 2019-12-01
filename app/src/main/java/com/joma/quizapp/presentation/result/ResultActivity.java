package com.joma.quizapp.presentation.result;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.joma.quizapp.R;

public class ResultActivity extends AppCompatActivity {

    private ResultViewModel resultViewModel;
    private Button finishButton;
    private TextView category;
    private TextView difficulty;
    private TextView correctAnswer;
    private TextView percent;
    private int id;
    private static String EXTRA_ID = "id";

    public static void start(Context context, int id) {
        Intent intent = new Intent(context, ResultActivity.class);
        intent.putExtra(EXTRA_ID, id);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);
        initView();

        resultViewModel.init(id);

        resultViewModel.quizResult.observe(this, quizResult -> {
            correctAnswer.setText(quizResult.getCorrectAnswers()+"/"+quizResult.getQuestions().size());
            percent.setText(quizResult.getCorrectAnswers()*100/quizResult.getQuestions().size()+"%");
        });
        finishButton.setOnClickListener(view -> finish());
    }

    private void initView(){
        id = getIntent().getIntExtra(EXTRA_ID, 0);
        finishButton = findViewById(R.id.result_finish_button);
        category = findViewById(R.id.result_category);
        difficulty = findViewById(R.id.result_difficulty);
        correctAnswer = findViewById(R.id.result_correct_answer);
        percent = findViewById(R.id.result_result_percent);
    }
}
