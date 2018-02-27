package com.example.pawel.orlikapp.api.client;

import com.example.pawel.orlikapp.model.City;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by Pawel on 01.12.2017.
 */

public interface CityClient {
    @GET("/city/all")
    Call<List<City>> getAllCity(@Header("authorization")String token);
}
