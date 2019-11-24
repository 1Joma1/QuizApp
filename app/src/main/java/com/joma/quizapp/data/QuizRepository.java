package com.joma.quizapp.data;

import com.joma.quizapp.data.response.CategoriesResponse;
import com.joma.quizapp.data.response.QuestionsResponse;
import com.joma.quizapp.data.response.TotalQuestionResponse;
import com.joma.quizapp.model.Question;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class QuizRepository implements IQuizRepository {

    private static final String BASE_URL = "https://opentdb.com";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://opentdb.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private Question shuffleAnswers(Question question) {
        ArrayList<String> answers = new ArrayList<>();
        answers.add(question.getCorrectAnswer());
        answers.addAll(question.getIncorrectAnswers());

        Collections.shuffle(answers);
        question.setAnswers(answers);
        return question;
    }

    @Override
    public void getQuiz(int amount, Integer category, String difficulty, OnQuizCallBack callBack) {
        Call<QuestionsResponse> call = retrofit.create(TriviaClient.class).getQuestions(amount, category, difficulty);
        call.enqueue(new Callback<QuestionsResponse>() {
            @Override
            public void onResponse(Call<QuestionsResponse> call, Response<QuestionsResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        for (int i = 0; i < response.body().getResults().size(); i++) {
                            Question question = response.body().getResults().get(i);
                            response.body().getResults().set(i, shuffleAnswers(question));
                        }
                        callBack.onSuccess(response.body().getResults());
                    } else {
                        callBack.onFailure(new Exception("Remote data error"));
                    }
                } else {
                    callBack.onFailure(new Exception("Remote data error"));
                }
            }

            @Override
            public void onFailure(Call<QuestionsResponse> call, Throwable t) {

            }
        });
        callBack.onFailure(new Exception("Remote data source not initialized"));
    }

    @Override
    public void getTotalQuestion(OnTotalQuestionCallBack callBack) {
        Call<TotalQuestionResponse> call = retrofit.create(TriviaTotalQuestions.class).getTotalQuestion();
        call.enqueue(new Callback<TotalQuestionResponse>() {
            @Override
            public void onResponse(Call<TotalQuestionResponse> call, Response<TotalQuestionResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        callBack.onSuccess(response.body().getOverall(), response.body().getCategories());
                    } else {
                        callBack.onFailure(new Exception("Remote data error"));
                    }
                } else {
                    callBack.onFailure(new Exception("Remote data error"));
                }
            }

            @Override
            public void onFailure(Call<TotalQuestionResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void getCategory(OnCategoryCallBack callBack) {
        Call<CategoriesResponse> call = retrofit.create(TriviaCategories.class).getCategories();
        call.enqueue(new Callback<CategoriesResponse>() {
            @Override
            public void onResponse(Call<CategoriesResponse> call, Response<CategoriesResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        callBack.onSuccess(response.body().getTriviaCategories());
                    } else {
                        callBack.onFailure(new Exception("Remote data error"));
                    }
                } else {
                    callBack.onFailure(new Exception("Remote data error"));
                }
            }

            @Override
            public void onFailure(Call<CategoriesResponse> call, Throwable t) {

            }
        });
    }


    private interface TriviaClient {
        @GET("api.php")
        Call<QuestionsResponse> getQuestions(@Query("amount") int amount,
                                             @Query("category") Integer category,
                                             @Query("difficulty") String difficulty);
    }

    private interface TriviaCategories {
        @GET("api_category.php")
        Call<CategoriesResponse> getCategories();
    }

    private interface TriviaTotalQuestions {
        @GET("api_count_global.php")
        Call<TotalQuestionResponse> getTotalQuestion();
    }
}
