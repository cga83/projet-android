package com.example.caroline.projet_android.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.caroline.projet_android.R;


public class InfoFragment extends Fragment {

    private InfoFragmentInterface mListener;
    private TextView textTitle;
    private TextView textInfo;

    public InfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_info, container, false);
        textTitle = rootView.findViewById(R.id.info_title);
        textTitle.setText("Informations sur les données de l'application");

        textInfo = rootView.findViewById(R.id.info_text);
        textInfo.setText("Les données utilisées sont celles de lieux de tournages à Paris.\n\n" +
                "Elles ont été récupérées sur le site https://opendata.paris.fr/explore/dataset/tournagesdefilmsparis2011/table/. \n\n" +
                "Pour chaque lieu de tournage, nous avons les informations suivantes :\n\n" +
                "- Le titre du film\n" +
                "- Le réalisateur du film\n" +
                "- L'adresse du lieu de tournage\n" +
                "- L'organisme demandeur du film\n" +
                "- Le type du tournage (long métrage, téléfilm ou série télévisée)\n" +
                "- L'arondissement dans lequel le tournage a eu lieu\n" +
                "- La date de début du tournage\n" +
                "- La date de fin du tournage\n" +
                "- Les coordonnées du lieu de tournage");
        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof InfoFragmentInterface) {
            mListener = (InfoFragmentInterface) context;
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
