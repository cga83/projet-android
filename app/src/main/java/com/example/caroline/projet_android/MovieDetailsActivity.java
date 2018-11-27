package com.example.caroline.projet_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.caroline.projet_android.model.ElementListMovie;

import java.util.ArrayList;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Bundle bundle = getIntent().getBundleExtra(Intent.EXTRA_TEXT);
        ElementListMovie movies =(ElementListMovie) bundle.getSerializable("movieElem");
    }
}
