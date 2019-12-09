package com.joma.quizapp.presentation.history;

import android.os.Bundle;

import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.joma.quizapp.R;
import com.joma.quizapp.core.CoreFragment;
import com.joma.quizapp.model.QuizResult;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends CoreFragment {

//    private HistoryViewModel mViewModel;
//    private HistoryAdapter adapter;
//    private RecyclerView recyclerView;
//    private List<QuizResult> quizResultList = new ArrayList<>();

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_history;
    }

    @Override
    protected void initView(View view) {
//        adapter = new HistoryAdapter();
//        recyclerView = view.findViewById(R.id.history_recycler);
//        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
//        recyclerView.setAdapter(adapter);
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
//        adapter.setHistory(mViewModel.init());
//    }

}
