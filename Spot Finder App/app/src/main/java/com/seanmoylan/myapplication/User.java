package com.seanmoylan.myapplication;

public class User {
    int id;
    String name;
    String email;
    String password;
    float latitude;
    float longitude;

    public void User(){

    }

    public void User(int id, String name, String password, String email, float latitude, float longitude){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
