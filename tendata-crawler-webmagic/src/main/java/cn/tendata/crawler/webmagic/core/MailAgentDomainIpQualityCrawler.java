package cn.tendata.crawler.webmagic.core;

import cn.tendata.crawler.webmagic.Item.processror.multirbl.MultiblCrawlerPipeline;
import cn.tendata.crawler.webmagic.Item.processror.talos.TalosCrawlerPipeline;
import cn.tendata.crawler.webmagic.context.MailAgentDomainCrawlCompleteEvent;
import cn.tendata.crawler.webmagic.context.MailAgentDomainCrawlEventData;
import cn.xinbee.crawler.core.Crawler;
import cn.xinbee.crawler.data.domain.MailChannelCrawlerAgentDomain;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import us.codecraft.webmagic.Spider;

import static cn.tendata.crawler.webmagic.Item.processror.multirbl.MultiblData.MULTIBL_REQUEST_URL;
import static cn.tendata.crawler.webmagic.Item.processror.talos.TalosData.TALOS_REQUEST_URL;

/**
 * {@inheritDoc}
 *
 * Created by ernest on 2017/8/24.
 */
public class MailAgentDomainIpQualityCrawler extends AbstractDomainIpQualityCrawler implements
    Crawler<MailChannelCrawlerAgentDomain>, ApplicationEventPublisherAware {

    private ApplicationEventPublisher eventPublisher;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SpiderFactory spiderFactory;


    public MailAgentDomainIpQualityCrawler(SpiderFactory spiderFactory) {
        this.spiderFactory = spiderFactory;
    }

    @Override
    public void crawl(MailChannelCrawlerAgentDomain mailAgentDomain) {
        MultiblCrawlerPipeline multiblCrawlerPipeline = runMultibl(mailAgentDomain);
        TalosCrawlerPipeline talosCrawlerPipeline = runTalos(mailAgentDomain);

        MailAgentDomainCrawlEventData mailAgentDomainCrawlEventData = createMailAgentDomainCrawlEventData(multiblCrawlerPipeline, talosCrawlerPipeline,mailAgentDomain);
        MailAgentDomainCrawlCompleteEvent mailAgentDomainCrawlCompleteEvent = new MailAgentDomainCrawlCompleteEvent(this, mailAgentDomainCrawlEventData);

        eventPublisher.publishEvent(mailAgentDomainCrawlCompleteEvent);
    }

    public MultiblCrawlerPipeline runMultibl(MailChannelCrawlerAgentDomain mailAgentDomain) {
        Spider multiblSpider = spiderFactory.getSpider(MULTIBL_KEY);
        multiblSpider.setEmptySleepTime(1000);
        MultiblCrawlerPipeline multiblCrawlerPipeline = new MultiblCrawlerPipeline();
        multiblSpider.addUrl(MULTIBL_REQUEST_URL.replace("{IP}", mailAgentDomain.getIpInfo()))
            .setPipelines(Collections.singletonList(multiblCrawlerPipeline))
            .run();
        logger.info("--> black List summary :{}", multiblCrawlerPipeline.getBlackListSummary());
        multiblSpider.close();
        return multiblCrawlerPipeline;
    }

    public TalosCrawlerPipeline runTalos(MailChannelCrawlerAgentDomain mailAgentDomain) {
        TalosCrawlerPipeline talosCrawlerPipeline = new TalosCrawlerPipeline();
        Spider taloSpider = spiderFactory.getSpider(TALOS_KEY);
        taloSpider
            .addUrl(TALOS_REQUEST_URL.replace("{IP}", mailAgentDomain.getIpInfo()))
            .setPipelines(Collections.singletonList(talosCrawlerPipeline))
            .run();
        taloSpider.close();
        return talosCrawlerPipeline;
    }

    private MailAgentDomainCrawlEventData createMailAgentDomainCrawlEventData(MultiblCrawlerPipeline multiblCrawlerPipeline,
        TalosCrawlerPipeline talosCrawlerPipeline,MailChannelCrawlerAgentDomain agentDomain){
        MailAgentDomainCrawlEventData mailAgentDomainCrawlEventData = new MailAgentDomainCrawlEventData();
        mailAgentDomainCrawlEventData.setMailChannelCrawlerAgentDomain(agentDomain);
        if(null != multiblCrawlerPipeline){
            mailAgentDomainCrawlEventData.setBlackListSummary(multiblCrawlerPipeline.getBlackListSummary());
        }
        if(null != talosCrawlerPipeline){
            mailAgentDomainCrawlEventData.setEmailReputation(talosCrawlerPipeline.getEmailReputation());
            mailAgentDomainCrawlEventData.setSpamLevel(talosCrawlerPipeline.getLastDaySpamLevel());
        }
        return mailAgentDomainCrawlEventData;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        eventPublisher = applicationEventPublisher;
    }
}
