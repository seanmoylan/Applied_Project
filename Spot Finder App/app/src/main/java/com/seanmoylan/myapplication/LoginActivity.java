package com.seanmoylan.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.seanmoylan.myapplication.Classes.Login;
import com.seanmoylan.myapplication.Classes.Tools;
import com.seanmoylan.myapplication.Classes.User;

public class LoginActivity extends AppCompatActivity {

    // Create variables needed to extract from UI
    EditText usernameTxt;
    EditText passwordTxt;
    Button loginButton;
    TextView registerTxt;


    Boolean loggedIn = false;

    // Retrofit used with api
    Retrofit retrofit;



    // TODO : Check that the user has allowed permissions

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameTxt = (EditText) findViewById(R.id.usernameText);
        passwordTxt = findViewById(R.id.passwordText);
        loginButton = findViewById(R.id.loginBtn);
        registerTxt = findViewById(R.id.registerText);

        // Any activity that is needed here
        Intent regIntent = new Intent(this, RegisterActivity.class);
        Intent profileIntent = new Intent(this, ProfileActivity.class);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission_group.LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission_group.LOCATION},
                    1);
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission_group.LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission


                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }else {
            // Permission was already granted.
            System.out.println("Permissions already granted");
        }





        // Set a click listener for the login btn
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Login user = new Login();
                user.setUsername(usernameTxt.getText().toString());
                user.setPassword(passwordTxt.getText().toString());


                loginUser(user);
                // TODO: Validate and Login user
                // Login u = new Login(username.getText().toString(), password.getText().toString());
                // loginUser(u);
            }
        });

        // If user isn't registered then send them to the register page
        registerTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to go to the register page
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });
    }



    private boolean loginUser(Login u) {


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
        Call<Login> call = flaskApi.userLogin(u);


        System.out.println(call.request());


        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                System.out.println("Code: "+response.code());
                if(!response.isSuccessful()){
                    System.out.println("Code: " + response.code());
                    loggedIn = true;
                    return;
                }


                Login u = response.body();
                Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();

                // If login is successfull then create an intent for the
                // profile activity and pass it the username that logged in
                Intent loadUserProfile = new Intent(getApplicationContext(), ProfileActivity.class);
                loadUserProfile.putExtra("username", u.getUsername());
                loadUserProfile.putExtra("password", u.getPassword());
                startActivity(loadUserProfile);

            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                System.out.println("Request failed");
                System.out.println();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });



        return loggedIn;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
