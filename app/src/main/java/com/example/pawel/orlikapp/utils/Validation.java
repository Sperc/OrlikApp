package com.example.pawel.orlikapp.utils;

import android.support.annotation.NonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Pawel on 12.11.2017.
 */

public class Validation {
    private static String VALID_EMAIL_ADDRES_REGEX = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";

    public static boolean onEmailAddresValidation(@NonNull String email) {
        Pattern pattern = Pattern.compile(VALID_EMAIL_ADDRES_REGEX);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            return false;
        } else {
            return true;
        }
    }
}
