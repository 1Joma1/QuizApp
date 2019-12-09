package com.joma.quizapp.presentation.quiz;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joma.quizapp.App;
import com.joma.quizapp.R;
import com.joma.quizapp.model.EType;
import com.joma.quizapp.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder> {

    private ArrayList<Question> mQuestions = new ArrayList<>();
    private QuizAdapter.Listener mListener;
    private int language = 0;

    public QuizAdapter(QuizAdapter.Listener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quiz, parent, false), mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mQuestions.get(position));
    }

    @Override
    public int getItemCount() {
        return mQuestions.size();
    }

    public void setQuestions(List<Question> qestions, int language) {
        this.language = language;
        mQuestions.clear();
        mQuestions.addAll(qestions);
        notifyDataSetChanged();
    }

    List<Question> getQuestions() {
        return mQuestions;
    }

    public interface Listener {
        void onAnswerClick(int questionPosition, int answerPosition);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private Listener mListener;
        private TextView questionText;
        private Button button;
        private Button button1;
        private Button button2;
        private Button button3;
        private Button buttonYes;
        private Button buttonNo;
        private View multiLayout;
        private View booleanLayout;

        ViewHolder(@NonNull View itemView, Listener listener) {
            super(itemView);
            mListener = listener;
            questionText = itemView.findViewById(R.id.item_question);
            button = itemView.findViewById(R.id.item_answer);
            button1 = itemView.findViewById(R.id.item_answer_1);
            button2 = itemView.findViewById(R.id.item_answer_2);
            button3 = itemView.findViewById(R.id.item_answer_3);
            buttonYes = itemView.findViewById(R.id.item_answer_yes);
            buttonNo = itemView.findViewById(R.id.item_answer_no);
            multiLayout = itemView.findViewById(R.id.item_multiple_answer_layout);
            booleanLayout = itemView.findViewById(R.id.item_boolean_answer_layout);
        }

        void bind(Question question) {
            if (language == 1) {
                App.translator.translate(question.getQuestion()).addOnSuccessListener(s -> questionText.setText(s));
            } else {
                questionText.setText(question.getQuestion());
            }
            if (question.getType() == EType.MULTIPLE) {
                multiLayout.setVisibility(View.VISIBLE);
                booleanLayout.setVisibility(View.GONE);
                resetButtonColor(button);
                resetButtonColor(button1);
                resetButtonColor(button2);
                resetButtonColor(button3);
                selectedButton(question);
                translateButton(button, question.getAnswers().get(0));
                translateButton(button1, question.getAnswers().get(1));
                translateButton(button2, question.getAnswers().get(2));
                translateButton(button3, question.getAnswers().get(3));
                button.setOnClickListener(view -> changeButtonColorOnClick(question, 0, button));
                button1.setOnClickListener(view -> changeButtonColorOnClick(question, 1, button1));
                button2.setOnClickListener(view -> changeButtonColorOnClick(question, 2, button2));
                button3.setOnClickListener(view -> changeButtonColorOnClick(question, 3, button3));
            } else {
                booleanLayout.setVisibility(View.VISIBLE);
                multiLayout.setVisibility(View.GONE);
                resetButtonColor(buttonYes);
                resetButtonColor(buttonNo);
                selectedButton(question);
                translateButton(buttonYes, question.getAnswers().get(0));
                translateButton(buttonNo, question.getAnswers().get(1));
                buttonYes.setOnClickListener(view -> changeButtonColorOnClick(question, 0, buttonYes));
                buttonNo.setOnClickListener(view -> changeButtonColorOnClick(question, 1, buttonNo));
            }
        }

        private void translateButton(Button btn, String text) {
            if (language==1) {
                App.translator.translate(text).addOnSuccessListener(s -> btn.setText(s));
            } else {
                btn.setText(text);
            }
        }

        private void changeButtonColorOnClick(Question question, int answerPos, Button btn) {
            mListener.onAnswerClick(getAdapterPosition(), answerPos);
            if (question.getAnswers().get(question.getSelectedAnswerPosition()).equals(question.getCorrectAnswer())) {
                btn.setBackground(btn.getContext().getResources().getDrawable(R.drawable.round_blue_button));
                btn.setTextColor(btn.getResources().getColor(R.color.white));
            } else {
                btn.setBackground(btn.getContext().getResources().getDrawable(R.drawable.round_red_button));
                btn.setTextColor(btn.getResources().getColor(R.color.white));
            }
        }

        private void resetButtonColor(Button btn) {
            btn.setBackground(button.getContext().getResources().getDrawable(R.drawable.round_outline_blue_button));
            btn.setTextColor(button.getContext().getResources().getColor(R.color.blue));
            btn.setEnabled(true);
        }

        private void selectedButton(Question question) {
            if (question.getSelectedAnswerPosition() != null) {
                if (question.getSelectedAnswerPosition() >= 0) {
                    if (question.getType() == EType.BOOLEAN) {
                        switch (question.getSelectedAnswerPosition()) {
                            case 0:
                                if (question.getAnswers().get(question.getSelectedAnswerPosition()).equals(question.getCorrectAnswer())) {
                                    buttonYes.setBackground(buttonYes.getContext().getResources().getDrawable(R.drawable.round_blue_button));
                                    buttonYes.setTextColor(buttonYes.getResources().getColor(R.color.white));
                                } else {
                                    buttonYes.setBackground(buttonYes.getContext().getResources().getDrawable(R.drawable.round_red_button));
                                    buttonYes.setTextColor(buttonYes.getResources().getColor(R.color.white));
                                }
                                break;
                            case 1:
                                if (question.getAnswers().get(question.getSelectedAnswerPosition()).equals(question.getCorrectAnswer())) {
                                    buttonNo.setBackground(buttonNo.getContext().getResources().getDrawable(R.drawable.round_blue_button));
                                    buttonNo.setTextColor(buttonNo.getResources().getColor(R.color.white));
                                } else {
                                    buttonNo.setBackground(buttonNo.getContext().getResources().getDrawable(R.drawable.round_red_button));
                                    buttonNo.setTextColor(buttonNo.getResources().getColor(R.color.white));
                                }
                                break;
                        }
                    } else {
                        switch (question.getSelectedAnswerPosition()) {
                            case 0:
                                if (question.getAnswers().get(question.getSelectedAnswerPosition()).equals(question.getCorrectAnswer())) {
                                    button.setBackground(button.getContext().getResources().getDrawable(R.drawable.round_blue_button));
                                    button.setTextColor(button.getResources().getColor(R.color.white));
                                } else {
                                    button.setBackground(button.getContext().getResources().getDrawable(R.drawable.round_red_button));
                                    button.setTextColor(button.getResources().getColor(R.color.white));
                                }
                                break;
                            case 1:
                                if (question.getAnswers().get(question.getSelectedAnswerPosition()).equals(question.getCorrectAnswer())) {
                                    button1.setBackground(button1.getContext().getResources().getDrawable(R.drawable.round_blue_button));
                                    button1.setTextColor(button1.getResources().getColor(R.color.white));
                                } else {
                                    button1.setBackground(button1.getContext().getResources().getDrawable(R.drawable.round_red_button));
                                    button1.setTextColor(button1.getResources().getColor(R.color.white));
                                }
                                break;
                            case 2:
                                if (question.getAnswers().get(question.getSelectedAnswerPosition()).equals(question.getCorrectAnswer())) {
                                    button2.setBackground(button2.getContext().getResources().getDrawable(R.drawable.round_blue_button));
                                    button2.setTextColor(button2.getResources().getColor(R.color.white));
                                } else {
                                    button2.setBackground(button2.getContext().getResources().getDrawable(R.drawable.round_red_button));
                                    button2.setTextColor(button2.getResources().getColor(R.color.white));
                                }
                                break;
                            case 3:
                                if (question.getAnswers().get(question.getSelectedAnswerPosition()).equals(question.getCorrectAnswer())) {
                                    button3.setBackground(button3.getContext().getResources().getDrawable(R.drawable.round_blue_button));
                                    button3.setTextColor(button3.getResources().getColor(R.color.white));
                                } else {
                                    button3.setBackground(button3.getContext().getResources().getDrawable(R.drawable.round_red_button));
                                    button3.setTextColor(button3.getResources().getColor(R.color.white));
                                }
                                break;
                        }
                    }
                }
                button.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                buttonYes.setEnabled(false);
                buttonNo.setEnabled(false);
            }
        }
    }
}
