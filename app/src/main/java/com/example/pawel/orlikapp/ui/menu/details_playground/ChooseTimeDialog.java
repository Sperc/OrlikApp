package com.example.pawel.orlikapp.ui.menu.details_playground;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.Booking;
import com.example.pawel.orlikapp.ui.menu.bookingdetails.BookingDetailsFragment;
import com.example.pawel.orlikapp.ui.menu.create_booking.CreateBookingFragment;
import com.example.pawel.orlikapp.utils.ConstansValues;
import com.example.pawel.orlikapp.utils.CreateBookingHelper;
import com.example.pawel.orlikapp.utils.DateHelper;
import com.example.pawel.orlikapp.utils.Logs;
import com.example.pawel.orlikapp.utils.Time;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Pawel on 09.01.2018.
 */

public class ChooseTimeDialog extends AppCompatDialogFragment {
    TextView startTimeTextView;
    TextView durationTimeTextView;
    SeekBar startTimeSeekBar;
    DetailsPlaygroundPresenter detailsPlaygroundPresenter;
    Calendar calendar;

    TimePicker timePicker;
    SeekBar durationTimeSeekBar;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.choose_time_dialog_1, null);
        detailsPlaygroundPresenter = new DetailsPlaygroundPresenter();
        setTimePicker(view);

        Bundle bundle = getArguments();
        Long playgroundId = (Long) bundle.getSerializable("playground_id");
        calendar = (Calendar) bundle.getSerializable("calendar");

        detailsPlaygroundPresenter.getSortedBookingByPlaygroundIdAndDate(playgroundId, DateHelper.convertCalendarToDateString(calendar), detailsPlaygroundListener);


        builder.setView(view)
                .setNegativeButton("ANULUJ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Time startTime = new Time(timePicker.getHour(),timePicker.getMinute()*ConstansValues.TIME_PICKER_INTERVAL);
                        int progress= durationTimeSeekBar.getProgress()/ConstansValues.TIME_PICKER_INTERVAL*ConstansValues.TIME_PICKER_INTERVAL;
                    }
                });
//        initialize(view);
        return builder.create();
    }

    private void initialize(View view) {
        startTimeSeekBar = (SeekBar) view.findViewById(R.id.startTimeSeekBar);
        durationTimeSeekBar = (SeekBar) view.findViewById(R.id.durationSeekBar);
        startTimeTextView = (TextView) view.findViewById(R.id.startTimeTextView);
        durationTimeTextView = (TextView) view.findViewById(R.id.durationTextView);
    }

    DetailsPlaygroundPresenter.DetailsPlaygroundListener detailsPlaygroundListener = new DetailsPlaygroundPresenter.DetailsPlaygroundListener() {
        @Override
        public void getBookingList(List<Booking> bookingList) {
            CreateBookingHelper createBookingHelper = new CreateBookingHelper(calendar, bookingList, new Time(23, 15));
            Time startTime = createBookingHelper.getMinimumTimeToStart();
            Time endTime = createBookingHelper.getMaxTimeToEndBooking();
//            seekBarValue(startTime,endTime);
            setTimePickerOptions(startTime, endTime, new Time(createBookingHelper.getHourClickTime(), createBookingHelper.getMinoutesClickTime()));
            setTimeSeekBarOptions(startTime,endTime,new Time(createBookingHelper.getHourClickTime(), createBookingHelper.getMinoutesClickTime()));
        }
    };

    private void seekBarValue(final Time start, Time end) {
        int test = start.getAllTimeInMinutes() % 60;
        test = start.getAllTimeInMinutes() + 60 - test;

        startTimeSeekBar.setMax(test);
        startTimeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int minValue = start.getAllTimeInMinutes();
            int progres;

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int stepSize = 15;
                progres = minValue + i;
                progres = progres / stepSize * stepSize;
                startTimeTextView.setText(String.valueOf(progres / 60) + ":" + String.valueOf(progres % 60));
//                startTimeTextView.setText(String .valueOf(progres));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setTimePicker(View view) {
        timePicker = (TimePicker) view.findViewById(R.id.timePicker);
        durationTimeSeekBar = (SeekBar) view.findViewById(R.id.durationTimeSeekBar);
        durationTimeTextView = (TextView) view.findViewById(R.id.durationTextView);
        timePicker.setIs24HourView(true);
        setTimePickerInterval(timePicker);
    }

    private void setSeekBar() {

    }

    public void setTimePickerOptions(final Time start, final Time end, final Time click) {


//        timePicker.setMinute(click.getMinutes());
        timePicker.setMinute(click.getMinutes() / ConstansValues.TIME_PICKER_INTERVAL);
        timePicker.setHour(click.getHour());

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                timePicker.setHour(click.getHour());
                Logs.d("ChooseTimeDialog", String.valueOf(i1));
                Logs.d("ChooseTimeDialog", "startmin" + String.valueOf(start.getMinutes()));

//                if (start.getMinutes() / ConstansValues.TIME_PICKER_INTERVAL < i1) {
//
//                    timePicker.setMinute(start.getMinutes() / ConstansValues.TIME_PICKER_INTERVAL);
//                }
                setTimeSeekBarOptions(start,end,click);
            }
        });

    }

    public void setTimeSeekBarOptions(Time start,Time firstBookingAfterClick,Time click){
        Time actualTime = new Time(timePicker.getHour(),timePicker.getMinute()*ConstansValues.TIME_PICKER_INTERVAL);
        durationTimeSeekBar.setMax(firstBookingAfterClick.getAllTimeInMinutes() - actualTime.getAllTimeInMinutes());
        durationTimeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int timeBooking =i;
                timeBooking = timeBooking / 15 * 15;
                durationTimeTextView.setText(timeBooking / 60 + ":" + timeBooking % 60);
                Logs.d("ChooseTimeDialog", "TIME: " + timeBooking);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setTimePickerInterval(TimePicker timePicker) {
        try {

            NumberPicker minutePicker = (NumberPicker) timePicker.findViewById(Resources.getSystem().getIdentifier(
                    "minute", "id", "android"));
            minutePicker.setMinValue(0);
            minutePicker.setMaxValue((60 / ConstansValues.TIME_PICKER_INTERVAL) - 1);
            List<String> displayedValues = new ArrayList<String>();
            for (int i = 0; i < 60; i += ConstansValues.TIME_PICKER_INTERVAL) {
                displayedValues.add(String.format("%02d", i));
            }
            minutePicker.setDisplayedValues(displayedValues.toArray(new String[0]));
        } catch (Exception e) {
            Logs.d(TAG, "Exception: " + e);
        }
    }
    private void openCreateBookingFragment(Time startTime,Time durationTime){
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        CreateBookingFragment createBookingFragment = new CreateBookingFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("start_Time", startTime);
        bundle.putSerializable("duration_Time", durationTime);
        createBookingFragment.setArguments(bundle);
        ft.replace(R.id.flcontent, createBookingFragment);
        ft.commit();
    }


}

