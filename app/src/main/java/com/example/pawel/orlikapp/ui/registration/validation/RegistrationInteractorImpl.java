package com.example.pawel.orlikapp.ui.registration.validation;

import com.example.pawel.orlikapp.model.AppUser;
import com.example.pawel.orlikapp.utils.Validation;

/**
 * Created by Pawel on 14.11.2017.
 */

public class RegistrationInteractorImpl implements RegistrationInteractor {
    @Override
    public boolean onCredentialisValidate(AppUser appUser, CredentialisListener credentialisListener) {
        boolean flag = true;
        if (!appUser.getPassword().equals(appUser.getRepeatPassword()) || appUser.getRepeatPassword().equals("")) {
            credentialisListener.onPasswordRepeatError();
            flag = false;
        }
        if (!Validation.onEmailAddresValidation(appUser.getUsername()) || appUser.getUsername().equals("")) {
            credentialisListener.onEmailError();
            flag = false;
        }
        if (appUser.getPassword().equals("")) {
            credentialisListener.onPasswordError();
            flag = false;
        }
        return flag;
//        return true;
    }
}
