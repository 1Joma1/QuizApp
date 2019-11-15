package com.joma.quizapp.presentation.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.joma.quizapp.R;
import com.joma.quizapp.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private QuizViewModel quizViewModel;
    private RecyclerView recyclerView;
    private List<Question> questionList = new ArrayList<>();
    private List<String> incorrectAns = new ArrayList<>();
    private static String EXTRA_AMOUNT = "amount";
    private static String EXTRA_CATEGORY = "category";
    private static String EXTRA_DIFFICULTY = "difficulty";

    public static void start(Context context, int amount, String category, String difficulty){
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

        incorrectAns.add("Ethiopian Coyote");
        incorrectAns.add("Amharic Fox");
        incorrectAns.add("Canis Simiensis");
        questionList.add(new Question("Animals", "multiple", "hard", "What was the name of the Ethiopian Wolf before they knew it was related to wolves?", "Simien Jackel", incorrectAns));

        questionList.add(new Question("Sports", "multiple", "medium", "Which Formula One driver was nicknamed &#039;The Professor&#039;?", "Alain Prost", incorrectAns));

        questionList.add(new Question("Vehicles", "boolean", "easy", "The full English name of the car manufacturer BMW is Bavarian Motor Works", "True", incorrectAns));

        recyclerView = findViewById(R.id.quiz_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new QuizAdapter(questionList));
        recyclerView.scrollToPosition(1);
    }
}
