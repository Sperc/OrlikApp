package com.example.pawel.orlikapp.ui.menu.find_playground;

import android.content.Context;
import android.hardware.Camera;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.Playground;
import com.example.pawel.orlikapp.utils.ConstansValues;
import com.example.pawel.orlikapp.utils.Logs;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Pawel on 21.12.2017.
 */

public class CustomWindowAdapter implements GoogleMap.InfoWindowAdapter {
    private final View mWindow;
    private Context context;

    public CustomWindowAdapter(Context context) {
        this.context = context;
        mWindow = LayoutInflater.from(context).inflate(R.layout.custom_info_window, null);
    }

    public void rendowWindowText(Marker marker, View view) {
        Playground playground = (Playground) marker.getTag();

        TextView textView = (TextView) view.findViewById(R.id.title);

        textView.setText(playground.getName());
        TextView snipsetView = (TextView) view.findViewById(R.id.snippet);
        snipsetView.setFilters(new InputFilter[]{new InputFilter.LengthFilter(40)});
        snipsetView.setText("ul. " + playground.getStreetName() + " " + playground.getStreetNumber());

        CircleImageView circleImageView = view.findViewById(R.id.photo);
        if (playground.getPicture() != null)
            Picasso.with(view.getContext())
                    .load(ConstansValues.BASE_IMG_URL + playground.getPicture().getId())
                    .placeholder(R.drawable.football)
                    .error(R.drawable.draw_person).into(circleImageView);
        else {
            Picasso.with(view.getContext()).load(R.drawable.football).fit().placeholder(R.drawable.draw_person).error(R.drawable.draw_person).into(circleImageView);
        }

    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        rendowWindowText(marker, mWindow);
        return mWindow;
    }
}
