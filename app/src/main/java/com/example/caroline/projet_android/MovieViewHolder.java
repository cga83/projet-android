package com.example.caroline.projet_android;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    public TextView titre;
    public TextView adresse;

    public MovieViewHolder(View rootView) {
        super(rootView);
        this.titre = rootView.findViewById(R.id.titre);
        this.adresse = rootView.findViewById(R.id.adresse);
    }
}
