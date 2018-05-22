package com.example.patrycja1.safedriver.help;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.patrycja1.safedriver.HelpObjects;
import com.example.patrycja1.safedriver.R;

import java.util.Locale;

public class HelpInstruction0 extends AppCompatActivity {

    private ImageView helpImage;
    private TextView helpInstuction;
    private TextView helpInfo;
    private long millis;
    private int seconds;
    private TextView help0text;
    private TextToSpeech komunikat1;

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            millis = System.currentTimeMillis();
            seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            //seconds = seconds % 60;
            int count = 1;
            if ((seconds == 0 || seconds % 30 == 0) && seconds < 150) {
                setContainers(count);
                count++;
            }
            timerHandler.postDelayed(this, 500);
            if(seconds==150){
                millis=0;
                count=0;
                timerHandler.removeCallbacks(timerRunnable);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_instruction0);
        helpImage=findViewById(R.id.help0Image);
        helpInstuction=findViewById(R.id.help0text);
        helpInfo=findViewById(R.id.instructionNumber);
        //long startTime = System.currentTimeMillis();
       // timerHandler.postDelayed(timerRunnable, 0);
        setContainers(4);
        Toast.makeText(getApplicationContext()," Uważnie wysłuchaj instrukcji ...", Toast.LENGTH_SHORT).show();
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

    public void setContainers(int identify){
        HelpObjects help=HelpObjects.findHelpById(identify);
        this.helpImage.setImageResource(help.gethelpImageId());
        this.helpInstuction.setText(help.getInstruction());
        this.helpInfo.setText(help.getInfo());
    }

    private void speak() {
        String text=help0text.getText().toString();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            komunikat1.speak(text, TextToSpeech.QUEUE_FLUSH,null,null);
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

