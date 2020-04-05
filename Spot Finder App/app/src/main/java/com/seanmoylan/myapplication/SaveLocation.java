package com.seanmoylan.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.seanmoylan.myapplication.Classes.Location;
import com.seanmoylan.myapplication.Classes.Login;

public class SaveLocation extends AppCompatActivity {

    private TextInputEditText latitudeTxt;
    private TextInputEditText longitudeTxt;
    private TextInputEditText titleTxt;
    private TextInputEditText descriptionTxt;
    private Button cancelBtn;
    private Button saveBtn;

    Retrofit retrofit;

    Location newLocation;
    double latitude, longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_location);

        // Connect to TextFields and buttons
        latitudeTxt= findViewById(R.id.latitudeTxt);
        longitudeTxt = findViewById(R.id.longitudeTxt);
        titleTxt = findViewById(R.id.saveTitleTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        saveBtn = findViewById(R.id.saveLocationBtn);
        cancelBtn = findViewById(R.id.canelBtn);


        // Stop the user from editing the coordinates
        latitudeTxt.setFocusable(false);
        longitudeTxt.setFocusable(false);

        // Display current coordinates in textfields
        if(populateCoordinates()){
            latitudeTxt.setText(String.valueOf(latitude));
            longitudeTxt.setText(String.valueOf(longitude));
        }

        // Save Click listener
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkForInput()){
                    Location newLocation = new Location();
                    newLocation.setLatitude(latitude);
                    newLocation.setLongitude(longitude);
                    newLocation.setTitle(titleTxt.getText().toString());
                    newLocation.setDescription(descriptionTxt.getText().toString());

                    // Pass location to saveLocation method
                    saveLocation(newLocation);
                }
            }
        });

        // Cancel Click listener
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
            }
        });


    }

    private boolean populateCoordinates() {
        Bundle dataRecieved = getIntent().getExtras();

        if (dataRecieved != null)
        {
            latitude = dataRecieved.getDouble("latitude");
            longitude = dataRecieved.getDouble("longitude") ;
            return true;
        }

        return false;
    }

    private boolean checkForInput(){
        return true;
    }

    private void saveLocation(Location location){

        // Test Location
        Location l = new Location();
        l.setLongitude(2.23523);
        l.setLatitude(3.23523);
        l.setTitle("Hello World");
        l.setDescription("Some generic text");
        l.setType("stair");

        // Set up retrofit instance for sending POST to server
        // Retrofit and Gson
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:5000/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        FlaskAPI flaskApi = retrofit.create(FlaskAPI.class);
        Call<Location> call = flaskApi.createLocation(l);

        call.enqueue(new Callback<Location>() {
            @Override
            public void onResponse(Call<Location> call, Response<Location> response) {
                System.out.println("Code: " + response.code());

                if(response.isSuccessful()){
                    //Return user to the map
                    startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                }
            }

            @Override
            public void onFailure(Call<Location> call, Throwable t) {
                System.out.println("Failure response: "+t.getMessage());
            }
        });

    }
}
