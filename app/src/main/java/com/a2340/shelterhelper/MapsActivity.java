package com.a2340.shelterhelper;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

@SuppressWarnings("ALL")
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        GoogleMap mMap = googleMap;

        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/

        if (MainActivity.searchQuery == null) {
            for (int i = 0; i < MainActivity.shelters.size(); i++) {
                LatLng shelterLocation = new LatLng(MainActivity.shelters.get(i).latitude,
                        MainActivity.shelters.get(i).longitude);
                mMap.addMarker(new MarkerOptions().position(shelterLocation).
                        title(MainActivity.shelters.get(i).name + " "
                                + MainActivity.shelters.get(i).phone));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(shelterLocation));
            }
        } else {
            for (int i = 0; i < ShelterAdapter.filteredArrList.size(); i++) {
                LatLng shelterLocation = new LatLng(ShelterAdapter.filteredArrList.get(i).
                        latitude, ShelterAdapter.filteredArrList.get(i).longitude);
                mMap.addMarker(new MarkerOptions().position(shelterLocation).
                        title(ShelterAdapter.filteredArrList.get(i).name + " "
                                + ShelterAdapter.filteredArrList.get(i).phone));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(shelterLocation));
            }
        }
    }
}
