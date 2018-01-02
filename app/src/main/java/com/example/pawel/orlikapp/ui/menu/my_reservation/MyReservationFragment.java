package com.example.pawel.orlikapp.ui.menu.my_reservation;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.Booking;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyReservationFragment extends Fragment implements MyReservationPresenter.ReservationListener {

    ArrayAdapter<Booking> adapterOwnReservation;
    ArrayAdapter<Booking> adapterReservation;

    ListView ownList;
    ListView list;

    public MyReservationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reservation, container, false);
        init(view);
        MyReservationPresenter reservationPresenter = new MyReservationPresenter(getContext(), this);
        reservationPresenter.getUserRerservation();
        return view;
    }

    @Override
    public void onSucces(List<Booking> ownReservation, List<Booking> memberReservation) {
        Toast.makeText(getContext(), "own: " + ownReservation.size() + " member: " + memberReservation.size(), Toast.LENGTH_SHORT).show();
        adapterOwnReservation = new ArrayAdapter<Booking>(getContext(), android.R.layout.simple_list_item_1, ownReservation);
        adapterReservation = new ArrayAdapter<Booking>(getContext(), android.R.layout.simple_list_item_1, memberReservation);
        list.setAdapter(adapterReservation);
        ownList.setAdapter(adapterOwnReservation);
    }

    @Override
    public void onFailure() {
        Toast.makeText(getContext(), "nie dziala", Toast.LENGTH_SHORT).show();

    }

    private void init(View view) {
        ownList = (ListView) view.findViewById(R.id.ownReservationList);
        list = (ListView)view.findViewById(R.id.reservationList);
    }
}
