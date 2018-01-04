package com.example.pawel.orlikapp.ui.menu.bookingdetails;

import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.pawel.orlikapp.model.Booking;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;
import com.example.pawel.orlikapp.retrofit.ApiClient.BookingClient;
import com.example.pawel.orlikapp.retrofit.ServiceGenerator;

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


    public interface BookingListener {
        public void getBooking(Booking booking);
    }

}
