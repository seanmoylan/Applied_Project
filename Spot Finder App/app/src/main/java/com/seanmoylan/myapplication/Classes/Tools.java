package com.seanmoylan.myapplication.Classes;

import android.content.Context;
import android.widget.Toast;

public class Tools {
    // Used for generating a toast when needed throughout the application
    public static void exceptionToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
