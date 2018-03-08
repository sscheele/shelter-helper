package com.a2340.shelterhelper;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView shelterListView = (ListView) findViewById(R.id.listview_shelter);
        final ArrayList<String> shelterNames = new ArrayList<>();
        final ArrayList<Shelter> shelters = new ArrayList<>();
        listAdapter = new ArrayAdapter<String>(this, R.layout.shelter_list_item, R.id.shelter_list_item_text, shelterNames);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.orderByChild("name").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Shelter newShelter = dataSnapshot.getValue(Shelter.class);
                listAdapter.add(newShelter.name);
                shelters.add(newShelter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        shelterListView.setAdapter(listAdapter);

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
