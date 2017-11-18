package com.example.pawel.orlikapp.model;

import java.util.Set;

/**
 * Created by Pawel on 31.10.2017.
 */

public class Booking {
    private Long id;
    //yyyy-MM-dd
    private String date;
    private double startOrder;
    private double endOrder;
    private int maxNumberOfPlayer = 14;
    private boolean isAvailable = true;
    private Long leaderId;

    private Set<Player> appUserSet;
    private Playground playground;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getStartOrder() {
        return startOrder;
    }

    public void setStartOrder(double startOrder) {
        this.startOrder = startOrder;
    }

    public double getEndOrder() {
        return endOrder;
    }

    public void setEndOrder(double endOrder) {
        this.endOrder = endOrder;
    }

    public int getMaxNumberOfPlayer() {
        return maxNumberOfPlayer;
    }

    public void setMaxNumberOfPlayer(int maxNumberOfPlayer) {
        this.maxNumberOfPlayer = maxNumberOfPlayer;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Long getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Long leaderId) {
        this.leaderId = leaderId;
    }

    public Set<Player> getAppUserSet() {
        return appUserSet;
    }

    public void setAppUserSet(Set<Player> appUserSet) {
        this.appUserSet = appUserSet;
    }

    public Playground getPlayground() {
        return playground;
    }

    public void setPlayground(Playground playground) {
        this.playground = playground;
    }
}
