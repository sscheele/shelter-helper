package com.a2340.shelterhelper;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

/**
 * Created by sam on 3/7/18.
 */

@SuppressLint("Registered")
@SuppressWarnings({"ChainedMethodCall", "DefaultFileTemplate"})
class Initialization extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this, new FirebaseOptions.Builder()
                .setDatabaseUrl("https://shelter-helper.firebaseio.com")
                .build()
        );
        Intent i = new Intent(this, LandingScreen.class);
        startActivity(i);
    }
}
