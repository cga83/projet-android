package com.example.caroline.projet_android;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.caroline.projet_android.model.LieuxTournage;
import com.example.caroline.projet_android.model.LieuxTournageClusterItem;
import com.example.caroline.projet_android.model.LongMetrageClusterRender;
import com.example.caroline.projet_android.model.SerieClusterRender;
import com.example.caroline.projet_android.model.TelefilmClusterRender;
import com.example.caroline.projet_android.model.TypeTournage;
import com.example.caroline.projet_android.services.AppDatabase;
import com.example.caroline.projet_android.services.TournageDatabaseService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.MarkerManager;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Maps extends Fragment implements OnMapReadyCallback,
        ClusterManager.OnClusterClickListener<LieuxTournageClusterItem>,
        ClusterManager.OnClusterItemClickListener<LieuxTournageClusterItem>,
        ClusterManager.OnClusterItemInfoWindowClickListener<LieuxTournageClusterItem> {

    private MapView mMapView;
    private GoogleMap mMap;
    private List<LieuxTournage> lieuxTournages = new ArrayList<>();
    boolean[] mSelectedItems = new boolean[3]; // ce tableau contient les filtres cochés
    private EnumMap<TypeTournage, ClusterManager<LieuxTournageClusterItem>> clusterMap = new EnumMap<>(TypeTournage.class);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_maps, null, false);
        mMapView = view.findViewById(R.id.map_tournages);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        try {
            MapsInitializer.initialize((getActivity().getApplicationContext()));
        } catch(Exception e) {
            e.printStackTrace();
        }
        mMapView.getMapAsync(this);

        //On récupère les données dans la database
        TournageDatabaseService tournageDatabaseService = AppDatabase.getAppDatabase(getActivity().getApplicationContext()).getTournagesDatabaseService();
        lieuxTournages.clear();
        lieuxTournages = tournageDatabaseService.getAll();

        // Au départ, tous les items sont cochés
        mSelectedItems[0] = true;
        mSelectedItems[1] = true;
        mSelectedItems[2] = true;

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_tournages);
//        mapFragment.getMapAsync(this);
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
        mMap.setInfoWindowAdapter(new CustomWindowInfoAdapter(getActivity().getApplicationContext()));
        // On positionne la carte au centre de Paris
        LatLng markerParis = new LatLng(48.861391, 2.334044);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markerParis, 14));

        final Button button = (getView().findViewById(R.id.button_filters));

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CharSequence[] mPossibleItems = new CharSequence[3];
                mPossibleItems[0] = "Téléfilm";
                mPossibleItems[1] = "Long Métrage";
                mPossibleItems[2] = "Série Télévisée";
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext()); //Read Update
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

        MarkerManager globalMarkerManager = new MarkerManager(mMap);

        // Initialisation du cluster manager
        for (TypeTournage type : TypeTournage.values()){
            ClusterManager<LieuxTournageClusterItem> clusterManager = new ClusterManager<>(getActivity().getApplicationContext(), googleMap, globalMarkerManager);
            switch (type){
                case SERIE:
                    clusterManager.setRenderer(new SerieClusterRender(getActivity().getApplicationContext(), googleMap, clusterManager));
                    break;
                case TELEFILM:
                    clusterManager.setRenderer(new TelefilmClusterRender(getActivity().getApplicationContext(), googleMap, clusterManager));
                    break;
                case LONG_METRAGE:
                    clusterManager.setRenderer(new LongMetrageClusterRender(getActivity().getApplicationContext(), googleMap, clusterManager));
                    break;
            }
            clusterManager.setOnClusterClickListener(this);
            clusterManager.setOnClusterItemClickListener(this);
            clusterManager.setOnClusterItemInfoWindowClickListener(this);
            clusterManager.getMarkerCollection()
                    .setOnInfoWindowAdapter(new CustomWindowInfoAdapter(getActivity().getApplicationContext()));
            clusterMap.put(type, clusterManager);
        }


        // Ajout des markers
        addMarkersToMap();

        // Ajout des action listener
        mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                addMarkersToMap();
            }
        });

        mMap.setOnMarkerClickListener(globalMarkerManager);
        mMap.setInfoWindowAdapter(globalMarkerManager);;

    }

    private void addMarkersToMap() {
        mMap.clear();
        mMap.clear();
        for (ClusterManager cluster : clusterMap.values()) {
            cluster.clearItems();
        }
        for (LieuxTournage lieux : lieuxTournages) {
            if (lieux.getXySize()>0) // Certains films n'ont pas de position associée
            {
                if (lieux.getTypeDeTournage().equals(TypeTournage.LONG_METRAGE.toString()) && mSelectedItems[1])
                    clusterMap.get(TypeTournage.of(lieux.getTypeDeTournage())).addItem(new LieuxTournageClusterItem(lieux));
                else if (lieux.getTypeDeTournage().equals(TypeTournage.TELEFILM.toString()) && mSelectedItems[0])
                    clusterMap.get(TypeTournage.of(lieux.getTypeDeTournage())).addItem(new LieuxTournageClusterItem(lieux));
                else if (lieux.getTypeDeTournage().equals(TypeTournage.SERIE.toString())&& mSelectedItems[2])
                    clusterMap.get(TypeTournage.of(lieux.getTypeDeTournage())).addItem(new LieuxTournageClusterItem(lieux));
            }
        }
        for (ClusterManager cluster : clusterMap.values()) {
                cluster.cluster();
        }
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

        try { mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 90));
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



