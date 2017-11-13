package com.example.pawel.orlikapp.ui.login;

import android.content.Context;
import android.widget.Toast;

import com.example.pawel.orlikapp.engine.ServiceGenerator;
import com.example.pawel.orlikapp.engine.httpApi.LoginAndRegisterClient;
import com.example.pawel.orlikapp.model.AppUser;
import com.example.pawel.orlikapp.prefs.SharedPrefs;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pawel on 05.11.2017.
 */

public class LoginPresenter implements LoginIntercator,LoginIntercator.LoginCredentialisListener {
    Context context;
    LoginPresenterListener loginPresenterListener;
    LoginView loginView;
    @Override
    public boolean onCredentialisValidate(String username, String password, LoginCredentialisListener loginCredentialisListener) {
        boolean flag = true;
        if(username.equals("")){
            loginCredentialisListener.onUsernameError();
            flag = false;
        }
        if(password.equals("")){
            loginCredentialisListener.onPasswordError();
            flag = false;
        }
        return flag;
    }

    public interface LoginPresenterListener{
        void loginSucces(AppUser appUser);
        void loginFailure();
    }

    public LoginPresenter(LoginPresenterListener loginPresenterListener,Context context,LoginView loginView) {
        this.loginPresenterListener = loginPresenterListener;
        this.context = context;
        this.loginView = loginView;
    }

    @Override
    public void onUsernameError() {
        loginView.setUsernameError();
    }

    @Override
    public void onPasswordError() {
        loginView.setPasswordError();
    }




    public void login(final String username, String password){
        if(!onCredentialisValidate(username,password,this)){
            return;
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
