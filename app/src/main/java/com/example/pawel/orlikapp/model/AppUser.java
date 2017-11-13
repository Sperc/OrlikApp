package com.example.pawel.orlikapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Pawel on 31.10.2017.
 */

public class AppUser {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private List<String> roles = new ArrayList<>();

    private Set<Booking> usersBookingSet;
    private Set<Team> userTeams;

    public AppUser() {
    }

    public AppUser(String firstName, String lastName, String username, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Set<Booking> getUsersBookingSet() {
        return usersBookingSet;
    }

    public void setUsersBookingSet(Set<Booking> usersBookingSet) {
        this.usersBookingSet = usersBookingSet;
    }

    public Set<Team> getUserTeams() {
        return userTeams;
    }

    public void setUserTeams(Set<Team> userTeams) {
        this.userTeams = userTeams;
    }
}