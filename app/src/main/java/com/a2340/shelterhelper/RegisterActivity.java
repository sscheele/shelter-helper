package com.a2340.shelterhelper;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

@SuppressWarnings("ALL")
/**
 * a method to create RegisterActivity
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView nameView;
    private TextView usernameView;
    private TextView passView;
    final Activity currActivity = this;

    private FirebaseAuth mAuth;
    private static final String TAG = "RegisterActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button registerBtn = findViewById(R.id.btn_register);
        registerBtn.setOnClickListener(this);

        nameView = findViewById(R.id.register_tb_name);
        usernameView = findViewById(R.id.register_tb_username);
        passView = findViewById(R.id.register_tb_password);

        mAuth = FirebaseAuth.getInstance();
        //final Activity currActivity = this;
    }

    public void onClick(View v) {



        final String name = nameView.getText().toString();
        final String username = usernameView.getText().toString();
        final String password = passView.getText().toString();
        final boolean isAdmin = ((RadioButton) findViewById(R.id.register_rb_isAdmin))
                .isChecked();

        Log.d(TAG, "createAccount:" + username);

        mAuth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            User newUser = new User(name, username, password, isAdmin);
                            LocalUsers.register(currActivity, newUser);
                            Toast.makeText(RegisterActivity.this, "Successfully registered",
                                    Toast.LENGTH_SHORT).show();
                            sendEmailVerification();
                            finish();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Registration failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
    }

    private void sendEmailVerification() {
         // Send verification email
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this,
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            //Log.e(TAG, "sendEmailVerification", task.getException());
                            Toast.makeText(RegisterActivity.this,
                                    "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}


