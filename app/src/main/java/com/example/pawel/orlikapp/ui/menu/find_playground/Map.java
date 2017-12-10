package com.example.pawel.orlikapp.ui.menu.find_playground;

import android.view.View;

import com.example.pawel.orlikapp.model.Playground;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

/**
 * Created by Pawel on 09.12.2017.
 */

public class Map{
    public static void addMarkerFromList(GoogleMap googleMap, List<Playground> playgrounds){
        for(Playground item: playgrounds){
            googleMap.addMarker(new MarkerOptions().position(new LatLng(item.getLatitude(),item.getLongitude())));
        }
    }
}
