package com.example.pawel.orlikapp.api.client;

import com.example.pawel.orlikapp.model.Booking;
import com.example.pawel.orlikapp.model.Player;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Pawel on 04.01.2018.
 */

public interface BookingClient {
    @GET("/booking/{id}")
    Call<Booking> getBookingById(@Header("authorization") String token,
                                 @Path("id") Long id);

    @GET("/booking/add-user")
    Call<List<Player>> addPlayerToBooking(@Header("authorization") String token,
                                          @Query("id") Long id,
                                          @Query("username") String username);

    @GET("/booking/remove-user/")
    Call<List<Player>> removePlayerFromBooking(@Header("authorization") String token,
                                               @Query("id") Long id,
                                               @Query("username") String username);

    @GET("/booking/playground/{playground_id}/get-all")
    Call<List<Booking>> getBookingByPlaygroundId(@Header("authorization") String token,
                                                 @Path("playground_id") Long playground_id);

    @GET("/booking/playground/{playground_id}/{date}")
    Call<List<Booking>> getBookingByPlaygroundIdAndDate(@Header("authorization") String token,
                                                        @Path("playground_id") Long id,
                                                        @Path("date") String date);
    @POST("/booking/add")
    Call<Void> addBooking(@Header("authorization") String token,
                    @Body Booking booking);
}
