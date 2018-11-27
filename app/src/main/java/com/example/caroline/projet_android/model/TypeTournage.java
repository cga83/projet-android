package com.example.caroline.projet_android.model;

// Création d'un enum pour le type de tournage
public enum TypeTournage {
    LONG_METRAGE("LONG METRAGE"),
    SERIE("SERIE TELEVISEE"),
    TELEFILM("TELEFILM");

    private final String name;

     TypeTournage(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    // Fonction utilisée pour la Maps
    // Lorsque l'on vérifie le type de chaque élément (marker)
    public static TypeTournage of(String name){
         if (name.equals(TypeTournage.LONG_METRAGE.toString()))
             return TypeTournage.LONG_METRAGE;
         else if (name.equals(TypeTournage.SERIE.toString()))
             return TypeTournage.SERIE;
         else
             return TypeTournage.TELEFILM;
    }
}
