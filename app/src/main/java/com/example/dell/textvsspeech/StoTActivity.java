package com.example.dell.textvsspeech;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class StoTActivity extends AppCompatActivity {

    ImageButton speechbtn;
    TextView text_speech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sto_t);

        speechbtn = (ImageButton)findViewById(R.id.speech_btn);
        text_speech = (TextView)findViewById(R.id.text_speech);

        speechbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                i.putExtra(RecognizerIntent.EXTRA_PROMPT,"Say Something!");

                try {
                    startActivityForResult(i, 100);
                }
                catch (ActivityNotFoundException a)
                {
                    Toast.makeText(StoTActivity.this,"Sorry! This feature is not supported",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void onActivityResult(int request_code,int result_code,Intent i)
    {
        super.onActivityResult(request_code,result_code,i);

        switch (request_code)
        {
            case 100:
                if(result_code == RESULT_OK && i != null)
                {
                    ArrayList<String> result = i.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    text_speech.setText(result.get(0));
                }
                break;
        }
    }
}
