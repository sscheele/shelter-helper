package com.a2340.shelterhelper;

import java.util.ArrayList;

/**
 * Created by sam on 2/27/18.
 */

public class User {
    private String name;
    private String username;
    private String password;
    private boolean isAdmin;
    private static ArrayList<User> allUsers = new ArrayList<>();

    User(String name, String username, String password, boolean isAdmin) {
        this.name = name;
        this.password = password;
        this.username = username;
        this.isAdmin = isAdmin;
    }

    public static boolean register(User u) {
        allUsers.add(u);
        return true;
    }

    public static User findByUsername(String username) {
        for (User u : allUsers) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
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
