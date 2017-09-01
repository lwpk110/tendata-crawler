package cn.xinbee.rcs.statistics.util;

import org.joda.time.DateTime;

public abstract class StatisticsUtils {

    public static String extractDateKey(DateTime date) {
        int year = date.getYear();
        int month = date.monthOfYear().get();
        int day = date.dayOfMonth().get();
        return year + "-" + month + "-" + day;
    }
}
