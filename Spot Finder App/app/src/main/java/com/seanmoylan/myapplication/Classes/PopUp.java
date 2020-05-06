package com.seanmoylan.myapplication.Classes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.TextView;

import com.seanmoylan.myapplication.R;


public class PopUp extends Activity {

    private String title, description;
    private TextView titleTxt, descriptionTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pop_up);

        // Strings to be initialized


        // Text views
        titleTxt = findViewById(R.id.titleTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);

        // Get data passed in the intent then populate textFields
        getIntentData();
        titleTxt.setText(title);
        descriptionTxt.setText(description);

        // Use display metric to get screen metrics
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.dimAmount = 0.82f;

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        // Will display the popup window to 80% of the screen
        getWindow().setLayout((int) (width * 0.6) ,(int) (height*0.3));
    }

    private void getIntentData() {
        Bundle data = getIntent().getExtras();
        title = data.getString("title");
        description = data.getString("description");
    }
}
