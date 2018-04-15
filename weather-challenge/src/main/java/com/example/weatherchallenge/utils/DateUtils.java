package com.example.weatherchallenge.utils;

import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class DateUtils {
    private static String stringDateFormat = "EEE, d MMM yyyy HH:mm aaa z";

    public Date getDateFromWeatherResponseDate(String stringDate) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(this.stringDateFormat);
        try {
            date = formatter.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public String toStringDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(this.stringDateFormat);
        return formatter.format(date);
    }

    public boolean lessThanHalfHourDifference(Date date1, Date date2) {
        long diff = date2.getTime() - date1.getTime();
        if (TimeUnit.MILLISECONDS.toMinutes(diff) <= 30) {
            return true;
        }
        return false;
    }
}
