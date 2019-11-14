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
import com.joma.quizapp.model.History;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>{

    private List<History> histories;

    public HistoryAdapter(List<History> histories) {
        this.histories = histories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(histories.get(position));
    }

    @Override
    public int getItemCount() {
        return histories.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

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
            horiz_dots.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(), String.valueOf(getAdapterPosition()), Toast.LENGTH_LONG).show();
                }
            });
        }

        public void bind(History histories){
            SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy HH:mm");
            category.setText("Category: "+histories.getCategory());
            question.setText("Correct answers: "+histories.getCorrectAnswers()+"/"+histories.getQuestions());
            difficulty.setText("Difficulty: "+histories.getDifficulty());
            time.setText(df.format(new Date(histories.getTime()*1000)));
        }
    }

}
