package com.example.caroline.projet_android.model;

import java.lang.reflect.Type;

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

    public static TypeTournage of(String name){
         if (name.equals(TypeTournage.LONG_METRAGE.toString()))
             return TypeTournage.LONG_METRAGE;
         else if (name.equals(TypeTournage.SERIE.toString()))
             return TypeTournage.SERIE;
         else
             return TypeTournage.TELEFILM;
    }
}
