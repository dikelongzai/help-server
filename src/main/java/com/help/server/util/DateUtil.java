package com.help.server.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by houlongbin on 2016/12/19.
 */
public class DateUtil {
    private static ThreadLocal<SimpleDateFormat> dateFormatter1 = new ThreadLocal();

    private static ThreadLocal<SimpleDateFormat> dateFormatter2 = new ThreadLocal();

    private static ThreadLocal<SimpleDateFormat> dateFormatter3 = new ThreadLocal();

    private static ThreadLocal<SimpleDateFormat> dateFormatter4 = new ThreadLocal();

    private static ThreadLocal<SimpleDateFormat> dateFormatter5 = new ThreadLocal();

    private static ThreadLocal<SimpleDateFormat> dateFormatter6 = new ThreadLocal();

    private static ThreadLocal<SimpleDateFormat> dateFormatter7 = new ThreadLocal();

    private static ThreadLocal<SimpleDateFormat> dateFormatter8 = new ThreadLocal();

    private static ThreadLocal<SimpleDateFormat> dateFormatter9 = new ThreadLocal();

    public static SimpleDateFormat getDateFormatter1() {
        SimpleDateFormat o = (SimpleDateFormat) dateFormatter1.get();
        if (o == null) {
            o = new SimpleDateFormat("yyyy-MM-dd");
            dateFormatter1.set(o);
        }
        return o;
    }

    public static SimpleDateFormat getDateFormatter2() {
        SimpleDateFormat o = (SimpleDateFormat) dateFormatter2.get();
        if (o == null) {
            o = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateFormatter2.set(o);
        }
        return o;
    }

    public static SimpleDateFormat getDateFormatter3() {
        SimpleDateFormat o = (SimpleDateFormat) dateFormatter3.get();
        if (o == null) {
            o = new SimpleDateFormat("yyyyMMdd");
            dateFormatter3.set(o);
        }
        return o;
    }

    public static SimpleDateFormat getDateFormatter4() {
        SimpleDateFormat o = (SimpleDateFormat) dateFormatter4.get();
        if (o == null) {
            o = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            dateFormatter4.set(o);
        }
        return o;
    }

    public static SimpleDateFormat getDateFormatter5() {
        SimpleDateFormat o = (SimpleDateFormat) dateFormatter5.get();
        if (o == null) {
            o = new SimpleDateFormat("yyyy.MM.dd");
            dateFormatter5.set(o);
        }
        return o;
    }

    public static SimpleDateFormat getDateFormatter6() {
        SimpleDateFormat o = (SimpleDateFormat) dateFormatter6.get();
        if (o == null) {
            o = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            dateFormatter6.set(o);
        }
        return o;
    }

    public static SimpleDateFormat getDateFormatter7() {
        SimpleDateFormat o = (SimpleDateFormat) dateFormatter7.get();
        if (o == null) {
            o = new SimpleDateFormat("HH:mm:ss");
            dateFormatter7.set(o);
        }
        return o;
    }

    public static SimpleDateFormat getDateFormatter8() {
        SimpleDateFormat o = (SimpleDateFormat) dateFormatter8.get();
        if (o == null) {
            o = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            dateFormatter8.set(o);
        }
        return o;
    }

    public static SimpleDateFormat getDateFormatter9() {
        SimpleDateFormat o = (SimpleDateFormat) dateFormatter7.get();
        if (o == null) {
            o = new SimpleDateFormat("yyyyMMddHHmmss");
            dateFormatter9.set(o);
        }
        return o;
    }

    public static Date toDate(String time) {
        SimpleDateFormat sdf = getDateFormatter1();
        if ((time.indexOf("-") > 0) && (time.indexOf(" ") > 0))
            sdf = getDateFormatter2();
        else if ((time.indexOf("/") > 0) && (time.indexOf(" ") < 0))
            sdf = getDateFormatter3();
        else if ((time.indexOf("/") > 0) && (time.indexOf(" ") > 0))
            sdf = getDateFormatter4();
        else if ((time.indexOf(".") > 0) && (time.indexOf(" ") < 0))
            sdf = getDateFormatter5();
        else if ((time.indexOf(".") > 0) && (time.indexOf(" ") > 0))
            sdf = getDateFormatter6();
        else if (time.indexOf("-") < 0)
            sdf = getDateFormatter7();

        try {
            return sdf.parse(time);
        } catch (java.text.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    public static String dateToString(Date time) {
        String res = getDateFormatter2().format(time);
        if (res.indexOf("00:00:00") > 0) {
            res = res.substring(0, 10);
        }
        return res;
    }

    public static String getSysdate() {
        String res = getDateFormatter1().format(new Date());
        if (res.indexOf("00:00:00") > 0) {
            res = res.substring(0, 10);
        }
        return res;
    }

    public static String getSysdatetime() {
        String res = getDateFormatter2().format(new Date());
        if (res.indexOf("00:00:00") > 0) {
            res = res.substring(0, 10);
        }
        return res;
    }

    public static Date addDays(Date dt, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.add(5, days);
        return cal.getTime();
    }

    public static int getYear(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        return cal.get(1);
    }

    public static int getMonth(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        return (cal.get(2) + 1);
    }

    public static int getDay(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        return cal.get(5);
    }

    public static String dateLongToString(Long dateLong) {
        String res = getDateFormatter8().format(dateLong);
        return res;
    }

    public static String dateLongToStringYYYYMMDD(Long datelong) {
        return getDateFormatter3().format(datelong);
    }

    public static String dateLongToString9(Long dateLong) {
        String res = getDateFormatter9().format(dateLong);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(dateLongToString9(System.currentTimeMillis()));
    }
}
