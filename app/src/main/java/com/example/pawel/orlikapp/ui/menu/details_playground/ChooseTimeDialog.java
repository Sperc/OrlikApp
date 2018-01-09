package com.example.pawel.orlikapp.ui.menu.details_playground;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.Booking;
import com.example.pawel.orlikapp.utils.CreateBookingHelper;
import com.example.pawel.orlikapp.utils.Time;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

/**
 * Created by Pawel on 09.01.2018.
 */

public class ChooseTimeDialog extends AppCompatDialogFragment {
    TextView startTimeTextView;
    TextView durationTimeTextView;
    SeekBar durationTimeSeekBar;
    SeekBar startTimeSeekBar;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_choose_time_layout, null);

        Bundle bundle = getArguments();
        Set<Booking> list = (Set<Booking>) bundle.getSerializable("booking");
        Calendar calendar = (Calendar) bundle.getSerializable("calendar");

        //test
        CreateBookingHelper createBookingHelper = new CreateBookingHelper(calendar, list);
        Time t = createBookingHelper.findNearestStartBooking();

        builder.setView(view)
                .setNegativeButton("ANULUJ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        initialize(view);
        return builder.create();
    }

    private void initialize(View view) {
        startTimeSeekBar = (SeekBar) view.findViewById(R.id.startTimeSeekBar);
        durationTimeSeekBar = (SeekBar) view.findViewById(R.id.durationSeekBar);
        startTimeTextView = (TextView) view.findViewById(R.id.startTimeTextView);
        durationTimeTextView = (TextView) view.findViewById(R.id.durationTextView);
    }
}

