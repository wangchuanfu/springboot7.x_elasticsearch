package com.j1.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {


    public static final Long SECOND = 1000L;
    public static final Long MINUTE = 60 * SECOND;
    public static final Long HOUR = 60 * MINUTE;
    public static final Long DAY = 24 * HOUR;
    public static final Long WEEK = 7 * DAY;

    /**
     * 计算两个日期相差的天数的绝对值
     * @param dateStr1 格式：yyyy-MM-dd
     * @param dateStr2 格式：yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static Long getDayDifference(String dateStr1, String dateStr2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(dateStr1);
        Date date2 = sdf.parse(dateStr2);
        return Math.abs(date1.getTime() - date2.getTime()) / DAY;
    }

    /**
     * 计算两个日期相差的天数的绝对值
     * @param date1
     * @param date1
     * @return
     * @throws ParseException
     */
    public static Long getDayDifference(Date date1, Date date2) throws ParseException {
        return getDayDifference(dateToStr(date1), dateToStr(date2));
    }

    /**
     * 计算两个日期相差的天数的绝对值
     * @param date1
     * @param dateStr2 格式：yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static Long getDayDifference(Date date1, String dateStr2) throws ParseException {
        return getDayDifference(dateToStr(date1), dateStr2);
    }

    /**
     * 计算两个日期相差的天数的绝对值
     * @param dateStr1 格式：yyyy-MM-dd
     * @param date2
     * @return
     * @throws ParseException
     */
    public static Long getDayDifference(String dateStr1, Date date2) throws ParseException {
        return getDayDifference(dateStr1, dateToStr(date2));
    }

    public static String dateToStr(Date date){
        return dateToStr(date, "yyyy-MM-dd");
    }

    public static String dateToStr(Date date, String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 字符串转换成日期
     * @param str
     * @param formatStr
     * @return
     */
    public static Date strToDate(String str, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date strToDate(String str){
        return strToDate(str, "yyyy-MM-dd");
    }
    public static Date strToDateYWeek(String str){
        return strToDate(str, "yyyy-MM");
    }

    public static Date strToDateY(String str){
        return strToDate(str, "yyyy");
    }

    /**
     * 时间计算，加减秒（分钟、小时、天、星期）
     * @param date 被计算的日期
     * @param amount 数量（比如加3小时就是3）
     * @param typeMillis 类型毫秒数，已在此工具类中定义常量
     * @return
     */
    public static Date dateCalc(Date date, int amount, Long typeMillis) {
        return new Date(date.getTime() + amount * typeMillis);
    }

    public static String dateCalc(String date, int amount, Long typeMillis) {
        return dateToStr(dateCalc(strToDate(date), amount, typeMillis));
    }

}
