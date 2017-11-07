package com.example.pawel.orlikapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Pawel on 31.10.2017.
 */

public class MyObject implements Serializable {
    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private String id;

    public MyObject(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
