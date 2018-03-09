package com.example.pawel.orlikapp.ui.menu.player_details;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pawel.orlikapp.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Pawel on 09.03.2018.
 */

class ViewHolder {
    CircleImageView playerPhoto;
    TextView nameOfUser, actualBookings, allBookings, phoneNumber, age, email;

    ViewHolder(Activity activity) {
        playerPhoto = (CircleImageView) activity.findViewById(R.id.playerPhoto);
        nameOfUser = (TextView) activity.findViewById(R.id.nameOfUser);
        actualBookings = (TextView) activity.findViewById(R.id.actualBookings);
        allBookings = (TextView) activity.findViewById(R.id.allBookings);
        phoneNumber = (TextView) activity.findViewById(R.id.phoneNumber);
        age = (TextView) activity.findViewById(R.id.age);
        email = (TextView) activity.findViewById(R.id.emailAddres);
    }

}
