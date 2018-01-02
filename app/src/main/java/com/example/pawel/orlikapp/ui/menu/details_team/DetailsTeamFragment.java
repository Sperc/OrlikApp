package com.example.pawel.orlikapp.ui.menu.details_team;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pawel.orlikapp.R;


public class DetailsTeamFragment extends Fragment {


    public DetailsTeamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_details_team, container, false);
        Toast.makeText(getActivity(), "setup", Toast.LENGTH_SHORT).show();
        return view;
    }



}
