package com.example.pawel.orlikapp.ui.menu.create_booking;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.Booking;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;
import com.example.pawel.orlikapp.utils.Logs;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateBookingFragment extends Fragment {


    private Booking booking;
    private Bitmap bitmap;

    private ImageView imageView;


    public CreateBookingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_booking, container, false);
        Bundle bundle = getArguments();
        booking = (Booking)bundle.getSerializable("booking");
        booking.setLeaderName(PreferencesShared.onReadString(PreferencesSharedKyes.username));
        CreateBookingPresenter createBookingPresenter = new CreateBookingPresenter();
        createBookingPresenter.addBooking(booking,addBookingListener);
        return view;
    }
    CreateBookingPresenter.AddBookingListener addBookingListener = new CreateBookingPresenter.AddBookingListener() {
        @Override
        public void onSucces() {
            Logs.d("CREATE_BOOKING_FRAGMENT","MSG: "+booking.toString());
        }
    };

}
