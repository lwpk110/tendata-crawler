package cn.xinbee.rcs.service;

import cn.xinbee.rcs.data.domain.MailChannelAgentDomainQualityMonitoring;
import java.util.Map;
import org.joda.time.DateTime;

public interface MailAgentDomainQualityMonitoringService extends EntityService<MailChannelAgentDomainQualityMonitoring,Long> {

    Map<String, Map<String, Integer>> crawlDataStatisticsDetails(Integer channelId, DateTime start, DateTime end);

    void crawl(Integer channelCode);
}
