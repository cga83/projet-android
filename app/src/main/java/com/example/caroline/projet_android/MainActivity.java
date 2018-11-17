package com.example.caroline.projet_android;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.caroline.projet_android.model.LieuxTournage;
import com.example.caroline.projet_android.model.LieuxTournageRecord;
import com.example.caroline.projet_android.model.LieuxTournageRecords;
import com.example.caroline.projet_android.services.TournageWebService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String BASE_URL = "opendata.paris.fr";

    private ArrayList<LieuxTournage> lieuxTournages = new ArrayList<>();

    Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://opendata.paris.fr")
            .build();

    TournageWebService tournageWebService = retrofit.create(TournageWebService.class);

//    TournageDatabaseService tournageDatabaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("url", BASE_URL);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Call<LieuxTournageRecords> lieux = tournageWebService.getAllTournages("search");

//        tournageDatabaseService = com.example.caroline.projet_android.services.AppDatabase.getAppDatabase(this)
//                .getTournagesDatabaseService();

        loadLieuxTournagesFromServer();
    }

    private void loadLieuxTournagesFromServer() {
        lieuxTournages.clear();
        Uri.Builder yri = new Uri.Builder();
        yri.scheme("https")
                .authority(BASE_URL)
                .appendPath("api")
                .appendPath("records")
                .appendPath("1.0")
                .appendPath("search")
                .appendQueryParameter("dataset", "tournagesdefilmsparis2011")
                .appendQueryParameter("rows", "2805")
                .appendQueryParameter("facet", "realisateur")
                .appendQueryParameter("facet", "organisme_demandeur")
                .appendQueryParameter("facet","type_de_tournage")
                .appendQueryParameter("facet","ardt");
        tournageWebService.getAllTournages(yri.build().toString())
                .enqueue(new Callback<LieuxTournageRecords>() {
                    @Override
                    public void onResponse(@NonNull Call<LieuxTournageRecords> call,
                                           @NonNull Response<LieuxTournageRecords> response) {
                        LieuxTournageRecords serverTournages = response.body();
                        if (serverTournages != null) {
                            for (LieuxTournageRecord record :
                                    serverTournages.getTournages()) {
                                lieuxTournages.add(record.getFields());
                            }
                            System.out.println("done");
                            //TODO : displayTournagesList();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<LieuxTournageRecords> call,
                                          @NonNull Throwable t) {
                        Toast.makeText(MainActivity.this, "Nous n'avons pas pu chargé les lieux de tournages.", Toast.LENGTH_LONG)
                                .show();
                    }
                });
    }

}
