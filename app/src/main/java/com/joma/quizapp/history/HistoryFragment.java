package com.joma.quizapp.history;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joma.quizapp.R;
import com.joma.quizapp.core.CoreFragment;
import com.joma.quizapp.model.History;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends CoreFragment {

    private HistoryViewModel mViewModel;
    private HistoryAdapter adapter;
    private RecyclerView recyclerView;
    private List<History> historyList = new ArrayList<>();

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.history_fragment;
    }

    @Override
    protected void initView(View view) {
        historyList.add(new History("Entertainment: Music", "Hard", 15, 8, 1573555000));
        historyList.add(new History("Entertainment: Video Games", "Medium", 12, 10, 1573555055));
        historyList.add(new History("Entertainment: Cartoon & Animations", "Easy", 6, 5, 1573554399));
        recyclerView = view.findViewById(R.id.history_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new HistoryAdapter(historyList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
        // TODO: Use the ViewModel
    }

}
