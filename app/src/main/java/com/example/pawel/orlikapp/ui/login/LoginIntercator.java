package com.example.pawel.orlikapp.ui.login;

/**
 * Created by Pawel on 13.11.2017.
 */

public interface LoginIntercator {
    public interface LoginCredentialisListener {
        public void onUsernameError();

        public void onPasswordError();
    }

    public boolean onCredentialisValidate(String username, String password, LoginCredentialisListener loginCredentialisListener);
}
