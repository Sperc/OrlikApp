package com.example.pawel.orlikapp.ui.menu.player_details;

import com.example.pawel.orlikapp.api.ServiceGenerator;
import com.example.pawel.orlikapp.api.client.client_impl.PlayerServiceImpl;
import com.example.pawel.orlikapp.model.Player;

/**
 * Created by Pawel on 09.03.2018.
 */

public class PlayerDetailsPresenter {
    private PlayerServiceImpl playerService;
    private PlayerDetailsView playerDetailsView;

    public PlayerDetailsPresenter(PlayerDetailsView playerDetailsView) {
        this.playerDetailsView = playerDetailsView;
        this.playerService = new PlayerServiceImpl();
    }
    public void getPlayerByUsername(String username){
        playerService.getPlayerByUsername(username,playerListener);
    }
    PlayerServiceImpl.PlayerListener playerListener = new PlayerServiceImpl.PlayerListener() {
        @Override
        public void onSucces(Player player) {
            playerDetailsView.getPlayer(player);
        }

        @Override
        public void onServerNotResponse() {
            playerDetailsView.onServerNotResponse();
        }

        @Override
        public void onUnauthorized() {
            playerDetailsView.onUnauthorized();
        }

        @Override
        public void onServerError() {
            playerDetailsView.onServerError();
        }
    };
}
