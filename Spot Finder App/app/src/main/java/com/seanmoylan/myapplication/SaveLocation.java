package com.seanmoylan.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.seanmoylan.myapplication.Classes.Location;

public class SaveLocation extends AppCompatActivity {

    private TextInputEditText latitudeTxt;
    private TextInputEditText longitudeTxt;
    private TextInputEditText titleTxt;
    private TextInputEditText descriptionTxt;
    private Button cancelBtn;
    private Button saveBtn;

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
        if(populateCoordinates()){
            latitudeTxt.setText(newLocation.getLatitude().toString());
            longitudeTxt.setText(newLocation.getLongitude().toString());
        }
    }

    private boolean populateCoordinates() {
        Bundle dataRecieved = getIntent().getExtras();

        if (dataRecieved != null)
        {
            newLocation.setLatitude(dataRecieved.getDouble("latitude"));
            newLocation.setLongitude(dataRecieved.getDouble("longitude"));
            return true;
        }

        return false;
    }
}
