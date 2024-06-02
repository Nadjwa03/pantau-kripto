package com.example.pantaukripto.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Formatter {
    public static String formatDate(String dateString, String simpleDateFormat) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());

        Date date;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }

        // Format the date
        SimpleDateFormat outputFormat = new SimpleDateFormat(simpleDateFormat, Locale.getDefault());

        return outputFormat.format(date);
    }
}
