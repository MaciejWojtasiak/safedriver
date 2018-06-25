package com.example.patrycja1.safedriver;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
/** This is the aparm activity class.
* You will find methods for creating the activity and turning off the alarm
 * @author Patrycja Mirkowska
*/
public class AlarmActivity extends AppCompatActivity {

    private long millis;
    private int seconds;
    private TextView timerTextView;
    private float deg = 0;
    private ImageView timerImage;
    MediaPlayer player;

    /** What does?
    * this function creates activity binds the components
    * to the corresponding variables
    * creates a new thread for the clock (counts down 30 seconds)
    * plays a sound
    * How it works?
    * initializes variables with the function findViewById
    * creates a new thread for the clock using Runnable and Handler
    * plays a sound signal using MediaPlayer, the sound signal is in the raw directory
     * @see MediaPlayer
     * @see Runnable
     * @see Handler
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        timerTextView=findViewById(R.id.timeralarm);
        timerImage=findViewById(R.id.timImg);
        timerHandler.postDelayed(timerRunnable, 0);
        player=MediaPlayer.create(getApplicationContext(),R.raw.alarm30);
        player.start();
    }
        // timer handler
        Handler timerHandler = new Handler();
        Runnable timerRunnable = new Runnable() {

            @Override
            public void run() {
                millis = System.currentTimeMillis();
                seconds = (int) (millis / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;

                ///timer format
                if ((30 - seconds) < 10) {
                    timerTextView.setText(String.format("00:0%d", 30 - seconds));
                    timerHandler.postDelayed(this, 500);
                } else {
                    timerTextView.setText(String.format("00:%d", 30 - seconds));
                    timerHandler.postDelayed(this, 500);
                }
                //stop the timer
                if (30 - seconds <= 0) {
                    timerHandler.removeCallbacks(timerRunnable);

                }

            }
        };
    /** What does?
     * this method pause alarm
     * How it works?
     * used the removeCallbacks method which stop the timeHandler
     * To use this method you must set?
     */
    @Override
    public void onPause() {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);
    }

    public void onBackPressed() {
    }

    /** What does?
     * this function stops the alarm and player,
     * stops the time handler and goes to the activity of the weather panel (main application activity)
     * How it works?
     * using stop() method had stopped player and using Intent goes to main application activity
     * To use this method you must set?
     * @param view
     * only click in the timer view
     * @see MediaPlayer
     */
    public void stopAlarm(View view) {
        player.stop();
        Intent intent = new Intent(getApplicationContext(),WeatherPanel.class);
        startActivity(intent);
    }
}
