package com.example.patrycja1.safedriver;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Patrycja1 on 01.05.2018.
 */

public class MemoryOperation {

    public MemoryOperation(){
        super();
    }

    // if data is correctly seved to "general_preferences"
    public void writeToMemory(Context context,String key,String data){
        SharedPreferences.Editor editor = context.getSharedPreferences("general_preferences", context.MODE_PRIVATE).edit();
        editor.putString(key,data);
        editor.apply();
    }

    // read data from "general_preferences"
    public String readFromMemory(Context context,String key){
        SharedPreferences sharedPref =context.getSharedPreferences("general_preferences",context.MODE_PRIVATE);
        String defaultValue = " ";
        String data= sharedPref.getString(key,defaultValue);
        return data;
    }
}
