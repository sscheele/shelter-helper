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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

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

        try {
            FileInputStream fis = new FileInputStream(new File(currActivity.getFilesDir(), "loginAttempts"));
            ObjectInputStream is = new ObjectInputStream(fis);
            //noinspection unchecked
            ArrayList<Instant> attempts = (ArrayList<Instant>) is.readObject();
            is.close();
            fis.close();

            if (attempts.size() == 3 && attempts.get(attempts.size() - 1).isAfter(Instant.now().minus(Duration.ofHours(1)))){
                Toast.makeText(LoginActivity.this, "You are locked out! Please wait an hour.", Toast.LENGTH_LONG).show();
                return;
            }
        } catch (IOException e) {
            //do nothing - this just means there have been no bad attempts
        } catch (ClassNotFoundException e) {
            Log.i("Login", "Could not deserialize from attempts file");
            return;
        }


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
                            ArrayList<Instant> attempts;
                            try {
                                FileInputStream fis = new FileInputStream(new File(currActivity.getFilesDir(), "loginAttempts"));
                                ObjectInputStream is = new ObjectInputStream(fis);
                                //noinspection unchecked
                                attempts = (ArrayList<Instant>) is.readObject();
                                is.close();
                                fis.close();
                            } catch (IOException e) {
                                attempts = new ArrayList<>();
                                Log.i("Login (failed attempt)", Log.getStackTraceString(e));
                            } catch (ClassNotFoundException e) {
                                Log.i("Login", "Could not deserialize from attempts file");
                                return;
                            }
                            attempts.add(Instant.now());
                            try {
                                FileOutputStream fos = new FileOutputStream(new File(currActivity.getFilesDir(), "loginAttempts"));
                                ObjectOutput os = new ObjectOutputStream(fos);
                                os.writeObject(attempts);
                                os.close();
                                fos.close();
                                if (attempts.size() == 3) {
                                    Toast.makeText(LoginActivity.this, "You are locked out! Please wait an hour.", Toast.LENGTH_LONG).show();
                                    finish();
                                }
                            } catch (IOException e) {
                                attempts = new ArrayList<>();
                                Log.i("Login (failed attempt)", Log.getStackTraceString(e));
                            }
                            //updateUI(null);
                        }
                    }
                });
    }
}



