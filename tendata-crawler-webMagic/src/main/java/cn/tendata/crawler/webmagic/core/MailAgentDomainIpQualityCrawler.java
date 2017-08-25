package cn.tendata.crawler.webmagic.core;

import cn.tendata.crawler.webmagic.Item.processror.multirbl.MultiblCrawlerPipeline;
import cn.tendata.crawler.webmagic.Item.processror.talos.TalosCrawlerPipeline;
import cn.xinbee.rcs.data.domain.MailAgentDomain;
import cn.xinbee.rcs.data.domain.MailAgentDomainQualityMonitoring;
import cn.xinbee.rcs.data.repository.MailAgentDomainIpQualityMonitoringRepository;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Spider;

import static cn.tendata.crawler.webmagic.Item.processror.multirbl.AbstractMultiblConst.MULTIBL_REQUEST_URL;
import static cn.tendata.crawler.webmagic.Item.processror.talos.TalosData.TALOS_REQUEST_URL;

/**
 * {@inheritDoc}
 *
 * Created by ernest on 2017/8/24.
 */
public class MailAgentDomainIpQualityCrawler extends AbstractDomainIpQualityCrawler
    implements Crawler<MailAgentDomain> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SpiderFactory spiderFactory;
    private final MailAgentDomainIpQualityMonitoringRepository
        mailAgentDomainIpQualityMonitoringRepository;

    public MailAgentDomainIpQualityCrawler(SpiderFactory spiderFactory,
        MailAgentDomainIpQualityMonitoringRepository mailAgentDomainIpQualityMonitoringRepository) {
        this.spiderFactory = spiderFactory;
        this.mailAgentDomainIpQualityMonitoringRepository =
            mailAgentDomainIpQualityMonitoringRepository;
    }

    @Override
    public void crawl(MailAgentDomain mailAgentDomain) {
        Spider multiblSpider = spiderFactory.getSpider(MULTIBL_KEY);
        multiblSpider.setEmptySleepTime(1000);
        MultiblCrawlerPipeline multiblCrawlerPipeline = new MultiblCrawlerPipeline();
        multiblSpider.addUrl(MULTIBL_REQUEST_URL.replace("{IP}", mailAgentDomain.getIpInfo()))
            .setPipelines(Collections.singletonList(multiblCrawlerPipeline))
            .run();
       logger.info("--> black List summary :{}", multiblCrawlerPipeline.getBlackListSummary());

        TalosCrawlerPipeline talosCrawlerPipeline = new TalosCrawlerPipeline();
        Spider taloSpider = spiderFactory.getSpider(TALOS_KEY);
        taloSpider
            .addUrl(TALOS_REQUEST_URL.replace("{IP}", mailAgentDomain.getIpInfo()))
            .setPipelines(Collections.singletonList(talosCrawlerPipeline))
            .run();

        MailAgentDomainQualityMonitoring mailAgentDomainIpQualityMonitoring =
            new MailAgentDomainQualityMonitoring();
        mailAgentDomainIpQualityMonitoring.setMailAgentDomain(mailAgentDomain);
        mailAgentDomainIpQualityMonitoring.setLastDaySpamLevel(
            talosCrawlerPipeline.getLastDaySpamLevel());
        mailAgentDomainIpQualityMonitoring.setEmailReputation(
            talosCrawlerPipeline.getEmailReputation());
        mailAgentDomainIpQualityMonitoring.setBlackListSummary(
            multiblCrawlerPipeline.getBlackListSummary());
        mailAgentDomainIpQualityMonitoringRepository.save(mailAgentDomainIpQualityMonitoring);

        multiblSpider.stop();
        taloSpider.stop();

    }
}
