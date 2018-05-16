package com.example.patrycja1.safedriver;

import android.content.Intent;
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

    public Button btn_2;
    public void init2(){
        btn_2=(Button)findViewById(R.id.btn_2);

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent przejscie2 = new Intent(HelpInstruction0.this,HelpInstruction01.class);
                startActivity(przejscie2);

            }
        });


        }

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
                    int result = komunikat1.setLanguage(new Locale("PL","PL"));  //wybór języka
                    if(result == TextToSpeech.LANG_MISSING_DATA ||        //sprawdzenie czy jezyk jest na urzadzeniu
                            result == TextToSpeech.LANG_NOT_SUPPORTED)   //sprawdzenie czy jezyk jest wspierany

                    {
                        Toast.makeText(HelpInstruction0.this,"This language is not supported", Toast.LENGTH_SHORT).show(); //komunikat błędu
                    }
                    else //jeżeli wszystkie powyższe warunki są spełnione, można użyć metody speak
                    {

                        komunikat1.setPitch(0.6f);      //intonacja
                        komunikat1.setSpeechRate(1.0f); //szybkość
                        speak();
                    }
                }

            }
        });
        help0text = (TextView)findViewById(R.id.help0text);
      init2();  //ta funkcja obsługuje naciśnięcie przycisku prześcia do kolejnego kroku

    }

    private void speak() {
        String text=help0text.getText().toString();  //zamiana instrukcji pp na string
        komunikat1.speak(text,TextToSpeech.QUEUE_FLUSH,null); // kod odpowiedzialny za mówienie wyżej zamienionego teksu
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
