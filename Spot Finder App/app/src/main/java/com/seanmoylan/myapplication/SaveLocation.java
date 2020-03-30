package com.seanmoylan.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;
import com.seanmoylan.myapplication.Classes.Location;

public class SaveLocation extends AppCompatActivity {

    private TextInputEditText latitudeTxt;
    private TextInputEditText longitudeTxt;
    private TextInputEditText titleTxt;
    private TextInputEditText descriptionTxt;

    Location newLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_location);

        // Connect to TextFields
        latitudeTxt= findViewById(R.id.latitudeTxt);
        longitudeTxt = findViewById(R.id.longitudeTxt);
        titleTxt = findViewById(R.id.saveTitleTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);



        // TODO: Fix this code
        // Retrieve coordinates for new location passed in the intent
        Bundle recievedData = getIntent().getExtras();

        if(recievedData != null){
            //newLocation.setLongitude(recievedData.getDouble("longitude"));
            //newLocation.setLatitude(recievedData.getDouble("latitude"));
        }

    }
}
