package com.a2340.shelterhelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

//import com.google.firebase.FirebaseApp;
//import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {
    private ShelterAdapter listAdapter;
    public static ArrayList<Shelter> shelters;

    @Nullable
    public static String searchQuery = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context currContext = this;

        final ListView shelterListView = (ListView) findViewById(R.id.listview_shelter);
        //final ArrayList<String> shelterNames = new ArrayList<>();
        //noinspection AssignmentToStaticFieldFromInstanceMethod
        shelters = new ArrayList<Shelter>();
        listAdapter = new ShelterAdapter(this, shelters);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("shelters");
        mDatabase.keepSynced(true);
        mDatabase.orderByChild("name").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Shelter newShelter = dataSnapshot.getValue(Shelter.class);
                shelters.add(newShelter);
                listAdapter.notifyDataSetChanged();
                Log.i("SHELTADD", "" + newShelter.key);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Shelter changedShelter = dataSnapshot.getValue(Shelter.class);
                for (int i = 0; i < shelters.size(); i++) {
                    if (shelters.get(i).name.equals(changedShelter.name)) {
                        shelters.set(i, changedShelter);
                        listAdapter.notifyDataSetChanged();
                        return;
                    }
                }
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

        shelterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent next = new Intent(currContext, shelter_detail_view.class);
                next.putExtra("shelter", ((Shelter) adapterView.getItemAtPosition(i)));
                startActivity(next);
            }
        });
        shelterListView.setAdapter(listAdapter);

        final Activity currActivity = this;
        Button logoutBtn = (Button) findViewById(R.id.btn_logout);
        logoutBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(currActivity, LandingScreen.class);
                startActivity(i);
                //finish();
            }
        });

        Button searchBtn = (Button) findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(currActivity, SearchActivity.class);
                startActivity(i);
            }
        });

        Button mapBtn = (Button) findViewById(R.id.btn_map);
        mapBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(currActivity, MapsActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        if (MainActivity.searchQuery != null) {
            listAdapter.getFilter().filter(MainActivity.searchQuery);
            //noinspection AssignmentToStaticFieldFromInstanceMethod
            MainActivity.searchQuery = null;
        }
    }
}
