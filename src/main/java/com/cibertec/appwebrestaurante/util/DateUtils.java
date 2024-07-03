package com.cibertec.appwebrestaurante.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static Date addOneDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return calendar.getTime();
    }

    public static Date addHoursToDate(Date date, Integer hoursAdded) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hoursAdded);
        return calendar.getTime();
    }
}
