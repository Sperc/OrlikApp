package com.example.pawel.orlikapp.retrofit.ApiClient;

import com.example.pawel.orlikapp.model.Booking;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by Pawel on 04.01.2018.
 */

public interface BookingClient {
    @GET("/booking/{id}")
    Call<Booking> getBookingById(@Header("authorization") String token,
                                 @Path("id") Long id);

}
