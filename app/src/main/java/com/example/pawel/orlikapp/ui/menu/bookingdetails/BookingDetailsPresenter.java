package com.example.pawel.orlikapp.ui.menu.bookingdetails;

import com.example.pawel.orlikapp.model.Booking;
import com.example.pawel.orlikapp.model.Player;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;
import com.example.pawel.orlikapp.retrofit.ApiClient.BookingClient;
import com.example.pawel.orlikapp.retrofit.ServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pawel on 04.01.2018.
 */

public class BookingDetailsPresenter {
    private BookingDetailsView bookingDetailsView;

    public BookingDetailsPresenter(BookingDetailsView bookingDetailsView) {
        this.bookingDetailsView = bookingDetailsView;
    }

    public void getBookingList(Long id, final BookingListener bookingListener) {
        final BookingClient bookingClient = ServiceGenerator.createService().create(BookingClient.class);
        Call<Booking> call = bookingClient.getBookingById(PreferencesShared.onReadString(PreferencesSharedKyes.token), id);
        call.enqueue(new Callback<Booking>() {
            @Override
            public void onResponse(Call<Booking> call, Response<Booking> response) {
                if (response.isSuccessful()) {
                    bookingListener.getBooking(response.body());
                }
            }

            @Override
            public void onFailure(Call<Booking> call, Throwable t) {
                bookingDetailsView.onFailure();
            }
        });
    }

    public void addPlayerToBooking(Long booking_id, final OnBookingPlayerListener onBookingPlayerListener) {
        BookingClient bookingClient = ServiceGenerator.createService().create(BookingClient.class);
        Call<List<Player>> call = bookingClient.addPlayerToBooking(PreferencesShared.onReadString(PreferencesSharedKyes.token), booking_id, PreferencesShared.onReadString(PreferencesSharedKyes.username));
        call.enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                if (response.isSuccessful()) {
                    onBookingPlayerListener.onSucces(response.body());
                } else if (response.code() == 401) {
                    bookingDetailsView.onFailure();
                }
            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                bookingDetailsView.onFailure();
            }
        });
    }

    public void removePlayerFromBooking(Long bookingId, final OnBookingPlayerListener onBookingPlayerListener) {
        BookingClient bookingClient = ServiceGenerator.createService().create(BookingClient.class);
        Call<List<Player>> call = bookingClient.removePlayerFromBooking(PreferencesShared.onReadString(PreferencesSharedKyes.token), bookingId, PreferencesShared.onReadString(PreferencesSharedKyes.username));
        call.enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                if (response.isSuccessful()) {
                    onBookingPlayerListener.onSucces(response.body());
                } else if (response.code() == 401) {
                    bookingDetailsView.onFailure();
                }
            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {

            }
        });
    }

    public interface OnBookingPlayerListener {
        void onSucces(List<Player> playerList);
    }

    public interface BookingListener {
        public void getBooking(Booking booking);
    }

}
