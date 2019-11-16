package com.joma.quizapp.presentation.quiz;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joma.quizapp.R;
import com.joma.quizapp.model.Question;
import com.joma.quizapp.presentation.history.HistoryAdapter;

import java.util.List;
import java.util.zip.Inflater;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder> {

    private List<Question> questions;

    public QuizAdapter(List<Question> questions) {
        this.questions = questions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quiz, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(questions.get(position));
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView questionText;
        private Button button;
        private Button button1;
        private Button button2;
        private Button button3;
        private Button buttonYes;
        private Button buttonNo;
        private ImageView buttonYesImg;
        private ImageView buttonNoImg;
        private View multiLayout;
        private View booleanLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            questionText = itemView.findViewById(R.id.item_question);
            button = itemView.findViewById(R.id.item_answer);
            button1 = itemView.findViewById(R.id.item_answer_1);
            button2 = itemView.findViewById(R.id.item_answer_2);
            button3 = itemView.findViewById(R.id.item_answer_3);
            buttonYes = itemView.findViewById(R.id.item_answer_yes);
            buttonNo = itemView.findViewById(R.id.item_answer_no);
            buttonYesImg = itemView.findViewById(R.id.item_answer_yes_img);
            buttonNoImg = itemView.findViewById(R.id.item_answer_no_img);
            multiLayout = itemView.findViewById(R.id.item_multiple_answer_layout);
            booleanLayout = itemView.findViewById(R.id.item_boolean_answer_layout);
        }

        public void bind(Question question){
            questionText.setText(question.getQuestion());
            if (question.getType().equals("multiple")){
                multiLayout.setVisibility(View.VISIBLE);
                booleanLayout.setVisibility(View.GONE);
                button.setText(question.getCorrectAnswer());
                button1.setText(question.getIncorrectAnswers().get(0));
                button2.setText(question.getIncorrectAnswers().get(1));
                button3.setText(question.getIncorrectAnswers().get(2));
            }
            if (question.getType().equals("boolean")){
                booleanLayout.setVisibility(View.VISIBLE);
                multiLayout.setVisibility(View.GONE);
            }
        }
    }
}
