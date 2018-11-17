package com.example.caroline.projet_android.services;

import com.example.caroline.projet_android.model.LieuxTournage;
import com.example.caroline.projet_android.model.LieuxTournageRecords;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface TournageWebService {
//    @GET("records/1.0/{param}")
//    Call<List<LieuxTournage>> getAllTournages(@Path("param") String param);
    @GET
    Call<LieuxTournageRecords> getAllTournages(@Url String url);
}
