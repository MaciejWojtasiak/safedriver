package com.example.patrycja1.safedriver;

import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class HelpInstruction01 extends AppCompatActivity {

    TextView help1text;
    TextToSpeech komunikat2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_instruction01);

        komunikat2 = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

                if (status == komunikat2.SUCCESS) {
                    int result = komunikat2.setLanguage(new Locale("PL", "PL"));
                    if (result == TextToSpeech.LANG_MISSING_DATA ||
                            result == TextToSpeech.LANG_NOT_SUPPORTED)

                    {
                        Toast.makeText(HelpInstruction01.this, "This language is not supported", Toast.LENGTH_SHORT).show();
                    } else {

                        komunikat2.setPitch(0.6f);
                        komunikat2.setSpeechRate(1.0f);
                        speak();
                    }
                }

            }
        });
        help1text = (TextView)findViewById(R.id.help1text);

        }
    private void speak() {
        String text = help1text.getText().toString();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            komunikat2.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        else
            komunikat2.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
    @Override
    protected void onDestroy() {
        if (komunikat2 != null) {
            komunikat2.stop();
            komunikat2.shutdown();
        }
        super.onDestroy();
    }
}
