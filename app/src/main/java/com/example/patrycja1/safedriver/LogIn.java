package com.example.patrycja1.safedriver;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LogIn extends AppCompatActivity {

    private ImageView[] viewElements=new ImageView[5];
    private TextView [] userDataElements=new TextView[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

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
                animator.goLeftElement(elementsTable[i],context);
            }else{
                animator.goRightElement(elementsTable[i],context);
            }
        }
    }

    public void writeToMemory(){

        Context context=getApplicationContext();
        String firstName=userDataElements[0].getText().toString();
        String lastName=userDataElements[1].getText().toString();
        String age=userDataElements[2].getText().toString();
        String contact=userDataElements[3].getText().toString();
        ParseData parse =new ParseData();


        // if data is correctly seved to "general_preferences"
        // parse method in ParseData class check correctnes of data entered by the user
        SharedPreferences.Editor editor = getSharedPreferences("general_preferences", MODE_PRIVATE).edit();
        editor.putString("FIRSTNAME",parse.parse(firstName,context));
        editor.putString("LASTNAME",parse.parse(lastName,context));
        editor.putString("AGE",parse.correctlyAge(age,context));
        editor.putString("CONTACT",parse.correctlyNumber(contact,context));
        editor.apply();

        //if user data correctly saved, set toast "Zapisano"
        if(parse.registrationFlag()!=0){
            Toast.makeText(getApplicationContext(),"Spróbuj jeszcze raz", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"Zapisano", Toast.LENGTH_SHORT).show();
        }

    }

    public void saveOnClick(View view) {
        // if user click save button, he's data saved to memory["general_preferences"]
        writeToMemory();
    }
}
