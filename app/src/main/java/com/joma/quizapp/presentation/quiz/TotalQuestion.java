package com.joma.quizapp.presentation.quiz;

import com.google.gson.annotations.SerializedName;

public class TotalQuestion {
    @SerializedName("total_num_of_questions")
    private int totalNumOfQuestions;
    @SerializedName("total_num_of_pending_questions")
    private int totalNumOfPendingQuestions;
    @SerializedName("total_num_of_verified_questions")
    private int totalNumOfVerifiedQuestions;
    @SerializedName("total_num_of_rejected_questions")
    private int totalNumOfRejectedQuestions;

    public TotalQuestion(int totalNumOfQuestions, int totalNumOfPendingQuestions, int totalNumOfVerifiedQuestions, int totalNumOfRejectedQuestions) {
        this.totalNumOfQuestions = totalNumOfQuestions;
        this.totalNumOfPendingQuestions = totalNumOfPendingQuestions;
        this.totalNumOfVerifiedQuestions = totalNumOfVerifiedQuestions;
        this.totalNumOfRejectedQuestions = totalNumOfRejectedQuestions;
    }

    public int getTotalNumOfQuestions() {
        return totalNumOfQuestions;
    }

    public void setTotalNumOfQuestions(int totalNumOfQuestions) {
        this.totalNumOfQuestions = totalNumOfQuestions;
    }

    public int getTotalNumOfPendingQuestions() {
        return totalNumOfPendingQuestions;
    }

    public void setTotalNumOfPendingQuestions(int totalNumOfPendingQuestions) {
        this.totalNumOfPendingQuestions = totalNumOfPendingQuestions;
    }

    public int getTotalNumOfVerifiedQuestions() {
        return totalNumOfVerifiedQuestions;
    }

    public void setTotalNumOfVerifiedQuestions(int totalNumOfVerifiedQuestions) {
        this.totalNumOfVerifiedQuestions = totalNumOfVerifiedQuestions;
    }

    public int getTotalNumOfRejectedQuestions() {
        return totalNumOfRejectedQuestions;
    }

    public void setTotalNumOfRejectedQuestions(int totalNumOfRejectedQuestions) {
        this.totalNumOfRejectedQuestions = totalNumOfRejectedQuestions;
    }
}
