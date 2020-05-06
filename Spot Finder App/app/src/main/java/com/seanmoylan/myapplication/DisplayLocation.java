package com.seanmoylan.myapplication;

import androidx.fragment.app.FragmentActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.seanmoylan.myapplication.Classes.Location;

public class DisplayLocation extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = "DisplayLocation";

    private GoogleMap mMap;
    private Location displayLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Try style map to dark mode - R.raw.style_json
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.style_json));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }
        getIntentData();
    }

    private void getIntentData() {
        Bundle data = getIntent().getExtras();

        displayLoc = new Location();
        displayLoc.setTitle(data.getString("title"));
        displayLoc.setDescription(data.getString("description"));
        displayLoc.setLatitude(data.getDouble("latitude"));
        displayLoc.setLongitude(data.getDouble("longitude"));

        // Add a marker in Sydney and move the camera
        LatLng loc = new LatLng(displayLoc.getLatitude(), displayLoc.getLongitude());
        mMap.addMarker(new MarkerOptions().position(loc).title(displayLoc.getTitle()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        final CameraUpdate location = CameraUpdateFactory.newLatLngZoom(loc, 15);

        mMap.animateCamera(location);
        Log.d(TAG, "onMapReady: received location info" + data.getString("title"));

    }
}
