package com.a2340.shelterhelper;

import android.content.Context;
import android.graphics.Color;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sam on 2/27/18.
 */

public class User implements Serializable {
    private String name;
    private String username;
    private String password;
    private boolean isAdmin;
    private int reservedBedAt = -1;
    private int spotsReserved = 0;

    public void setSpotsReserved(int spotsReserved) {
        this.spotsReserved = spotsReserved;
    }

    public int getSpotsReserved() {
        return spotsReserved;
    }

    User(String name, String username, String password, boolean isAdmin) {
        this.name = name;
        this.password = password;
        this.username = username;
        this.isAdmin = isAdmin;
    }



    public void setReservedBedAt(int reserved) {
        reservedBedAt = reserved;
    }

    public int getReservedBedAt() {
        return reservedBedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

}
