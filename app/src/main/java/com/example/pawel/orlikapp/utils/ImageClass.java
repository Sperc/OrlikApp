package com.example.pawel.orlikapp.utils;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Pawel on 27.01.2018.
 */

public class ImageClass implements Serializable{
    private String title;
    private String image;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
