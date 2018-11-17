package com.example.caroline.projet_android.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class LieuxTournage {
    @SerializedName("type_de_tournage")
    private String typeDeTournage;
    @SerializedName("organisme_demandeur")
    private String organismeDemandeur;
    @SerializedName("adresse")
    private String adresse;
    @SerializedName("date_fin")
    private String dateFin;
    @SerializedName("realisateur")
    private String realisateur;
    @SerializedName("xy")
    private List<Double> xy = null;
    @SerializedName("ardt")
    private Integer ardt;
    @SerializedName("titre")
    private String titre;
    @SerializedName("date_debut")
    private String dateDebut;

    public LieuxTournage(){
        this.adresse = "";
        this.ardt = 0;
        this.realisateur = "";
        this.titre = "";
        this.dateDebut = "";
        this.organismeDemandeur = "";
        this.dateFin = "";
        this.typeDeTournage = "";
    }

    public LieuxTournage(String titre, String realisateur, String dateDebut, String dateFin, String adresse,
                         String orgaDemandeur, String typeTournage, int ardt, List<Double> xy) {
        this.adresse = adresse;
        this.ardt = ardt;
        this.realisateur = realisateur;
        this.titre = titre;
        this.dateDebut = dateDebut;
        this.organismeDemandeur = orgaDemandeur;
        this.dateFin = dateFin;
        this.typeDeTournage = typeTournage;
        this.xy = xy;
    }

    public String getTypeDeTournage() {
        return typeDeTournage;
    }

    public void setTypeDeTournage(String typeDeTournage) {
        this.typeDeTournage = typeDeTournage;
    }

    public String getOrganismeDemandeur() {
        return organismeDemandeur;
    }

    public void setOrganismeDemandeur(String organismeDemandeur) {
        this.organismeDemandeur = organismeDemandeur;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }

    public List<Double> getXy() {
        return xy;
    }

    public void setXy(List<Double> xy) {
        this.xy = xy;
    }

    public Integer getArdt() {
        return ardt;
    }

    public void setArdt(Integer ardt) {
        this.ardt = ardt;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

}
