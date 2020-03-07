package com.seanmoylan.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.seanmoylan.myapplication.Classes.Login;
import com.seanmoylan.myapplication.Classes.User;

public class RegisterActivity extends AppCompatActivity {

    EditText usernameTxt;
    EditText emailTxt;
    EditText passwordTxt;
    EditText confPasswordTxt;
    TextView loginText;
    Button registerBtn;

    Retrofit retrofit;

    Toast userNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameTxt = findViewById(R.id.regUsernameText);
        passwordTxt = findViewById(R.id.regPasswordText);
        confPasswordTxt = findViewById(R.id.regConfirmPasswordText);
        loginText = findViewById(R.id.regSignIn);
        registerBtn = findViewById(R.id.signupBtn);

        //TODO Need to POST to the API and add a new user to the database

        // Any activity that is needed here
        final Intent loginIntent = new Intent(this, LoginActivity.class);

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(loginIntent);
            }
        });


        final Intent profileIntent = new Intent(this, ProfileActivity.class);

        final User user = buildUser();


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(createUser(user)){
                    startActivity(profileIntent);
                }
            }
        });
    }

    private User buildUser() {
        User user = new User();

        try {
            user.setUsername(usernameTxt.getText().toString());
            user.setEmail(emailTxt.getText().toString());
            user.setPassword(passwordTxt.getText().toString());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


        return user;
    }

    private Boolean createUser(User user){



        // Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FlaskAPI flaskApi = retrofit.create(FlaskAPI.class);
        Call<User> call = flaskApi.createUser(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful()){
                    System.out.println("Code: " + response.code());
                    System.out.println("Successfully created user");
                    User newUser = response.body();

                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println("Didnt work");
            }
        });




        return true;

    }
}
