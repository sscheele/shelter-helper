package com.a2340.shelterhelper;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sam on 3/28/18.
 */

@SuppressWarnings({"UtilityClass", "ChainedMethodCall"})
public class LocalUsers {
    private static List<User> allUsers = new ArrayList<>();
    private static User currentUser;

    public static void readInUsers(Context c) {
        try {
            FileInputStream fis = new FileInputStream(new File(c.getFilesDir(), "allUsers"));
            ObjectInputStream is = new ObjectInputStream(fis);
            allUsers = (ArrayList<User>) is.readObject();
            Log.i("Hmm", "Should be done");
            is.close();
            fis.close();
        } catch (Exception e) {
            Log.i("Yikes", Log.getStackTraceString(e));
        }
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static boolean tryLogin(String username, String password, EditText userBox, EditText passBox) {
        User match = findByUsername(username);
        if (match == null) {
            userBox.setBackgroundColor(Color.RED);
            return false;
        }
        if (!password.equals(match.getPassword())) {
            passBox.setBackgroundColor(Color.RED);
            return false;
        }
        currentUser = match;
        return true;
    }

    public static boolean register(Context c, User u) {
        allUsers.add(u);
        try {
            FileOutputStream fos = new FileOutputStream(new File(c.getFilesDir(), "allUsers"));
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(allUsers);
            os.close();
            fos.close();
        } catch (Exception e) {
            Log.i("Nooo", Log.getStackTraceString(e));
            return false;
        }

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
}
