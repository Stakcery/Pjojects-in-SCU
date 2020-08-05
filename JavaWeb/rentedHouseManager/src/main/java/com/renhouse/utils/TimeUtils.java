package com.renhouse.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @ClassName TimeUtils
 * @Description 时间操作
 * @Author 1TreeForest
 * @Date 2020/7/16 18:02
 * @Version 1.0
 */
public class TimeUtils {
    /**
     * 取得给定字符串的日期对象.
     *
     * @param dateStr 日期描述 从给定字符串的开始分析文本，以生成一个日期对象
     * @return 给定字符串描述的日期对象。
     */
    public static Date getDateFromString(String dateStr) {
        String format = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date resDate = null;
        try {
            resDate = sdf.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resDate;
    }

    /**
     * 根据给定的格式与时间(Date类型的)，返回时间字符串。
     *
     * @param date 指定的日期
     * @return String 指定格式的时间字符串.
     */
    public static String getFormatDate(Date date) {
        String format = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 判断一个日期是否在开始日期和结束日期之间。
     *
     * @param srcDate   目标日期 yyyy/MM/dd 或者 yyyy-MM-dd
     * @param startDate 开始日期 yyyy/MM/dd 或者 yyyy-MM-dd
     * @param endDate   结束日期 yyyy/MM/dd 或者 yyyy-MM-dd
     * @return 大于等于开始日期小于等于结束日期，那么返回true，否则返回false
     */
    public static boolean isInStartEnd(String srcDate, String startDate,
                                       String endDate) {
        if (startDate.compareTo(srcDate) <= 0
                && endDate.compareTo(srcDate) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 求两个日期相差天数
     *
     * @param sd 起始日期，格式yyyy-MM-dd
     * @param ed 终止日期，格式yyyy-MM-dd
     * @return 两个日期相差天数
     */
    public static long getIntervalDays(String sd, String ed) {
        return ((java.sql.Date.valueOf(ed)).getTime() - (java.sql.Date
                .valueOf(sd)).getTime()) / (3600 * 24 * 1000);
    }

    /**
     * 起始日期yyyy-MM-dd与终止日期yyyy-MM-dd之间跨度的月数。
     *
     * @param beginDate 格式为yyyy-MM-dd
     * @param beginDate 格式为yyyy-MM-dd
     * @return 相差月整数。
     */
    public static int getInterval(String beginDate, String endDate) {

        String regex = "-";// 按照-来进行切割

        String[] date1 = beginDate.split(regex);
        String[] date2 = endDate.split(regex);

        int y1 = Integer.parseInt(date1[0]);
        int y2 = Integer.parseInt(date2[0]);

        int m1 = Integer.parseInt(date1[1]);
        int m2 = Integer.parseInt(date2[1]);

        return ((y2 - y1) * 12)
                + (m2 - m1); //相差的月数
    }

    /**
     * 取得给定日期加上一定天数后的格式化日期字符串.
     *
     * @param dateStr 给定的日期对象
     * @param amount  需要添加的天数，如果是向前的天数，使用负数就可以，已根据需求设置为30天
     * @return String 加上一定天数以后的日期字符串.
     */
    public static String getDateAdd(String dateStr, int amount) {
        Calendar cal = new GregorianCalendar();
        Date date = getDateFromString(dateStr);
        cal.setTime(date);
        cal.add(GregorianCalendar.DATE, amount);
        return getFormatDate(cal.getTime());
    }

    /**
     * 获得当前日期的格式化字符串
     *
     * @return String 系统当前时间格式化字符串
     */
    public static String getCurrentDateStr() {
        return getFormatDate(new Date());
    }

    /**
     * 获得当前日期的对象
     *
     * @return Date 系统当前时间对象
     */
    public static Date getCurrentDate() {
        return new Date();
    }

    /**
     * 获得当前日期固定间隔天数的日期，如前60天dateAdd(-60)
     *
     * @param interval 距今天的间隔日期长度，向前为负，向后为正
     * @return java.lang.String 按照格式输出的间隔的日期字符串.
     */
    public static String getFormatCurrentAdd(int interval) {
        String d = getDateAdd(getFormatDate(new Date()), interval);

        return d;
    }

    /**
     * 取得给定日期加上一定天数后的日期对象.
     *
     * @param dateStr 给定的日期对象
     * @param amount  需要添加的天数，如果是向前的天数，使用负数就可以.
     * @return Date 加上一定天数以后的Date对象.
     */
    public static String getFormatDateAdd(String dateStr, int amount) {
        Calendar cal = new GregorianCalendar();
        Date date = getDateFromString(dateStr);
        cal.setTime(date);
        cal.add(GregorianCalendar.DATE, amount);
        return getFormatDate(cal.getTime());
    }


}
