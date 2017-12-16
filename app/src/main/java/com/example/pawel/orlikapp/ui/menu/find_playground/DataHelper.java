package com.example.pawel.orlikapp.ui.menu.find_playground;

import com.example.pawel.orlikapp.model.Playground;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pawel on 09.12.2017.
 */

public class DataHelper {
    public static List<String> getListString(List<Playground> playgrounds){
        List<String> addres = new ArrayList<>();
        for(Playground p:playgrounds){
            addres.add(p.getStreetName()+" "+p.getStreetNumber()+" "+p.getDesctiption());
        }
        return addres;
    }
}
