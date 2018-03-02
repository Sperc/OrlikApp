package com.example.pawel.orlikapp.ui.menu.my_reservation;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.pawel.orlikapp.api.client.client_impl.PlayerServiceImpl;
import com.example.pawel.orlikapp.model.Booking;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;
import com.example.pawel.orlikapp.api.client.PlayerClient;
import com.example.pawel.orlikapp.api.ServiceGenerator;
import com.example.pawel.orlikapp.utils.Logs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pawel on 27.11.2017.
 */

public class MyReservationPresenter {
    private MyReservationView myReservationView;
    private PlayerServiceImpl playerService;

    public MyReservationPresenter(MyReservationView myReservationView) {
        this.myReservationView = myReservationView;
        playerService = new PlayerServiceImpl();
    }

    public void getPlayerBooking() {
        playerService.getPlayerBooking(userReservationListener);
    }

    PlayerServiceImpl.UserReservationListener userReservationListener = new PlayerServiceImpl.UserReservationListener() {
        @Override
        public void onSucces(List<Booking> bookingList) {
            String usernamePlayer = PreferencesShared.onReadString(PreferencesSharedKyes.username);
            List<Booking> ownBooking = new ArrayList<>();
            List<Booking> participantBooking = new ArrayList<>();
            for (Booking b : bookingList) {
                if (b.getLeaderName().equals(usernamePlayer)) {
                    ownBooking.add(b);
                } else {
                    participantBooking.add(b);
                }
            }
            myReservationView.onSuccesGetBooking(ownBooking, participantBooking);
        }

        @Override
        public void onServerNotResponse() {
            myReservationView.onServerNotResponse();
        }

        @Override
        public void onUnauthorized() {
            myReservationView.onUnauthorized();
        }

        @Override
        public void onServerError() {
            myReservationView.onServerError();
        }
    };
}
