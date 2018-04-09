package com.a2340.shelterhelper;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

@SuppressWarnings("ALL")
public class LandingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_screen);
        Button loginBtn = findViewById(R.id.btn_landing_login);
        final Activity currActivity = this;
        loginBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(currActivity, LoginActivity.class);
                startActivity(i);
            }
        });
        Button registerBtn = findViewById(R.id.btn_landing_register);
        registerBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(currActivity, RegisterActivity.class);
                startActivity(i);
            }
        });
    }
}
