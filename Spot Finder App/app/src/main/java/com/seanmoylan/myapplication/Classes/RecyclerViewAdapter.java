package com.seanmoylan.myapplication.Classes;

import android.content.Context;
import android.content.Intent;
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

    public RecyclerViewAdapter(Context mContext, ArrayList<String> mTitles, ArrayList<String> mDescriptions) {
        this.mTitles = mTitles;
        this.mDescriptions = mDescriptions;
        this.mContext = mContext;
    }

    private ArrayList<String> mTitles = new ArrayList<>();
    private ArrayList<String> mDescriptions = new ArrayList<>();
    private Context mContext;

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

        holder.title.setText(mTitles.get(position));
        holder.description.setText(mDescriptions.get(position));

        // TODO maybe render a layout when the item is clicked
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Clicked on: " + mTitles.get(position));
                Intent intent = new Intent(mContext, DisplayLocation.class);
                mContext.startActivity(intent);

                //TODO Pass location information to the DisplayLocation class
            }
        });

    }

    @Override
    public int getItemCount() {
        return mTitles.size();
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
