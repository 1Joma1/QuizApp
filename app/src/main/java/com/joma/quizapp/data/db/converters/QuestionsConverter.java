package com.joma.quizapp.data.db.converters;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joma.quizapp.model.Question;

import java.lang.reflect.Type;
import java.util.List;

public class QuestionsConverter {

    @TypeConverter
    public static List<Question> fromRow(String row) {
        if (row == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Question>>() {
        }.getType();
        return gson.fromJson(row, type);
    }

    @TypeConverter
    public static String questionsToRow(List<Question> questions) {
        if (questions == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Question>>() {
        }.getType();
        return gson.toJson(questions, type);
    }
}
