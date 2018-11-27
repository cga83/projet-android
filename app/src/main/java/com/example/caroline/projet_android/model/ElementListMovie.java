package com.example.caroline.projet_android.model;

import java.io.Serializable;

public class ElementListMovie implements Serializable {
    //Classe qui contient les element à afficher dans la liste des lieu de tournage et leur id

    private String titre;
    private Integer ardt;
    private String adresse;
    private String typeTournage; // Nous permet d'afficher la bonne couleur de logo en fonction du type de tournage
    private int id;

    public ElementListMovie(){
        this.ardt = 75000;
        this.titre = "new";
        this.adresse = "rue";
        this.typeTournage = "TELEFILM";
        this.id = 0;
    }

    public ElementListMovie(String titre, Integer ardt, String adresse, String typeTournage, int id){
        this.ardt = ardt;
        this.titre = titre;
        this.adresse = adresse;
        this.typeTournage = typeTournage;
        this.id = id;
    }

    public Integer getArdt() { return ardt; }

    public String getTitre() {
        return titre;
    }

    public void setArdt(Integer ardt) {
        this.ardt = ardt;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAdresse() { return adresse; }

    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getTypeTournage() { return typeTournage; }

    public void setTypeTournage(String typeTournage) { this.typeTournage = typeTournage; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }
}
