package com.example.pawel.orlikapp.utils;

/**
 * Created by Pawel on 09.01.2018.
 */

public class Time {
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
}
