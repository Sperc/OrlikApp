package com.example.pawel.orlikapp.ui.menu.find_playground;


import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.Playground;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;
import com.example.pawel.orlikapp.utils.Logs;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class FindPlaygroundFragment extends Fragment implements OnMapReadyCallback, FindPlaygroundPresenter.FindPlaygroundListener {
    SupportMapFragment supportMapFragment;
    FindPlaygroundPresenter findPlaygroundPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_playground, container, false);
        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        EditText editText = (EditText)getActivity().findViewById(R.id.navbox);
        editText.setText("asd");
        editText.setVisibility(View.VISIBLE);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (supportMapFragment == null) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            supportMapFragment = SupportMapFragment.newInstance();
            ft.replace(R.id.map, supportMapFragment).commit();
        }
        supportMapFragment.getMapAsync(this);

        findPlaygroundPresenter = new FindPlaygroundPresenter(this);
        findPlaygroundPresenter.getPlaygroundByCity(PreferencesShared.onReadString(PreferencesSharedKyes.city));

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        double lati = Double.parseDouble(PreferencesShared.onReadString(PreferencesSharedKyes.latitude));
        double longi = Double.parseDouble(PreferencesShared.onReadString(PreferencesSharedKyes.longitude));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lati,longi),12));
    }

    @Override
    public void onSucces(List<Playground> playgrounds) {
        Toast.makeText(getActivity(), String.valueOf(playgrounds.size()), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure() {
        Toast.makeText(getActivity(), R.string.serverProblem, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAuthorizationProblem() {
        Toast.makeText(getActivity(), R.string.authorizationProblem, Toast.LENGTH_SHORT).show();
    }
}
