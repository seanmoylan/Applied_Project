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
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.seanmoylan.myapplication.Classes.Location;
import com.seanmoylan.myapplication.Classes.RecyclerViewAdapter;
import com.seanmoylan.myapplication.Classes.Tools;
import com.seanmoylan.myapplication.Classes.User;

import java.util.ArrayList;
import java.util.List;

public class ViewLocations extends AppCompatActivity {
    private static final String TAG = "ViewLocations";

    // Variables
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    private List<Location> locations;
    private ArrayList<String> titles = new ArrayList<>();
    private ArrayList<String> descriptions = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_locations);
        Log.d(TAG, "onCreate: started");

        // Get the locations and store them in a List
        initLocations();
    }

    // Makes the request to the server for all locations
    public void initLocations(){
        // Retrofit
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:5000/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        FlaskAPI flaskApi = retrofit.create(FlaskAPI.class);
        Call<List<Location>> call = flaskApi.getLocations();


        System.out.println(call.request());

        call.enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                if(response.isSuccessful()){
                    //locations = response.body();
                    storeLocations(response.body());
                }


            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Unable to load Locations!", Toast.LENGTH_LONG);
                System.out.println(t.getMessage());
            }
        });


    }

    // If the server request is successful then this method is a=called and the locations are stored in an List
    private boolean storeLocations(List<Location> body) {
        if(body != null){
            locations = body;

            // Add the title and description from each location to their own arrayList so they can be passed to the RecyclerView
            for(Location loc : locations){
                titles.add(loc.getTitle());
                descriptions.add(loc.getDescription());
            }
            initRecyclerView();
            Log.d(TAG, "storeLocations: Locations stored");
            return true;
        }
        return false;
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: started");

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new RecyclerViewAdapter(this, titles, descriptions );
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
