package com.example.pawel.orlikapp.ui.registration.validation;

import com.example.pawel.orlikapp.utils.Validation;

/**
 * Created by Pawel on 14.11.2017.
 */

public class RegistrationInteractorImpl implements RegistrationInteractor {
    @Override
    public boolean onCredentialisValidate(String email, String password, String repeatPassword, String username, String firstName, String lastName, CredentialisListener credentialisListener) {
        boolean flag = true;
        if (!password.equals(repeatPassword) || password.equals("")) {
            credentialisListener.onPasswordRepeatError();
            flag = false;
        }
        if (!Validation.onEmailAddresValidation(email) || email.equals("")) {
            credentialisListener.onEmailError();
            flag = false;
        }
        if (password.equals("")) {
            credentialisListener.onPasswordError();
            flag = false;
        }
        if (username.equals("")) {
            credentialisListener.onUsernameError();
            flag = false;
        }
        if (firstName.equals("")) {
            credentialisListener.onFirstNameError();
            flag = false;
        }
        if (lastName.equals("")) {
            credentialisListener.onLastNameError();
            flag = false;
        }
        return flag;
//        return true;
    }
}
