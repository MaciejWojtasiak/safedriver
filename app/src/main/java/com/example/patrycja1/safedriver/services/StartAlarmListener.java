package com.example.patrycja1.safedriver.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Patrycja1 on 04.06.2018.
 */

public class StartAlarmListener extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Start service", Toast.LENGTH_SHORT).show();
        Log.i("Status","Detection has started");
        context.startService(new Intent(context, AlarmService.class));

    }
}