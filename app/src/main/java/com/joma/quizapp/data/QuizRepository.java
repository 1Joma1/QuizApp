package com.joma.quizapp.data;

import android.util.Log;

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

    private TriviaClient client = retrofit.create(TriviaClient.class);

    @Override
    public void getQuiz(OnQuizCallBack callBack, int amount, Integer category, String difficulty) {
        Call<QuestionsResponse> call = client.getQuestions(amount, category, difficulty);
        call.enqueue(new Callback<QuestionsResponse>() {
            @Override
            public void onResponse(Call<QuestionsResponse> call, Response<QuestionsResponse> response) {
                if (response.isSuccessful()){
                    if (response.body()!=null){
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
        //TODO callBack.onSuccess();
    }

    private interface TriviaClient {
        @GET("api.php")
        Call<QuestionsResponse> getQuestions(@Query("amount") int amount,
                                             @Query("category") Integer category,
                                             @Query("difficulty") String difficulty);
    }
}
