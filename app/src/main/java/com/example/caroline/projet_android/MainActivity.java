package com.example.caroline.projet_android;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.caroline.projet_android.model.LieuxTournage;
import com.example.caroline.projet_android.model.LieuxTournageRecord;
import com.example.caroline.projet_android.model.LieuxTournageRecords;
import com.example.caroline.projet_android.services.AppDatabase;
import com.example.caroline.projet_android.services.TournageDatabaseService;
import com.example.caroline.projet_android.services.TournageWebService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String BASE_URL = "opendata.paris.fr";
    private static final int CREATE_MAPS_ACTIVITY = 1;

    private ArrayList<LieuxTournage> lieuxTournages = new ArrayList<>();

    Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://opendata.paris.fr")
            .build();

    TournageWebService tournageWebService = retrofit.create(TournageWebService.class);

    TournageDatabaseService tournageDatabaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("url", BASE_URL);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tournageDatabaseService = AppDatabase.getAppDatabase(this).getTournagesDatabaseService();

        if (tournageDatabaseService.getAll().size()>0) {
            loadLieuxTournagesFromDatabase();
        } else {
            loadLieuxTournagesFromServer();
        }

        final Button button = (findViewById(R.id.button_enter_app));

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Maps.class);
                startActivity(intent);
            }
        });
    }

    private void loadLieuxTournagesFromServer() {
        lieuxTournages.clear();
        Uri.Builder uri = new Uri.Builder();
        uri.scheme("https")
                .authority(BASE_URL)
                .appendPath("api")
                .appendPath("records")
                .appendPath("1.0")
                .appendPath("search")
                .appendQueryParameter("dataset", "tournagesdefilmsparis2011")
                //.appendQueryParameter("rows", "2805") // Chargement de toutes les données
               .appendQueryParameter("rows", "700")
                .appendQueryParameter("facet", "realisateur")
                .appendQueryParameter("facet", "organisme_demandeur")
                .appendQueryParameter("facet","type_de_tournage")
                .appendQueryParameter("facet","ardt");
        tournageWebService.getAllTournages(uri.build().toString())
                .enqueue(new Callback<LieuxTournageRecords>() {
                    @Override
                    public void onResponse(@NonNull Call<LieuxTournageRecords> call,
                                           @NonNull Response<LieuxTournageRecords> response) {
                        LieuxTournageRecords serverTournages = response.body();
                        if (serverTournages != null) {
                            for (LieuxTournageRecord record :
                                    serverTournages.getTournages()) {
                                lieuxTournages.add(record.getFields());
                                tournageDatabaseService.insert(record.getFields());
                            }
                            System.out.println("done");
                            //TODO : displayTournagesList();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<LieuxTournageRecords> call,
                                          @NonNull Throwable t) {
                        Toast.makeText(MainActivity.this, "Nous n'avons pas pu chargé les lieux de tournages.", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void loadLieuxTournagesFromDatabase() {
        lieuxTournages.clear();
        List<LieuxTournage> dbLieuxTournages = tournageDatabaseService.getAll();

        if (dbLieuxTournages != null) {
            lieuxTournages.addAll(dbLieuxTournages);
        }
    }

}
