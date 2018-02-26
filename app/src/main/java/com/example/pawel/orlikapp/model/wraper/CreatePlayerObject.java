package com.example.pawel.orlikapp.model.wraper;

import com.example.pawel.orlikapp.model.Player;

/**
 * Created by Pawel on 25.02.2018.
 */

public class CreatePlayerObject {
    private Player player;
    private byte[] photo;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
