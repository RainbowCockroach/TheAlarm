package com.example.thealarm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity1 extends AppCompatActivity {

    EditText text;
    Button btnConvert;
    TextToSpeech toSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById((R.id.editText));
        btnConvert = findViewById((R.id.button));


        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                String noidung1 = text.getText().toString();
//                view1.setText(noidung1);
//                test();
                String noidung = text.getText().toString();
                toSpeech = new TextToSpeech(MainActivity1.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                        if(i != TextToSpeech.ERROR){
                            toSpeech.setLanguage(Locale.ENGLISH);
                            toSpeech.speak(noidung, TextToSpeech.QUEUE_FLUSH,null);

                        }

                    }
                });

            }
        });
    }
}