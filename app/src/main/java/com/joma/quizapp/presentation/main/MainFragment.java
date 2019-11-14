package com.joma.quizapp.presentation.main;

import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.joma.quizapp.core.SimpleOnSeekBarChangeListener;
import com.joma.quizapp.R;
import com.joma.quizapp.core.CoreFragment;
import com.joma.quizapp.presentation.quiz.QuizActivity;

import org.angmarch.views.NiceSpinner;

public class MainFragment extends CoreFragment {

    private SeekBar seekBar;
    private TextView amountText;
    private NiceSpinner categorySpinner, difficultySpinner;
    private Button startButton;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
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
                QuizActivity.start(getContext(), seekBar.getProgress(), categorySpinner.getSelectedItem().toString(), difficultySpinner.getSelectedItem().toString());
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
