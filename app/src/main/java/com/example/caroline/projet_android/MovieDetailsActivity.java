package com.example.caroline.projet_android;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.caroline.projet_android.model.ElementListMovie;
import com.example.caroline.projet_android.model.LieuxTournage;
import com.example.caroline.projet_android.services.AppDatabase;
import com.example.caroline.projet_android.services.TournageDatabaseService;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MovieDetailsActivity extends AppCompatActivity {

    private FloatingActionButton exit;
    private TextView titre;
    private TextView typeRealisateur;
    private TextView demandeur;
    private TextView date;
    private TextView lieu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Bundle bundle = getIntent().getBundleExtra(Intent.EXTRA_TEXT);
        ElementListMovie movie =(ElementListMovie) bundle.getSerializable("movieElem");

        // On cherche toute les info du tournage a partir du l'id du film contenu dans movie
        TournageDatabaseService tournageDatabaseService = AppDatabase.getAppDatabase(this.getApplicationContext()).getTournagesDatabaseService();
        LieuxTournage tournage = tournageDatabaseService.getLieu(movie.getId());

        setResult(1);

        titre = findViewById(R.id.titre);
        typeRealisateur = findViewById(R.id.type_realisateur);
        demandeur = findViewById(R.id.demandeur);
        date = findViewById(R.id.dates);
        lieu = findViewById(R.id.lieu);
        exit = findViewById(R.id.exit);

        titre.setText(tournage.getTitre());
        typeRealisateur.setText(tournage.getTypeDeTournage() + " - " + tournage.getRealisateur());
        demandeur.setText("Tournage demandé par " + tournage.getOrganismeDemandeur());
        date.setText("Tourné du " + tournage.getDateDebut() + " au " + tournage.getDateFin());
        lieu.setText("Lieu : " + tournage.getAdresse() + " " + tournage.getArdt());
        exit.setOnClickListener( v ->
                finish());

    }
}
