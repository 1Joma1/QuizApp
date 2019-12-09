package com.joma.quizapp.core;

import android.util.Log;

import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions;
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslator;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions;

public class EnglishToRussianFirebaseTranslator {

    public static FirebaseTranslator init() {
        FirebaseTranslatorOptions options =
                new FirebaseTranslatorOptions.Builder()
                        .setSourceLanguage(FirebaseTranslateLanguage.EN)
                        .setTargetLanguage(FirebaseTranslateLanguage.RU)
                        .build();
        final FirebaseTranslator englishRussianTranslator =
                FirebaseNaturalLanguage.getInstance().getTranslator(options);

        FirebaseModelDownloadConditions conditions = new FirebaseModelDownloadConditions.Builder()
                .requireWifi()
                .build();
        englishRussianTranslator.downloadModelIfNeeded(conditions)
                .addOnFailureListener(
                        e -> Log.e("Firebase translator", e.getMessage()));


        return englishRussianTranslator;
    }
}


