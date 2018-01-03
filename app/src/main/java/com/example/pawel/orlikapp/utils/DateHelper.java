package com.example.pawel.orlikapp.utils;

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

}
