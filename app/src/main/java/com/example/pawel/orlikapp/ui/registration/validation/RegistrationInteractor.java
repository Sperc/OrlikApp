package com.example.pawel.orlikapp.ui.registration.validation;

import com.example.pawel.orlikapp.model.AppUser;

/**
 * Created by Pawel on 12.11.2017.
 */

public interface RegistrationInteractor {
    public interface CredentialisListener{
        public void onPasswordRepeatError();
        public void onEmailError();
        public void onPasswordError();
    }
    public boolean onCredentialisValidate(AppUser appUser,
                                          CredentialisListener credentialisListener);
}
