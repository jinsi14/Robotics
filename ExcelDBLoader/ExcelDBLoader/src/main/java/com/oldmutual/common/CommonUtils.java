package com.oldmutual.common;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

public class CommonUtils {

    public static String getCellValue(Cell cell) {
        String val = "";
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            return val;
        }

        switch (cell.getCellType()) {
            case NUMERIC:
                DataFormatter dateFormat = new DataFormatter();
                val = dateFormat.formatCellValue(cell);
                break;
            case STRING:
                val = cell.getStringCellValue();
                break;
            case BOOLEAN:
                val = String.valueOf(cell.getBooleanCellValue());
                break;
            default:
                break;
        }

        return val;
    }

    public static BigDecimal getBigDecimal(Cell cell) {
        BigDecimal val = new BigDecimal(0.0);
        if (cell == null || cell.getCellType() == CellType.BLANK || (cell.getCellType() == CellType.STRING && cell.getStringCellValue().equalsIgnoreCase("N/A"))
            ) {
            return val;
        }
        switch (cell.getCellType()) {
            case NUMERIC:
                val = new BigDecimal(cell.getNumericCellValue());
                break;
            case STRING:
                System.out.print("d:"+cell.getStringCellValue());
                val = new BigDecimal(cell.getStringCellValue());
                break;
            default:
                break;
       }
        return val;
    }

    public static boolean isRowEmpty(Row row) {
        boolean isEmpty = true;
        DataFormatter dataFormatter = new DataFormatter();
        if (row != null) {
            for (Cell cell : row) {
                if (dataFormatter.formatCellValue(cell).trim().length() > 0) {
                    isEmpty = false;
                    break;
                }
            }
        }
        return isEmpty;
    }

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
