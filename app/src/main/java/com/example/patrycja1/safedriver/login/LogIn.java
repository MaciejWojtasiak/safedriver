package com.example.patrycja1.safedriver.login;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.patrycja1.safedriver.ElementAnimator;
import com.example.patrycja1.safedriver.MemoryOperation;
import com.example.patrycja1.safedriver.R;
import com.example.patrycja1.safedriver.WeatherPanel;
import com.example.patrycja1.safedriver.services.AlarmService;

/**This class performs the user registration procedure.
 * It contains methods for creating activities, animating elements using the
 * @see ElementAnimator
 * saving to memory using the
 * @see MemoryOperation
 * and validating user data using the
 * @see ParseData
 * @author Patrycja Mirkowska
 */
public class LogIn extends AppCompatActivity {
    public static String CHANNEL_ID = "ChannelID";
    private ImageView[] viewElements=new ImageView[5];
    private TextView [] userDataElements=new TextView[5];
    private boolean loginStatus=false;

    /**What does?
     * this method creates activity binds the components
     * to the corresponding variables, display of the built notification
     * and animate elements
     * How it works?
     * initializes variables with the function findViewById
     * using Intent and NotificationChannel I call a notification and create a service for notification using NotificationManager
     * @see NotificationChannel
     * @see NotificationManager
     * @see Intent
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        Toast.makeText(getApplicationContext(),"Uzupełnij swoje dane", Toast.LENGTH_SHORT).show();
        // find elements by id
        viewElements[0]=findViewById(R.id.element1);
        viewElements[1]=findViewById(R.id.element2);
        viewElements[2]=findViewById(R.id.element3);
        viewElements[3]=findViewById(R.id.element4);
        viewElements[4]=findViewById(R.id.save);
        userDataElements[0]=findViewById(R.id.firstName);
        userDataElements[1]=findViewById(R.id.lastName);
        userDataElements[2]=findViewById(R.id.age);
        userDataElements[3]=findViewById(R.id.contactFamily);
        userDataElements[4]=findViewById(R.id.welcomeText);

        //animate activity elements
        animateElements(viewElements);
        animateElements(userDataElements);

        //build nottification
        Intent intent = new Intent(getApplicationContext(), AlarmService.class);
        startService(intent);
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "My Channel", importance);
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(
                    Context.NOTIFICATION_SERVICE);
            mNotificationManager.createNotificationChannel(channel);
        }

    }
    /** What does?
     * this method animate activity elements method [used XML file]
     * To use this method you must set?
     * @param elementsTable
     * which is an array of components to be animated
     * How it works?
     * iterating an array in a for loop, elements with an even identifier are subjected to the goRightElement animation (leave on the right)
     * and the remaining goLeftElement (leave on the left)
     * @see ElementAnimator
     */
    public void animateElements(View[] elementsTable){
        Context context = getApplicationContext();
        ElementAnimator animator=new ElementAnimator();
        for(int i=0;i<elementsTable.length;i++){
            if(i%2==0){
                animator.goRightElement(elementsTable[i],context);
            }else{
                animator.goLeftElement(elementsTable[i],context);
            }
        }
    }

    /** What does?
    * this method checks the correctness of data entered by the user using the local ParseData library,
    * after checking the data they are saved to the device's memory. After successful operation,
    * a corresponding message will be printed. In the event of failure, an appropriate error will be printed.
    * How it works?
    * using ParseData the correctness of the text downloaded from the text field is checked,
    * if the data is correct using MemoryOperation writeToMemory data is saved to SharedPreferences stored in the phone memory
    * @see ParseData
    * @see MemoryOperation
    */
    public void writeToMemory(){
        ParseData parse =new ParseData();
        MemoryOperation writeToMem=new MemoryOperation();
        Context context=getApplicationContext();

        String firstName=parse.parse(userDataElements[0].getText().toString(),context);
        String lastName=parse.parse(userDataElements[1].getText().toString(),context);
        String age=parse.correctlyAge(userDataElements[2].getText().toString(),context);
        String contact=parse.correctlyNumber(userDataElements[3].getText().toString(),context);

        //if user data correctly saved, set toast "Zapisano"
        if(parse.registrationFlag()!=0){
            loginStatus=false;
        }else{
            // if data is correctly seved to "general_preferences"
            // parse method in ParseData class check correctnes of data entered by the user
            writeToMem.writeToMemory(context,"FIRSTNAME",parse.parse(firstName,context));
            writeToMem.writeToMemory(context,"LASTNAME",parse.parse(lastName,context));
            writeToMem.writeToMemory(context,"AGE",parse.correctlyAge(age,context));
            writeToMem.writeToMemory(context,"CONTACT",parse.correctlyNumber(contact,context));
            loginStatus=true;
            Toast.makeText(getApplicationContext(), R.string.saved, Toast.LENGTH_SHORT).show();

        }

    }

    /** What does?
    * the onClick method associated with the component
    * if user click save button, he's data saved to memory["general_preferences"]
    * if the data is incorrect, it will display a corresponding message
    * To use this method you must set?
    * @param view , so you must click on save button
    * How it works?
    * starts the writeToMemory method,if the data is correct, then write to the memory and go to the main application activity,
    * otherwise a error message is displayed
    */
    public void saveOnClick(View view) {
        writeToMemory();
        if(loginStatus){
            Intent intent = new Intent(getApplicationContext(),WeatherPanel.class);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(), R.string.correct_data, Toast.LENGTH_SHORT).show();
        }
    }
}
