package com.example.pawel.orlikapp.api;

/**
 * Created by Pawel on 27.02.2018.
 */

public interface BaseApiListener {
    void onServerNotResponse();
    void onUnauthorized();
    void onServerError();
}
