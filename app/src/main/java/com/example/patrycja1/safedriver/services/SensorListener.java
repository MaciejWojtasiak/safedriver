package com.example.patrycja1.safedriver.services;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.widget.Toast;

/**
 * This class is about listening for the accelerometer and implementing the SensorEventListener.
 * You'll find there methods to listen for accelerometer movements, accident detection, and accuracy changes
 * @author Patrycja Mirkowska
 * @see SensorEventListener
 */

public class SensorListener implements SensorEventListener {

    int ze;
    private Handler handler = new Handler();
    private Sensor mySensor;
    private SensorManager SM;
    private long now = 0;
    private long timeDiff = 0;
    private long lastShake = 0;
    int i=0;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    int interwal , accident, stabilization;
    boolean status;
    private static final int SHAKE_THRESHOLD = 600;
    Intent intent;
    private AlarmService alarmService;

    public SensorListener(AlarmService alarmService) {
        this.alarmService = alarmService;
    }


    /** What does?
     * the function using the SensorEventListener library listens for accelerometer movements,
     * retrieves current values of items returned by the accelerometer,
     * and compares them with previous values retrieved. If the detection algorithm,
     * ie the difference between the current and previous accelerometer values, is large iterates the accident flag,
     * if it falls within the tolerance, the program iterates the interval (interval variable) flag,
     * if the accelerator is idle iterates the stabilization variable.
     * After 5 flags of the accident and 23 flags of stabilization an alarm is triggered and the flags are reset.
     * The genesis of the accident - some very large movements of the accelerometer and several dozen stabilizations.
     * How it works?
     * creates variables, modes of operation and values from the accelerometer. If the data is received correctly,
     * it saves them to variables and then to the variables last x, y, z. Based on the difference between last and current,
     * it identifies the action mode. when the action is detected, activates the alarm activity.
     * It may throw an exception if the data is not received correctly
     * To use this method you must set?
     * @param sensorEvent from device
     * Other
     * @throws Exception while can't get accelerometer values
     *  @see Sensor
     *  @see SensorEventListener
     *  @see SensorEvent
     *  @see SensorManager
     *  @see Math
     */
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;
        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            try {
                float x = sensorEvent.values[0];
                float y = sensorEvent.values[1];
                float z = sensorEvent.values[2];
                long curTime = System.currentTimeMillis();

                if ((curTime - lastUpdate) > 100) {
                    long diffTime = (curTime - lastUpdate);
                    lastUpdate = curTime;
                    // this complicated algorithm detects flags.
                    float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;

                    if (speed > SHAKE_THRESHOLD) {

                        accident++;

                    }
                    if (speed < SHAKE_THRESHOLD && speed > 200) {
                        interwal++;
                    }
                    if (speed < 10) {

                        if (accident > 5) {
                            stabilization++;
                        }
                    }
                    last_x = x;
                    last_y = y;
                    last_z = z;
                }
                if ((accident > 5) && (stabilization == 23)) {
                    onDetect();
                    accident = 0;
                    interwal = 0;
                    stabilization = 0;

                }
            }catch (Exception e){
                Toast.makeText(alarmService.getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }
    /** What does?
     *Override on Accuracy Changed Method
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    /** What does?
     * accident detection
     * How it works?
     * start listening from AlarmService
     * @see AlarmService
     */
    private void onDetect(){
        alarmService.start();
    }
}


