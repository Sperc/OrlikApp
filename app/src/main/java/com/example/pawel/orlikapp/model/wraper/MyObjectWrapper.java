package com.example.pawel.orlikapp.model.wraper;

import com.example.pawel.orlikapp.model.MyObject;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Pawel on 31.10.2017.
 */

public class MyObjectWrapper {
    @SerializedName("myObject")
    private MyObject myObject;

    public MyObject getMyObject() {
        return myObject;
    }

    public void setMyObject(MyObject myObject) {
        this.myObject = myObject;
    }
}
