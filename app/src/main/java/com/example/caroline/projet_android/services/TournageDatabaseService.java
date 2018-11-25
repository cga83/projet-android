package com.example.caroline.projet_android.services;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.caroline.projet_android.model.LieuxTournage;

import java.util.List;



@Dao
public interface TournageDatabaseService {

    @Query("SELECT * FROM lieuxTournage")
    List<LieuxTournage> getAll();

    @Query("SELECT * FROM lieuxTournage WHERE id = :id")
    LieuxTournage getLieu(int id);

    @Query("DELETE FROM lieuxTournage")
    void dropAll();

    @Insert
    void insert(LieuxTournage... lieuxTournages);

    @Delete
    void delete(LieuxTournage lieuxTournage);
}