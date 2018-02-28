package com.a2340.shelterhelper;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button registerBtn = (Button) findViewById(R.id.btn_register);
        final Activity currActivity = this;
        registerBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ((EditText) findViewById(R.id.register_tb_name)).getText().toString();
                String username = ((EditText) findViewById(R.id.register_tb_username)).getText().toString();
                String password = ((EditText) findViewById(R.id.register_tb_password)).getText().toString();
                boolean isAdmin = ((RadioButton) findViewById(R.id.register_rb_isAdmin)).isChecked();
                User newUser = new User(name, username, password, isAdmin);
                User.register(newUser);
            }
        });
    }
}
