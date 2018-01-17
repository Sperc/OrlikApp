package com.example.pawel.orlikapp.ui.menu.details_playground;

import com.example.pawel.orlikapp.model.Booking;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;
import com.example.pawel.orlikapp.retrofit.ApiClient.BookingClient;
import com.example.pawel.orlikapp.retrofit.ServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pawel on 06.01.2018.
 */

public class DetailsPlaygroundPresenter {
    private DetailsPlaygroundView detailsPlaygroundView;

    public DetailsPlaygroundPresenter() {
    }

    public DetailsPlaygroundPresenter(DetailsPlaygroundView detailsPlaygroundView) {
        this.detailsPlaygroundView = detailsPlaygroundView;
    }

    public void getBookingByPlaygroundId(Long playgroundId, final DetailsPlaygroundListener detailsPlaygroundListener) {
        BookingClient bookingClient = ServiceGenerator.createService().create(BookingClient.class);
        Call<List<Booking>> call = bookingClient.getBookingByPlaygroundId(PreferencesShared.onReadString(PreferencesSharedKyes.token), playgroundId);
        call.enqueue(new Callback<List<Booking>>() {
            @Override
            public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
                if (response.isSuccessful()) {
                    detailsPlaygroundListener.getBookingList(response.body());
                } else {

                }
            }

            @Override
            public void onFailure(Call<List<Booking>> call, Throwable t) {
            }
        });
    }

    public void getSortedBookingByPlaygroundIdAndDate(Long playgroundId, String date, final DetailsPlaygroundListener detailsPlaygroundListener) {
        BookingClient bookingClient = ServiceGenerator.createService().create(BookingClient.class);
        Call<List<Booking>> call = bookingClient.getBookingByPlaygroundIdAndDate(PreferencesShared.onReadString(PreferencesSharedKyes.token),
                playgroundId,date);
        call.enqueue(new Callback<List<Booking>>() {
            @Override
            public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
                if(response.isSuccessful()){
                    detailsPlaygroundListener.getBookingList(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<Booking>> call, Throwable t) {

            }
        });
    }

    public interface DetailsPlaygroundListener {
        void getBookingList(List<Booking> bookingList);
    }
}
