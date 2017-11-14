package com.example.pawel.orlikapp.ui.login.validation;

/**
 * Created by Pawel on 13.11.2017.
 */

public interface LoginIntercator {
    public interface LoginCredentialisListener {
        public void onEmptyUsernameError();

        public void onEmptyPasswordError();
    }

    public boolean onCredentialisValidate(String username, String password, LoginCredentialisListener loginCredentialisListener);
}
