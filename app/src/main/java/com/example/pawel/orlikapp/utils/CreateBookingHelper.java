package com.example.pawel.orlikapp.utils;

import com.example.pawel.orlikapp.model.Booking;
import com.example.pawel.orlikapp.ui.menu.find_playground.DataHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

/**
 * Created by Pawel on 09.01.2018.
 */

public class CreateBookingHelper {
    private Calendar calendar;
    private Set<Booking> bookingSet;

    public CreateBookingHelper(Calendar calendar, Set<Booking> bookingSet) {
        this.bookingSet = bookingSet;
        this.calendar = calendar;
    }

    public String getTime(Calendar calendar) {
        return String.format("%02d:%02d", calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
    }

    public Booking getLatestBookingByMinutes(List<Booking> bookingList) {
        Booking w = bookingList.get(0);
        for (Booking b : bookingList) {
            if (w.getEndOrderMinutes() < b.getEndOrderMinutes()) {
                w = b;
            }
        }
        return w;
    }

    public List<Booking> getBookingByDay(String date, String clickTime) {
        int hourClick = DateHelper.getHourFromDate(clickTime);
        // int mminuteClick = DateHelper.getMinutesFromDate(clickTime);

        List<Booking> bookings = new ArrayList<>();
        for (Booking b : bookings) {
            if (b.getEndDate().equals(date) && b.getEndOrderHour() == hourClick) {
                bookings.add(b);
            }
        }
        return bookings;
    }

    public String getDataFromCalendar(Calendar calendar) {
        return String.valueOf(calendar.get(Calendar.YEAR)) + "-" + String.valueOf(calendar.get(Calendar.MONTH) + 1) + "-" + String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
    }

    public Time findNearestStartBooking() {
        String date = getDataFromCalendar(calendar);
        String clickTime = getTime(calendar);

        List<Booking> bookings = getBookingByDay(date, clickTime);
        if (!bookings.isEmpty()) {
            Booking w = getLatestBookingByMinutes(bookings);
            return new Time(w.getEndOrderHour(), w.getEndOrderMinutes());
        }
        return new Time(DateHelper.getHourFromDate(clickTime), DateHelper.getMinutesFromDate(clickTime));
    }
}