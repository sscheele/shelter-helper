package com.a2340.shelterhelper;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

@SuppressWarnings({"ChainedMethodCall", "LawOfDemeter"})
public class shelter_detail_view extends AppCompatActivity {
    private DatabaseReference mDatabase;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_detail_view);
        Bundle b = getIntent().getExtras();
        Shelter s = (Shelter) b.get("shelter");
        ((TextView) findViewById(R.id.name_tv)).setText(s.name);
        ((TextView) findViewById(R.id.capacity_tv)).setText("" + s.capacity);
        ((TextView) findViewById(R.id.gender_tv)).setText(s.restrictions);
        ((TextView) findViewById(R.id.address_tv)).setText(s.address);
        ((TextView) findViewById(R.id.phone_tv)).setText(s.phone);

        Button reserveBtn = findViewById(R.id.reserve_btn);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        reserveBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LocalUsers.getCurrentUser().getSpotsReserved() != 0) {
                    return;
                }
                Bundle b = getIntent().getExtras();
                Shelter s = (Shelter) b.get("shelter");
                int i = Integer.parseInt(((EditText) findViewById(R.id.reserve_number_box)).getText().toString());
                if (s.capacity < i) {
                    return;
                }
                s.capacity -= i;
                s.registered = s.registered.substring(0, s.registered.length() - 1) + ", " + LocalUsers.getCurrentUser() + "]";
                mDatabase.child("" + s.key).setValue(s);
                LocalUsers.getCurrentUser().setReservedBedAt(s.key);
                LocalUsers.getCurrentUser().setSpotsReserved(i);
            }
        });

        Button releaseBtn = findViewById(R.id.release_bed_btn);
        releaseBtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Bundle b = getIntent().getExtras();
                Shelter s = (Shelter) b.get("shelter");
                s.capacity += LocalUsers.getCurrentUser().getSpotsReserved();
                LocalUsers.getCurrentUser().setSpotsReserved(0);
                LocalUsers.getCurrentUser().setReservedBedAt(-1);
                mDatabase.child("" + s.key).setValue(s);
            }
        });
    }


}
