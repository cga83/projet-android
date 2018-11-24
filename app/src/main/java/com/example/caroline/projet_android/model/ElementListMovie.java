package com.example.caroline.projet_android.model;

public class ElementListMovie {
    private String titre;
    private String annee;

    public ElementListMovie(){
        this.annee = "0";
        this.titre = "new";
    }

    public ElementListMovie(String titre, String annee){
        this.annee = annee;
        this.titre = titre;
    }

    public String getAnnee() {
        return annee;
    }

    public String getTitre() {
        return titre;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

}
