package com.example.pawel.orlikapp.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by Pawel on 31.10.2017.
 */

public class Booking implements Serializable {
    private Long id;
    //yyyy-MM-dd
    private String date;
    private String endDate;
    private int startOrderHour;
    private int startOrderMinutes;
    private int endOrderHour;
    private int endOrderMinutes;
    private int maxNumberOfPlayer = 14;
    private boolean available;
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

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getStartOrderHour() {
        return startOrderHour;
    }

    public void setStartOrderHour(int startOrderHour) {
        this.startOrderHour = startOrderHour;
    }

    public int getStartOrderMinutes() {
        return startOrderMinutes;
    }

    public void setStartOrderMinutes(int startOrderMinutes) {
        this.startOrderMinutes = startOrderMinutes;
    }

    public int getEndOrderHour() {
        return endOrderHour;
    }

    public void setEndOrderHour(int endOrderHour) {
        this.endOrderHour = endOrderHour;
    }

    public int getEndOrderMinutes() {
        return endOrderMinutes;
    }

    public void setEndOrderMinutes(int endOrderMinutes) {
        this.endOrderMinutes = endOrderMinutes;
    }

    public int getMaxNumberOfPlayer() {
        return maxNumberOfPlayer;
    }

    public void setMaxNumberOfPlayer(int maxNumberOfPlayer) {
        this.maxNumberOfPlayer = maxNumberOfPlayer;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
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
