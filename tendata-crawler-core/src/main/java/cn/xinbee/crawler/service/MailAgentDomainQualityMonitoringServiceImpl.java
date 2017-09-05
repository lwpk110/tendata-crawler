package cn.xinbee.crawler.service;

import cn.xinbee.crawler.core.Crawler;
import cn.xinbee.crawler.api.channel.domain.MailChannelManager;
import cn.xinbee.crawler.data.domain.MailChannelAgentDomainQualityMonitoring;
import cn.xinbee.crawler.data.domain.MailChannelCrawlerAgentDomain;
import cn.xinbee.crawler.data.repository.MailAgentDomainIpQualityMonitoringRepository;
import cn.xinbee.crawler.statistics.CrawDataStatisticsHandler;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class MailAgentDomainQualityMonitoringServiceImpl extends EntityServiceSupport<MailChannelAgentDomainQualityMonitoring, Long,
        MailAgentDomainIpQualityMonitoringRepository> implements MailAgentDomainQualityMonitoringService {

    private final CrawDataStatisticsHandler statisticsHandler;
    private final MailChannelManager mailChannelManager;
    private final Crawler<MailChannelCrawlerAgentDomain> crawler;

    @Autowired
    public MailAgentDomainQualityMonitoringServiceImpl(
        MailAgentDomainIpQualityMonitoringRepository repository,
        CrawDataStatisticsHandler statisticsHandler, MailChannelManager mailChannelManager,
        Crawler<MailChannelCrawlerAgentDomain> crawler) {
        super(repository);
        this.statisticsHandler = statisticsHandler;
        this.mailChannelManager = mailChannelManager;
        this.crawler = crawler;
    }

    @Override
    public Map<String, Map<String, Integer>> crawlDataStatisticsDetails(Integer channelCode, DateTime start, DateTime end) {
            Collection<MailChannelAgentDomainQualityMonitoring> source = getRepository().findByCreatedDateAfterAndCreatedDateBeforeOrderByCreatedDate(start, end);
            return statisticsHandler.handle(source,channelCode);

    }

    @Override
    public void crawl(Integer channelCode) {
        List<MailChannelCrawlerAgentDomain> mailAgentDomains = mailChannelManager.getDomains(channelCode);
        if(!CollectionUtils.isEmpty(mailAgentDomains)){
            this.crawler.crawl(mailAgentDomains.get(0));
        }
    }
}