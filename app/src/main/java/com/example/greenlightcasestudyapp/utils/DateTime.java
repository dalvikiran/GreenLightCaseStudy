package com.example.greenlightcasestudyapp.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * Summary
 * This java file is used for calender operation
 */

public class DateTime {


    //get Current Date and time in string
    public static String getCurrentDataTime ()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }

}
