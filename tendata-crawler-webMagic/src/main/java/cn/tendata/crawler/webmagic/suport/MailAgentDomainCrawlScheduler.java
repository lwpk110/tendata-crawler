package cn.tendata.crawler.webmagic.suport;

import cn.tendata.crawler.webmagic.core.MailAgentDomainIpQualityCrawler;
import cn.xinbee.rcs.data.domain.MailAgentDomain;
import cn.xinbee.rcs.data.repository.MailAgentDomainRepository;
import java.util.List;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * {@inheritDoc}
 *
 * Created by ernest on 2017/8/24.
 */
public class MailAgentDomainCrawlScheduler {
    private final MailAgentDomainIpQualityCrawler mailAgentDomainIpQualityCrawler;
    private final MailAgentDomainRepository mailAgentDomainRepository;

    public MailAgentDomainCrawlScheduler(
        MailAgentDomainIpQualityCrawler mailAgentDomainIpQualityCrawler,
        MailAgentDomainRepository mailAgentDomainRepository) {
        this.mailAgentDomainIpQualityCrawler = mailAgentDomainIpQualityCrawler;
        this.mailAgentDomainRepository = mailAgentDomainRepository;
    }

    @Scheduled(initialDelay = 10000,fixedDelay = 3600000)
    public void poll(){
        final List<MailAgentDomain> mailAgentDomainList = mailAgentDomainRepository.findAllByDisabledFalseAndDeletedFalseAndIpInfoNotNull();
        mailAgentDomainList.forEach(mailAgentDomainIpQualityCrawler::crawl);
    }
}
