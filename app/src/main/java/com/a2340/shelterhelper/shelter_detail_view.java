package com.a2340.shelterhelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class shelter_detail_view extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_detail_view);
        Bundle b = getIntent().getExtras();
        Shelter s = (Shelter) b.get("shelter");
        ((TextView) findViewById(R.id.name_tv)).setText(s.name);
        ((TextView) findViewById(R.id.capacity_tv)).setText(s.capacity);
        ((TextView) findViewById(R.id.gender_tv)).setText(s.restrictions);
        ((TextView) findViewById(R.id.longitude_tv)).setText("" + s.longitude);
        ((TextView) findViewById(R.id.latitiude_tv)).setText("" + s.latitude);
        ((TextView) findViewById(R.id.address_tv)).setText(s.address);
        ((TextView) findViewById(R.id.phone_tv)).setText(s.phone);
    }
}
