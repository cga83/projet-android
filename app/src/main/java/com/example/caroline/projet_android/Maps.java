package com.example.caroline.projet_android;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.example.caroline.projet_android.model.LieuxTournage;
import com.example.caroline.projet_android.services.AppDatabase;
import com.example.caroline.projet_android.services.TournageDatabaseService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class Maps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<LieuxTournage> lieuxTournages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //On récupère les données dans la database
        TournageDatabaseService tournageDatabaseService = AppDatabase.getAppDatabase(this).getTournagesDatabaseService();
        lieuxTournages.clear();
        lieuxTournages = tournageDatabaseService.getAll();

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
        // Ajout d'un InfoWindowAdapter car les snippets ne tenaient pas sur une ligne
        mMap.setInfoWindowAdapter(new CustomWindowInfoAdapter(Maps.this));

        final Button button = (findViewById(R.id.button_filters));

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final ArrayList<Integer> mSelectedItems = new ArrayList<>();
                CharSequence[] mPossibleItems = new CharSequence[3];
                mPossibleItems[0] = "TELEFILM";
                mPossibleItems[1] = "LONG METRAGE";
                mPossibleItems[2] = "SERIE TELEVISEE";
                AlertDialog.Builder builder = new AlertDialog.Builder(Maps.this); //Read Update
                builder.setTitle("Choisis un filtre !")
                        .setMessage("Quel type de tournage souhaites-tu voir sur la carte ?")
                        .setMultiChoiceItems(mPossibleItems, null, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if (isChecked) {
                                    // If the user checked the item, add it to the selected items
                                    mSelectedItems.add(which);
                                } else if (mSelectedItems.contains(which)) {
                                    // Else, if the item is already in the array, remove it
                                    mSelectedItems.remove(Integer.valueOf(which));
                                }
                            }
                        })
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // TO DO
                            }
                        })
                        .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // TO DO
                            }
                        });




                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        if (lieuxTournages.size()>0) {
            // Ajout des markers
            LatLng markerParis = new LatLng(48.861391, 2.334044);

            for (LieuxTournage lieux : lieuxTournages) {
                if (lieux.getXySize()>0) // Certains films n'ont pas de position associée
                {
                    if (lieux.getTypeDeTournage().equals("LONG METRAGE"))
                        mMap.addMarker(new MarkerOptions()
                                .position(new LatLng(lieux.getX(), lieux.getY()))
                                .icon(BitmapDescriptorFactory
                                        .defaultMarker(0))
//                                .icon(BitmapDescriptorFactory
//                                        .defaultMarker(BitmapDescriptorFactory.HUE_RED))
                                .title(lieux.getTitre())
                                .snippet("Tournage réalisé par " + lieux.getRealisateur() +
                                        " à l'adresse " +lieux.getAdresse() + " ("
                                        + lieux.getArdt() + ") entre " + lieux.getDateDebut() + " et "
                                        + lieux.getDateFin() + "."));
                    else if (lieux.getTypeDeTournage().equals("TELEFILM"))
                        mMap.addMarker(new MarkerOptions()
                                .position(new LatLng(lieux.getX(), lieux.getY()))
                                .icon(BitmapDescriptorFactory
                                        .defaultMarker(50))
                                //.icon(BitmapDescriptorFactory
                                //        .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                                .title(lieux.getTitre())
                                .snippet("Tournage réalisé par " + lieux.getRealisateur() +
                                        " à l'adresse " +lieux.getAdresse() + " ("
                                        + lieux.getArdt() + ") entre " + lieux.getDateDebut() + " et "
                                        + lieux.getDateFin() + "."));
                    else if (lieux.getTypeDeTournage().equals("SERIE TELEVISEE"))
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(lieux.getX(), lieux.getY()))
                            .icon(BitmapDescriptorFactory
                                    .defaultMarker(25))
                            //.icon(BitmapDescriptorFactory
                            //        .defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                            .title(lieux.getTitre())
                            .snippet("Tournage réalisé par " + lieux.getRealisateur() +
                                    " à l'adresse " +lieux.getAdresse() + " ("
                                    + lieux.getArdt() + ") entre " + lieux.getDateDebut() + " et "
                                    + lieux.getDateFin() + "."));
                }
            }

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markerParis, 14));
        }
    }

}


