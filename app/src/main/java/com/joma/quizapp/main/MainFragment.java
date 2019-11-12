package com.joma.quizapp.main;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.joma.quizapp.core.SimpleOnSeekBarChangeListener;
import com.joma.quizapp.R;
import com.joma.quizapp.core.CoreFragment;
import com.joma.quizapp.quiz.QuizActivity;

import org.angmarch.views.NiceSpinner;

public class MainFragment extends CoreFragment {

    private MainViewModel mViewModel;
    private SeekBar seekBar;
    private TextView amountText;
    private NiceSpinner categorySpinner, difficultySpinner;
    private Button startButton;

    private int questionAmount;
    private String category;
    private String difficulty;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment;
    }

    @Override
    protected void initView(View view) {
        seekBar = view.findViewById(R.id.seek_bar_question_amount);
        seekBar.setMax(50);
        categorySpinner = view.findViewById(R.id.nice_spinner_category);
        difficultySpinner = view.findViewById(R.id.nice_spinner_difficulty);
        amountText = view.findViewById(R.id.tv_question_amount);
        startButton = view.findViewById(R.id.start_button);
        seekBarListener();
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionAmount = seekBar.getProgress();
                category = categorySpinner.getSelectedItem().toString();
                difficulty = difficultySpinner.getSelectedItem().toString();
                Intent intent = new Intent(getContext(), QuizActivity.class);
                intent.putExtra("questionAmount", questionAmount);
                intent.putExtra("category", category);
                intent.putExtra("difficulty", difficulty);
                startActivity(intent);
            }
        });
    }

    private void seekBarListener() {
        seekBar.setOnSeekBarChangeListener(new SimpleOnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (i < 6) {
                    seekBar.setProgress(5);
                }
                amountText.setText(String.valueOf(seekBar.getProgress()));
            }
        });
    }
}
