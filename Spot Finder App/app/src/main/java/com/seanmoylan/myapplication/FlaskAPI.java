package com.seanmoylan.myapplication;

import com.seanmoylan.myapplication.Classes.Location;
import com.seanmoylan.myapplication.Classes.Login;
import com.seanmoylan.myapplication.Classes.User;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FlaskAPI {

    @GET("/users")
    Call<User> getUser();

    @POST("/users/login")
    Call<Login> userLogin(@Body Login user);

    @GET("/locations")
    Call<List<Location>> getLocations();

    @GET("/location")
    Call<Location> getLocation(@Body String id);

    @POST("/locations/create")
    Call<Location> createLocation(@Body Location location);


    @POST("/users/create")
    Call<User> createUser(@Body User user);



}
