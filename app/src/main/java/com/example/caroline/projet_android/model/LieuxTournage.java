package com.example.caroline.projet_android.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

import java.util.List;


@Entity(tableName = "lieuxTournage")

public class LieuxTournage {
    @PrimaryKey
    private int id;

//    @SerializedName("xy")
//    private List<Double> xy = null;
    @SerializedName("titre")
    private String titre;
    @SerializedName("realisateur")
    private String realisateur;
    @SerializedName("date_debut")
    private String dateDebut;
    @SerializedName("date_fin")
    private String dateFin;
    @SerializedName("adresse")
    private String adresse;
    @SerializedName("organisme_demandeur")
    private String orgaDemandeur;
    @SerializedName("type_de_tournage")
    private String typeTournage;
    @SerializedName("ardt")
    private int ardt;

    public LieuxTournage(){
        this.adresse = "";
        this.ardt = 0;
        this.realisateur = "";
        this.titre = "";
        this.dateDebut = "";
        this.orgaDemandeur = "";
        this.dateFin = "";
        this.typeTournage = "";
    }

    public LieuxTournage(String titre, String realisateur, String dateDebut, String dateFin, String adresse,
                         String orgaDemandeur, String typeTournage, int ardt, List<Double> xy) {
        this.adresse = adresse;
        this.ardt = ardt;
        this.realisateur = realisateur;
        this.titre = titre;
        this.dateDebut = dateDebut;
        this.orgaDemandeur = orgaDemandeur;
        this.dateFin = dateFin;
        this.typeTournage = typeTournage;
//        this.xy = xy;
    }

    public String getTitre() {
        return titre;
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

    public String getDateDebut() {
        return dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public void setTitre(String fields) {
        this.titre = fields;
    }

    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setOrgaDemandeur(String orgaDemandeur) {
        this.orgaDemandeur = orgaDemandeur;
    }

    public void setTypeTournage(String typeTournage) {
        this.typeTournage = typeTournage;
    }

    public void setArdt(int ardt) {
        this.ardt = ardt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public List<Double> getXy() {
//        return xy;
//    }
//
//    public void setXy(List<Double> xy) {
//        this.xy = xy;
//    }
}
