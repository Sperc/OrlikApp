package com.example.pawel.orlikapp.retrofit.ApiClient;

import com.example.pawel.orlikapp.model.Booking;
import com.example.pawel.orlikapp.model.Player;
import com.example.pawel.orlikapp.model.Playground;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Pawel on 28.11.2017.
 */

public interface PlayerClient {
    @GET("/player/reservation")
    Call<List<Booking>> getUserReservation(@Header("authorization") String token);

    @GET("/player/get")
    Call<Player> getActualPlayer(@Header("authorization") String token);

    @POST("/player/add")
    Call<Void> addPlayer(@Header("authorization") String token,
                           @Body Player player);
    @GET("/player/get-by-username")
    Call<Player> getPlayerByUsername(@Header("authorization") String token, @Query("username") String username);
}
