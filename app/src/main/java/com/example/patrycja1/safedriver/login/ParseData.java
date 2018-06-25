package com.example.patrycja1.safedriver.login;

import android.content.Context;
import android.widget.Toast;

/**
 * this class is used to validate the data downloaded from the user.
 * You'll find methods to validate text data, phone number and age
 * @author Patrycja Mirkowska
 */
public class ParseData {

    private int registrationFlag=0;

    public ParseData(){
        super();
    }
    /**What does?
    * this method checks the correctness of the entered data by the user
    * set application context and data
    * To use this method you must set?
    * @param data and
    * @param context
    * which describe the data to be checked and the context of the application
    * How it works?
    * if the data is not empty or null returns it,
    * Otherwise, it increments the error flag, which will later show the user an error
    */
    public String parse(String data, Context context){
        if((data.isEmpty()) || (data==null) || (data.length()==0)){
            registrationFlag+=1;
        }
        return data;
    }

    /**What does?
     * this method checks the correctness of the entered age by the user
     * set application context and data
     * To use this method you must set?
     * @param age and
     * @param context
     * which describe the age to be checked and the context of the application
     * How it works?
     * if the age is not empty or null or length of age is less than 2, returns it,
     * Otherwise, it increments the error flag, which will later show the user an error
     */
    public String correctlyAge(String age,Context context){
        if((age.length()==0) || (age.length()>2)|| (age.isEmpty())){
            registrationFlag+=1;
        }
        return age;
    }

    /**What does?
     * this method checks the correctness of the entered number by the user
     * set application context and data
     * To use this method you must set?
     * @param number and
     * @param context
     * which describe the number to be checked and the context of the application
     * How it works?
     * if the data is not empty or null or lenght of the number is equal to 9 returns it,
     * Otherwise, it increments the error flag, which will later show the user an error
     */
    public String correctlyNumber(String number,Context context){
        if((number.length()>9) || (number.length()<9)|| (number.isEmpty())){
            registrationFlag+=1;
        }
        return number;
    }

    /**What does?
     * this function puts the flags of the registration procedure
     * if registrationFlag !=0 registration procedure not compled
     * How it works?
     * this method return registrationFlag variable
     */
    public int registrationFlag(){
        return registrationFlag;
    }
}
