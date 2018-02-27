package com.example.pawel.orlikapp.api.client;

import com.example.pawel.orlikapp.model.MyObject;
import com.example.pawel.orlikapp.model.Playground;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by Pawel on 31.10.2017.
 */

public interface PlaygroundClient {
    @GET("test/get-all")
    Call<List<Playground>> getAllPlaygrounds();

    @GET("test/test")
    Call<MyObject> getObj();

    @GET("/test/get/{cityName}")
    Call<List<Playground>> getAllPlaygroundByCity(@Header("authorization") String token,
                                                  @Path("cityName") String cityName);

    @GET("/test/get/{cityName}/{category}")
    Call<List<Playground>> getAllPlaygroudByCityAndCategory(@Header("authorization") String token,
                                                            @Path("cityName") String cityName,
                                                            @Path("category") String category);
}
