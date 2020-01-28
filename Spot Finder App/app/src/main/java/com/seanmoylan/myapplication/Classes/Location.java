package com.seanmoylan.myapplication.Classes;

public class Location {
    String latitude;
    String longitude;
    String title;
    String description;
    Type type;

    public void Location(){

    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
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

    public void Location(String latitude, String longitude, String title, String type ){
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
        this.type = Type.valueOf(type);
    }
}
