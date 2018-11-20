package com.example.caroline.projet_android.services;

import com.example.caroline.projet_android.model.LieuxTournage;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import java.util.List;



@Dao
//@TypeConverters(LieuxTournage.PositionConverter.class)
public interface TournageDatabaseService {

    @Query("SELECT * FROM lieuxTournage")
    List<LieuxTournage> getAll();

    @Insert
    void insert(LieuxTournage... lieuxTournages);

    @Delete
    void delete(LieuxTournage lieuxTournage);
}