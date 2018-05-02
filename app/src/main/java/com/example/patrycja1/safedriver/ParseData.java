package com.example.patrycja1.safedriver;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Patrycja1 on 30.04.2018.
 */

public class ParseData {

    private int registrationFlag=0;

    public ParseData(){
        super();
    }

    public String parse(String data, Context context){
        if((data.isEmpty()) || (data==null) || (data.length()==0)){
            Toast.makeText(context, R.string.error_data, Toast.LENGTH_SHORT).show();
            registrationFlag+=1;
        }
        return data;
    }

    public String correctlyAge(String age,Context context){
        if((age.length()==0) || (age.length()>2)|| (age.isEmpty())){
            Toast.makeText(context, R.string.error_data, Toast.LENGTH_SHORT).show();
            registrationFlag+=1;
        }
        return age;
    }

    public String correctlyNumber(String number,Context context){
        if((number.length()>9) || (number.length()<9)|| (number.isEmpty())){
            Toast.makeText(context, R.string.error_data, Toast.LENGTH_SHORT).show();
            registrationFlag+=1;
        }
        return number;
    }

    public int registrationFlag(){
        //if registrationFlag !=0 registration procedure not compled
        return registrationFlag;
    }
}
