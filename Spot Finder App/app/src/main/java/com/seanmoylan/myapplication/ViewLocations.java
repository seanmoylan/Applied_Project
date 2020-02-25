package com.seanmoylan.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;

import com.seanmoylan.myapplication.Classes.Location;
import com.seanmoylan.myapplication.Classes.User;

import java.util.List;

public class ViewLocations extends AppCompatActivity {

    Retrofit retrofit;
    RecyclerView recyclerView;
    List<Location> locations;
    Location[] locals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_locations);

        // Get the locations and store them in a List
        getLocations();

        if(locations != null){

            locals = (Location[]) locations.toArray();
            System.out.println(locals[0]);
            recyclerView = findViewById(R.id.recyclerView);
            LocationsViewAdapter myAdapter = new LocationsViewAdapter(this, locals);
            recyclerView.setAdapter(myAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        // Initialize adapter class


    }

    public void getLocations(){
        // Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FlaskAPI flaskApi = retrofit.create(FlaskAPI.class);
        Call<List<Location>> call = flaskApi.getLocations();

        call.enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                System.out.println(response.code());
                if(response.isSuccessful()){
                    // Add locations to the list of locations
                    System.out.println(response.code());
                    locations = response.body();
                    System.out.println(locations.get(0));
                }

            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {
                System.out.println("Failed to load locations!");
                // TODO Add a Toast here so the user knows when this fails
            }
        });

        //System.out.println(call.request());

    }
}
