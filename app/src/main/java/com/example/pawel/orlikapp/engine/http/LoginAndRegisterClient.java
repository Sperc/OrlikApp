package com.example.pawel.orlikapp.engine.http;

import com.example.pawel.orlikapp.model.AppUser;
import com.example.pawel.orlikapp.model.Player;
import com.example.pawel.orlikapp.model.wraper.RegisterAccount;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Pawel on 04.11.2017.
 */

public interface LoginAndRegisterClient {
    @POST("/login")
    Call<Player> login(@Body AppUser appUser);
    @POST("/register")
    Call<Player> register(@Body RegisterAccount registerAccount);
}
