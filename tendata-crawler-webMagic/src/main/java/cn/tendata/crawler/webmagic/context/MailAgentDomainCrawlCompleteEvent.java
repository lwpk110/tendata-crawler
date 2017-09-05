package cn.tendata.crawler.webmagic.context;

import org.springframework.context.ApplicationEvent;

/**
 * {@inheritDoc}
 *
 * Created by ernest on 2017/9/5.
 */
public class MailAgentDomainCrawlCompleteEvent extends ApplicationEvent {
    private final MailAgentDomainCrawlEventData mailAgentDomainCrawlEventData;
    public MailAgentDomainCrawlCompleteEvent(Object source,
        MailAgentDomainCrawlEventData mailAgentDomainCrawlEventData) {
        super(source);
        this.mailAgentDomainCrawlEventData = mailAgentDomainCrawlEventData;
    }

    public MailAgentDomainCrawlEventData getMailAgentDomainCrawlEventData() {
        return mailAgentDomainCrawlEventData;
    }
}
