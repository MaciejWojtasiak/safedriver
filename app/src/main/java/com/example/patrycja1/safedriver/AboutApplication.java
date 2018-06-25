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

/** This is the activity class that describes the application
 * @author Patrycja Mirkowska
 */
public class AboutApplication extends AppCompatActivity {

    /**What does?
    * this method creates activity binds the components
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // this function creates activity binds the components
        // to the corresponding variables
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_application);

    }

}
