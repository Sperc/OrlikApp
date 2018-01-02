package com.example.pawel.orlikapp.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by Pawel on 31.10.2017.
 */

public class Booking implements Serializable{
    private Long id;
    //yyyy-MM-dd
    private String date;
    private double startOrder;
    private double endOrder;
    private int maxNumberOfPlayer = 14;
    private boolean isAvailable = true;
    private String leaderName;

    private List<Player> players;
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

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Playground getPlayground() {
        return playground;
    }

    public void setPlayground(Playground playground) {
        this.playground = playground;
    }
}
