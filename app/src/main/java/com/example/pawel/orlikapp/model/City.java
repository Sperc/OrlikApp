package com.example.pawel.orlikapp.model;

import java.util.List;

/**
 * Created by Pawel on 01.12.2017.
 */

public class City {
    private Long id;
    private String name;
    private String latitude;
    private String longitude;
    private List<Playground> playgroundList;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

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
