package com.seanmoylan.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.seanmoylan.myapplication.Classes.Location;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LocationsViewAdapter extends RecyclerView.Adapter<LocationsViewAdapter.MyViewHolder> {

    Context context;
    Location[] locations;

    public LocationsViewAdapter(Context ct, Location[] locations){
        this.context = ct;
        this.locations = locations;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(locations[position].getTitle());
        holder.description.setText(locations[position].getDescription());

    }

    @Override
    public int getItemCount() {
        return locations.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title, description;
        ImageView image;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            title = itemView.findViewById(R.id.titleTxt);
            description = itemView.findViewById(R.id.descriptionTxt);
            // TODO Can add images here
        }

    }

}
