package com.example.pawel.orlikapp.ui.menu.create_booking;

import com.example.pawel.orlikapp.model.Booking;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;
import com.example.pawel.orlikapp.retrofit.ApiClient.BookingClient;
import com.example.pawel.orlikapp.retrofit.ServiceGenerator;
import com.example.pawel.orlikapp.utils.Logs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pawel on 21.01.2018.
 */

public class CreateBookingPresenter {

    public void addBooking(Booking booking, final AddBookingListener addBookingListener){
        BookingClient bookingClient = ServiceGenerator.createService().create(BookingClient.class);
        Call<Void> call = bookingClient.addBooking(PreferencesShared.onReadString(PreferencesSharedKyes.token),booking);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    addBookingListener.onSucces();
                }
                Logs.d("CREATE_BOOKING_PRESENTER","SERVER RESPONSE: "+ response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Logs.d("CREATE_BOOKING_PRESENTER","CONNECTION FAILED");
            }
        });
    }
    interface AddBookingListener{
        void onSucces();
    }
}
