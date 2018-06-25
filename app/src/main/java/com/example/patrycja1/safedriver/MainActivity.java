package com.example.patrycja1.safedriver;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.patrycja1.safedriver.help.HelpInstruction0;
import com.example.patrycja1.safedriver.login.LogIn;
/** This is the first login activity class supports the log/register buttons
 * @author Patrycja Mirkowska
 */
public class MainActivity extends AppCompatActivity {
    private TextView signIn;
    private Button logInButton;
    private MemoryOperation memoryOperation;


    /** What does?
     * creates a new activity, sets up components,
     * binds them to variables,
     * also contains the action methods of the button and the transition between activities
     * @see Intent
     * @see MemoryOperation
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signIn=findViewById(R.id.createAccount);
        logInButton=findViewById(R.id.logInButton);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LogIn.class);
                startActivity(intent);
            }
        });
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memoryOperation=new MemoryOperation();
                if(memoryOperation.readFromMemory(getApplicationContext(),"FIRSTNAME").isEmpty() ||
                        memoryOperation.readFromMemory(getApplicationContext(),"FIRSTNAME").equals(" ")){
                    Toast.makeText(getApplicationContext(),"Nie znaleziono żadnego użytkownika", Toast.LENGTH_SHORT).show();

                }else {
                    Intent intent = new Intent(getApplicationContext(), WeatherPanel.class);
                    startActivity(intent);
                }
            }
        });
    }
}
