package com.example.patrycja1.safedriver.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**This class extends from BroadcastReceiver and uses to resume application
 * @see BroadcastReceiver
 * @author Patrycja Mirkowska
 */

public class StartAlarmListener extends BroadcastReceiver {
    /** What does?
     * listens to the events of pressing the start button in the notification and controls the application.
     * Resumes the application action
     * How it works?
     * Retrieves the Context object and using the startService method resume listening for the alarm,
     * displays the message in Logcat about the application resume
     * To use this method you must set?
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Start service", Toast.LENGTH_SHORT).show();
        Log.i("Status","Detection has started");
        context.startService(new Intent(context, AlarmService.class));

    }
}