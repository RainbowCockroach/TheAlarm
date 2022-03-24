package com.example.thealarm;

import android.content.Context;

import java.util.Locale;

public class TextToSpeech {
    private Context context;
    private android.speech.tts.TextToSpeech toSpeech;

    public TextToSpeech(Context context) {
        this.context = context;
        toSpeech = new android.speech.tts.TextToSpeech(context, new android.speech.tts.TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i != android.speech.tts.TextToSpeech.ERROR){
                    toSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });
    }

    public void toSpeech(String theText) {
        toSpeech.speak(theText, android.speech.tts.TextToSpeech.QUEUE_FLUSH,null);
    }
}
