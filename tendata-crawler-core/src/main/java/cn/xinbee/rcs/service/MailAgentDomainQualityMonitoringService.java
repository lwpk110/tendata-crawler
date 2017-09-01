package cn.xinbee.rcs.service;

import cn.xinbee.rcs.data.domain.MailAgentDomain;
import cn.xinbee.rcs.data.domain.MailAgentDomainQualityMonitoring;
import org.joda.time.DateTime;

import java.util.Map;

public interface MailAgentDomainQualityMonitoringService extends EntityService<MailAgentDomainQualityMonitoring,Long> {

    Map<String, Map<String, Integer>> crawlDataStatisticsDetails(MailAgentDomain mailAgentDomain, DateTime start, DateTime end);
}
