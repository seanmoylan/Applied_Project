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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Show that user has logged in
        Toast.makeText(this, ("The user that logged in was " + getIntent().getStringExtra("username")),Toast.LENGTH_LONG).show();

        username = findViewById(R.id.usernameText);
        password = findViewById(R.id.passwordText);
        viewMap = findViewById(R.id.viewLocations);

        username.setText(getIntent().getStringExtra("username"));
        password.setText(getIntent().getStringExtra("password"));

        viewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapsActivity = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(mapsActivity);
            }
        });


    }
}
