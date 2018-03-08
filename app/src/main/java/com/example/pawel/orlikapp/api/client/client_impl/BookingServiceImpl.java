package com.example.pawel.orlikapp.api.client.client_impl;

import com.example.pawel.orlikapp.api.BaseApiListener;
import com.example.pawel.orlikapp.api.ServiceGenerator;
import com.example.pawel.orlikapp.api.client.BookingClient;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;
import com.example.pawel.orlikapp.ui.base.BaseListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pawel on 08.03.2018.
 */

public class BookingServiceImpl {
    private BookingClient bookinClient;

    public BookingServiceImpl() {
        this.bookinClient = ServiceGenerator.createService().create(BookingClient.class);
    }

    public interface DeleteBookingListener extends BaseListener{
        public void onSucces();
    }
    public void onDeleteBooking(Long id,DeleteBookingListener deleteBookingListener){
        Call<Void> call = bookinClient.onDeleteBooking(PreferencesShared.onReadString(PreferencesSharedKyes.token),id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    deleteBookingListener.onSucces();
                }
                else {
                    deleteBookingListener.onFailure(response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                deleteBookingListener.onServerNotResponse();
            }
        });

    }
}
