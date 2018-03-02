package com.example.pawel.orlikapp.ui.menu.editplayer;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pawel.orlikapp.R;

/**
 * Created by Pawel on 01.03.2018.
 */

class ViewHolder {
    EditText firstName, lastName, phoneNumber, birthDate;
    Button accept;

    ViewHolder(View view) {
        firstName = view.findViewById(R.id.firstName);
        lastName = view.findViewById(R.id.lastName);
        phoneNumber = view.findViewById(R.id.phoneNumber);
        birthDate = view.findViewById(R.id.birthDay);
        accept = view.findViewById(R.id.accept);
    }
}
