package com.example.pawel.orlikapp.ui.menu.editplayer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pawel.orlikapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditPlayerFragment extends Fragment {


    public EditPlayerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_player, container, false);

        return view;
    }

}
