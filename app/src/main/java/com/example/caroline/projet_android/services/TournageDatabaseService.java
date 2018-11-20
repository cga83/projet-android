package com.example.caroline.projet_android.services;

import com.example.caroline.projet_android.model.LieuxTournage;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.TypeConverters;

import java.util.List;


@Dao
//@TypeConverters(LieuxTournage.PositionConverter.class)
public interface TournageDatabaseService {

    @android.arch.persistence.room.Query("SELECT * FROM lieuxTournage")
    List<LieuxTournage> getAll();
}