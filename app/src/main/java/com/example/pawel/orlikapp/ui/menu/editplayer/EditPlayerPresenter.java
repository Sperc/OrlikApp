package com.example.pawel.orlikapp.ui.menu.editplayer;

import com.example.pawel.orlikapp.api.client.client_impl.PlayerServiceImpl;
import com.example.pawel.orlikapp.model.Player;

/**
 * Created by Pawel on 01.03.2018.
 */

public class EditPlayerPresenter {

    private EditPlayerView editPlayerView;
    private PlayerServiceImpl playerService = new PlayerServiceImpl();

    public EditPlayerPresenter(EditPlayerView editPlayerView) {
        this.editPlayerView = editPlayerView;
    }

    public void editPlayer(Player player){
        playerService.editPlayer(player,editPlayerListener);
    }

    PlayerServiceImpl.EditPlayerListener editPlayerListener = new PlayerServiceImpl.EditPlayerListener() {
        @Override
        public void onSucces() {
            editPlayerView.setSucces();
        }

        @Override
        public void onServerNotResponse() {
            editPlayerView.setServerNotResponse();
        }

        @Override
        public void onUnauthorized() {
            editPlayerView.setUnauthorized();
        }

        @Override
        public void onServerError() {
            editPlayerView.setServerError();
        }
    };

}
