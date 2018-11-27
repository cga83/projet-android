package com.example.caroline.projet_android.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class LieuxTournageClusterItem implements ClusterItem {
    private double latitude;
    private double longitude;
    private String titre;
    private String snippet;
    private LieuxTournage lieux;

    // Création d'un ClusterItem à partir d'un LieuxTournage
    public LieuxTournageClusterItem(LieuxTournage lieuxTournage) {
        latitude = lieuxTournage.getX();
        longitude = lieuxTournage.getY();
        titre = lieuxTournage.getTitre();
        snippet = "Tournage réalisé par " + lieuxTournage.getRealisateur() +
                " à l'adresse " +lieuxTournage.getAdresse() + " ("
                + lieuxTournage.getArdt() + ") entre " + lieuxTournage.getDateDebut() + " et "
                + lieuxTournage.getDateFin() + ".";
        lieux = lieuxTournage;
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

    public LieuxTournage getLieux() {
        return lieux;
    }

    public void setLieux(LieuxTournage lieux) {
        this.lieux = lieux;
    }
}
