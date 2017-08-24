package cn.xinbee.rcs.util;

import cn.xinbee.rcs.data.elasticsearch.model.ChannelReportSearchConstants;
import org.springframework.util.Assert;

import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class ChannelReportUtil {

    public static Map<String, Object> toPercentSendQualityData(Map<String, Object> data) {
        Assert.notNull(data, "channel report data must not be null");
        Map<String, Object> result = new LinkedHashMap<>();
        for (String key : data.keySet()) {
            Map<String, Object> value = (Map<String, Object>) data.get(key);
            double total = (double) value.get(ChannelReportSearchConstants.TOTAL);
            double hardBounce = (double) value.get(ChannelReportSearchConstants.HARDBOUNCE);
            double softBounce = (double) value.get(ChannelReportSearchConstants.SOFTBOUNCE);
            double unsubscribe = (double) value.get(ChannelReportSearchConstants.UNSUBSCRIBE);
            double spam = (double) value.get(ChannelReportSearchConstants.SPAM);
            double uniqueOpenCount = (double) value.get(ChannelReportSearchConstants.UNIQUEOPENCOUNT);
            double mailClicked = (double) value.get(ChannelReportSearchConstants.MAILCLICKED);
            String hardBouncePercent = "0", softBouncePercent = "0",
                    unsubscribePercent = "0", spamPercent = "0",
                    openPercent = "0", clickPercent = "0";

            if (total != 0) {
                double cardinalNumber = (total - hardBounce - softBounce);
                hardBouncePercent = format(hardBounce / total * 100);
                softBouncePercent = format(softBounce / total * 100);
                unsubscribePercent = format(unsubscribe / cardinalNumber * 100);
                spamPercent = format(spam / cardinalNumber * 100);
                openPercent = format((uniqueOpenCount + mailClicked + unsubscribe + spam) / cardinalNumber * 100);
                clickPercent = format(mailClicked / cardinalNumber * 100);
            }

            value.put(ChannelReportSearchConstants.HARDBOUNCE, hardBouncePercent);
            value.put(ChannelReportSearchConstants.SOFTBOUNCE, softBouncePercent);
            value.put(ChannelReportSearchConstants.UNSUBSCRIBE, unsubscribePercent);
            value.put(ChannelReportSearchConstants.SPAM, spamPercent);
            value.put(ChannelReportSearchConstants.UNIQUEOPENCOUNT, openPercent);
            value.put(ChannelReportSearchConstants.MAILCLICKED, clickPercent);
            value.remove(ChannelReportSearchConstants.TOTAL);
            result.put(key, value);
        }
        return result;
    }

    private static String format(double value) {
        DecimalFormat df = new DecimalFormat("#0.00");
        if (value == 0) {
            return "0";
        }
        return df.format(value);
    }

}
