package com.example.patrycja1.safedriver.services;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.widget.Toast;

/**
 * Created by Patrycja1 on 02.05.2018.
 */

public class SensorListener implements SensorEventListener {

    private Handler handler = new Handler();
    private Sensor mySensor;
    private SensorManager SensorManager;
    private long nowStatus = 0;
    private long timeDiff = 0;
    private long lastShake = 0;
    private long lastUpdate = 0;
    private float lastXPOsition;
    private float lastYPOsition;
    private float lastZPOsition;
    private boolean interwalMode;
    private boolean accidentMode;
    private boolean stabilizationMode;
    private boolean sensorStatus;
    private static final int SHAKE_THRESHOLD = 600;

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;
        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            try {
                float xPosition = sensorEvent.values[0];
                float yPosition = sensorEvent.values[1];
                float zPosition = sensorEvent.values[2];
                long curTime = System.currentTimeMillis();
/*
                if ((curTime - lastUpdate) > 100) {
                    long diffTime = (curTime - lastUpdate);
                    lastUpdate = curTime;

                    float speed = Math.abs(xPosition + yPosition + zPosition - lastXPOsition- lastYPOsition- lastZPOsition) / diffTime * 10000;

                    if (speed > SHAKE_THRESHOLD) {

                    }
                    if (speed < SHAKE_THRESHOLD && speed > 200) {

                    }
                    if (speed < 10) {

                    }
                    lastXPOsition = xPosition;
                    lastYPOsition = yPosition;
                    lastZPOsition = zPosition;
                }
                if ((a > 5) && (c == 23)) {
                    onDetect();


                }*/
            }catch (Exception e){
                //Toast.makeText(alarmService.getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
