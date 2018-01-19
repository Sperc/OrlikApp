package com.example.pawel.orlikapp.ui.menu.create_booking;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pawel.orlikapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateBookingFragment extends Fragment {


    public CreateBookingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_booking, container, false);
    }

}
