package com.example.caroline.projet_android;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.caroline.projet_android.model.LieuxTournage;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Maps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<LieuxTournage> lieuxTournages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Bundle bundle = getIntent().getExtras();
        //On extrait les données…
        if(bundle!=null)
        {
            System.out.println("test");
            lieuxTournages = (ArrayList<LieuxTournage>) bundle.getSerializable("TOURNAGES");
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_tournages);
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
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        // Ajout des markers
        LatLng premierMarker = new LatLng(lieuxTournages.get(0).getX(), lieuxTournages.get(0).getY());

        for (LieuxTournage lieux: lieuxTournages) {
            System.err.println(lieux.getTitre());
            if (lieux.getXy() != null) // Certains films n'ont pas de position associée
                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(lieux.getX(), lieux.getY()))
                        .title(lieux.getTitre()));
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(premierMarker, 12));
    }

}


