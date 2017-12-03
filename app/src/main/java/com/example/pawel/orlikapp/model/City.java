package com.example.pawel.orlikapp.model;

import java.util.List;

/**
 * Created by Pawel on 01.12.2017.
 */

public class City {
    private Long id;
    private String name;
    private List<Playground> playgroundList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Playground> getPlaygroundList() {
        return playgroundList;
    }

    public void setPlaygroundList(List<Playground> playgroundList) {
        this.playgroundList = playgroundList;
    }

    @Override
    public String toString() {
        return name;
    }
}
