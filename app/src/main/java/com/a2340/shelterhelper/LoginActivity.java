package com.a2340.shelterhelper;

import android.app.Activity;
import android.content.Intent;
//import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

@SuppressWarnings("ALL")
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    final Activity currActivity = this;
    private static final String TAG = "LoginActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button loginBtn = findViewById(R.id.btn_login);
        loginBtn.setOnClickListener(this);
        //EditText userBox = (EditText) findViewById(R.id.tb_username);

        mAuth = FirebaseAuth.getInstance();


        LocalUsers.readInUsers(this);
    }

    @Override
    public void onClick(View v) {
        EditText userBox = findViewById(R.id.tb_username);
        String username = userBox.getText().toString();
        EditText passBox = findViewById(R.id.tb_password);
        String password = passBox.getText().toString();

        /*if (!LocalUsers.tryLogin(username, password, userBox, passBox)) {
            return;
        }*/

        mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user.isEmailVerified()) {
                                Intent i = new Intent(currActivity, MainActivity.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(LoginActivity.this, "Please verify email first",
                                        Toast.LENGTH_SHORT).show();
                            }
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
    }
}



