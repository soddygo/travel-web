package com.travel.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期工具类
 *
 * @author soddy
 */
public class DateUtils {

    /**
     * 根据传入周数值,倒推时间
     *
     * @param amountWeek 几周,可以是负数
     * @return 时间
     */
    public static LocalDateTime getAddWeek(int amountWeek) {
        Date startData = CommonDateUtil.addWeeks(new Date(), amountWeek);
        Instant instant = startData.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();

        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();

        return localDateTime;
    }

    /**
     * 获取几天后的时间
     */
    public static Date getDayAfter(int day) {
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, day);
        currentDate.set(Calendar.HOUR, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.MINUTE, 0);
        Date monday = currentDate.getTime();
        return monday;
    }

    /**
     * 获取当天起始时间
     *
     * @return Date
     */
    public static Date dayStartDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 获取当天结束时间
     *
     * @return Date
     */
    public static Date dayEndDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 判断是否同一天
     *
     * @return boolean
     */
    public static boolean isSameDay(Date dateA, Date dateB) {
        Calendar calDateA = Calendar.getInstance();
        calDateA.setTime(dateA);
        Calendar calDateB = Calendar.getInstance();
        calDateB.setTime(dateB);
        return ((calDateA.get(1) == calDateB.get(1)) && (calDateA.get(2) == calDateB.get(2)) && (calDateA.get(5) == calDateB.get(5)));
    }

    /**
     * 根据配送日期和时间获取配送时段
     *
     * @return Date[]
     */
    public static Date[] getDateByShipmentTime(String shipmentDate, String shipmentTime) {
        String[] split = shipmentTime.split("-");
        String time1 = null;
        String time2 = null;
        if (split.length == 2) {
            time1 = split[0];
            time2 = split[1];
        }
        String dateString1 = shipmentDate.trim() + " " + time1.trim() + ":00";
        String dateString2 = shipmentDate.trim() + " " + time2.trim() + ":00";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date date1 = sdf.parse(dateString1);
            Date date2 = sdf.parse(dateString2);
            Date[] date = {date1, date2};
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据配送日期和时间获取配送时段
     *
     * @return Date[]
     */
    public static Date[] getDate24HByShipmentTime(String shipmentDate, String shipmentTime) {
        String[] split = shipmentTime.split("-");
        String time1 = null;
        String time2 = null;
        if (split.length == 2) {
            time1 = split[0];
            time2 = split[1];
        }
        String dateString1 = shipmentDate.trim() + " " + time1.trim() + ":00";
        String dateString2 = shipmentDate.trim() + " " + time2.trim() + ":00";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date1 = sdf.parse(dateString1);
            Date date2 = sdf.parse(dateString2);
            Date[] date = {date1, date2};
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取昨天日期字符串
     *
     * @return String
     */
    public static String getLastDay() {
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, -1);
        return format(ca.getTime(), "yyyy-MM-dd");
    }

    /**
     * 格式化日期
     *
     * @return String
     */
    public static String format(Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 根据指定形式格式化日期
     *
     * @return String
     */
    public static String format(Date date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 组装配送时间 startTime = 2015-10-15 12:00:00 endTime = 2015-10-15 13:00:00 shipmentTime = 2015-10-15 12:00:00-13:00:00
     *
     * @return String
     */
    public static String assembleShipmentTime(Date startTime, Date endTime) {
        return assembleShipmentTime(startTime, endTime, "yyyy-MM-dd HH:mm");
    }

    /**
     * 组装配送时间 startTime = 2015-10-15 12:00:00 endTime = 2015-10-15 13:00:00 shipmentTime = 2015-10-15 12:00:00-13:00:00
     *
     * @return String
     */
    public static String assembleShipmentTime(Date startTime, Date endTime, String format) {
        String shipmentTime = null;
        if (null != startTime && null != endTime) {
            String trim1 = DateUtils.format(startTime, format).trim();
            String trim2 = DateUtils.format(endTime, format).trim();
            String[] split = trim1.split(" ");
            String[] split2 = trim2.split(" ");
            if (null != split && split.length > 0 && null != split2 && split2.length > 0) {
                StringBuilder append = new StringBuilder().append(split[0]).append(" ").append(split[1]).append("-").append(split2[1]);
                shipmentTime = append.toString();
            }
        }
        return shipmentTime;
    }

    /**
     * 组装配送时间 startTime = 2015-10-15 12:00:00 endTime = 2015-10-15 13:00:00 shimentTime = 12:00:00 - 13:00:00
     *
     * @return String
     */
    public static String assembleShotShipmentTime(Date startTime, Date endTime, String format) {
        String shipmentTime = null;
        String assembleShipmentTime = assembleShipmentTime(startTime, endTime, format);
        String[] split = assembleShipmentTime.split(" ");
        if (null != split && split.length > 0) {
            shipmentTime = split[1];
        }
        return shipmentTime;
    }

    /**
     * 组装配送日期 startTime = 2015-10-15 12:00:00 endTime = 2015-10-15 13:00:00 shimentDate = 2015-10-15
     *
     * @return String
     */
    public static String assembleShotShipmentDate(Date startTime, Date endTime, String format) {
        String shipmentTime = null;
        String assembleShipmentTime = assembleShipmentTime(startTime, endTime, format);
        String[] split = assembleShipmentTime.split(" ");
        if (null != split && split.length > 0) {
            shipmentTime = split[0];
        }
        return shipmentTime;
    }

    /**
     * 格式化日期
     *
     * @return String
     */
    public static String dateformat(Date date) {
        return format(date, "yyyy-MM-dd");
    }

    /**
     * 字符串转换成日期 yyyy-MM-dd
     *
     * @return date
     */
    public static Date strToDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 字符串转换成日期 yyyy-MM-dd HH:mm:ss
     *
     * @return date
     */
    public static Date strToDateTime(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 字符串转换成日期 yyyy-MM-dd HH:mm
     *
     * @return date
     */
    public static Date strToDateTimeMin(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date dayStartSecond(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }


    public static Date dayEndSecond(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }

    /**
     * 判断两个日期的大小
     */
    public static int compare_date(Date DATE1, Date DATE2) {
        try {
            if (DATE1.getTime() > DATE2.getTime()) {
//                System.out.println("dt1 在dt2前");
                return 1;
            } else if (DATE1.getTime() < DATE2.getTime()) {
//                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
        }
        return 0;
    }

    /**
     * 计算日期 小时加减
     */
    public static String computeDateByHour(Date nowDate, int hour) {
        String res = null;
        try {
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(nowDate);
//          rightNow.add(Calendar.YEAR,-1);//日期减1年
//          rightNow.add(Calendar.MONTH,3);//日期加3个月
//          rightNow.add(Calendar.DAY_OF_YEAR,hour);//日期加减
            rightNow.add(Calendar.HOUR_OF_DAY, hour);//小时加减
//          res=rightNow.getTime();
            res = format(rightNow.getTime());
        } catch (Exception e) {
        }
        return res;
    }

    /**
     * 计算日期 月份加减
     */
    public static String computeDateByMonth(Date nowDate, int month) {
        String res = null;
        try {
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(nowDate);
//          rightNow.add(Calendar.YEAR,-1);//日期减1年
            rightNow.add(Calendar.MONTH, month);//日期加3个月
//          rightNow.add(Calendar.DAY_OF_YEAR,hour);//日期加减
//          rightNow.add(Calendar.HOUR_OF_DAY,hour);//小时加减
//          res=rightNow.getTime();
            res = format(rightNow.getTime());
        } catch (Exception e) {
        }
        return res;
    }

    /**
     * 计算日期 天数加减
     */
    public static String computeDateByDay(Date nowDate, int day) {
        String res = null;
        try {
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(nowDate);
//          rightNow.add(Calendar.YEAR,-1);//日期减1年
//            rightNow.add(Calendar.MONTH,month);//日期加3个月
            rightNow.add(Calendar.DAY_OF_YEAR, day);//日期加减
//          rightNow.add(Calendar.HOUR_OF_DAY,hour);//小时加减
//          res=rightNow.getTime();
            res = format(rightNow.getTime());
        } catch (Exception e) {
        }
        return res;
    }

    /**
     * 返回间隔时间
     */
    public static Date getDateBySlot(Date date, int times, int type) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        if (Calendar.HOUR_OF_DAY == type) {
            instance.add(Calendar.HOUR_OF_DAY, 0 - times);
        } else if (Calendar.MINUTE == type) {
            instance.add(Calendar.HOUR_OF_DAY, 0 - times);
        }
        return instance.getTime();
    }

    /**
     * 计算两个日期间隔天数
     *
     * @return 返回类型说明
     */
    public static int daysBetween(Date sdate, Date edate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdate = sdf.parse(sdf.format(sdate));
        edate = sdf.parse(sdf.format(edate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(edate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }


    /**
     * 将某时间字符串转为自定义时间格式的LocalDateTime
     */
    public static LocalDateTime parseStrToLocalDateTime(String time, String format) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(time, df);
    }

    /**
     * 将LocalDateTime转为long类型的timestamp
     */
    public static long getTimestampOfLocalDateTime(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return instant.toEpochMilli();
    }


    /**
     * 将long类型的timestamp转为LocalDateTime
     */
    public static LocalDateTime getLocalDateTimeOfTimestamp(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }


    /**
     * 将LocalDateTime转为自定义的时间格式的字符串
     */
    public static String getLocalDateTimeAsString(LocalDateTime localDateTime, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(formatter);
    }


}