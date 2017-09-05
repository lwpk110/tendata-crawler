package cn.tendata.crawler.webmagic.suport;

import cn.tendata.crawler.webmagic.core.MailAgentDomainIpQualityCrawler;
import cn.xinbee.crawler.api.channel.domain.MailChannelManager;
import cn.xinbee.crawler.data.domain.MailChannelCrawlerAgentDomain;
import java.util.List;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * {@inheritDoc}
 *
 * Created by ernest on 2017/8/24.
 */
public class MailAgentDomainCrawlScheduler {
    private final MailAgentDomainIpQualityCrawler mailAgentDomainIpQualityCrawler;
    private final MailChannelManager mailChannelManager;

    public MailAgentDomainCrawlScheduler(
        MailAgentDomainIpQualityCrawler mailAgentDomainIpQualityCrawler,
         MailChannelManager mailChannelManager) {
        this.mailAgentDomainIpQualityCrawler = mailAgentDomainIpQualityCrawler;
        this.mailChannelManager = mailChannelManager;
    }

    @Scheduled(cron = "${scheduler.crawler.domain-cron}")
    public void poll(){
        final List<MailChannelCrawlerAgentDomain> mailAgentDomainList = mailChannelManager.getAllDomains();
        mailAgentDomainList.forEach(mailAgentDomainIpQualityCrawler::crawl);
    }
}
