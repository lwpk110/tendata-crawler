package cn.xinbee.rcs.util;

import org.joda.time.DateTime;

public final class DateTimeQuery {

    private final DateTime startDate;
    private final DateTime endDate;

    public DateTimeQuery(DateTime startDate, DateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public DateTime getStartDate() {
        return startDate;
    }
}
