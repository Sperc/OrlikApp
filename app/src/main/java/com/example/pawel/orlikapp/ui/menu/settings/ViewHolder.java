package com.example.pawel.orlikapp.ui.menu.settings;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pawel.orlikapp.R;

/**
 * Created by Pawel on 04.03.2018.
 */
class ViewHolder {
    EditText currentPassword, newPasword, confirmPassword, deleteEditText;
    Button saveBtn, deleteBtn;

    public ViewHolder(View view) {
        confirmPassword = (EditText) view.findViewById(R.id.confirmPassword);
        newPasword = (EditText) view.findViewById(R.id.newPasword);
        deleteEditText = (EditText) view.findViewById(R.id.deleteEditText);
        currentPassword = (EditText) view.findViewById(R.id.currentPassword);
        saveBtn = (Button) view.findViewById(R.id.saveBtn);
        deleteBtn = (Button) view.findViewById(R.id.deleteBtn);
    }
}
