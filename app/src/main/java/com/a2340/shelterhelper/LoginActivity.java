package com.a2340.shelterhelper;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button loginBtn = (Button) findViewById(R.id.btn_login);
        final Activity currActivity = this;
        loginBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userBox = (EditText) findViewById(R.id.tb_username);
                String username = userBox.getText().toString();
                EditText passBox = (EditText) findViewById(R.id.tb_password);
                String password = passBox.getText().toString();
                User match = User.findByUsername(username);
                if (match == null) {
                    userBox.setBackgroundColor(Color.RED);
                    return;
                }
                if (!password.equals(match.getPassword())) {
                    passBox.setBackgroundColor(Color.RED);
                    return;
                }
                Intent i = new Intent(currActivity, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
