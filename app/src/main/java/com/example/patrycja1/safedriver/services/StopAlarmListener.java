package com.example.patrycja1.safedriver.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**This class extends from BroadcastReceiver and uses to stop application
 * @see BroadcastReceiver
 * @author Patrycja Mirkowska
 */

public class StopAlarmListener extends BroadcastReceiver {
    /** What does?
     * listens to the events of pressing the stop button in the notification and controls the application.
     * Pause the application action
     * How it works?
     * Retrieves the Context object and using the stopService method stops listening for the alarm,
     * displays the message in Logcat about the application stoppage
     * To use this method you must set?
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Stop service", Toast.LENGTH_SHORT).show();
        Log.i("Status","Detection has stopped");
        context.stopService(new Intent(context, AlarmService.class));
    }
}
