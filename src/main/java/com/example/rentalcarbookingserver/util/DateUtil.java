package com.example.rentalcarbookingserver.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

    private DateUtil(){}

    public static final String FORMAT = "yyyy-MM-dd";
    public static final TimeZone TIME_ZONE = TimeZone.getTimeZone("GMT");

    public static Date parseDateStr(String dateStr) throws ParseException {
        if (!checkDateStrValue(dateStr)) {
            throw new ParseException("日期格式不正确", 0);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT);
        simpleDateFormat.setTimeZone(TIME_ZONE);
        return simpleDateFormat.parse(dateStr);
    }

    public static boolean checkDateStrValue(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            return false;
        }
        String[] split = dateStr.split("-");
        if (split.length != 3) {
            return false;
        }
        //check month
        try {
            int month = Integer.parseInt(split[1]);
            if (month < 1 || month > 12) {
                return false;
            }
            //check day
            int day = Integer.parseInt(split[2]);
            if (day < 1 || day > 31) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
