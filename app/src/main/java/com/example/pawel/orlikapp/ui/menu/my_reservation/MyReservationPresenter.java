package com.example.pawel.orlikapp.ui.menu.my_reservation;

import android.content.Context;

import com.example.pawel.orlikapp.model.Booking;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;
import com.example.pawel.orlikapp.retrofit.ApiClient.PlayerClient;
import com.example.pawel.orlikapp.retrofit.ServiceGenerator;
import com.example.pawel.orlikapp.utils.Logs;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pawel on 27.11.2017.
 */

public class MyReservationPresenter {
    private Context context;
    private ReservationListener reservationListener;

    public MyReservationPresenter(Context context, ReservationListener reservationListener) {
        this.reservationListener = reservationListener;
        this.context = context;
    }

    public void getUserRerservation() {
        PlayerClient playerClient = ServiceGenerator.createService().create(PlayerClient.class);
        Call<List<Booking>> call = playerClient.getUserReservation(PreferencesShared.onReadString(PreferencesSharedKyes.token));
        Logs.d(this.toString(), "getUserReservation");
        call.enqueue(new Callback<List<Booking>>() {
            @Override
            public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
                if (response.isSuccessful()) {
                    MyReservationFilter reservationFilter = new MyReservationFilter();
                    List<Booking> ownReservation = reservationFilter.getUserOwnReservation(response.body(), PreferencesShared.onReadString(PreferencesSharedKyes.username));
                    List<Booking> memberReservation = reservationFilter.getMemberReservation(response.body(),PreferencesShared.onReadString(PreferencesSharedKyes.username));
                    reservationListener.onSucces(ownReservation,memberReservation);
                }

            }

            @Override
            public void onFailure(Call<List<Booking>> call, Throwable t) {

            }
        });
    }

    public interface ReservationListener {
        void onSucces(List<Booking> ownReservation, List<Booking> memberReservation);

        void onFailure();
    }
}
