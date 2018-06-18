package com.example.patrycja1.safedriver.help;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.patrycja1.safedriver.HelpObjects;
import com.example.patrycja1.safedriver.MemoryOperation;
import com.example.patrycja1.safedriver.R;
import com.example.patrycja1.safedriver.WeatherPanel;

import java.util.Locale;

public class HelpInstruction0 extends AppCompatActivity {

    private ImageView helpImage;
    private TextView helpInfo;
    private ImageView goFuture;
    private TextView help0text;
    private TextToSpeech komunikat1;
    private int instructionFlag=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_instruction0);
        helpImage = findViewById(R.id.help0Image);
        helpInfo = findViewById(R.id.instructionNumber);
        goFuture=findViewById(R.id.krok);
        help0text = (TextView)findViewById(R.id.help0text);
        long startTime = System.currentTimeMillis();
        Toast.makeText(getApplicationContext(), " Uważnie wysłuchaj instrukcji ...", Toast.LENGTH_SHORT).show();

        final TextToSpeech finalKomunikat = komunikat1;
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

        goFuture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                instructionFlag++;
                if(instructionFlag==6){
                    //onDestroy();
                    Intent intent = new Intent(getApplicationContext(), WeatherPanel.class);
                    startActivity(intent);
                }else {
                    setContainers(instructionFlag);
                    speak();
                }
            }
        });
    }

    public void setContainers(int identify){
        HelpObjects help=HelpObjects.findHelpById(identify);
        this.helpImage.setImageResource(help.gethelpImageId());
        this.help0text.setText(help.getInstruction());
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

