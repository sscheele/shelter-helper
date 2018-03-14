package com.a2340.shelterhelper;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Button searchBtn = (Button) findViewById(R.id.search);

        searchBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nameBox = (EditText) findViewById(R.id.editName);
                String searchName = nameBox.getText().toString();

                EditText ageBox = (EditText) findViewById(R.id.editAge);
                String searchAge = ageBox.getText().toString();

                EditText genderBox = (EditText) findViewById(R.id.editGender);
                String searchGender = genderBox.getText().toString();

                if (!searchName.isEmpty()) {
                    MainActivity.searchQuery = searchName;
                } else if (!searchAge.isEmpty()) {
                    MainActivity.searchQuery = searchAge;
                } else if (!searchGender.isEmpty()) {
                    MainActivity.searchQuery = searchGender;
                }

                finish();
            }
        });


    }
}
