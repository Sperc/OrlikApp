package com.example.pawel.orlikapp.model;

import java.io.Serializable;

/**
 * Created by Pawel on 25.02.2018.
 */

public class Picture implements Serializable {
    private Long id;
    private String name;

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
}
