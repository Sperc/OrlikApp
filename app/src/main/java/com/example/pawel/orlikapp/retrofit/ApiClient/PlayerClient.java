package com.example.pawel.orlikapp.retrofit.ApiClient;

import com.example.pawel.orlikapp.model.Booking;
import com.example.pawel.orlikapp.model.Player;
import com.example.pawel.orlikapp.model.Playground;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by Pawel on 28.11.2017.
 */

public interface PlayerClient {
    @GET("/player/reservation")
    Call<List<Booking>> getUserReservation(@Header("authorization")String token);
}
