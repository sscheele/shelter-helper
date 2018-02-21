package com.a2340.shelterhelper;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Activity currActivity = this;
        Button logoutBtn = (Button) findViewById(R.id.btn_logout);
        logoutBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(currActivity, LandingScreen.class);
                startActivity(i);
                //finish();
            }
        });
    }
}
