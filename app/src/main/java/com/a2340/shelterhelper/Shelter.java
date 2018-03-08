package com.a2340.shelterhelper;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

/**
 * Created by sam on 3/7/18.
 */
@IgnoreExtraProperties
public class Shelter implements Serializable {
    public String address;
    public String capacity;
    public double latitude;
    public double longitude;
    public String phone;
    public int key;
    public String name;
    public String notes;
    public String restrictions;

    public Shelter() {

    }

    public Shelter(String address, String capacity, double latitude, double longitude, String phone, int key, String name, String notes, String restrictions) {
        this.address = address;
        this.capacity = capacity;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phone = phone;
        this.key = key;
        this.name = name;
        this.notes = notes;
        this.restrictions = restrictions;
    }
}
