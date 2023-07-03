package com.hp.backend.utils;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;

import org.springframework.stereotype.Component;

@Component
public class CommonUtils {
    public Date getCurrentDate() {
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

    public byte[] imageToDatabase(String image) {
        byte[] avatar = new byte[0];
        if (!image.isEmpty()) {
            try {
                avatar = Base64.getDecoder().decode(image);
            } catch (IllegalArgumentException e) {
                // Handle the exception (e.g., log an error message)
                // You can also choose to return null or throw a custom exception here
                avatar = new byte[0]; // Set avatar as an empty byte array
            }
        }
        return avatar;
    }

    public String imageToFrontEnd(byte[] image) {
        String avatar = "";
        if (image != null) {
            avatar = Base64.getEncoder().encodeToString(image);
        }

        return avatar;
    }
}
