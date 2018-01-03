package com.example.pawel.orlikapp.ui.menu.details_playground;

/**
 * Created by Pawel on 03.01.2018.
 */

public class EventName {
    String leaderName;
    int maxNumberOfPlayer;
    int numberOfPlayer;
    int startHour;
    int startMinute;
    int endHour;
    int endMinute;

    public EventName(String leaderName, int maxNumberOfPlayer, int numberOfPlayer, int startHour, int startMinute, int endHour, int endMinute) {
        this.leaderName = leaderName;
        this.maxNumberOfPlayer = maxNumberOfPlayer;
        this.numberOfPlayer = numberOfPlayer;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
    }
    public String getName(){
        return startHour+":"+startMinute+" - "+endHour+":"+endMinute+
                "\nRecerwujący: "+leaderName+
                "\nWolne miejsca: "+numberOfPlayer+"/"+maxNumberOfPlayer;
    }
}
