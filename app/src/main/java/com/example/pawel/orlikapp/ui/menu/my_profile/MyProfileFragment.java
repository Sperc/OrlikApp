package com.example.pawel.orlikapp.ui.menu.my_profile;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pawel.orlikapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyProfileFragment extends Fragment {

    private MyProfilePresenter myProfilePresenter;


    public MyProfileFragment() {
        // Required empty public constructor
    }

    ViewHolder viewHolder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view1 = inflater.inflate(R.layout.fragment_my_profile, container, false);
        viewHolder = new ViewHolder(view1);
        myProfilePresenter = new MyProfilePresenter(this);
        return view1;
    }

    @Override
    public void onResume() {
        super.onResume();
        myProfilePresenter.getUser();
    }

}
