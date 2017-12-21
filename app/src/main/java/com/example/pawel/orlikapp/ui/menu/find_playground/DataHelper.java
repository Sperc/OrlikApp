package com.example.pawel.orlikapp.ui.menu.find_playground;

import com.example.pawel.orlikapp.model.Playground;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Pawel on 09.12.2017.
 */

public class DataHelper {
    public static List<String> getListString(List<Playground> playgrounds){
        List<String> addres = new ArrayList<>();
        for(Playground p:playgrounds){
            addres.add(p.getStreetName()+" "+p.getStreetNumber());
        }
        return addres;
    }
    public static Map<String,LatLng> getPlaygroundsLatLng(List<Playground> playgrounds){
        Map<String,LatLng> map = new HashMap<>();
        for(Playground p: playgrounds){
            map.put(p.getStreetName()+" "+p.getStreetNumber(),new LatLng(p.getLatitude(),p.getLongitude()));
        }
        return map;
    }
}
