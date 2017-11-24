package com.example.pawel.orlikapp.ui.registration;

import android.content.Context;
import android.widget.Toast;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.retrofit.ServiceGenerator;
import com.example.pawel.orlikapp.retrofit.ApiClient.LoginAndRegisterClient;
import com.example.pawel.orlikapp.model.AppUser;
import com.example.pawel.orlikapp.model.Player;
import com.example.pawel.orlikapp.model.wraper.RegisterAccount;
import com.example.pawel.orlikapp.ui.registration.validation.RegistrationInteractor;
import com.example.pawel.orlikapp.ui.registration.validation.RegistrationInteractorImpl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pawel on 12.11.2017.
 */

public class RegistrationPresenter implements RegistrationInteractor.CredentialisListener {
    private Context context;
    private RegistrationView registrationView;
    private PresenterListener listener;
    private RegistrationInteractor registrationInteractor;
    public interface PresenterListener {
        public void onSuccesRegistration();
        public void onFailure();
    }

    public RegistrationPresenter(Context context, PresenterListener listener, RegistrationView registrationView) {
        this.context = context;
        this.listener = listener;
        this.registrationView = registrationView;
        registrationInteractor = new RegistrationInteractorImpl();
    }

    @Override
    public void onPasswordRepeatError() {
        registrationView.setRepeatPasswordError();
    }

    @Override
    public void onEmailError() {
        registrationView.setEmailError();
    }

    @Override
    public void onUsernameError() {
        registrationView.setUsernameError();
    }

    @Override
    public void onPasswordError() {
        registrationView.setPasswordError();
    }

    @Override
    public void onFirstNameError() {
        registrationView.setFirstNameError();
    }

    @Override
    public void onLastNameError() {
        registrationView.setLastNameError();
    }



    public void onRegister(String email, String password, String repeatPassword, String username,
                             String firstName, String lastName) {
        if (!registrationInteractor.onCredentialisValidate(email, password, repeatPassword, username, firstName, lastName, this)) {
            return;
        }
        AppUser appUser = new AppUser();
        appUser.setPassword(password);
        appUser.setUsername(username);
        Player player = new Player(firstName,lastName,username,email);
        RegisterAccount registerAccount = new RegisterAccount();
        registerAccount.setAppUser(appUser);
        registerAccount.setPlayer(player);
        LoginAndRegisterClient loginAndRegisterClient = ServiceGenerator.createService().create(LoginAndRegisterClient.class);
        Call<Player> call = loginAndRegisterClient.register(registerAccount);
        call.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                if (response.isSuccessful()) {
                    listener.onSuccesRegistration();
                } else {
                    listener.onFailure();
                }
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                Toast.makeText(context, R.string.serverProblem, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
