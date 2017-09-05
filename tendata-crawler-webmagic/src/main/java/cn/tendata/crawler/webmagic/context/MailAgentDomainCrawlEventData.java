package cn.tendata.crawler.webmagic.context;

import cn.xinbee.crawler.data.domain.MailChannelCrawlerAgentDomain;

/**
 * {@inheritDoc}
 *
 * Created by ernest on 2017/9/5.
 */
public class MailAgentDomainCrawlEventData {
    private Integer blackListSummary;
    private String spamLevel;
    private String emailReputation;

    private MailChannelCrawlerAgentDomain mailChannelCrawlerAgentDomain;

    public Integer getBlackListSummary() {
        return blackListSummary;
    }

    public void setBlackListSummary(Integer blackListSummary) {
        this.blackListSummary = blackListSummary;
    }

    public String getSpamLevel() {
        return spamLevel;
    }

    public void setSpamLevel(String spamLevel) {
        this.spamLevel = spamLevel;
    }

    public String getEmailReputation() {
        return emailReputation;
    }

    public void setEmailReputation(String emailReputation) {
        this.emailReputation = emailReputation;
    }

    public MailChannelCrawlerAgentDomain getMailChannelCrawlerAgentDomain() {
        return mailChannelCrawlerAgentDomain;
    }

    public void setMailChannelCrawlerAgentDomain(
        MailChannelCrawlerAgentDomain mailChannelCrawlerAgentDomain) {
        this.mailChannelCrawlerAgentDomain = mailChannelCrawlerAgentDomain;
    }
}
