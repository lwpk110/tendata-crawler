package cn.tendata.crawler.webmagic.suport;

import cn.tendata.crawler.webmagic.core.MailAgentDomainIpQualityCrawler;
import cn.xinbee.rcs.api.channel.domain.MailChannelManager;
import cn.xinbee.rcs.data.domain.MailChannelCrawlerAgentDomain;
import java.util.List;

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

    //@Scheduled(initialDelay = 10000,fixedDelay = 3600000)
    public void poll(){
        final List<MailChannelCrawlerAgentDomain> mailAgentDomainList = mailChannelManager.getAllDomains();
        mailAgentDomainList.forEach(mailAgentDomainIpQualityCrawler::crawl);
    }
}
