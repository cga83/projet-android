package com.example.caroline.projet_android.model;

import android.arch.persistence.room.TypeConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Pour stocker les coordonnées dans la base de donnée locale, nous avons eu besoin de passer par un TypeConverter
// Il n'est pas possible de stocker un tableau de double directement dans la base de donnée
// On convertit donc le tableau de double en une String (de type "x,y") pour le stockage
public class PositionConverter {
    @TypeConverter
    public  List<Double> storedStringToPosition(String value) {
        List<String> stringPositions = Arrays.asList(value.split("\\s*,\\s*"));
        List<Double> positions = new ArrayList<>();
        // On convertit la String si elle n'est pas nulle ou vide
        for (String pos : stringPositions) {
            if (pos!=null && !pos.equals(""))
                positions.add(Double.parseDouble(pos));
        }
        return positions;
    }

    @TypeConverter
    public String positionsToStoredString( List<Double> cl) {
        String value = "";
        // Certains films n'ont pas de coordonnées
        // On le vérifie avant
        if (cl!=null){
            for (double pos : cl)
                value += pos + ",";
        }
        return value;
    }
}
