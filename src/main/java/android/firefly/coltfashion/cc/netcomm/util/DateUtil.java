package android.firefly.coltfashion.cc.netcomm.util;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {

    public static final int STRING_YEAR_FLAG = 0x01;

    public static final int STRING_MONTH_FLAG = 0x02;

    public static final int STRING_DAY_FLAG = 0x04;

    public static final int STRING_WEEK_FLAG = 0x08;

    public static final int STRING_HOUR_FLAG = 0x10;

    public static final int STRING_MINUTE_FLAG = 0x20;

    public static final int STRING_SECOND_FLAG = 0x40;

    public static final int STRING_MSECOND_FLAG = 0x80;

    static public final String TIMEDATE_SPLIT = " ";

    static public final String TIME_SPLIT = ":";

    static public final String DATE_SPLIT = "-";

    static public final int DATESTR_NOTCOMPARE = -1;

    // param1=param2
    static public final int DATESTR_COMPARE0 = 0;

    // param1>param2
    static public final int DATESTR_COMPARE1 = 1;

    // param1<param2
    static public final int DATESTR_COMPARE2 = 2;

    public static Calendar calendarToFullDate(Calendar calDt) {
        calDt.set(Calendar.HOUR_OF_DAY, 0);
        calDt.set(Calendar.MINUTE, 0);
        calDt.set(Calendar.SECOND, 0);
        calDt.set(Calendar.MILLISECOND, 0);

        return calDt;
    }

    public static Calendar strDateToCalendar(String strDt) {

        return stringToCalendar(strDt, "yyyy-MM-dd");

    }

    public static Calendar strDateTimeToCalendar(String strDt) {

        return stringToCalendar(strDt, "yyyy-MM-dd HH:mm:ss");

    }

    @SuppressLint("SimpleDateFormat")
    public static Calendar stringToCalendar(String strDt, String strFormat) {

        SimpleDateFormat df = new SimpleDateFormat(strFormat);
        Date dt = null;
        try {
            dt = df.parse(strDt);
        } catch (Throwable ex) {

        }

        if (dt != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dt);
            return calendar;
        }

        return null;

    }

    @SuppressLint("SimpleDateFormat")
    public static String calendarToString(Calendar calDt, String strFormat) {

        SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
        return sdf.format(calDt.getTime());

    }

    public static String calendarToDateString(Calendar calDt) {

        return calendarToString(calDt, "yyyy-MM-dd");

    }

    public static boolean getNowInTimes(String begtime, String endtime) {

        String[] arrs1 = begtime.split(":");
        String[] arrs2 = endtime.split(":");
        if (arrs1.length != 3)
            return false;
        if (arrs2.length != 3)
            return false;
        Calendar c = Calendar.getInstance();
        long ts = c.getTimeInMillis();

        c.set(Calendar.HOUR_OF_DAY, Integer.valueOf(arrs1[0]));
        c.set(Calendar.MINUTE, Integer.valueOf(arrs1[1]));
        c.set(Calendar.SECOND, Integer.valueOf(arrs1[2]));
        long ts1 = c.getTimeInMillis();

        c.set(Calendar.HOUR_OF_DAY, Integer.valueOf(arrs2[0]));
        c.set(Calendar.MINUTE, Integer.valueOf(arrs2[1]));
        c.set(Calendar.SECOND, Integer.valueOf(arrs2[2]));
        long ts2 = c.getTimeInMillis();

        return ((ts2 >= ts1) && (ts >= ts1) && (ts <= ts2));

    }

    public static long getmSecondTime(String time) {

        Calendar c = Calendar.getInstance();

        String[] arrs1 = time.split(":");
        if (arrs1.length <= 3) {

            if (arrs1.length > 0)
                c.set(Calendar.HOUR_OF_DAY, Integer.valueOf(arrs1[0]));
            if (arrs1.length > 1)
                c.set(Calendar.MINUTE, Integer.valueOf(arrs1[1]));
            if (arrs1.length > 2)
                c.set(Calendar.SECOND, Integer.valueOf(arrs1[2]));

            c.set(Calendar.MILLISECOND, 0);
        }

        return c.getTimeInMillis();
    }

    public static int getSecondTime(Calendar c) {

        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);

        return hour * 3600 + minute * 60 + second;

    }

    public static int getSecondTime(String time) {

        String[] arrs1 = time.split(":");
        if (arrs1.length <= 3) {

            int re = 0;
            if (arrs1.length > 0) {
                re += 3600 * Integer.valueOf(arrs1[0]);
            }

            if (arrs1.length > 1) {
                re += 60 * Integer.valueOf(arrs1[1]);
            }

            if (arrs1.length > 2) {
                re += Integer.valueOf(arrs1[2]);
            }
            return re;
        }

        return -1;

    }

    public static int getNowSecondTime() {

        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);

        return hour * 3600 + minute * 60 + second;

    }

    public static int getDateValueBymSecond(long value, int type) {

        if (Calendar.DATE == type) {

            long second = value / 1000;
            return (int) second / (24 * 3600);

        } else if (Calendar.HOUR == type) {

            long second = value / 1000;
            return (int) second % (24 * 3600) / 3600;

        } else if (Calendar.MINUTE == type) {
            long second = value / 1000;
            return (int) second % 3600 / 60;

        } else if (Calendar.SECOND == type) {
            long second = value / 1000;
            return (int) second % 60;
        }

        return 0;
    }

    public static int compareDateTimeString(String dateStr1, String dateStr2) {

        List<Integer> dates1 = new ArrayList<Integer>();
        List<Integer> times1 = new ArrayList<Integer>();

        String[] arr1 = dateStr1.split(TIMEDATE_SPLIT);
        if (arr1.length == 2) {

            String[] arr12 = arr1[0].split(DATE_SPLIT);
            if (arr12.length <= 3) {
                for (int i = 0; i < arr12.length; i++) {

                    dates1.add(Integer.valueOf(arr12[i]));

                }
            }

            String[] arr13 = arr1[1].split(TIME_SPLIT);
            if (arr13.length <= 4) {
                for (int i = 0; i < arr13.length; i++) {

                    times1.add(Integer.valueOf(arr13[i]));

                }
            }

        } else if (arr1.length == 1) {

            if (arr1[0].contains(DATE_SPLIT)) {

                String[] arr12 = arr1[0].split(DATE_SPLIT);

                if (arr12.length <= 3) {
                    for (int i = 0; i < arr12.length; i++) {

                        dates1.add(Integer.valueOf(arr12[i]));
                    }
                }

            } else if (arr1[0].contains(TIME_SPLIT)) {

                String[] arr12 = arr1[0].split(TIME_SPLIT);

                if (arr12.length <= 4) {
                    for (int i = 0; i < arr12.length; i++) {

                        times1.add(Integer.valueOf(arr12[i]));
                    }
                }

            } else {
                times1.add(Integer.valueOf(arr1[0]));
            }

        } else {
            return DATESTR_NOTCOMPARE;
        }

        List<Integer> dates2 = new ArrayList<Integer>();
        List<Integer> times2 = new ArrayList<Integer>();

        String[] arr2 = dateStr2.split(TIMEDATE_SPLIT);
        if (arr2.length == 2) {

            String[] arr12 = arr2[0].split(DATE_SPLIT);
            if (arr12.length <= 3) {
                for (int i = 0; i < arr12.length; i++) {

                    dates2.add(Integer.valueOf(arr12[i]));

                }
            }

            String[] arr13 = arr2[1].split(TIME_SPLIT);
            if (arr13.length <= 4) {
                for (int i = 0; i < arr13.length; i++) {

                    times2.add(Integer.valueOf(arr13[i]));

                }
            }

        } else if (arr2.length == 1) {

            if (arr2[0].contains(DATE_SPLIT)) {

                String[] arr12 = arr2[0].split(DATE_SPLIT);

                if (arr12.length <= 3) {
                    for (int i = 0; i < arr12.length; i++) {

                        dates2.add(Integer.valueOf(arr12[i]));
                    }
                }

            } else if (arr2[0].contains(TIME_SPLIT)) {

                String[] arr12 = arr2[0].split(TIME_SPLIT);

                if (arr12.length <= 4) {
                    for (int i = 0; i < arr12.length; i++) {

                        times2.add(Integer.valueOf(arr12[i]));
                    }
                }

            } else {
                times2.add(Integer.valueOf(arr2[0]));
            }

        } else {
            return DATESTR_NOTCOMPARE;
        }

        if ((dates1.size() <= 0) && (times1.size() <= 0)) {
            return DATESTR_NOTCOMPARE;
        }

        if ((dates1.size() != dates2.size())
                || (times1.size() != times2.size())) {
            return DATESTR_NOTCOMPARE;
        }

        for (int i = 0; i < dates1.size(); i++) {

            if (dates1.get(i) > dates2.get(i))
                return DATESTR_COMPARE1;
            else if (dates1.get(i) < dates2.get(i))
                return DATESTR_COMPARE2;

        }

        for (int i = 0; i < times1.size(); i++) {

            if (times1.get(i) > times2.get(i))
                return DATESTR_COMPARE1;
            else if (times1.get(i) < times2.get(i))
                return DATESTR_COMPARE2;
        }

        return DATESTR_COMPARE0;

    }

    private static boolean flagIsWeek(int flag) {

        return ((flag & STRING_WEEK_FLAG) == STRING_WEEK_FLAG);
    }

    private static boolean flagIsYear(int flag) {

        return ((flag & STRING_YEAR_FLAG) == STRING_YEAR_FLAG);
    }

    private static boolean flagIsMonth(int flag) {

        return ((flag & STRING_MONTH_FLAG) == STRING_MONTH_FLAG);
    }

    private static boolean flagIsDay(int flag) {

        return ((flag & STRING_DAY_FLAG) == STRING_DAY_FLAG);

    }

    private static boolean flagIsHour(int flag) {

        return ((flag & STRING_HOUR_FLAG) == STRING_HOUR_FLAG);
    }

    private static boolean flagIsMinute(int flag) {

        return ((flag & STRING_MINUTE_FLAG) == STRING_MINUTE_FLAG);
    }

    private static boolean flagIsSecond(int flag) {

        return ((flag & STRING_SECOND_FLAG) == STRING_SECOND_FLAG);
    }

    private static boolean flagIsMsecond(int flag) {

        return ((flag & STRING_MSECOND_FLAG) == STRING_MSECOND_FLAG);

    }

    public static String getDateString(Calendar car, int flag) {

        if (flagIsWeek(flag)) {

            int week = car.get(Calendar.DAY_OF_WEEK) - 1;
            if (week == 0)
                week = 7;

            String formatStr = StringUtil.EMPTYSTR;
            if (flagIsHour(flag)) {

                formatStr = "HH";
            }

            if (flagIsMinute(flag)) {

                if (StringUtil.isNullEmpty(formatStr))
                    formatStr = "mm";
                else
                    formatStr += ":mm";
            }

            if (flagIsSecond(flag)) {
                if (StringUtil.isNullEmpty(formatStr))
                    formatStr = "ss";
                else
                    formatStr += ":ss";
            }

            if (flagIsMsecond(flag)) {
                if (StringUtil.isNullEmpty(formatStr))
                    formatStr = "SSS";
                else
                    formatStr += ":SSS";
            }

            String s = StringUtil.EMPTYSTR;
            if (!StringUtil.isNullEmpty(formatStr))
                s = DateUtil
                        .calendarToString(car, formatStr);

            return String.format("%d %s", week, s).trim();
        } else {

            String formatStr1 = StringUtil.EMPTYSTR;

            if (flagIsYear(flag)) {
                formatStr1 = "yyyy";
            }

            if (flagIsMonth(flag)) {
                if (StringUtil.isNullEmpty(formatStr1))
                    formatStr1 = "MM";
                else
                    formatStr1 += "-MM";
            }

            if (flagIsDay(flag)) {
                if (StringUtil.isNullEmpty(formatStr1))
                    formatStr1 = "dd";
                else
                    formatStr1 += "-dd";
            }

            String formatStr2 = StringUtil.EMPTYSTR;
            if (flagIsHour(flag)) {

                formatStr2 = "HH";

            }

            if (flagIsMinute(flag)) {
                if (StringUtil.isNullEmpty(formatStr2))
                    formatStr2 = "mm";
                else
                    formatStr2 += ":mm";
            }

            if (flagIsSecond(flag)) {
                if (StringUtil.isNullEmpty(formatStr2))
                    formatStr2 = "ss";
                else
                    formatStr2 += ":ss";
            }

            if (flagIsMsecond(flag)) {
                if (StringUtil.isNullEmpty(formatStr2))
                    formatStr2 = "SSS";
                else
                    formatStr2 += ":SSS";
            }

            String formatStr = StringUtil.EMPTYSTR;
            if (!StringUtil.isNullEmpty(formatStr1))
                formatStr = formatStr1 + " " + formatStr2;
            else
                formatStr = formatStr2;

            formatStr = formatStr.trim();

            if (!StringUtil.isNullEmpty(formatStr))
                return DateUtil.calendarToString(car,
                        formatStr);
            else
                return StringUtil.EMPTYSTR;
        }

    }

}
