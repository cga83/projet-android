package com.example.caroline.projet_android.model;

import java.util.ArrayList;

public class LieuxTournage {
    private String titre;
    private String realisateur;
    private int annee;
    private String dateDebut;
    private String dateFin;
    private String adresse;
    private String orgaDemandeur;
    private String typeTournage;
    private int ardt;
    private ArrayList<Double> position;

    public LieuxTournage(){
        this.adresse = "";
        this.annee = 0;
        this.ardt = 0;
        this.realisateur = "";
        this.titre = "";
        this.dateDebut = "";
        this.orgaDemandeur = "";
        this.dateFin = "";
        this.typeTournage = "";
        this.position = new ArrayList<>(2);
        this.position.add(0.0);
        this.position.add(0.0);
    }

    public LieuxTournage(String titre, String realisateur, int annee, String dateDebut, String dateFin, String adresse,
                         String orgaDemandeur, String typeTournage, int ardt, ArrayList position) {
        this.adresse = adresse;
        this.annee = annee;
        this.ardt = ardt;
        this.realisateur = realisateur;
        this.titre = titre;
        this.dateDebut = dateDebut;
        this.orgaDemandeur = orgaDemandeur;
        this.dateFin = dateFin;
        this.typeTournage = typeTournage;
        this.position = position;
    }

    public String getTitre() {
        return titre;
    }

    public int getAnnee() {
        return annee;
    }

    public String getTypeTournage() {
        return typeTournage;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getArdt() {
        return ardt;
    }

    public String getOrgaDemandeur() {
        return orgaDemandeur;
    }

    public String getRealisateur() {
        return realisateur;
    }
}
