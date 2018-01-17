package com.example.pawel.orlikapp.utils;

import android.support.annotation.Nullable;

import com.example.pawel.orlikapp.model.Booking;
import com.example.pawel.orlikapp.ui.menu.find_playground.DataHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * Created by Pawel on 09.01.2018.
 */

public class CreateBookingHelper {
    private Calendar calendar;
    private List<Booking> bookingList;
    private int hourClickTime;
    private int minoutesClickTime;
    private Time maxTimeToEndBooking;

    public CreateBookingHelper(Calendar calendar, List<Booking> bookingList, @Nullable Time maxTimeToEndBooking) {
        this.maxTimeToEndBooking = maxTimeToEndBooking;
        this.calendar = calendar;
        this.bookingList = bookingList;
        this.hourClickTime = DateHelper.getHourFromDate(DateHelper.convertCalendarToTime(calendar));
        this.minoutesClickTime = DateHelper.getMinutesFromDate(DateHelper.convertCalendarToTime(calendar));
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    public int getHourClickTime() {
        return hourClickTime;
    }

    public void setHourClickTime(int hourClickTime) {
        this.hourClickTime = hourClickTime;
    }

    public int getMinoutesClickTime() {
        return minoutesClickTime;
    }

    public void setMinoutesClickTime(int minoutesClickTime) {
        this.minoutesClickTime = minoutesClickTime;
    }

    //    public Booking getLatestBookingByMinutes(List<Booking> bookingList) {
//        Booking w = bookingList.get(0);
//        for (Booking b : bookingList) {
//            if (w.getEndOrderMinutes() < b.getEndOrderMinutes()) {
//                w = b;
//            }
//        }
//        return w;
//    }
//
//    public List<Booking> getBookingByDay(String date, String clickTime) {
//        int hourClick = DateHelper.getHourFromDate(clickTime);
//        // int mminuteClick = DateHelper.getMinutesFromDate(clickTime);
//
//        List<Booking> bookings = new ArrayList<>();
//        for (Booking b : bookings) {
//            if (b.getEndDate().equals(date) && b.getEndOrderHour() == hourClick) {
//                bookings.add(b);
//            }
//        }
//        return bookings;
//    }
//
//    public String getDataFromCalendar(Calendar calendar) {
//        return String.valueOf(calendar.get(Calendar.YEAR)) + "-" + String.valueOf(calendar.get(Calendar.MONTH) + 1) + "-" + String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
//    }
//
//    public Time findNearestStartBooking() {
//        String date = getDataFromCalendar(calendar);
//        String clickTime = getTime(calendar);
//
//        List<Booking> bookings = getBookingByDay(date, clickTime);
//        if (!bookings.isEmpty()) {
//            Booking w = getLatestBookingByMinutes(bookings);
//            return new Time(w.getEndOrderHour(), w.getEndOrderMinutes());
//        }
//        return new Time(DateHelper.getHourFromDate(clickTime), DateHelper.getMinutesFromDate(clickTime));
//    }
//
//    public List<Booking> findBookingByDay(String date) {
//        List<Booking> bookings = new ArrayList<>();
//        for (Booking b : bookings) {
//            if (b.getEndDate().equals(date)) {
//                bookings.add(b);
//            }
//        }
//        return bookings;
//    }
    private List<Booking> bookinngInClickHour(){
        List<Booking> bookings =  new ArrayList<>();
        for(Booking b:bookingList){
            if(b.getEndOrderHour()==hourClickTime){
                bookings.add(b);
            }
        }
        return bookings;
    }

    public Time getMinimumTimeToStart(){
        List<Booking> bookings = bookinngInClickHour();
        if(bookings.isEmpty()){
            //w kliknietej godzinie nie ma zadnych innych rezeracji
            return new Time(hourClickTime,0);
        }
        Booking lastBooking = bookings.get(bookings.size()-1);
        return new Time(lastBooking.getEndOrderHour(),lastBooking.getEndOrderMinutes());
    }
    public Time getMaxTimeToEndBooking(){
        for(Booking b: bookingList){

            if(b.getStartOrderHour()>= hourClickTime){
                return new Time(b.getStartOrderHour(),b.getStartOrderMinutes());
            }

        }
        return maxTimeToEndBooking;
    }

}