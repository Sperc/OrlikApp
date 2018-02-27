package com.example.pawel.orlikapp.api.client_impl;

import com.example.pawel.orlikapp.model.Player;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;
import com.example.pawel.orlikapp.api.client.PlayerClient;
import com.example.pawel.orlikapp.api.ServiceGenerator;
import com.example.pawel.orlikapp.api.BaseApiListener;
import com.example.pawel.orlikapp.utils.CodeStatus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pawel on 27.02.2018.
 */

public class PlayerServiceImpl {

    public interface ActualPlayerListener extends BaseApiListener {
        void onSuccess(Player player);
    }

    public void getActualPlayer(ActualPlayerListener actualPlayerListener) {
        PlayerClient playerClient = ServiceGenerator.createService().create(PlayerClient.class);
        Call<Player> call = playerClient.getActualPlayer(PreferencesShared.onReadString(PreferencesSharedKyes.token));
        call.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                if(response.isSuccessful()){
//                    actualPlayerListener.getPlayer(response.body());
                    actualPlayerListener.onSuccess(response.body());
                }else if(response.code()== CodeStatus.UNAUTHORIZED){
                    actualPlayerListener.onUnauthorized();
                }
                else {
                    actualPlayerListener.onServerError();
                }
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                actualPlayerListener.onServerNotResponse();
            }
        });
    }
}
