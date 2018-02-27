package com.example.pawel.orlikapp.ui.menu.my_profile;

import com.example.pawel.orlikapp.api.client_impl.PlayerServiceImpl;

/**
 * Created by Pawel on 08.01.2018.
 */

public class MyProfilePresenter {
    GetActualPlayer onGetPlayerList;

    MyProfileFragment fragment;


    public MyProfilePresenter(MyProfileFragment fragment) {
        this.fragment = fragment;
        onGetPlayerList = new GetActualPlayer(fragment);
    }

    public void getUser() {
        PlayerServiceImpl playerService = new PlayerServiceImpl();
        playerService.getActualPlayer(onGetPlayerList);
    }
}
