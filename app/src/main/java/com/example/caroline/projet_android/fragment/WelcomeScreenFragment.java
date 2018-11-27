package com.example.caroline.projet_android.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.caroline.projet_android.R;

public class WelcomeScreenFragment extends Fragment {

    private WelcomeScreenFragmentInterface mListener;
    private Button entrer;

    public WelcomeScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_welcome_screen, container, false);
        entrer = rootView.findViewById((R.id.button_enter_app));

        entrer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mListener.entrerToList();
            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof WelcomeScreenFragmentInterface) {
            mListener = (WelcomeScreenFragmentInterface) context;
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
