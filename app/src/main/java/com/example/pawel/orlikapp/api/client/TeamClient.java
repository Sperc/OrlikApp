package com.example.pawel.orlikapp.api.client;

import com.example.pawel.orlikapp.model.Team;

import java.util.Set;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by Pawel on 20.11.2017.
 */

public interface TeamClient {
    @GET("/teams/my")
    public Call<Set<Team>> getMyTeams(@Header("authorization") String token);
}
