package com.joma.quizapp.presentation.history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joma.quizapp.R;
import com.joma.quizapp.model.QuizResult;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private ArrayList<QuizResult> mHistories = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mHistories.get(position));
    }

    @Override
    public int getItemCount() {
        return mHistories.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView category;
        private TextView question;
        private TextView difficulty;
        private TextView time;
        private ImageView horiz_dots;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.history_category);
            question = itemView.findViewById(R.id.history_questions);
            difficulty = itemView.findViewById(R.id.history_difficulty);
            time = itemView.findViewById(R.id.history_time);
            horiz_dots = itemView.findViewById(R.id.history_horiz_dots);
            horiz_dots.setOnClickListener(view -> Toast.makeText(itemView.getContext(), String.valueOf(getAdapterPosition()), Toast.LENGTH_LONG).show());
        }

        public void bind(QuizResult results) {
            SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy HH:mm");
            category.setText("Category: " + results.getCategory());
            question.setText("Correct answers: " + results.getCorrectAnswers() + "/" + results.getQuestions().size());
            difficulty.setText("Difficulty: " + results.getDifficulty());
            time.setText(df.format(results.getCreatedAt()));
        }
    }

    public void setHistory(List<QuizResult> results) {
        mHistories.clear();
        mHistories.addAll(results);
        notifyDataSetChanged();
    }
}
