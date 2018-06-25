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
 * This class inherits from the Service and allows background activity controlled by a notification.
 * In this class you will find methods such as creating notifications,
 * downloading contexts, linking notification and service activities, enabling you to listen
 * @author Patrycja Mirkowska
 */

public class AlarmService extends Service {

    private SensorManager sensorManager;
    private long lastUpdate;
    private SensorEventListener listen;
    private Notification notification;


    /** What does?
     * the method starts to control the application by the user,
     * creates a custom notification and sets the required values.
     * Starts listening for the accelerometer and accident detection
     * How it works?
     * initializes the listener variables and sensorManager
     * To use this method you must set?
     * @param intent
     * @param flags
     * @param startId
     * Other
     * @throws Exception
     * @return int value which is flag on start command
     */
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


    /** What does?
     * on bind method
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    /** What does?
     * resumption of listening
     * How it works?
     * activates the alarm activity for a new task flag
     */
    public void start(){
        Intent intent = new Intent(getApplicationContext(), AlarmActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    /** What does?
     * getter method
     * How it works?
     * returned application context
     */
    public Context getApplicationContext(){
        return super.getApplicationContext();
    }

    /** What does?
     * this method combines a notification with the created site
     * How it works?
     * creates and initializes the NotificationManager object, then using the notify method displays the notification,
     * which it accepts as a parameter with the given id
     * To use this method you must set?
     * @param notification
     * this param is created notification
     */
    private void callNotification(Notification notification){
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager notificationManager = (NotificationManager) getSystemService(ns);
        notificationManager.notify(1, notification);
    }
    /** What does?
     * the method creates a custom notification,
     * sets individual system fields, sets the listener,
     * binds with the pause action and the resume action
     * How it works?
     * creates and initializes variables. PendingIntent and RemoteViews allow you to display notifications on the notification status bar.
     * Next, the Notification object is created and its values are set, among others, the icon. Finally, the notification is pushed out
     * @see PendingIntent
     * @see RemoteViews
     * @see Notification
     * @see NotificationManager
     */
    private void createNotification4(){

        String longText = "Intent service has a new message with:";
        RemoteViews notificationView = new RemoteViews(getPackageName(), R.layout.notification_switch);
        Intent notificationIntent = new Intent(this, WeatherPanel.class);
        PendingIntent pendingNotificationIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        Intent startIntent = new Intent(this, StartAlarmListener.class);
        PendingIntent pendingstartIntent = PendingIntent.getBroadcast(this, 0, startIntent, 0);

        Intent stopIntent = new Intent(this, StopAlarmListener.class);
        PendingIntent pendingstopIntent = PendingIntent.getBroadcast(this, 0, stopIntent, 0);

        // create noti
        notificationView.setOnClickPendingIntent(R.id.start, pendingstartIntent);
        notificationView.setOnClickPendingIntent(R.id.stop, pendingstopIntent);
        Notification noti =
                new NotificationCompat.Builder(this, LogIn.CHANNEL_ID)
                        .setSmallIcon(R.drawable.person)
                        .setCustomContentView(notificationView)
                        .setContentIntent(pendingNotificationIntent)
                        .build();

        // Hide the notification after its selected
        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify((int) System.currentTimeMillis(), noti);

    }
}
