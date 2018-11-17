package com.example.caroline.projet_android.services;

import com.example.caroline.projet_android.model.LieuxTournage;

import android.arch.persistence.room.Dao;

import java.util.List;

import retrofit2.http.Query;

@Dao
public interface TournageDatabaseService {

    @android.arch.persistence.room.Query("SELECT * FROM lieuxTournage")
    List<LieuxTournage> getAll();
}
