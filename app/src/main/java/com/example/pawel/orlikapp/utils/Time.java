package com.example.pawel.orlikapp.utils;

import java.io.Serializable;

/**
 * Created by Pawel on 09.01.2018.
 */

public class Time implements Serializable{
    private int hour;
    private int minutes;

    public Time(int hour, int minutes) {
        this.hour = hour;
        this.minutes = minutes;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getAllTimeInMinutes(){
        return hour * 60 +minutes;
    }

    public Time sumToActualTime(Time t){
        int tMinutes = t.getAllTimeInMinutes();
        int actualMinutes = getAllTimeInMinutes();
        return new Time(actualMinutes/60,actualMinutes%60);
    }
}
