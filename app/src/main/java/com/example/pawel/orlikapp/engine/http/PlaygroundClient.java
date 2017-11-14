package com.example.pawel.orlikapp.engine.http;

import com.example.pawel.orlikapp.model.MyObject;
import com.example.pawel.orlikapp.model.Playground;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Pawel on 31.10.2017.
 */

public interface PlaygroundClient {
    @GET("test/get-all")
    Call<List<Playground>> getAllPlaygrounds();
    @GET("test/test")
    Call<MyObject> getObj();
}
