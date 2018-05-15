package com.example.patrycja1.safedriver;

import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class HelpInstruction0 extends AppCompatActivity {

    TextView help0text;
    TextToSpeech komunikat1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_instruction0);

        komunikat1 = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

                if(status == komunikat1.SUCCESS){
                    int result = komunikat1.setLanguage(new Locale("PL","PL"));
                    if(result == TextToSpeech.LANG_MISSING_DATA ||
                            result == TextToSpeech.LANG_NOT_SUPPORTED)

                    {
                        Toast.makeText(HelpInstruction0.this,"This language is not supported", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {

                        komunikat1.setPitch(0.6f);
                        komunikat1.setSpeechRate(1.0f);
                        speak();
                    }
                }

            }
        });
        help0text = (TextView)findViewById(R.id.help0text);


    }

    private void speak() {
        String text=help0text.getText().toString();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            komunikat1.speak(text,TextToSpeech.QUEUE_FLUSH,null,null);
        else
            komunikat1.speak(text,TextToSpeech.QUEUE_FLUSH,null);
    }




    @Override
    protected void onDestroy() {
        if(komunikat1 != null)
        {
            komunikat1.stop();
            komunikat1.shutdown();
        }
        super.onDestroy();
    }



}
