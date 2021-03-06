package com.example.pawel.orlikapp.ui.create_player;

import com.example.pawel.orlikapp.model.Player;
import com.example.pawel.orlikapp.model.wraper.CreatePlayerObject;
import com.example.pawel.orlikapp.api.client.PlayerClient;
import com.example.pawel.orlikapp.api.ServiceGenerator;
import com.example.pawel.orlikapp.utils.CodeStatus;
import com.example.pawel.orlikapp.utils.Logs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pawel on 06.02.2018.
 */

public class CreatePlayerPresenter implements CreatePlayerInteractor.PlayerValidator {

    private CreatePlayerView createPlayerView;

    public CreatePlayerPresenter(CreatePlayerView createPlayerView) {
        this.createPlayerView = createPlayerView;
    }

    @Override
    public void emptyFirstName() {
        createPlayerView.emptyFirstName();
    }

    @Override
    public void emptyLastName() {
        createPlayerView.emptyLastName();
    }

    @Override
    public void emptyDate() {
        createPlayerView.emptyDate();
    }

    @Override
    public void incrrectDate() {
        createPlayerView.incrrectDate();
    }

    public void createPlayer(Player player, String token, byte[] imageByte) {

        if (!CreatePlayerInteractor.onPlayerValidate(player, this)) {
            Logs.d("CREATE_PLAYER_PRESENTER", "INCORRET VALIDATION");
            return;
        }

        PlayerClient playerClient = ServiceGenerator.createService().create(PlayerClient.class);
        CreatePlayerObject createPlayerObject = new CreatePlayerObject();
        createPlayerObject.setPhoto(imageByte);
        createPlayerObject.setPlayer(player);
        Call<Void> call = playerClient.addPlayer(token, createPlayerObject);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    createPlayerView.onPlayerCreate();
                } else if (response.code() == CodeStatus.UNAUTHORIZED) {
                    createPlayerView.unuthorized();
                } else {
                    createPlayerView.badRequest();
                }
                Logs.d("CREATE_PLAYER_PRESENTER", "Call method" + String.valueOf(response.code()));
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                createPlayerView.onConntectionError();
            }
        });


    }


}
