package com.example.caroline.projet_android;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.example.caroline.projet_android.model.LieuxTournage;
import com.example.caroline.projet_android.model.LieuxTournageClusterItem;
import com.example.caroline.projet_android.model.LongMetrageClusterRender;
import com.example.caroline.projet_android.model.SerieClusterRender;
import com.example.caroline.projet_android.model.TelefilmClusterRender;
import com.example.caroline.projet_android.services.AppDatabase;
import com.example.caroline.projet_android.services.TournageDatabaseService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Maps extends FragmentActivity implements OnMapReadyCallback,
        ClusterManager.OnClusterClickListener<LieuxTournageClusterItem>,
        ClusterManager.OnClusterItemClickListener<LieuxTournageClusterItem>,
        ClusterManager.OnClusterItemInfoWindowClickListener<LieuxTournageClusterItem> {

    private GoogleMap mMap;
    private List<LieuxTournage> lieuxTournages = new ArrayList<>();
    private boolean[] mSelectedItems = new boolean[3]; // ce tableau contient les filtres cochés
    private ClusterManager<LieuxTournageClusterItem> clusterManagerLongMetrage;
    private ClusterManager<LieuxTournageClusterItem> clusterManagerTelefilm;
    private ClusterManager<LieuxTournageClusterItem> clusterManagerSerie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //On récupère les données dans la database
        TournageDatabaseService tournageDatabaseService = AppDatabase.getAppDatabase(this).getTournagesDatabaseService();
        lieuxTournages.clear();
        lieuxTournages = tournageDatabaseService.getAll();

        // Au départ, tous les items sont cochés
        mSelectedItems[0] = true;
        mSelectedItems[1] = true;
        mSelectedItems[2] = true;

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
        // On positionne la carte au centre de Paris
        LatLng markerParis = new LatLng(48.861391, 2.334044);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markerParis, 14));

        final Button button = (findViewById(R.id.button_filters));

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CharSequence[] mPossibleItems = new CharSequence[3];
                mPossibleItems[0] = "Téléfilm";
                mPossibleItems[1] = "Long Métrage";
                mPossibleItems[2] = "Série Télévisée";
                AlertDialog.Builder builder = new AlertDialog.Builder(Maps.this); //Read Update
                builder.setTitle("Choisis un filtre !")
                        .setMultiChoiceItems(mPossibleItems, mSelectedItems, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if (isChecked) {
                                    // If the user checked the item, add it to the selected items
                                    mSelectedItems[which] = true;
                                } else {
                                    // Else, if the item is already in the array, remove it
                                    mSelectedItems[which] =  false;
                                }
                            }
                        })
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                addMarkersToMap();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        // Initialisation du cluster manager
        clusterManagerLongMetrage = new ClusterManager<LieuxTournageClusterItem>(this, googleMap);
        LongMetrageClusterRender rendererLongMetrage = new LongMetrageClusterRender(this, googleMap, clusterManagerLongMetrage);
        clusterManagerTelefilm = new ClusterManager<LieuxTournageClusterItem>(this, googleMap);
        TelefilmClusterRender rendererTelefilm = new TelefilmClusterRender(this, googleMap, clusterManagerTelefilm);
        clusterManagerSerie = new ClusterManager<LieuxTournageClusterItem>(this, googleMap);
        SerieClusterRender rendererSerie = new SerieClusterRender(this, googleMap, clusterManagerSerie);


        // Ajout des markers
        clusterManagerLongMetrage.setRenderer(rendererLongMetrage);
        clusterManagerTelefilm.setRenderer(rendererTelefilm);
        clusterManagerSerie.setRenderer(rendererSerie);
        addMarkersToMap();

        // Ajout des action listener
        mMap.setOnCameraIdleListener(clusterManagerLongMetrage);
        mMap.setOnMarkerClickListener(clusterManagerLongMetrage);
        mMap.setOnCameraIdleListener(clusterManagerTelefilm);
        mMap.setOnMarkerClickListener(clusterManagerTelefilm);
        mMap.setOnCameraIdleListener(clusterManagerSerie);
        mMap.setOnMarkerClickListener(clusterManagerSerie);
        clusterManagerLongMetrage.setOnClusterClickListener(this);
        clusterManagerLongMetrage.setOnClusterItemClickListener(this);
        clusterManagerLongMetrage.setOnClusterItemInfoWindowClickListener(this);
        clusterManagerTelefilm.setOnClusterClickListener(this);
        clusterManagerTelefilm.setOnClusterItemClickListener(this);
        clusterManagerTelefilm.setOnClusterItemInfoWindowClickListener(this);
        clusterManagerSerie.setOnClusterClickListener(this);
        clusterManagerSerie.setOnClusterItemClickListener(this);
        clusterManagerSerie.setOnClusterItemInfoWindowClickListener(this);

        clusterManagerLongMetrage.getMarkerCollection()
                .setOnInfoWindowAdapter(new CustomWindowInfoAdapter(Maps.this));
        mMap.setInfoWindowAdapter(clusterManagerLongMetrage.getMarkerManager());
        clusterManagerTelefilm.getMarkerCollection()
                .setOnInfoWindowAdapter(new CustomWindowInfoAdapter(Maps.this));
        mMap.setInfoWindowAdapter(clusterManagerTelefilm.getMarkerManager());
        clusterManagerSerie.getMarkerCollection()
                .setOnInfoWindowAdapter(new CustomWindowInfoAdapter(Maps.this));
        mMap.setInfoWindowAdapter(clusterManagerSerie.getMarkerManager());

    }

    private void addMarkersToMap() {
        mMap.clear();
        for (LieuxTournage lieux : lieuxTournages) {
            if (lieux.getXySize()>0) // Certains films n'ont pas de position associée
            {
                if (lieux.getTypeDeTournage().equals("LONG METRAGE") && mSelectedItems[1])
                    clusterManagerLongMetrage.addItem(new LieuxTournageClusterItem(lieux));
                else if (lieux.getTypeDeTournage().equals("TELEFILM") && mSelectedItems[0])
                    clusterManagerTelefilm.addItem(new LieuxTournageClusterItem(lieux));
                else if (lieux.getTypeDeTournage().equals("SERIE TELEVISEE") && mSelectedItems[2])
                    clusterManagerSerie.addItem(new LieuxTournageClusterItem(lieux));
            }
        }
        if (clusterManagerTelefilm!=null) clusterManagerTelefilm.cluster();
        if (clusterManagerLongMetrage!=null) clusterManagerLongMetrage.cluster();
        if (clusterManagerSerie!=null) clusterManagerSerie.cluster();
    }

    @Override
    public boolean onClusterClick(Cluster<LieuxTournageClusterItem> cluster) {
        LatLngBounds.Builder builder = LatLngBounds.builder();
        Collection<LieuxTournageClusterItem> lieuxTournagesMarkers = cluster.getItems();

        for (ClusterItem item : lieuxTournagesMarkers) {
            LatLng lieuxTournagePosition = item.getPosition();
            builder.include(lieuxTournagePosition);
        }

        final LatLngBounds bounds = builder.build();

        try { mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
        } catch (Exception error) {
            System.err.println(error);
        }

        return true;
    }

    @Override
    public boolean onClusterItemClick(LieuxTournageClusterItem lieuxTournageClusterItem) {
        return false;
    }

    @Override
    public void onClusterItemInfoWindowClick(LieuxTournageClusterItem lieuxTournageClusterItem) {

    }
}



