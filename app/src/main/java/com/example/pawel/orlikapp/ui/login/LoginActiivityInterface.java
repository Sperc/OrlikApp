package com.example.pawel.orlikapp.ui.login;

import com.example.pawel.orlikapp.model.AppUser;

/**
 * Created by Pawel on 06.11.2017.
 */

public interface LoginActiivityInterface {
    public void onLoginSucces(AppUser appUser);

    public void login(String username, String password);

}
