package com.example.pawel.orlikapp.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by Pawel on 18.11.2017.
 */

public class Player implements Serializable{
    private Long id;
    private String firstName;
    private String lastName;
    private String username;

    private List<Booking> bookingList;
    private Set<Team> userTeams;

    public Player(String firstName, String lastName, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    public Set<Team> getUserTeams() {
        return userTeams;
    }

    public void setUserTeams(Set<Team> userTeams) {
        this.userTeams = userTeams;
    }

    @Override
    public String toString() {
        return "nick: " + username+"\n"+firstName+" "+lastName;
    }
}
