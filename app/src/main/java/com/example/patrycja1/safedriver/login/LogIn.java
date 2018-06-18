package com.example.patrycja1.safedriver.login;

import android.content.Context;
import android.content.Intent;
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

public class LogIn extends AppCompatActivity {

    private ImageView[] viewElements=new ImageView[5];
    private TextView [] userDataElements=new TextView[5];
    private boolean loginStatus=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

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


    }

    public void animateElements(View[] elementsTable){
        //animate activity elements method [used XML file]
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

    public void saveOnClick(View view) {
        // if user click save button, he's data saved to memory["general_preferences"]
        writeToMemory();
        if(loginStatus){
            Intent intent = new Intent(getApplicationContext(),WeatherPanel.class);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(), R.string.correct_data, Toast.LENGTH_SHORT).show();
        }
    }
}
