package com.example.pawel.orlikapp.ui.menu.my_reservation;

import com.example.pawel.orlikapp.model.Booking;
import com.example.pawel.orlikapp.ui.base.BaseView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Pawel on 01.03.2018.
 */

public interface MyReservationView extends BaseView {
    void onSuccesGetBooking(List<Booking> ownBooking,List<Booking> participantBooking);
}
