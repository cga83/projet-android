package com.example.caroline.projet_android.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.caroline.projet_android.MainActivity;
import com.example.caroline.projet_android.MovieDetailsActivity;
import com.example.caroline.projet_android.MovieElementAdapter;
import com.example.caroline.projet_android.OnRecyclerViewMovieClickListener;
import com.example.caroline.projet_android.R;
import com.example.caroline.projet_android.model.ElementListMovie;

import java.util.ArrayList;


public class MovieListFragment extends Fragment implements OnRecyclerViewMovieClickListener {

    private MovieListFragmentInterface mListener;

    private RecyclerView rcvMovie;
    private MovieElementAdapter movieAdapter;

    ArrayList<ElementListMovie> movies = new ArrayList<ElementListMovie>();

    public MovieListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        Bundle bundle = getArguments();
        ArrayList<ElementListMovie> movies =(ArrayList<ElementListMovie>) bundle.getSerializable("movies");

        View rootview = inflater.inflate(R.layout.fragment_movie_list, container, false);

        rcvMovie = rootview.findViewById(R.id.a_main_rcv_movies);
        rcvMovie.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(rootview.getContext());
        rcvMovie.setLayoutManager(layoutManager);


        // specify an adapter (see also next example)
//        ElementListMovie movie = new ElementListMovie("Harry Potter", "2012");
//
//        movies.add(movie);
//        movies.add(movie);
        movieAdapter = new MovieElementAdapter(movies);
        movieAdapter.setOnRecyclerViewMovieClickListener(this);
        rcvMovie.setAdapter(movieAdapter);

        return rootview;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MovieListFragmentInterface) {
            mListener = (MovieListFragmentInterface) context;
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

    @Override
    public void onMovieClick(int position, View view) {
        ElementListMovie movie = (ElementListMovie) view.getTag();
        switch (view.getId()) {
            case R.id.row_main_adapter_linear_layout:
                System.out.println("Position clicked: " + String.valueOf(position) + ", "+ movie.getTitre());
                getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();

                mListener.callDetails();
                break;
        }
    }
}
