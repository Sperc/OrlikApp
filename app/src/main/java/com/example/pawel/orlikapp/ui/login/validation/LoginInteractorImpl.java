package com.example.pawel.orlikapp.ui.login.validation;

/**
 * Created by Pawel on 15.11.2017.
 */

public class LoginInteractorImpl implements LoginIntercator {
    @Override
    public boolean onCredentialisValidate(String username, String password, LoginCredentialisListener loginCredentialisListener) {
        boolean flag = true;
        if(username.equals("")){
            loginCredentialisListener.onEmptyUsernameError();
            flag = false;
        }
        if(password.equals("")){
            loginCredentialisListener.onEmptyPasswordError();
            flag = false;
        }
        return flag;
    }
}
