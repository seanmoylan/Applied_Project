package com.seanmoylan.myapplication;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.content.pm.PackageManager;
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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.seanmoylan.myapplication.Classes.Location;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.seanmoylan.myapplication.Classes.Login;

import java.sql.Array;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Retrofit retrofit;

    LatLng myHouse;
    Location newLocation;

    // Android Location class
    android.location.Location myLocation;
    FusedLocationProviderClient mFusedLocationProviderClient;
    Boolean mLocationPermissionGranted;


    private List<Location> locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        // Check that the user has given permission to access location services
        checkLocationPermitions();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                loadLocations();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        //System.out.println(locations.toString());
        // List of locations





    }

    private void checkLocationPermitions() {
        // Check user permissions are all granted
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        LatLng sy = new LatLng(-35, 252);

        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker near sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sy));


    }

    private void loadLocations() {

        List<Location> list;
        // Send request to the server to compare credentials
        // Retrofit and Gson
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:5000/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        FlaskAPI flaskApi = retrofit.create(FlaskAPI.class);
        Call<List<Location>> call = flaskApi.getLocations();


        System.out.println(call.request());

        call.enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                if(response.isSuccessful()){
                    //locations = response.body();
                    saveLocations(response.body());
                }


            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Unable to load Locations!", Toast.LENGTH_LONG);
                System.out.println(t.getMessage());
            }
        });

        //System.out.println(locations.get(2));
    }

    private boolean saveLocations(List<Location> body) {
        if(body != null){
            locations = body;

            displayLocations();
            return true;
        }
        return false;
    }

    private void displayLocations() {

        for(Location loc : locations){
            Log.i("saveLocations", "Displaying locals");
            System.out.println(loc.toString());
            LatLng pos = new LatLng(loc.getLatitude(), loc.getLongitude());
            mMap.addMarker(new MarkerOptions().position(pos).title(loc.getTitle()));
            if(loc.getTitle().contains("NUIG")){
                CameraUpdate location = CameraUpdateFactory.newLatLngZoom(pos, 10);
                mMap.animateCamera(location);
            }
        }



    }
}
