package com.example.pawel.orlikapp.ui.create_player;

/**
 * Created by Pawel on 06.02.2018.
 */

public interface CreatePlayerView {
    void onPlayerCreate();
    void onConntectionError();
    void badRequest();
    void unuthorized();
}
