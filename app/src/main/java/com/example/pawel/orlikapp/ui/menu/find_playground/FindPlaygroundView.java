package com.example.pawel.orlikapp.ui.menu.find_playground;

import com.example.pawel.orlikapp.model.Playground;
import com.google.android.gms.maps.GoogleMap;

import java.util.List;

/**
 * Created by Pawel on 09.12.2017.
 */

public interface FindPlaygroundView {
    void onResponse(String message);
    void onMapReady(GoogleMap googleMap);
}
