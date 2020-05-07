package com.travel.common.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

/**
 * 时间工具.
 *
 * @author songgc
 * @date 2018/7/19 下午1:50
 **/
public class CommonDateUtil extends DateUtils {


    /**
     * LocalDateTime 转Date
     *
     * @param localDateTime 时间
     * @return 时间
     */
    public static Date convertFromLocalDateTime(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }

        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);

        return Date.from(zdt.toInstant());
    }


    /**
     * Date 转 LocalDateTime
     *
     * @param date 时间
     * @return 时间
     */
    public static LocalDateTime convertFromDate(Date date) {
        if (date == null) {
            return null;
        }

        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();

        return instant.atZone(zoneId).toLocalDateTime();
    }

    /**
     * 获取时间戳秒
     *
     * @param date 时间
     * @return 秒
     */
    public static Long getTimestamp(Date date) {
        if (null == date) {
            return null;
        }
        String timestamp = String.valueOf(date.getTime());
        int length = timestamp.length();
        if (length > 3) {
            return Long.valueOf(timestamp.substring(0, length - 3));
        } else {
            return null;
        }
    }

    /**
     * 增加或减少周数
     *
     * @param date   日期
     * @param amount 周数
     */
    public static Date addWeeks(Date date, int amount) {

        return addDays(date, amount * 7);
    }

    /**
     * 增加或减少季度数
     *
     * @param date   日期
     * @param amount 季度数
     */
    public static Date addQuarters(Date date, int amount) {

        return addMonths(date, amount * 3);
    }

    /**
     * 根据给定日期返回一年中第几周
     */
    public static int getWeekNum(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setTime(date);

        return cal.get(Calendar.WEEK_OF_YEAR);

    }

    /**
     * 根据给定日期返回一年中第几季度
     */
    public static int getQuarterNum(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int month = cal.get(Calendar.MONTH);

        if (month <= 2) {
            return 1;
        } else if (month <= 5) {
            return 2;
        } else if (month <= 8) {
            return 3;
        } else {
            return 4;
        }

    }


    /**
     * 字符串转日期
     */
    public static Date parseDate(String str) {
        try {
            return parseDate(str, "yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 日期格式化
     */
    public static String formatDate(Date date) {

        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 日期格式化
     */
    public static String formatDate(Date date, String pattern) {
        try {
            return new SimpleDateFormat(pattern).format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 计算两个日期相差多少天
     */
    public static int countDays(Date d1, Date d2) {

        return (int) ((Math.abs(d2.getTime() - d1.getTime()) + 1000) / 86400000);
    }

    /**
     * 获取当前日期的指定偏移天数
     */
    public static Date getDateFromNow(int amount) {

        return addDays(new Date(), amount);
    }

    /**
     * 获取本周第一天
     */
    public static Date getCurWeekFirst() {

        Calendar cal = Calendar.getInstance();
        //解决周日会出现并到下一周的情况
        cal.add(Calendar.DAY_OF_MONTH, -1);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        return truncate(cal.getTime(), Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取本月第一天
     */
    public static Date getCurMonthFirst() {

        return truncate(new Date(), Calendar.MONTH);
    }


    /**
     * 获取本季度第一天
     */
    public static Date getCurQuarterFirst() {

        Calendar cal = Calendar.getInstance();
        int nowMonth = cal.get(Calendar.MONTH);

        int startMonth = 0;
        if (nowMonth <= 2) {
            startMonth = 0;
        } else if (nowMonth <= 5) {
            startMonth = 3;
        } else if (nowMonth <= 8) {
            startMonth = 6;
        } else {
            startMonth = 9;
        }

        cal.set(Calendar.MONTH, startMonth);
        cal.set(Calendar.DATE, 1);

        return truncate(cal.getTime(), Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取上周第一天
     */
    public static Date getLastWeekFirst() {

        return addDays(getCurWeekFirst(), -7);
    }


    /**
     * 获取上周最后一天
     */
    public static Date getLastWeekLast() {

        return addDays(getCurWeekFirst(), -1);
    }

    /**
     * 获取上月第一天
     */
    public static Date getLastMonthFirst() {

        return addMonths(getCurMonthFirst(), -1);
    }

    /**
     * 获取上月最后一天
     */
    public static Date getLastMonthLast() {

        return addDays(getCurMonthFirst(), -1);
    }

    /**
     * 获取上季度第一天
     */
    public static Date getLastQuarterFirst() {

        return addMonths(getCurQuarterFirst(), -3);
    }

    /**
     * 获取上季度最后一天
     */
    public static Date getLastQuarterLast() {

        return addDays(getCurQuarterFirst(), -1);
    }

}
