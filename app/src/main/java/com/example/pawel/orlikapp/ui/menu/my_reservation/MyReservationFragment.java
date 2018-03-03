package com.example.pawel.orlikapp.ui.menu.my_reservation;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.Booking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyReservationFragment extends Fragment implements MyReservationView {
    public MyReservationFragment() {
        // Required empty public constructor
    }

    private ExpantableListAdapter expantableListAdapter;
    private ExpandableListView expandableListView;
    private MyReservationPresenter myReservationPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reservation, container, false);
        expandableListView = view.findViewById(R.id.expand_list_view);
        myReservationPresenter = new MyReservationPresenter(this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        myReservationPresenter.getPlayerBooking();
    }

    @Override
    public void onServerError() {

    }

    @Override
    public void onServerNotResponse() {

    }

    @Override
    public void onUnauthorized() {

    }

    @Override
    public void onSuccesGetBooking(List<Booking> ownBooking, List<Booking> participantBooking) {
        HashMap<String, List<Booking>> listHashMap = new HashMap<>();
        String[] categories = getResources().getStringArray(R.array.category_of_reservation_array);
        listHashMap.put(categories[0], ownBooking);
        listHashMap.put(categories[1], participantBooking);

        expantableListAdapter = new ExpantableListAdapter(getContext(), categories, listHashMap);
        expandableListView.setAdapter(expantableListAdapter);
    }
}
