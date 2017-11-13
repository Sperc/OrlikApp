package com.example.pawel.orlikapp.ui.registration;

/**
 * Created by Pawel on 12.11.2017.
 */

public interface RegistrationInteractor {
    public interface CredentialisListener{
        public void onPasswordRepeatError();
        public void onEmailError();
        public void onUsernameError();
        public void onPasswordError();
        public void onFirstNameError();
        public void onLastNameError();
    }
    public boolean onCredentialisValidate(String email,String password,String repeatPassword,
                                          String username,String firstName,String lastName,
                                          CredentialisListener credentialisListener);
}
