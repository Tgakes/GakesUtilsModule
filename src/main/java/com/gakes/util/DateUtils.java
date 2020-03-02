package com.gakes.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public static String getStringDateShort(Date currentTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }


    public static String formatDate(long time, String format) {
        DateFormat dateFormat2 = new SimpleDateFormat(format, Locale.getDefault());
        String formatDate = dateFormat2.format(time);
        return formatDate;
    }


    public static String formatDate(long time) {
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String formatDate = dateFormat2.format(time);
        return formatDate;
    }


    public static String formatDateTime(long date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        String formatDate = dateFormat.format(date);
        return formatDate;
    }


    public static String formatTime(long millis) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String formatDate = dateFormat.format(millis);
        return formatDate;
    }

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到秒的字符串
     * @return
     */
    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param date_str 字符串日期
     * @param format   如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static long date2TimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(date_str).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String[] WEEK = {"星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    public static final int WEEKDAYS = 7;

    public static String dateToWeek(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int dayIndex = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayIndex < 1 || dayIndex > WEEKDAYS) {
            return null;
        }
        return WEEK[dayIndex - 1];
    }


    /**
     * 获取上一周日期
     *
     * @return
     */
    public static String getLastWeekDate() {

        SimpleDateFormat format = getSimpleDate(6);
        Calendar c = Calendar.getInstance();
        //过去七天
        c.setTime(getCurrentDate());
        c.add(Calendar.DATE, -7);
        Date d = c.getTime();
        String day = format.format(d);

        return day;
    }

    /**
     * 获取过去日期或者将来
     *
     * @param flag       负数为过去的日期；正数为将来的日期 单位天
     * @param parameters
     * @return
     */
    public static String getLastFutureDate(int flag, int parameters) {

        SimpleDateFormat format = getSimpleDate(parameters);
        Calendar c = Calendar.getInstance();
        //
        c.setTime(getCurrentDate());
        c.add(Calendar.DATE, flag);
        Date d = c.getTime();
        String day = format.format(d);
        return day;
    }

    public static long getLastFutureDate(int flag) {
        Calendar c = Calendar.getInstance();
        c.setTime(getCurrentDate());
        c.add(Calendar.DATE, flag);
        return c.getTimeInMillis();
    }

    /**
     * @Description 时间戳转换成日期字符窜
     * @date 2015/11/30
     */
    public static String getDateToString(long time, int parameters) {

        SimpleDateFormat formatter = getSimpleDate(parameters);
        if (formatter != null) {
            Date date = new Date(time);
            return formatter.format(date);
        }
        return "";

    }

    /**
     * @Description 日期转换成日期字符窜
     * @date 2015/11/30
     */
    public static String getDateToString(Date date, int parameters) {

        SimpleDateFormat formatter = getSimpleDate(parameters);
        if (formatter != null) {
            return formatter.format(date);
        }
        return "";

    }


    public static String dateToString(Date date, int parameters) {

        SimpleDateFormat formatter = getSimpleDate(parameters);
        if (formatter != null) {
            return formatter.format(date);
        }
        return "";

    }


    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentTimeDate(int parameters) {
        SimpleDateFormat formatter = getSimpleDate(parameters);
        Date curDate = getCurrentDate();// 获取当前时间
        if (formatter != null) {
            return formatter.format(curDate);
        }
        return "";
    }

    public static Date getCurrentDate() {

        return new Date(System.currentTimeMillis());// 获取当前时间
    }


    /**
     * 字符串转时间戳
     *
     * @param timeString
     * @return
     */
    public static Long strToTimestamp(String timeString, int parameters) {

        SimpleDateFormat simpleDateFormat = getSimpleDate(parameters);
        Date d;
        try {
            d = simpleDateFormat.parse(timeString);
            return d.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public static SimpleDateFormat getSimpleDate(int parameters) {
        SimpleDateFormat formatter = null;
        switch (parameters) {
            case 0:
                formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
                break;
            case 1:
                formatter = new SimpleDateFormat("yyyy.MM.dd;HH:mm", Locale.getDefault());
                break;
            case 2:
                formatter = new SimpleDateFormat("MM.dd;HH:mm", Locale.getDefault());
                break;
            case 3:
                formatter = new SimpleDateFormat("yyyy.MM.dd;HH:mm:ss", Locale.getDefault());
                break;
            case 4:
                formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                break;
            case 5:
                formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
                break;
            case 6:
                formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                break;
            case 7:
            case 16:
                formatter = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
                break;
            case 8:
                formatter = new SimpleDateFormat("yyyyMM", Locale.getDefault());
                break;
            case 9:
                formatter = new SimpleDateFormat("HH:mm", Locale.getDefault());
                break;
            case 10:
                formatter = new SimpleDateFormat("dd", Locale.getDefault());
                break;
            case 11:
                formatter = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                break;
            case 12:
                formatter = new SimpleDateFormat("yyyy-MM", Locale.getDefault());
                break;
            case 13:
                formatter = new SimpleDateFormat("MM-dd HH:mm:ss", Locale.getDefault());
                break;
            case 14:
                formatter = new SimpleDateFormat("MM-dd HH:mm", Locale.getDefault());
                break;
            case 15:
                formatter = new SimpleDateFormat("MM. dd, yyyy", Locale.getDefault());
                break;
        }
        return formatter;
    }


    /**
     * 获取两个日期所差的天数
     *
     * @param time1 大的时间
     * @param time2 小的时间
     * @return
     */
    public static long[] getTwoTimeOffset(long time1, long time2) {


        SimpleDateFormat df = getSimpleDate(4);
        try {
            Date d1 = df.parse(getDateToString(time1, 4)); //
            Date d2 = df.parse(getDateToString(time2, 4)); //
            long diff = d1.getTime() - d2.getTime(); // 两个时间差,精确到毫秒

            long day = diff / (1000 * 60 * 60 * 24); // 以天数为单位取整
            long hour = (diff / (60 * 60 * 1000) - day * 24); // 以小时为单位取整
            long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60); // 以分钟为单位取整
            return new long[]{day, hour, min};
//            long seconds = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }

    public static long[] getTimeOffset(long startTime, long endTime) {

        SimpleDateFormat df = getSimpleDate(4);
        try {

            Date d1 = df.parse(getDateToString(startTime, 4)); //
            Date d2 = df.parse(getDateToString(endTime, 4)); //
            long diff = d2.getTime() - d1.getTime(); // 两个时间差,精确到毫秒

            long day = diff / (1000 * 60 * 60 * 24); // 以天数为单位取整
            long hour = (diff / (60 * 60 * 1000) - day * 24); // 以小时为单位取整
            long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60); // 以分钟为单位取整
            long seconds = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
            return new long[]{hour + (day * 24), min, seconds};

        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }


    public static Date getEffectiveDate(String time) {

        try {
            return getSimpleDate(11).parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime   当前时间
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     * @author jqlin
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }
}
