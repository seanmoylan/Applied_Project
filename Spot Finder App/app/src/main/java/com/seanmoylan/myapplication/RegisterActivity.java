package com.seanmoylan.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    EditText confPassword;
    TextView loginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.regUsernameText);
        password = findViewById(R.id.regPasswordText);
        confPassword = findViewById(R.id.regConfirmPasswordText);
        loginText = findViewById(R.id.regSignIn);

        // Any activity that is needed here
        final Intent loginIntent = new Intent(this, LoginActivity.class);

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(loginIntent);
            }
        });
    }
}
