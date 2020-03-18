package com.seanmoylan.myapplication;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.seanmoylan.myapplication.Classes.Login;

public class CurrentLocationMap extends FragmentActivity implements OnMapReadyCallback {

    // UI elements
    private GoogleMap mMap;
    private Button saveBtn;
    private ImageButton gpsBtn;

    private FusedLocationProviderClient fusedLocationProviderClient;
    private LatLng currentLocation;

    //

    Retrofit retrofit;

    private static final int FINE_LOCATION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_location_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        saveBtn = findViewById(R.id.saveBtn);
        gpsBtn = findViewById(R.id.gpsBtn);


        // Check permission granted
        checkPermission(Manifest.permission.ACCESS_FINE_LOCATION, FINE_LOCATION_REQUEST_CODE);


        // Initialize fusedLocation
        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        currentLocation = new LatLng(location.getLatitude(), location.getLongitude());



        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Create an activity for the user to fill in details about the location they want to save
            }
        });



    }

    private void saveLocation(com.seanmoylan.myapplication.Classes.Location location) {
        // Returns true if Location was saved successfully
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:5000/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        FlaskAPI flaskApi = retrofit.create(FlaskAPI.class);
        Call<com.seanmoylan.myapplication.Classes.Location> call = flaskApi.createLocation(location);

        call.enqueue(new Callback<com.seanmoylan.myapplication.Classes.Location>() {
            @Override
            public void onResponse(Call<com.seanmoylan.myapplication.Classes.Location> call, Response<com.seanmoylan.myapplication.Classes.Location> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Sucessfully saved Location", Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onFailure(Call<com.seanmoylan.myapplication.Classes.Location> call, Throwable t) {

            }
        });


    }

    private void checkPermission(String permission, int requestCode) {

        String[] perm = new String[]{permission};


        if(ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED){
            // Request the permission
            ActivityCompat.requestPermissions(CurrentLocationMap.this, new String[]{permission}, FINE_LOCATION_REQUEST_CODE);

        } else {
            Toast.makeText(this, "Permission is already granted", Toast.LENGTH_LONG);
        }

    }


    private void updateUI(Location location) {

        LatLng local = new LatLng(location.getLatitude(), location.getLongitude());
        Log.e("Getting location", local.toString());
        mMap.addMarker(new MarkerOptions().position(local).title("Current Location"));
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        CameraUpdate location = CameraUpdateFactory.newLatLngZoom(currentLocation, 20);
        mMap.animateCamera(location);

        MarkerOptions marker = new MarkerOptions().title("Current Location");
        marker.draggable(true);
        marker.position(currentLocation);
        mMap.addMarker(marker);

    }

}

