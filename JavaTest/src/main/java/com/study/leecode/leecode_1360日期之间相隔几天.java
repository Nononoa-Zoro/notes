package com.study.leecode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class leecode_1360日期之间相隔几天 {
    public static int daysBetweenDates(String date1, String date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date first = null;
        Date second = null;
        try {
            first = sdf.parse(date1);
            second = sdf.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long time1 = first.getTime();
        long time2 = second.getTime();
        long res = (time2 - time1) / (1000 * 60 * 60 * 24);
        return res < 0 ? (int) -res : (int) res;
    }

    public static void main(String[] args) throws ParseException {
        int res = daysBetweenDates("1971-06-29", "2010-09-23");
        System.out.println(res);
    }
}
