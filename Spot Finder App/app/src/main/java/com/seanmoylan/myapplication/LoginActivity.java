package com.seanmoylan.myapplication;

import androidx.appcompat.app.AppCompatActivity;
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

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.seanmoylan.myapplication.Classes.Login;
import com.seanmoylan.myapplication.Classes.Tools;
import com.seanmoylan.myapplication.Classes.User;

public class LoginActivity extends AppCompatActivity {

    // Create variables needed to extract from UI
    EditText usernameTxt;
    EditText passwordTxt;
    Button loginButton;
    TextView registerTxt;

    // Retrofit used with api
    Retrofit retrofit;

    // Create an instance of the FlaskAPI interface
    CompositeDisposable dump;
    Login response;


    // TODO : Check that the user has allowed permissions

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameTxt = (EditText) findViewById(R.id.usernameText);
        passwordTxt = findViewById(R.id.passwordText);
        loginButton = findViewById(R.id.loginBtn);
        registerTxt = findViewById(R.id.registerText);

        // Get string from text views
        final String username = usernameTxt.getText().toString();
        final String password = passwordTxt.getText().toString();


        // Any activity that is needed here
        final Intent regIntent = new Intent(this, MapsActivity.class);




        // Set a click listener for the login btn
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Login user = new Login();
                user.setUsername(username);
                user.setPassword(password);

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
                startActivity(regIntent);
            }
        });
    }

    private void loginUser(Login u) {
        // Send request to the server to compare credentials

        // Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FlaskAPI flaskApi = retrofit.create(FlaskAPI.class);
        Call<User> call = flaskApi.getUser();
        Call<User> call2 = flaskApi.createUser();

        System.out.println(call.request());


        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                System.out.println("Code: "+response.code());
                if(!response.isSuccessful()){
                    System.out.println("Code: " + response.code());
                    return;
                }

                User u = response.body();
                System.out.println(u.toString());
                usernameTxt.setText(u.getUsername());
                passwordTxt.setText(u.getPassword());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println("Request failed");
                System.out.println(t.getMessage());
            }
        });


    }

}
