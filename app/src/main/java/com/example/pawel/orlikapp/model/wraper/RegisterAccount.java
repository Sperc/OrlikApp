package com.example.pawel.orlikapp.model.wraper;

import com.example.pawel.orlikapp.model.AppUser;
import com.example.pawel.orlikapp.model.Player;

/**
 * Created by Pawel on 18.11.2017.
 */

public class RegisterAccount {
    private AppUser appUser;
    private Player player;

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
