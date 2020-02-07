package com.seanmoylan.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.seanmoylan.myapplication.Classes.Location;
import com.seanmoylan.myapplication.Classes.Tools;

// Sean Moylan
// Partly adapted from https://developer.android.com/training/location

public class MapsActivity extends AppCompatActivity {

    private double latitude;
    private double longitude;

    private FusedLocationProviderClient fusedLocationClient;
    public Location lastLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        lastLocation = new Location();

        // Request Location
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<android.location.Location>() {
            @Override
            public void onSuccess(android.location.Location location) {
                if(location != null){
                    lastLocation.setLongitude(location.getLongitude());
                    lastLocation.setLatitude(location.getLatitude());
                }
                Tools.exceptionToast(getBaseContext(), "Problem loading location");
            }
        });

    }
    
}
