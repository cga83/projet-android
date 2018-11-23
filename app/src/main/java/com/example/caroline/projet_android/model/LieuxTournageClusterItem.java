package com.example.caroline.projet_android.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class LieuxTournageClusterItem implements ClusterItem {
    private double latitude;
    private double longitude;

    public LieuxTournageClusterItem(LieuxTournage lieuxTournage) {
        latitude = lieuxTournage.getX();
        longitude = lieuxTournage.getY();
    }

    public LieuxTournageClusterItem(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public LatLng getPosition() {
        return new LatLng(latitude, longitude);
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getSnippet() {
        return null;
    }
}
