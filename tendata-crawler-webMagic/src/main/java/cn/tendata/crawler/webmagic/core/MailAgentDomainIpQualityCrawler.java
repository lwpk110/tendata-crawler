package cn.tendata.crawler.webmagic.core;

import cn.tendata.crawler.webmagic.Item.processror.multirbl.AbstractMultiblConst;
import cn.tendata.crawler.webmagic.Item.processror.talos.AbstractTalosConst;
import cn.xinbee.rcs.data.domain.MailAgentDomain;
import us.codecraft.webmagic.Spider;

/**
 * {@inheritDoc}
 *
 * Created by ernest on 2017/8/24.
 */
public class MailAgentDomainIpQualityCrawler extends AbstractDomainIpQualityCrawler implements Crawler<MailAgentDomain> {

    private final SpiderFactory spiderFactory;

    public MailAgentDomainIpQualityCrawler(SpiderFactory spiderFactory) {
        this.spiderFactory = spiderFactory;
    }


    @Override
    public void crawl(MailAgentDomain mailAgentDomain) {
        MailAgentDomainScheduler mailAgentDomainScheduler  = new MailAgentDomainScheduler();
        mailAgentDomainScheduler.setMailAgentDomain(mailAgentDomain);
        Spider multiblSpider = spiderFactory.getSpider(MULTIBL_KEY);
        multiblSpider.addUrl(AbstractMultiblConst.MULTIBL_REQUEST_URL.replace("{IP}",mailAgentDomain.getIpInfo()))
            .setScheduler(mailAgentDomainScheduler)
            .run();

        Spider taloSpider = spiderFactory.getSpider(TALOS_KEY);
        taloSpider
            .addUrl(AbstractTalosConst.TALOS_REQUEST_URL.replace("{IP}",mailAgentDomain.getIpInfo()))
            .setScheduler(mailAgentDomainScheduler)
            .run();
    }
}
