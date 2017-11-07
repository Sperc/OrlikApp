package com.example.pawel.orlikapp.model.wraper;

import com.example.pawel.orlikapp.model.Playground;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Pawel on 02.11.2017.
 */

public class PlaygroundWrapper {
    @SerializedName("List")
    private List<Playground> playground;

    public List<Playground> getPlayground() {
        return playground;
    }

    public void setPlayground(List<Playground> playground) {
        this.playground = playground;
    }
}
