package cn.tendata.crawler.webmagic.config;

import cn.tendata.crawler.webmagic.Item.processror.multirbl.MultiblBlackListProcessor;
import cn.tendata.crawler.webmagic.Item.processror.talos.TalosIpQualityProcessor;
import cn.tendata.crawler.webmagic.context.MailAgentDomainCrawlCompleteListener;
import cn.tendata.crawler.webmagic.core.MailAgentDomainIpQualityCrawler;
import cn.tendata.crawler.webmagic.core.SpiderFactory;
import cn.tendata.crawler.webmagic.suport.MailAgentDomainCrawlScheduler;
import cn.xinbee.crawler.api.channel.domain.MailChannelManager;
import cn.xinbee.crawler.api.config.MailChannelManagerConfig;
import cn.xinbee.crawler.data.repository.MailAgentDomainIpQualityMonitoringRepository;
import cn.xinbee.crawler.data.repository.MailChannelCrawlerAgentDomainRepository;
import java.util.Collection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * {@inheritDoc}
 *
 * Created by ernest on 2017/8/24.
 */
@Configuration
@EnableScheduling
@Import(MailChannelManagerConfig.class)
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
    public MailAgentDomainCrawlCompleteListener mailAgentDomainCrawlCompleteListener(MailChannelCrawlerAgentDomainRepository mailChannelCrawlerAgentDomainRepository,
        MailAgentDomainIpQualityMonitoringRepository mailAgentDomainIpQualityMonitoringRepository){
        return  new MailAgentDomainCrawlCompleteListener(mailChannelCrawlerAgentDomainRepository,mailAgentDomainIpQualityMonitoringRepository);
    }

    @Bean
    public MailAgentDomainIpQualityCrawler mailAgentDomainIpQualityCrawler() {
        return new MailAgentDomainIpQualityCrawler( multiblBlackListProcessor(),
            talosIpQualityProcessor());
    }
    @Bean
    public MailAgentDomainCrawlScheduler mailAgentDomainCrawlScheduler(
        MailAgentDomainIpQualityCrawler mailAgentDomainIpQualityCrawler,
        MailChannelManager mailChannelManager) {
        return new MailAgentDomainCrawlScheduler(mailAgentDomainIpQualityCrawler,
            mailChannelManager);
    }
}
