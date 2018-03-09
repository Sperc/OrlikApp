package com.example.pawel.orlikapp.ui.menu.find_playground;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.Playground;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

/**
 * Created by Pawel on 09.12.2017.
 */

public class MapHelper {
    public static void addMarkerFromList(GoogleMap googleMap, List<Playground> playgrounds, Context context) {
        if (playgrounds.isEmpty()) {
            return;
        }
        Bitmap icon = null;
        for (Playground item : playgrounds) {
            if (item.getName().equalsIgnoreCase("orlik")) {
                icon = BitmapFactory.decodeResource(context.getResources(),
                        R.drawable.playground_marker_v2);
            } else {
                icon = BitmapFactory.decodeResource(context.getResources(),
                        R.drawable.ic_home_black_36dp);
            }

            String snippet = "Adres: " + item.getCity().getName() + "," + " " + item.getStreetName() + " " + item.getStreetNumber();
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(item.getLatitude(), item.getLongitude()))
                    .title(item.getName())
//                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_home_black_24dp))
                    .icon(BitmapDescriptorFactory.fromBitmap(icon))
                    .snippet(snippet))
                    .setTag(item);

        }
    }
}