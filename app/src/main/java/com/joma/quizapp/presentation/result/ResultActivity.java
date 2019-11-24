package com.joma.quizapp.presentation.result;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.joma.quizapp.R;

public class ResultActivity extends AppCompatActivity {

    private ResultViewModel resultViewModel;

    public static void start(Context context) {
        Intent intent = new Intent(context, ResultActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        resultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);

    }

}
