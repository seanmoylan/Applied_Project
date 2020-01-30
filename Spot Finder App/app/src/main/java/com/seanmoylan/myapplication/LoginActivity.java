package com.seanmoylan.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.seanmoylan.myapplication.Classes.Login;
import com.seanmoylan.myapplication.Classes.User;

public class LoginActivity extends AppCompatActivity {

    // Create variables needed to extract from UI
    EditText username = findViewById(R.id.usernameText);
    EditText password = findViewById(R.id.passwordText);
    Button loginButton = findViewById(R.id.loginBtn);
    TextView registerText = findViewById(R.id.registerText);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Any activity that is needed here
        final Intent regIntent = new Intent(this, RegisterActivity.class);


        // Set a click listener for the login btn
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Validate and Login user

                Login u = new Login(username.getText().toString(), password.getText().toString());
                loginUser(u);
            }
        });

        // If user isn't registered then send them to the register page
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to go to the register page
                startActivity(regIntent);
            }
        });
    }

    private void loginUser(Login u){
        // Send request to the server to compare credentials
    }

}
