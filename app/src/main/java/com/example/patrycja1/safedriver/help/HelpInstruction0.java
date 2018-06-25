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

/**
 * This class generates first aid instructions.
 * You will find methods for creating activities, setting components (texts, pictures, etc.) and playing voice instructions.
 * @author Patrycja Mirkowska and Maciej Wojtasiak
 */
public class HelpInstruction0 extends AppCompatActivity {

    private ImageView helpImage;
    private TextView helpInfo;
    private ImageView goFuture;
    private TextView help0text;
    private TextToSpeech komunikat1;
    private int instructionFlag=1;

    /**What does?
     * this method creates activity binds the components
     * to the corresponding variables and plays the voice instructions
     * How it works?
     * initializes variables with the method findViewById
     * using TextToSpeech and speaks the message after pressing the button
     * @see TextToSpeech
     */
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

            /**What does?
             * if the message is downloaded correctly, the Polish language is chosen and a message is given
             * To use this method you must set?
             * @param status
             * which informs about the download status of the message
             * How it works?
             * if the message download status == succes with the setLanguage option that accepts as a parameter,
             * the Locale object sets the language and country parameters
             * Then he sets the pitch and speed of speaking and starts speaking instructions through the speak function
             * if can not downloading language show message " This language is not supported"
             * @see Locale
             */
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
                    Intent intent = new Intent(getApplicationContext(), WeatherPanel.class);
                    startActivity(intent);
                }else {
                    setContainers(instructionFlag);
                    speak();
                }
            }
        });
    }

    /** What does?
     * this method, by downloading the appropriate identification number,
     * sets the required text, picture and instruction number in the first aid activity.
     * To use this method you must set?
     * @param identify
     * which identifies the instruction in the set
     * @see HelpObjects
     * How it works?
     * reference values set to variables
     */
    public void setContainers(int identify){
        HelpObjects help=HelpObjects.findHelpById(identify);
        this.helpImage.setImageResource(help.gethelpImageId());
        this.help0text.setText(help.getInstruction());
        this.helpInfo.setText(help.getInfo());
    }

    /**What does?
     * this method retrieves the required text from the textView, then plays it back in voice form
     * How it works?
     * depending on the version, sdk calls the speakText method on the TextToSpeech object from the TextToSpeech class that accepts,
     * among other things, the text of the instruction and delivers the message
     * @see TextToSpeech
     */
    private void speak() {
        String text=help0text.getText().toString();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            komunikat1.speak(text, TextToSpeech.QUEUE_FLUSH,null,null);
        else
            komunikat1.speak(text,TextToSpeech.QUEUE_FLUSH,null);
    }

    /**What does?
     * this feature ends the message
     * How it works?
     * when the message contains a value, it is destroyed by using the function stop() and shutdown();
     */
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

