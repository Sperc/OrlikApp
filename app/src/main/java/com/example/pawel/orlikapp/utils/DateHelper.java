package com.example.pawel.orlikapp.utils;

import android.os.Build;
import android.support.annotation.RequiresApi;

import org.joda.time.LocalDate;
import org.joda.time.Years;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Pawel on 03.01.2018.
 */

public class DateHelper {
    //yyyy-mm-DD
    public static int getMonthFromDate(String date){
        String month = date.substring(5,7);
        return Integer.parseInt(month);
    }
    public static int getDayFromDate(String date){
        String day = date.substring(8);
        return Integer.parseInt(day);
    }
    public static int getYearFromDate(String date){
        String year = date.substring(0,4);
        return Integer.parseInt(year);
    }
    public static int getHourFromDate(String date){
        String hour =  date.substring(0,2);
        return Integer.parseInt(hour);
    }
    public static int getMinutesFromDate(String date){
        String minutes = date.substring(3);
        return Integer.parseInt(minutes);
    }
    public static String convertCalendarToTime(Calendar calendar) {
        return String.format("%02d:%02d", calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
    }
    public static String convertCalendarToDateString(Calendar calendar){
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String inActiveDate = null;
        Date date = calendar.getTime();
        inActiveDate = format1.format(date);
        return inActiveDate;
    }
    public static int getActualAgeFromBirthday(String date){
        LocalDate birthDay = LocalDate.parse(date);
        LocalDate nowDay = LocalDate.now();
        Years age = Years.yearsBetween(birthDay,nowDay);
        return age.getYears();
    }
}
