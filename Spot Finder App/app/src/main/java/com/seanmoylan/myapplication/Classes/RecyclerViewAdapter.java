package com.seanmoylan.myapplication.Classes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.seanmoylan.myapplication.DisplayLocation;
import com.seanmoylan.myapplication.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<Location> locals = new ArrayList<>();
    private Context mContext;


    public RecyclerViewAdapter(Context mContext, ArrayList<Location> locals) {
        //this.mTitles = mTitles;
        //this.mDescriptions = mDescriptions;
        this.locals = locals;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");

        final Location loc = locals.get(position);

        holder.title.setText(loc.getTitle());
        holder.description.setText(loc.getDescription());

        // When the user clicks on one of the cards, the location will be displayed on a map
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG, "onClick: Clicked on: " + loc.getTitle());
                // Intent to display location on map that the user selected
                Intent intent = new Intent(mContext, DisplayLocation.class);

                // Bundle that passes the info of the location to display
                Bundle location = new Bundle();
                location.putDouble("latitude", loc.getLatitude());
                location.putDouble("longitude", loc.getLongitude());
                location.putString("title", loc.getTitle());
                location.putString("description", loc.getDescription());

                intent.putExtras(location);

                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return locals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title;
        TextView description;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.titleTxt);
            description = itemView.findViewById(R.id.descriptionTxt);
            parentLayout = itemView.findViewById(R.id.relativeLayout);

        }
    }
}
