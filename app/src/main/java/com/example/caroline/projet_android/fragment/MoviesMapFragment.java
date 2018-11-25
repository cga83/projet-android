package com.example.caroline.projet_android.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.caroline.projet_android.MainActivity;
import com.example.caroline.projet_android.Maps;
import com.example.caroline.projet_android.R;


public class MoviesMapFragment extends Fragment {

    private MoviesMapFragmentInterface mListener;

    public MoviesMapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_movies_map, container, false);

        Intent intent = new Intent(getContext(), Maps.class);
        startActivity(intent);
        return rootview;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MoviesMapFragmentInterface) {
            mListener = (MoviesMapFragmentInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
