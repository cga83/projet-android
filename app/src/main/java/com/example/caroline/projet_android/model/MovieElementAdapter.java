package com.example.caroline.projet_android.model;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.caroline.projet_android.OnRecyclerViewMovieClickListener;
import com.example.caroline.projet_android.R;

import java.util.ArrayList;

public class MovieElementAdapter extends RecyclerView.Adapter<MovieElementAdapter.MovieViewHolder> {

    private ArrayList<ElementListMovie> icons;

    public MovieElementAdapter(ArrayList<ElementListMovie> icons) {
        this.icons = icons;
    }

    private OnRecyclerViewMovieClickListener onRecyclerViewMovieClickListener;

    public void setOnRecyclerViewMovieClickListener(OnRecyclerViewMovieClickListener onRecyclerViewMovieClickListener) {
        this.onRecyclerViewMovieClickListener = onRecyclerViewMovieClickListener;
    }


    public MovieElementAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_movie_element, parent, false);
        return new MovieElementAdapter.MovieViewHolder(row);
    }

    public void onBindViewHolder(MovieElementAdapter.MovieViewHolder viewholder, int position) {
        ElementListMovie iconToDisplay = this.icons.get(position);
        viewholder.constraintLayout.setTag(iconToDisplay);


        viewholder.titre.setText(iconToDisplay.getTitre());
        viewholder.adresse.setText(iconToDisplay.getAdresse() + " " + iconToDisplay.getArdt());
        if (iconToDisplay.getTypeTournage().equals("TELEFILM")) {
            viewholder.logo.setImageResource(R.drawable.ic_videocam_blue_24dp);

        } else if (iconToDisplay.getTypeTournage().equals("LONG METRAGE")) {
            viewholder.logo.setImageResource(R.drawable.ic_videocam_black_24dp);

        } else {
            viewholder.logo.setImageResource(R.drawable.ic_videocam_red_24dp);
        }
    }

    public void onMovieClick(int position, View view) {
        ElementListMovie movie = (ElementListMovie) view.getTag();
        switch (view.getId()) {
            case R.id.a_main_rcv_movies:
               System.out.println("Position clicked: " + String.valueOf(position) + ", "+ movie.getTitre());
                break;
        }
    }

    public int getItemCount() {
        return this.icons.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        public TextView titre;
        public TextView adresse;
        public ImageView logo;
        public ConstraintLayout constraintLayout;

        public MovieViewHolder(View rootView) {
            super(rootView);
            this.titre = rootView.findViewById(R.id.titre);
            this.adresse = rootView.findViewById(R.id.adresse);
            this.logo = rootView.findViewById(R.id.image);
            constraintLayout = rootView.findViewById(R.id.row_main_adapter_linear_layout);
            constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onRecyclerViewMovieClickListener != null) {
                        onRecyclerViewMovieClickListener.onMovieClick(getAdapterPosition(), view);
                    }
                }
            });
        }

    }
}
