package com.example.caroline.projet_android;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.caroline.projet_android.model.ElementListMovie;

import java.util.ArrayList;

public class MovieElementAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private ArrayList<ElementListMovie> icons;

    public MovieElementAdapter(ArrayList<ElementListMovie> icons) { this.icons = icons; }

    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_movie_element, parent, false);
        return new MovieViewHolder(row);
    }

    public void onBindViewHolder(MovieViewHolder viewholder, int position) {
        ElementListMovie iconToDisplay = this.icons.get(position);

        viewholder.titre.setText(iconToDisplay.getTitre());
        viewholder.adresse.setText(iconToDisplay.getAdresse() + " " +iconToDisplay.getArdt());
        if(iconToDisplay.getTypeTournage().equals("TELEFILM")) {
            viewholder.logo.setImageResource(R.drawable.ic_videocam_blue_24dp);

        } else if (iconToDisplay.getTypeTournage().equals("LONG METRAGE")) {
            viewholder.logo.setImageResource(R.drawable.ic_videocam_black_24dp);

        } else {
            viewholder.logo.setImageResource(R.drawable.ic_videocam_red_24dp);
        }
    }

    public int getItemCount() {
        return this.icons.size();
    }

}
