package com.example.patrycja1.safedriver.help;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.patrycja1.safedriver.HelpObjects;
import com.example.patrycja1.safedriver.R;

public class HelpInstruction0 extends AppCompatActivity {

    private ImageView helpImage;
    private TextView helpInstuction;
    private TextView helpInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_instruction0);
        helpImage=findViewById(R.id.help0Image);
        helpInstuction=findViewById(R.id.help0text);
        helpInfo=findViewById(R.id.instructionNumber);
        long startTime = System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);
        Toast.makeText(getApplicationContext()," Uważnie wysłuchaj instrukcji ...", Toast.LENGTH_SHORT).show();

    }


    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            long millis= System.currentTimeMillis();;
            int seconds= (int) (millis / 1000);;
            int minutes= seconds / 60;;
            seconds = seconds % 60;
            while(seconds<150) {
                //timerHandler.postDelayed(this, 500);
                switch (seconds) {
                    case 0:
                        setContainers(1);
                        break;
                    case 30:
                        setContainers(2);
                        break;
                    case 60:
                        setContainers(3);
                        break;
                    case 90:
                        setContainers(4);
                        break;
                    case 120:
                        setContainers(5);
                        break;
                    default:
                        break;
                }
            }
            timerHandler.removeCallbacks(timerRunnable);
        }

    };



    public void setContainers(int identify){
        HelpObjects help=HelpObjects.findHelpById(identify);
        this.helpImage.setImageResource(help.gethelpImageId());
        this.helpInstuction.setText(help.getInstruction());
        this.helpInfo.setText(help.getInfo());
    }
}
