package com.example.pawel.orlikapp.model;

import java.util.Set;

/**
 * Created by Pawel on 31.10.2017.
 */

public class Team {
    private Long id;
    private String name;
    private Long leaderId;
    private boolean isAvaiable;

    private Set<Player> setOfUsers;

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

    public Long getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Long leaderId) {
        this.leaderId = leaderId;
    }

    public boolean isAvaiable() {
        return isAvaiable;
    }

    public void setAvaiable(boolean avaiable) {
        isAvaiable = avaiable;
    }

    public Set<Player> getSetOfUsers() {
        return setOfUsers;
    }

    public void setSetOfUsers(Set<Player> setOfUsers) {
        this.setOfUsers = setOfUsers;
    }
}
