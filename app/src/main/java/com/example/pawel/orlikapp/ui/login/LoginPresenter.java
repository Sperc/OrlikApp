package com.example.pawel.orlikapp.ui.login;

import android.util.Log;
import android.widget.Toast;

import com.example.pawel.orlikapp.engine.ServiceGenerator;
import com.example.pawel.orlikapp.engine.httpApi.LoginAndRegisterClient;
import com.example.pawel.orlikapp.model.AppUser;
import com.example.pawel.orlikapp.ui.base.BasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pawel on 05.11.2017.
 */

public class LoginPresenter extends BasePresenter<LoginActivity>{
    LoginActivity activity;
    LoginActiivityInterface loginPresenterInterface;

    @Override
    public void onLoad(LoginActivity activity) {
        this.activity = activity;
    }
    public LoginPresenter(LoginActiivityInterface loginPresenterInterface){
        this.loginPresenterInterface =loginPresenterInterface;
    }

    public void login(String username, String password){
        final AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(password);
        LoginAndRegisterClient loginAndRegisterClient = ServiceGenerator.createService().create(LoginAndRegisterClient.class);
        Call<AppUser> call = loginAndRegisterClient.login(user);
        call.enqueue(new Callback<AppUser>() {
            @Override
            public void onResponse(Call<AppUser> call, Response<AppUser> response) {
                //Toast.makeText(activity.getApplicationContext(),response.body().getUsername(),Toast.LENGTH_LONG).show();
                loginPresenterInterface.onAction(response.body());
            }

            @Override
            public void onFailure(Call<AppUser> call, Throwable t) {
                Toast.makeText(activity.getApplicationContext(),"not",Toast.LENGTH_LONG).show();
            }
        });
    }


}
