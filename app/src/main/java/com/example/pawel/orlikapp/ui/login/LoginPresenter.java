package com.example.pawel.orlikapp.ui.login;

import android.content.Context;
import android.widget.Toast;

import com.example.pawel.orlikapp.retrofit.ServiceGenerator;
import com.example.pawel.orlikapp.retrofit.ApiClient.LoginAndRegisterClient;
import com.example.pawel.orlikapp.model.AppUser;
import com.example.pawel.orlikapp.model.Player;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;
import com.example.pawel.orlikapp.ui.login.validation.LoginInteractorImpl;
import com.example.pawel.orlikapp.ui.login.validation.LoginIntercator;
import com.example.pawel.orlikapp.utils.CodeStatus;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pawel on 05.11.2017.
 */

public class LoginPresenter implements LoginIntercator.LoginCredentialisListener {
    Context context;
    private LoginPresenterListener loginPresenterListener;
    private LoginView loginView;
    private LoginIntercator loginIntercator;
//    private SharedPrefs sharedPrefs;


    public interface LoginPresenterListener {
        void loginSucces(Player player);

        void onFirstLogin();

        void loginFailure();

        void onServerError();
    }

    public LoginPresenter(LoginPresenterListener loginPresenterListener, Context context, LoginView loginView) {
        this.loginPresenterListener = loginPresenterListener;
        this.context = context;
        this.loginView = loginView;
        loginIntercator = new LoginInteractorImpl();
//        sharedPrefs = new SharedPrefs(context);
    }

    @Override
    public void onEmptyUsernameError() {
        loginView.setUsernameError();
    }

    @Override
    public void onEmptyPasswordError() {
        loginView.setPasswordError();
    }


    public void onLogin(final String username, String password) {
        if (!loginIntercator.onCredentialisValidate(username, password, this)) {
            return;
        }
        final AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(password);
        LoginAndRegisterClient loginAndRegisterClient = ServiceGenerator.createService().create(LoginAndRegisterClient.class);
        Call<Player> call = loginAndRegisterClient.login(user);
        call.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                Headers headers = response.headers();
                if (response.isSuccessful()) {
//                    SharedPrefs sharedPrefs = new SharedPrefs(context);
                    PreferencesShared.onStoreData(PreferencesSharedKyes.username, username);
                    PreferencesShared.onStoreData(PreferencesSharedKyes.token, headers.get("authorization"));
                    loginPresenterListener.loginSucces(response.body());
                } else if (response.code() == CodeStatus.NOT_FOUND) {
                    PreferencesShared.onStoreData(PreferencesSharedKyes.username, username);
                    PreferencesShared.onStoreData(PreferencesSharedKyes.token, headers.get("authorization"));
                    loginPresenterListener.onFirstLogin();
                } else if (response.code() == CodeStatus.UNAUTHORIZED) {
                    loginPresenterListener.loginFailure();
                } else
                    loginPresenterListener.onServerError();

            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                loginPresenterListener.onServerError();
//                Toast.makeText(context.getApplicationContext(), "Problem z serwerem", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
