package com.seanmoylan.myapplication.Classes;

public class Location {
    double latitude;
    double longitude;
    String title;
    String description;
    Type type;

    public void Location(){

    }
    /*  Used to create a location and populate it with
        lat and long.
     */
    public void Location(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void Location(double latitude, double longitude, String title, String type ){
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
        this.type = Type.valueOf(type);
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


}
