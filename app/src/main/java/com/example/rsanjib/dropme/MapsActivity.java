package com.example.rsanjib.dropme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the cameragit . In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // Get request gps location


        //Respond to Intent
        Intent intent = getIntent();
        // Get data via the key
        Double longitude = intent.getDoubleExtra("latitude", 100);
        Double latitude = intent.getDoubleExtra("longitude", -100);

        // Add a marker in KU and move the camera
        LatLng client = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(client).title("Your Marker"));
        Marker m1 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(27.622093, 85.552056))
                .anchor(0.5f, 0.5f)
                .title("Waiting a ride")
                .snippet("Mahesh"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(client));
        mMap.setMinZoomPreference(15.0f);

        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(Marker marker) {

                try {

                    Intent mapIntent = new Intent(MapsActivity.this, ChooseUser.class);
                    MapsActivity.this.startActivity(mapIntent);

                } catch (ArrayIndexOutOfBoundsException e) {
                    Log.e("ArrayIndexOutOfBounds", " Occured");
                }

            }
        });

    }

}
