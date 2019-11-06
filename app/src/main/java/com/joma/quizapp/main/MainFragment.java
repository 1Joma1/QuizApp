package com.joma.quizapp.main;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.joma.quizapp.R;
import com.joma.quizapp.core.CoreFragment;

import org.angmarch.views.NiceSpinner;

public class MainFragment extends CoreFragment {

    private MainViewModel mViewModel;
    private SeekBar seekBar;
    private TextView amountText;
    private NiceSpinner categorySpinner, difficultySpinner;
    private Button startButton;

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
    }

    private void seekBarListener(){
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (i == 0){
                    seekBar.setProgress(1);
                }
                amountText.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
