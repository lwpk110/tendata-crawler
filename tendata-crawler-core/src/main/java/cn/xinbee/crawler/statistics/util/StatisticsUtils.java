package cn.xinbee.crawler.statistics.util;

import org.joda.time.DateTime;

public abstract class StatisticsUtils {

    public static String extractDateKey(DateTime date) {
        int year = date.getYear();
        int month = date.monthOfYear().get();
        int day = date.dayOfMonth().get();
        return year + "-" + month + "-" + day;
    }

    public static int getReputationVal(String ipEmailReputation) {
        int reputationLevel = 0;
        switch (ipEmailReputation) {
            case "Good":
                reputationLevel = 1;
                break;
            case "Neutral":
                reputationLevel = 2;
                break;
            case "Poor":
                reputationLevel = 3;
                break;
        }
        return reputationLevel;
    }

    public static int getSpamLevelVal(String ipLastDaySpamLevel) {
        int spamLevel = 0;
        switch (ipLastDaySpamLevel) {
            case "None":
                spamLevel = 1;
                break;
            case "Medium":
                spamLevel = 2;
                break;
            case "High":
                spamLevel = 3;
                break;
            case "Very High":
                spamLevel = 4;
                break;
        }
        return spamLevel;
    }
}
