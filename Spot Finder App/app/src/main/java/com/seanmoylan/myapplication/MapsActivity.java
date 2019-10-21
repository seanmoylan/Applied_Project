package com.seanmoylan.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MapsActivity extends AppCompatActivity {

    // Location service client
    private FusedLocationProviderClient locationProvider;
    private Location myLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        locationProvider = LocationServices.getFusedLocationProviderClient(this);
        locationProvider.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if(location == null){
                    System.out.println("Did not get location");
                }else{
                    myLocation = location;
                    System.out.println("Latitude = "+myLocation.getLatitude()+" Longitude = "+myLocation.getLongitude());
                }
            }
        });


    }
}
