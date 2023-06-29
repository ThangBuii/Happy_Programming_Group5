package com.hp.backend.utils;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {
    public Date getCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        String formattedDate = dateFormat.format(currentDate);

        java.sql.Date sqlDate;
        try {
            java.util.Date parsedDate = dateFormat.parse(formattedDate);
            sqlDate = new java.sql.Date(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle parsing error
            return null; // or throw an exception
        }
        return sqlDate;
    }
}
