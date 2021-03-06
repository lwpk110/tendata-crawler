package cn.tendata.crawler.webmagic.core;

import cn.tendata.crawler.webmagic.Item.processror.multirbl.MultiblCrawlerPipeline;
import cn.tendata.crawler.webmagic.Item.processror.talos.TalosCrawlerPipeline;
import cn.xinbee.rcs.data.domain.MailAgentDomain;

/**
 * {@inheritDoc}
 *
 * Created by ernest on 2017/8/24.
 */
public abstract class AbstractDomainIpQualityCrawler {
    public static String MULTIBL_KEY = "MULTI_BL";
    public static String TALOS_KEY = "TALOS";

    public static String BLACKLIST_SUMMARY= "blacklist_summary";
    public static String EMAIL_REPUTATION= "email_reputation";
    public static String LAST_DAY_SPAM_LEVEL= "last_day_spam_level";

    abstract MultiblCrawlerPipeline runMultibl(MailAgentDomain mailAgentDomain);

    abstract TalosCrawlerPipeline runTalos(MailAgentDomain mailAgentDomain);

}
