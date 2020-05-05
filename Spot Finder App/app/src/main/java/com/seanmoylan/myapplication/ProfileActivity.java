package com.seanmoylan.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {


    TextView username;
    TextView password;
    Button viewMap;
    Button viewSavedLocations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Show that user has logged in
        Toast.makeText(this, ("The user that logged in was " + getIntent().getStringExtra("username")),Toast.LENGTH_LONG).show();

        username = findViewById(R.id.usernameText);
        password = findViewById(R.id.passwordText);
        viewMap = findViewById(R.id.viewLocations);
        viewSavedLocations = findViewById(R.id.viewMyLocations);


        username.setText(getIntent().getStringExtra("username"));
        password.setText(getIntent().getStringExtra("password"));

        viewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
            }
        });

        viewSavedLocations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Implement this later to display a given users saves
                //startActivity(new Intent(getApplicationContext(), MapsActivity.class));
            }
        });



    }
}
