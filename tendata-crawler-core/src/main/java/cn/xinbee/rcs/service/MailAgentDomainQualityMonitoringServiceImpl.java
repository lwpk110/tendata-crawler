package cn.xinbee.rcs.service;

import cn.xinbee.rcs.data.domain.MailAgentDomain;
import cn.xinbee.rcs.data.domain.MailAgentDomainQualityMonitoring;
import cn.xinbee.rcs.data.repository.MailAgentDomainIpQualityMonitoringRepository;
import cn.xinbee.rcs.statistics.CrawDataStatisticsHandler;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Service
public class MailAgentDomainQualityMonitoringServiceImpl extends EntityServiceSupport<MailAgentDomainQualityMonitoring, Long,
        MailAgentDomainIpQualityMonitoringRepository> implements MailAgentDomainQualityMonitoringService {

    private final CrawDataStatisticsHandler statisticsHandler;

    @Autowired
    public MailAgentDomainQualityMonitoringServiceImpl(MailAgentDomainIpQualityMonitoringRepository repository, CrawDataStatisticsHandler statisticsHandler) {
        super(repository);
        this.statisticsHandler = statisticsHandler;
    }

    @Override
    public Map<String, Map<String, Integer>> crawlDataStatisticsDetails(MailAgentDomain mailAgentDomain, DateTime start, DateTime end) {
        Collection<MailAgentDomainQualityMonitoring> source = getRepository().findByMailAgentDomainAndCreatedDateAfterAndCreatedDateBeforeOrderByCreatedDate(
                mailAgentDomain, start, end);
        return statisticsHandler.handle(source);
    }
}
