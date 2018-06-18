package com.example.patrycja1.safedriver;

<<<<<<< HEAD
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
=======
import android.location.Location;
import android.graphics.Typeface;
import android.location.LocationListener;
import android.location.LocationManager;
>>>>>>> c45467b91c8014dd50b14801ae7073e7ba46a903
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;

<<<<<<< HEAD
import com.example.patrycja1.safedriver.help.HelpInstruction0;
import com.example.patrycja1.safedriver.login.LogIn;
import com.example.patrycja1.safedriver.services.AlarmService;

public class WeatherPanel extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_panel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();
=======

>>>>>>> c45467b91c8014dd50b14801ae7073e7ba46a903

public class WeatherPanel extends AppCompatActivity {



<<<<<<< HEAD
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




    }
=======
>>>>>>> c45467b91c8014dd50b14801ae7073e7ba46a903

    TextView cityField, detailsField, currentTemperatureField, humidity_field, pressure_field, weatherIcon, updatedField;

    Typeface weatherFont;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_weather_panel);

<<<<<<< HEAD

         if (id == R.id.help) {
             Intent intent = new Intent(getApplicationContext(),HelpInstruction0.class);
             startActivity(intent);
=======
>>>>>>> c45467b91c8014dd50b14801ae7073e7ba46a903

        weatherFont = Typeface.createFromAsset(getAssets(), "fonts/weathericons-regular-webfont.ttf");

<<<<<<< HEAD
        } else if (id == R.id.dataPerson) {
            Intent intent = new Intent(getApplicationContext(),LogIn.class);
            startActivity(intent);

        } else if (id == R.id.shutDown) {
            MemoryOperation memoryOperation=new MemoryOperation();
            memoryOperation.deleteData(getApplicationContext());
             Intent intent = new Intent(getApplicationContext(),MainActivity.class);
             startActivity(intent);
=======
        cityField = (TextView)findViewById(R.id.city_field);
        updatedField = (TextView)findViewById(R.id.updated_field);
        detailsField = (TextView)findViewById(R.id.details_field);
        currentTemperatureField = (TextView)findViewById(R.id.current_temperature_field);
        humidity_field = (TextView)findViewById(R.id.humidity_field);
        pressure_field = (TextView)findViewById(R.id.pressure_field);
        weatherIcon = (TextView)findViewById(R.id.weather_icon);
        weatherIcon.setTypeface(weatherFont);


        Function.placeIdTask asyncTask =new Function.placeIdTask(new Function.AsyncResponse() {
            public void processFinish(String weather_city, String weather_description, String weather_temperature, String weather_humidity, String weather_pressure, String weather_updatedOn, String weather_iconText, String sun_rise) {
>>>>>>> c45467b91c8014dd50b14801ae7073e7ba46a903

                cityField.setText(weather_city);
                updatedField.setText(weather_updatedOn);
                detailsField.setText(weather_description);
                currentTemperatureField.setText(weather_temperature);
                humidity_field.setText("Wilgotność: "+weather_humidity);
                pressure_field.setText("Ciśnienie: "+weather_pressure);
                weatherIcon.setText(Html.fromHtml(weather_iconText));

            }
        });
        asyncTask.execute("51.9537506", "19.1343787"); //  asyncTask.execute("Latitude", "Longitude")

    }

            


}