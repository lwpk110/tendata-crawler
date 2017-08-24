package cn.tendata.crawler.webmagic.core;

import cn.xinbee.rcs.data.domain.MailAgentDomain;
import us.codecraft.webmagic.scheduler.QueueScheduler;

/**
 * {@inheritDoc}
 *
 * Created by ernest on 2017/8/24.
 */
public class MailAgentDomainScheduler extends QueueScheduler {

    private MailAgentDomain mailAgentDomain;

    public MailAgentDomain getMailAgentDomain() {
        return mailAgentDomain;
    }

    public void setMailAgentDomain(MailAgentDomain mailAgentDomain) {
        this.mailAgentDomain = mailAgentDomain;
    }
}
