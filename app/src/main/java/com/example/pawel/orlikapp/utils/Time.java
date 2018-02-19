package com.example.pawel.orlikapp.utils;

import java.io.Serializable;

/**
 * Created by Pawel on 09.01.2018.
 */

public class Time implements Serializable {
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

    public int getAllTimeInMinutes() {
        return hour * 60 + minutes;
    }

    public Time sumToActualTime(Time t) {
        int tMinutes = t.getAllTimeInMinutes();
        int actualMinutes = getAllTimeInMinutes();
        int sumMinutes = tMinutes + actualMinutes;
        return new Time(sumMinutes / 60, sumMinutes % 60);
    }

    private String convertIntToTimeDisplayString(int w) {
        String time = String.valueOf(w);
        if (w < 10) {
            time = "0" + w;
        }
        return time;
    }

    public String displayTime() {
        String hour = convertIntToTimeDisplayString(getHour());
        String minutes = convertIntToTimeDisplayString(getMinutes());
        return hour + ":" + minutes;
    }
    public String displayTime(int hour_,int minutes_){
        String hour = convertIntToTimeDisplayString(hour_);
        String minutes = convertIntToTimeDisplayString(minutes_);
        return hour + ":" + minutes;
    }
}
