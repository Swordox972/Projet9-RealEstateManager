package com.openclassrooms.realestatemanager.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils {

    public static long getDaysBetweenDates(String start, String end) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        long days = 0;
        try {
            Date startDate = simpleDateFormat.parse(start);
            Date endDate = simpleDateFormat.parse(end);

            long diff = endDate.getTime() - startDate.getTime();
            days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return days;
    }

    public static String returnTodayDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date todayDate = new Date();
        String currentDate = simpleDateFormat.format(todayDate);
        return currentDate;
    }

    public static Date convertStringToDate(String stringDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        try {
             date = simpleDateFormat.parse(stringDate);
             return date;

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String convertDateToString(Date date) {
        if ( date == null)
            return "";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        String stringDate = simpleDateFormat.format(date);
        return stringDate;
    }
}
