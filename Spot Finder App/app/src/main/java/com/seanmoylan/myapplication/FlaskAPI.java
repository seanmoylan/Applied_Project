package com.seanmoylan.myapplication;

import com.seanmoylan.myapplication.Classes.Location;
import com.seanmoylan.myapplication.Classes.Login;
import com.seanmoylan.myapplication.Classes.User;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FlaskAPI {

    @GET("/users")
    Call<User> getUser();

    @GET("/locations")
    Call<Location> getLocations();


    @POST("/users/<user>")
    Call<User> createUser();



}
