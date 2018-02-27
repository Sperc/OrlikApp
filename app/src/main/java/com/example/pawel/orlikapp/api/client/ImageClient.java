package com.example.pawel.orlikapp.api.client;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Pawel on 27.01.2018.
 */

public interface ImageClient {
    @FormUrlEncoded
    @POST("/image")
    Call<ImageClient> uploadImage(@Field("title") String title, @Field("image") String image);
}
