package com.example.patrycja1.safedriver.services;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.widget.Toast;

/**
 * Created by Patrycja1 on 04.06.2018.
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

    //    SensorEvent event;
    private AlarmService alarmService;

    public SensorListener(AlarmService alarmService) {
        this.alarmService = alarmService;
    }

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

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private void onDetect(){
        alarmService.start();
    }
}


