package com.example.patrycja1.safedriver.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.patrycja1.safedriver.AlarmActivity;
import com.example.patrycja1.safedriver.MainActivity;
import com.example.patrycja1.safedriver.R;
import com.example.patrycja1.safedriver.WeatherPanel;
import com.example.patrycja1.safedriver.login.LogIn;

/**
 * Created by Patrycja1 on 04.06.2018.
 */

public class AlarmService extends Service {
    private SensorManager sensorManager;
    private long lastUpdate;
    private SensorEventListener listen;
    private Notification notification;



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            createNotification4();
            sensorManager = (SensorManager) getApplicationContext()
                    .getSystemService(SENSOR_SERVICE);
            lastUpdate = System.currentTimeMillis();
            listen = new SensorListener(this);
            Sensor accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(listen, accel, SensorManager.SENSOR_DELAY_NORMAL);
            return START_STICKY;
        } catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            return START_STICKY;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void start(){
        Intent intent = new Intent(getApplicationContext(), AlarmActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    public Context getApplicationContext(){
        return super.getApplicationContext();
    }


    private void callNotification(Notification notification){
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager notificationManager = (NotificationManager) getSystemService(ns);

        notificationManager.notify(1, notification);
    }

    private void createNotification4(){
        String longText = "Intent service has a new message with:";
        RemoteViews notificationView = new RemoteViews(getPackageName(), R.layout.notification_switch);
        Intent notificationIntent = new Intent(this, WeatherPanel.class);
        PendingIntent pendingNotificationIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        Intent startIntent = new Intent(this, StartAlarmListener.class);
        PendingIntent pendingstartIntent = PendingIntent.getBroadcast(this, 0, startIntent, 0);

        Intent stopIntent = new Intent(this, StopAlarmListener.class);
        PendingIntent pendingstopIntent = PendingIntent.getBroadcast(this, 0, stopIntent, 0);


        notificationView.setOnClickPendingIntent(R.id.start, pendingstartIntent);
        notificationView.setOnClickPendingIntent(R.id.stop, pendingstopIntent);
        Notification noti =
                new NotificationCompat.Builder(this, LogIn.CHANNEL_ID)
                        .setSmallIcon(R.drawable.person)
                        .setCustomContentView(notificationView)
                        .setContentIntent(pendingNotificationIntent)
                        .build();

        // Hide the notification after its selected
        noti.flags |= Notification.FLAG_NO_CLEAR;

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify((int) System.currentTimeMillis(), noti);

    }
}
