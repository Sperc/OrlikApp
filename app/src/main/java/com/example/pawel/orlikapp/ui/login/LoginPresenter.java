package com.example.pawel.orlikapp.ui.login;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.pawel.orlikapp.engine.ServiceGenerator;
import com.example.pawel.orlikapp.engine.httpApi.LoginAndRegisterClient;
import com.example.pawel.orlikapp.model.AppUser;
import com.example.pawel.orlikapp.prefs.SharedPrefs;
import com.example.pawel.orlikapp.ui.base.BasePresenter;

import java.util.List;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pawel on 05.11.2017.
 */

public class LoginPresenter{
    Context context;
    LoginPresenterListener loginPresenterListener;

    public interface LoginPresenterListener{
        void loginSucces(AppUser appUser);
        void loginFailure();
    }

    public LoginPresenter(LoginPresenterListener loginPresenterListener,Context context) {
        this.loginPresenterListener = loginPresenterListener;
        this.context = context;
    }

    public void login(final String username, String password){
        if(username==null || password==null){
            loginPresenterListener.loginFailure();
            throw new IllegalArgumentException("username or password is null");
        }
        final AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(password);
        LoginAndRegisterClient loginAndRegisterClient = ServiceGenerator.createService().create(LoginAndRegisterClient.class);
        Call<AppUser> call = loginAndRegisterClient.login(user);
        call.enqueue(new Callback<AppUser>() {
            @Override
            public void onResponse(Call<AppUser> call, Response<AppUser> response) {
                if(response.isSuccessful()){
                    SharedPrefs sharedPrefs = new SharedPrefs(context);
                    Headers headers = response.headers();
                    sharedPrefs.storeUser(username);
                    sharedPrefs.storeToken(headers.get("token"));
                    loginPresenterListener.loginSucces(response.body());
                }
                else{
                    loginPresenterListener.loginFailure();
                }
            }

            @Override
            public void onFailure(Call<AppUser> call, Throwable t) {
                Toast.makeText(context.getApplicationContext(),"Problem z serwerem",Toast.LENGTH_SHORT).show();
            }
        });
    }


}
