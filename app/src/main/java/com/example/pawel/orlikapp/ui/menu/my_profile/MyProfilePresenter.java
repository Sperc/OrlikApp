package com.example.pawel.orlikapp.ui.menu.my_profile;

import com.example.pawel.orlikapp.model.Player;
import com.example.pawel.orlikapp.model.Playground;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;
import com.example.pawel.orlikapp.retrofit.ApiClient.PlayerClient;
import com.example.pawel.orlikapp.retrofit.ApiClient.PlaygroundClient;
import com.example.pawel.orlikapp.retrofit.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pawel on 08.01.2018.
 */

public class MyProfilePresenter {

    public void getPlayer(){
        PlayerClient playerClient = ServiceGenerator.createService().create(PlayerClient.class);
        Call<Player> call = playerClient.getActualPlayer(PreferencesShared.onReadString(PreferencesSharedKyes.token));
        call.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {

            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {

            }
        });
    }
}
