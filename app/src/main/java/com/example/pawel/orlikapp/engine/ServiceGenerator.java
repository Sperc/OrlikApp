package com.example.pawel.orlikapp.engine;

import com.example.pawel.orlikapp.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Pawel on 31.10.2017.
 */

public class ServiceGenerator {
    public static final String BASE_URL = "http://192.168.0.185:8080/";
//    public static final String BASE_URL = "http://192.168.1.26:8080/";
    //public static final String BASE_URL = "http://192.168.0.185:8080/";

//    public static Retrofit client() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://192.168.1.17:8080")
//                //.client(client)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        return retrofit;
//    }

    public static Retrofit createService() {
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (BuildConfig.DEBUG) {
            okBuilder.addInterceptor(loggingInterceptor);
        }
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okBuilder.build());
        Retrofit retrofit = builder.build();
        return retrofit;
    }
}
