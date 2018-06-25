package com.example.patrycja1.safedriver;

import android.content.Context;
import android.content.SharedPreferences;

/** This class is used for actions on the device's memory.
 You will find methods for saving, reading and deleting data
 @author Patrycja Mirkowska
 @see SharedPreferences
 */

public class MemoryOperation {

    public MemoryOperation(){
        super();
    }
    /** What does?
     * write data to memory
     * How it works?
     * if data is correctly seved to "general_preferences"
     * To use this method you must set?
     * @param context
     * @param key , which id key in wrtite data
     * @param data - is a data, which you want to write
     */
    public void writeToMemory(Context context,String key,String data){
        SharedPreferences.Editor editor = context.getSharedPreferences("general_preferences", context.MODE_PRIVATE).edit();
        editor.putString(key,data);
        editor.apply();
    }

    /** What does?
     * read data from "general_preferences"
     * How it works?
     * if key is correctly get data from "general_preferences"
     * To use this method you must set?
     * @param context
     * @param key , which id key in reading data
     */
    public String readFromMemory(Context context,String key){
        SharedPreferences sharedPref =context.getSharedPreferences("general_preferences",context.MODE_PRIVATE);
        String defaultValue = " ";
        String data= sharedPref.getString(key,defaultValue);
        return data;
    }
    /** What does?
     * delete all data from "general_preferences"
     * How it works?
     * clear and commit all "general_preferences"
     * To use this method you must set?
     * @param context
     */
    public void deleteData(Context context){
        SharedPreferences preferences = context.getSharedPreferences("general_preferences", 0);
        preferences.edit().clear().commit();
    }
}
