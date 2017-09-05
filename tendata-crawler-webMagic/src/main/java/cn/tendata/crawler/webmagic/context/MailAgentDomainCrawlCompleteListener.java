package cn.tendata.crawler.webmagic.context;

import cn.xinbee.rcs.data.domain.MailChannelAgentDomainQualityMonitoring;
import cn.xinbee.rcs.data.domain.MailChannelCrawlerAgentDomain;
import cn.xinbee.rcs.data.repository.MailAgentDomainIpQualityMonitoringRepository;
import cn.xinbee.rcs.data.repository.MailChannelCrawlerAgentDomainRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 1.保存域名对象信息；
 * 2.保存当天的唯一记录；
 * 3.保存爬取历史；
 *
 * Created by ernest on 2017/9/5.
 */
public class MailAgentDomainCrawlCompleteListener
    implements ApplicationListener<MailAgentDomainCrawlCompleteEvent> {

    private final MailChannelCrawlerAgentDomainRepository mailChannelCrawlerAgentDomainRepository;
    private final MailAgentDomainIpQualityMonitoringRepository
        mailAgentDomainIpQualityMonitoringRepository;

    public MailAgentDomainCrawlCompleteListener(
        MailChannelCrawlerAgentDomainRepository mailChannelCrawlerAgentDomainRepository,
        MailAgentDomainIpQualityMonitoringRepository mailAgentDomainIpQualityMonitoringRepository) {
        this.mailChannelCrawlerAgentDomainRepository = mailChannelCrawlerAgentDomainRepository;
        this.mailAgentDomainIpQualityMonitoringRepository =
            mailAgentDomainIpQualityMonitoringRepository;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void onApplicationEvent(
        MailAgentDomainCrawlCompleteEvent mailAgentDomainCrawlCompleteEvent) {
        MailAgentDomainCrawlEventData mailAgentDomainCrawlEventData =
            mailAgentDomainCrawlCompleteEvent.getMailAgentDomainCrawlEventData();
        this.mailChannelCrawlerAgentDomainRepository.save(
            mailAgentDomainCrawlEventData.getMailChannelCrawlerAgentDomain());
        this.store(mailAgentDomainCrawlEventData.getBlackListSummary(),
            mailAgentDomainCrawlEventData.getSpamLevel(),
            mailAgentDomainCrawlEventData.getEmailReputation(),
            mailAgentDomainCrawlEventData.getMailChannelCrawlerAgentDomain());
    }

    private void store(Integer blackListSummary, String lastDaySpamLevel, String emailReputation,
        MailChannelCrawlerAgentDomain mailAgentDomain) {
        MailChannelAgentDomainQualityMonitoring mailAgentDomainIpQualityMonitoring =
            new MailChannelAgentDomainQualityMonitoring();
        mailAgentDomainIpQualityMonitoring.setMailAgentDomain(mailAgentDomain);
        mailAgentDomainIpQualityMonitoring.setIpLastDaySpamLevel(lastDaySpamLevel);
        mailAgentDomainIpQualityMonitoring.setIpEmailReputation(emailReputation);
        mailAgentDomainIpQualityMonitoring.setIpBlackListSummary(blackListSummary);
        mailAgentDomainIpQualityMonitoringRepository.save(mailAgentDomainIpQualityMonitoring);
    }
}
