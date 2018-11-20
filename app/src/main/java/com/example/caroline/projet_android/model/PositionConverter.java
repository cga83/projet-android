package com.example.caroline.projet_android.model;

import android.arch.persistence.room.TypeConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PositionConverter {

    @TypeConverter
    public  List<Double> storedStringToPosition(String value) {
        List<String> stringPositions = Arrays.asList(value.split("\\s*,\\s*"));
        List<Double> positions = new ArrayList<>();
        for (String pos :
                stringPositions) {
            positions.add(Double.parseDouble(pos));
        }
        return positions;
    }

    @TypeConverter
    public String positionsToStoredString( List<Double> cl) {
        String value = "";

        if (cl!=null){
            for (double pos : cl)
                value += pos + ",";
        }

        return value;
    }
}
