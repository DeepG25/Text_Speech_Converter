package com.example.dell.textvsspeech;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class TtoSActivity extends AppCompatActivity {

    TextToSpeech textToSpeech;
    EditText nedittext;
    Button ttoscon;
    int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tto_s);

        nedittext = (EditText)findViewById(R.id.text_space);
        ttoscon = (Button)findViewById(R.id.ttos_con_btn);

        textToSpeech = new TextToSpeech(TtoSActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS)
                {
                    result = textToSpeech.setLanguage(Locale.ENGLISH);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Feature is not present",Toast.LENGTH_SHORT).show();
                }
            }
        });

        ttoscon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = nedittext.getText().toString();

                textToSpeech.speak(s,TextToSpeech.QUEUE_FLUSH,null);
            }
        });
    }
}
