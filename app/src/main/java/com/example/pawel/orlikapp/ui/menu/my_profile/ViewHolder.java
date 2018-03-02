package com.example.pawel.orlikapp.ui.menu.my_profile;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pawel.orlikapp.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Pawel on 27.02.2018.
 */
class ViewHolder {
    CircleImageView playerPhoto;
    TextView nameOfUser, actualBookings, allBookings, phoneNumber, age, email;
    Button test;

    ViewHolder(View view) {
        test = (Button) view.findViewById(R.id.test);
        playerPhoto = (CircleImageView) view.findViewById(R.id.playerPhoto);
        nameOfUser = (TextView) view.findViewById(R.id.nameOfUser);
        actualBookings = (TextView) view.findViewById(R.id.actualBookings);
        allBookings = (TextView) view.findViewById(R.id.allBookings);
        phoneNumber = (TextView) view.findViewById(R.id.phoneNumber);
        age = (TextView) view.findViewById(R.id.age);
        email = (TextView) view.findViewById(R.id.emailAddres);
    }
}
