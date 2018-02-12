package com.example.pawel.orlikapp.utils;

import android.support.annotation.NonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Pawel on 12.11.2017.
 */

public class Validation {
    public static String VALID_EMAIL_ADDRES_REGEX = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
//    public static String VALID_BIRTH_DATE_REGEX = "^\\s*(3[01]|[12][0-9]|0?[1-9])\\-(1[012]|0?[1-9])\\-((?:19|20)\\d{2})\\s*$";
//    yyyy-MM-dd
    public static String VALID_BIRTH_DATE_REGEX = "^\\s*((?:19|20)\\d{2})\\-(1[012]|0?[1-9])\\-(3[01]|[12][0-9]|0?[1-9])\\s*$";

    public static boolean onEmailAddresValidation(@NonNull String email) {
        Pattern pattern = Pattern.compile(VALID_EMAIL_ADDRES_REGEX);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            return false;
        } else {
            return true;
        }
    }
    public static boolean validate(@NonNull String birthDate,@NonNull String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(birthDate);
        if(!matcher.matches()){
            return false;
        }else
            return true;
    }

}
