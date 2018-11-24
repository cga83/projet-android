package com.example.caroline.projet_android.model;

import java.io.Serializable;

public class ElementListMovie implements Serializable {
    private String titre;
    private Integer ardt;
    private String adresse;
    private String typeTournage;

    public ElementListMovie(){
        this.ardt = 75000;
        this.titre = "new";
        this.adresse = "rue";
        this.typeTournage = "TELEFILM";
    }

    public ElementListMovie(String titre, Integer ardt, String adresse, String typeTournage){
        this.ardt = ardt;
        this.titre = titre;
        this.adresse = adresse;
        this.typeTournage = typeTournage;
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
}
