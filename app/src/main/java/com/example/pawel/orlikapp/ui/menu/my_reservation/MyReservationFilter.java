package com.example.pawel.orlikapp.ui.menu.my_reservation;

import com.example.pawel.orlikapp.model.Booking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pawel on 28.11.2017.
 */

public class MyReservationFilter {
    public List<Booking> getUserOwnReservation(List<Booking> list,String username){
        List<Booking> booking = new ArrayList<>();
        for(Booking b:list){
            if(b.getLeaderName().equals(username)){
                booking.add(b);
            }
        }
        return booking;
    }
    public List<Booking> getMemberReservation(List<Booking> list,String username){
        List<Booking> booking = new ArrayList<>();
        for(Booking b:list){
            if(!b.getLeaderName().equals(username))
                list.add(b);
        }
        return booking;
    }
}
