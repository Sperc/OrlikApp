package com.example.pawel.orlikapp.ui.menu.find_playground;

import android.content.Context;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.Playground;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by Pawel on 21.12.2017.
 */

public class CustomWindowAdapter implements GoogleMap.InfoWindowAdapter {
    private final View mWindow;
    private Context context;

    public CustomWindowAdapter(Context context) {
        this.context = context;
        mWindow = LayoutInflater.from(context).inflate(R.layout.custom_info_window,null);
    }
    public void rendowWindowText(Marker marker,View view){
        Playground playground = (Playground) marker.getTag();

        String title = marker.getTitle();
        TextView textView = (TextView)view.findViewById(R.id.title);
//        if(!title.equals("")){
//            textView.setText(title);
//        }

        textView.setText(playground.getName());
//        String snippet = marker.getSnippet();
        TextView snipsetView = (TextView)view.findViewById(R.id.snippet);
        snipsetView.setFilters(new InputFilter[]{ new InputFilter.LengthFilter(25) });
//        if(!snippet.equals("")){
//            snipsetView.setText(snippet);
//        }
        snipsetView.setText(playground.getAddres());
    }

    @Override
    public View getInfoWindow(Marker marker) {
        rendowWindowText(marker,mWindow);
        return mWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        rendowWindowText(marker,mWindow);
        return mWindow;
    }
}
