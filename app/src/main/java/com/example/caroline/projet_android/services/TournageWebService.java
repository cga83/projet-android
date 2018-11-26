package com.example.caroline.projet_android.services;

import com.example.caroline.projet_android.model.LieuxTournageRecords;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface TournageWebService {
    @GET
    Call<LieuxTournageRecords> getAllTournages(@Url String url);
}
