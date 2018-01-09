package com.example.pawel.orlikapp.retrofit.ApiClient;

import com.example.pawel.orlikapp.model.Booking;
import com.example.pawel.orlikapp.model.Player;

import java.util.List;

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

    @GET("/booking/{id}/add-user/{username}")
    Call<List<Player>> addPlayerToBooking(@Header("authorization")String token,
                                          @Path("id")Long id,
                                          @Path("username")String username);
    @GET("/booking/{id}/remove-user/{username}")
    Call<List<Player>> removePlayerFromBooking(@Header("authorization")String token,
                                               @Path("id") Long id,
                                               @Path("username")String username);
    @GET("/booking/playground/{playground_id}/get-all")
    Call<List<Booking>> getBookingByPlaygroundId(@Header("authorization") String token,
                                                 @Path("playground_id")Long playground_id);

}
