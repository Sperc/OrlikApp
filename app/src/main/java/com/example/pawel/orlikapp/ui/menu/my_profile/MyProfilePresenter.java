package com.example.pawel.orlikapp.ui.menu.my_profile;

import com.example.pawel.orlikapp.api.client.client_impl.PlayerServiceImpl;
import com.example.pawel.orlikapp.model.Player;

/**
 * Created by Pawel on 08.01.2018.
 */

public class MyProfilePresenter {

    private PlayerServiceImpl playerService;
    private MyProfileView myProfileView;

    public MyProfilePresenter(MyProfileView myProfileView) {
        playerService = new PlayerServiceImpl();
        this.myProfileView = myProfileView;
    }

    public void getUser() {
        playerService.getActualPlayer(actualPlayerListener);
    }

    PlayerServiceImpl.ActualPlayerListener actualPlayerListener = new PlayerServiceImpl.ActualPlayerListener() {
        @Override
        public void onSuccess(Player player) {
            myProfileView.onGetPlayer(player);
        }

        @Override
        public void onServerNotResponse() {
            myProfileView.onServerNotResponse();
        }

        @Override
        public void onUnauthorized() {
            myProfileView.onUnauthorized();
        }

        @Override
        public void onServerError() {
            myProfileView.onServerError();
        }
    };
}
