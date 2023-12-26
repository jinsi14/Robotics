package com.om.gc.common;

import java.math.BigDecimal;
import java.text.ParseException;

public class CommonUtils {

    public static java.sql.Date getSQLDate(String inputDate, String pattern) throws ParseException {
        if (inputDate == null || inputDate.trim().equals("") || inputDate.equalsIgnoreCase("N/A")) {
            return null;
        }
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(pattern);
        java.util.Date date = sdf.parse(inputDate);
        return new java.sql.Date(date.getTime());
    }

    public static java.sql.Timestamp getSQLDateTime(String inputDateTime, String pattern) throws ParseException {
        if (inputDateTime == null || inputDateTime.trim().equals("") || inputDateTime.equalsIgnoreCase("N/A")) {
            return null;
        }
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(pattern);
        java.util.Date date = sdf.parse(inputDateTime);
        return new java.sql.Timestamp(date.getTime());
    }
}
