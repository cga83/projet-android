package com.example.caroline.projet_android;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.caroline.projet_android.fragment.InfoFragment;
import com.example.caroline.projet_android.fragment.InfoFragmentInterface;
import com.example.caroline.projet_android.fragment.MovieListFragment;
import com.example.caroline.projet_android.fragment.MovieListFragmentInterface;
import com.example.caroline.projet_android.fragment.MoviesMapFragment;
import com.example.caroline.projet_android.fragment.MoviesMapFragmentInterface;
import com.example.caroline.projet_android.fragment.WelcomeScreenFragment;
import com.example.caroline.projet_android.fragment.WelcomeScreenFragmentInterface;
import com.example.caroline.projet_android.model.ElementListMovie;
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

public class MainActivity extends AppCompatActivity implements MovieListFragmentInterface, WelcomeScreenFragmentInterface, InfoFragmentInterface, MoviesMapFragmentInterface {
    private static final String BASE_URL = "opendata.paris.fr";
    private static final String NB_ROWS = "2805";

    FragmentManager fragmentManager;

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

        fragmentManager = getSupportFragmentManager();
        WelcomeScreenFragment welcomeFragment = new WelcomeScreenFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, welcomeFragment);
        fragmentTransaction.commit();

    }

    private void loadLieuxTournagesFromServer() {
        lieuxTournages.clear();
        //tournageDatabaseService.dropAll();
        Uri.Builder uri = new Uri.Builder();
        uri.scheme("https")
                .authority(BASE_URL)
                .appendPath("api")
                .appendPath("records")
                .appendPath("1.0")
                .appendPath("search")
                .appendQueryParameter("dataset", "tournagesdefilmsparis2011")
                .appendQueryParameter("rows", NB_ROWS) // Chargement de toutes les données
                .appendQueryParameter("facet", "realisateur")
                .appendQueryParameter("facet", "organisme_demandeur")
                .appendQueryParameter("facet","type_de_tournage")
                .appendQueryParameter("facet","ardt");
        tournageWebService.getAllTournages(uri.build().toString())
                .enqueue(new Callback<LieuxTournageRecords>() {
                    @Override
                    public void onResponse(@NonNull Call<LieuxTournageRecords> call,
                                           @NonNull Response<LieuxTournageRecords> response) {
                        tournageDatabaseService.dropAll();
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void entrerToList() {
        Bundle bundle = new Bundle();
        ArrayList<ElementListMovie> listMovies = new ArrayList<ElementListMovie>();
        lieuxTournages.forEach(lieuxTournage ->
                listMovies.add(lieuxTournage.elementList()));

        bundle.putSerializable("movies", listMovies);

        MovieListFragment listFragment = new MovieListFragment();
        fragmentManager = getSupportFragmentManager();
        listFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
        fragmentTransaction2.replace(R.id.container, listFragment);
        fragmentTransaction2.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.list:
                entrerToList();
                return true;
            case R.id.map:
//                Intent intent = new Intent(MainActivity.this, Maps.class);
//                startActivity(intent);
                fragmentManager = getSupportFragmentManager();
//                MoviesMapFragment map = new MoviesMapFragment();
                Maps map = new Maps();
                FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                fragmentTransaction1.replace(R.id.container, map);
                fragmentTransaction1.commit();
                return true;
            case R.id.info:
                fragmentManager = getSupportFragmentManager();
                InfoFragment info = new InfoFragment();
                FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
                fragmentTransaction2.replace(R.id.container, info);
                fragmentTransaction2.commit();
                return true;

            default: // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void callDetails() {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        startActivity(intent);
    }
}
