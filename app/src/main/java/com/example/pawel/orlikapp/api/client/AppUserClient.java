package com.example.pawel.orlikapp.api.client;

import com.example.pawel.orlikapp.model.AppUser;
import com.example.pawel.orlikapp.model.wraper.ChangePassword;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by Pawel on 04.11.2017.
 */

public interface AppUserClient {
    @POST("/login")
    Call<Void> login(@Body AppUser appUser);

    @POST("/register")
    Call<AppUser> register(@Body AppUser appUser);

    @PUT("/change-password")
    Call<Void> changePassword(@Header("authorization") String token, @Body ChangePassword changePassword);

    @DELETE("delete-account")
    Call<Void> deleteAccount(@Header("authorization")String token);
}
