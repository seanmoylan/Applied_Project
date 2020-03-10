package com.seanmoylan.myapplication;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
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

public class CurrentLocationMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LatLng currentLocation;

    private static final int FINE_LOCATION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_location_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Check permission granted
        checkPermission(Manifest.permission.ACCESS_FINE_LOCATION, FINE_LOCATION_REQUEST_CODE);


        // Initialize fusedLocation
        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        currentLocation = new LatLng(location.getLatitude(), location.getLongitude());





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

