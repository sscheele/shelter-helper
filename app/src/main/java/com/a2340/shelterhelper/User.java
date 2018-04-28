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

@SuppressWarnings("DefaultFileTemplate")
public class User implements Serializable {
    private String name;
    private String username;
    private String password;
    private boolean isAdmin;
    private int reservedBedAt = -1;
    private int spotsReserved = 0;

    /**
     * a setter method for spotsReserved
     * @param spotsReserved a int for spotsReserved
     */
    public void setSpotsReserved(int spotsReserved) {
        this.spotsReserved = spotsReserved;
    }

    /**
     * getter for spotsReserved
     * @return returns spotsReserved
     */
    public int getSpotsReserved() {
        return spotsReserved;
    }

    User(String name, String username, String password, boolean isAdmin) {
        this.name = name;
        this.password = password;
        this.username = username;
        this.isAdmin = isAdmin;
    }

    /**
     * setter method for setReserved
     * @param reserved an integer for the number of beds reserved
     */

    public void setReservedBedAt(int reserved) {
        reservedBedAt = reserved;
    }

    /**
     * a getter method for reservedbed
     * @return returns reservedBedAt
     */
    public int getReservedBedAt() {
        return reservedBedAt;
    }

    /**
     * getter for name
     * @return returns the name
     */
    public String getName() {
        return name;
    }

    /**
     * setter for user
     * @param name name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter for the username
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * setter for username
     * @param username the username of the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * getter for password
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * setter for the password
     * @param password the password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * a method for isAdmin
     * @return whether isAdmin is true or false
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * setter for admin
     * @param admin either true or false
     */
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

}
