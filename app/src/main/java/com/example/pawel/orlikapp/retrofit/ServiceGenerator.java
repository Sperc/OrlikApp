package com.example.pawel.orlikapp.retrofit;

import com.example.pawel.orlikapp.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Pawel on 31.10.2017.
 */

public class ServiceGenerator {
    public static final String BASE_URL = "http://192.168.1.26:8080/";
//    public static final String BASE_URL = "http://192.168.43.100:8080/";
//    public static final String BASE_URL = "http://192.168.0.185:8080/";
    //        public static final String BASE_URL = "http://192.168.1.16:8080/";
//    public static final String BASE_URL = "http://192.168.1.19:8080/";

    public static final String BASE_URL_IMAGE = BASE_URL + "picture/get/";

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
