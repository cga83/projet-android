package com.example.caroline.projet_android.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class LieuxTournageClusterItem implements ClusterItem {
    private double latitude;
    private double longitude;
    private String titre;
    private String snippet;

    // Création d'un ClusterItem à partir d'un LieuxTournage
    public LieuxTournageClusterItem(LieuxTournage lieuxTournage) {
        latitude = lieuxTournage.getX();
        longitude = lieuxTournage.getY();
        titre = lieuxTournage.getTitre();
        snippet = "Tournage réalisé par " + lieuxTournage.getRealisateur() +
                " à l'adresse " +lieuxTournage.getAdresse() + " ("
                + lieuxTournage.getArdt() + ") entre " + lieuxTournage.getDateDebut() + " et "
                + lieuxTournage.getDateFin() + ".";
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
        return titre;
    }

    @Override
    public String getSnippet() {
        return snippet;
    }
}
