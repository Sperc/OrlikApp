package com.example.pawel.orlikapp.ui.create_player;

import com.example.pawel.orlikapp.model.Player;
import com.example.pawel.orlikapp.prefs.PreferencesShared;
import com.example.pawel.orlikapp.prefs.PreferencesSharedKyes;
import com.example.pawel.orlikapp.retrofit.ApiClient.PlayerClient;
import com.example.pawel.orlikapp.retrofit.ServiceGenerator;
import com.example.pawel.orlikapp.utils.CodeStatus;
import com.example.pawel.orlikapp.utils.Logs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pawel on 06.02.2018.
 */

public class CreatePlayerPresenter {

    private CreatePlayerView createPlayerView;

    public CreatePlayerPresenter(CreatePlayerView createPlayerView) {
        this.createPlayerView = createPlayerView;
    }

    public void createPlayer(Player player,String token){
        PlayerClient playerClient = ServiceGenerator.createService().create(PlayerClient.class);
        Call<Void> call = playerClient.addPlayer(token,player);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    createPlayerView.onPlayerCreate();
                }
                else if(response.code()== CodeStatus.UNAUTHORIZED){
                    createPlayerView.unuthorized();
                }
                else{
                    createPlayerView.badRequest();
                }
                Logs.d("CREATE_PLAYER_PRESENTER","Call method"+String.valueOf(response.code()));
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                createPlayerView.onConntectionError();
            }
        });


    }
}
