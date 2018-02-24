package com.example.pawel.orlikapp.ui.menu.main;

import android.content.Context;

import com.example.pawel.orlikapp.model.Player;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;
import com.example.pawel.orlikapp.retrofit.ApiClient.PlayerClient;
import com.example.pawel.orlikapp.retrofit.ServiceGenerator;
import com.example.pawel.orlikapp.ui.login.LoginPresenter;
import com.example.pawel.orlikapp.utils.CodeStatus;
import com.example.pawel.orlikapp.utils.Logs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pawel on 26.11.2017.
 */

public class MainActivityPresenter {

    public interface ActualPlayerListener {
        void succes(Player player);

        void notFound();
    }

    public void getActualPlayer(final LoginPresenter.ActualPlayerListener actualPlayerListener) {
        PlayerClient playerClient = ServiceGenerator.createService().create(PlayerClient.class);
        Call<Player> call = playerClient.getActualPlayer(PreferencesShared.onReadString(PreferencesSharedKyes.token));
        call.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                Logs.d("LOGIN_PRESENTER_GET_ACTUAL_PLAYER_RESPONSE", "code: " + response.code());
                if (response.isSuccessful()) {
                    actualPlayerListener.succes(response.body());
                } else if (response.code() == CodeStatus.NOT_FOUND) {
                    actualPlayerListener.notFound();
                    Logs.d("LOGIN_PRESENTER_GET_ACTUAL_PLAYER_RESPONSE", "code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                actualPlayerListener.notFound();
                t.printStackTrace();
            }
        });
    }

}
