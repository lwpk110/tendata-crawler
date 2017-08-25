package cn.tendata.crawler.webmagic.config;

import cn.tendata.crawler.webmagic.Item.processror.multirbl.MultiblBlackListProcessor;
import cn.tendata.crawler.webmagic.Item.processror.talos.TalosIpQualityProcessor;
import cn.tendata.crawler.webmagic.core.MailAgentDomainIpQualityCrawler;
import cn.tendata.crawler.webmagic.core.SpiderFactory;
import cn.tendata.crawler.webmagic.suport.MailAgentDomainCrawlScheduler;
import cn.xinbee.rcs.data.repository.MailAgentDomainIpQualityMonitoringRepository;
import cn.xinbee.rcs.data.repository.MailAgentDomainRepository;
import java.util.Collection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import static cn.tendata.crawler.webmagic.core.AbstractDomainIpQualityCrawler.MULTIBL_KEY;
import static cn.tendata.crawler.webmagic.core.AbstractDomainIpQualityCrawler.TALOS_KEY;

/**
 * {@inheritDoc}
 *
 * Created by ernest on 2017/8/24.
 */
@Configuration
@EnableScheduling
public class MailAgentDomainQualityConfig {

    @Bean
    public PageProcessor multiblBlackListProcessor() {
        return new MultiblBlackListProcessor();
    }

    @Bean
    public PageProcessor talosIpQualityProcessor() {
        return new TalosIpQualityProcessor();
    }

    @Bean
    public Spider multiblBlackListSpider() {
        return new Spider(multiblBlackListProcessor())
            .thread(300).setUUID(MULTIBL_KEY);
    }

    @Bean
    public Spider talosIpQualitySpider() {
        return new Spider(talosIpQualityProcessor())
            .thread(1).setUUID(TALOS_KEY);
    }

    @Bean
    public SpiderFactory spiderFactory(Collection<Spider> spiderCollection) {
        return new SpiderFactory(spiderCollection);
    }

    @Bean
    public MailAgentDomainIpQualityCrawler mailAgentDomainIpQualityCrawler(
        SpiderFactory spiderFactory,
        MailAgentDomainIpQualityMonitoringRepository mailAgentDomainIpQualityMonitoringRepository) {
        return new MailAgentDomainIpQualityCrawler(spiderFactory,
            mailAgentDomainIpQualityMonitoringRepository);
    }

    @Bean
    public MailAgentDomainCrawlScheduler mailAgentDomainCrawlScheduler(
        MailAgentDomainIpQualityCrawler mailAgentDomainIpQualityCrawler,
        MailAgentDomainRepository mailAgentDomainRepository) {
        return new MailAgentDomainCrawlScheduler(mailAgentDomainIpQualityCrawler,
            mailAgentDomainRepository);
    }
}
