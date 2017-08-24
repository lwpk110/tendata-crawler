package cn.tendata.crawler.webmagic.core;

import cn.xinbee.rcs.data.domain.MailAgentDomainIpQualityMonitoring;
import cn.xinbee.rcs.data.repository.MailAgentDomainIpQualityMonitoringRepository;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import static cn.tendata.crawler.webmagic.core.AbstractDomainIpQualityCrawler.BLACKLIST_SUMMARY;
import static cn.tendata.crawler.webmagic.core.AbstractDomainIpQualityCrawler.EMAIL_REPUTATION;
import static cn.tendata.crawler.webmagic.core.AbstractDomainIpQualityCrawler.LAST_DAY_SPAM_LEVEL;

/**
 * {@inheritDoc}
 *
 * Created by ernest on 2017/8/24.
 */
public class MailAgentDomainIpQualityPipeline implements Pipeline {

    private final MailAgentDomainIpQualityMonitoringRepository mailAgentDomainIpQualityMonitoringRepository;

    private MailAgentDomainIpQualityMonitoring domainIpQualityMonitoring;

    public MailAgentDomainIpQualityPipeline(
        MailAgentDomainIpQualityMonitoringRepository mailAgentDomainIpQualityMonitoringRepository) {
        this.mailAgentDomainIpQualityMonitoringRepository = mailAgentDomainIpQualityMonitoringRepository;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        Spider spider = (Spider) task;
        Request request = spider.getScheduler().poll(task);
        if(null == request){
            MailAgentDomainScheduler mailAgentDomainScheduler = (MailAgentDomainScheduler) spider.getScheduler();
            domainIpQualityMonitoring = new MailAgentDomainIpQualityMonitoring();
            domainIpQualityMonitoring.setBlackListSummary(resultItems.get(BLACKLIST_SUMMARY));
            domainIpQualityMonitoring.setEmailReputation(resultItems.get(EMAIL_REPUTATION));
            domainIpQualityMonitoring.setLastDaySpamLevel(resultItems.get(LAST_DAY_SPAM_LEVEL));
            domainIpQualityMonitoring.setMailAgentDomain(mailAgentDomainScheduler.getMailAgentDomain());
            mailAgentDomainIpQualityMonitoringRepository.save(domainIpQualityMonitoring);
        }

    }

    public MailAgentDomainIpQualityMonitoring getDomainIpQualityMonitoring() {
        return domainIpQualityMonitoring;
    }

    public void setDomainIpQualityMonitoring(
        MailAgentDomainIpQualityMonitoring domainIpQualityMonitoring) {
        this.domainIpQualityMonitoring = domainIpQualityMonitoring;
    }


}



