package cn.xinbee.rcs.data.domain;

import org.springframework.util.StringUtils;

import javax.persistence.*;

/**
 * Created by jeashi on 2016/6/23.
 */
@Entity
public class MailChannelAgentDomainQualityMonitoring extends AbstractEntityAuditable<Long> {

    private static final long serialVersionUID = 1L;

    private MailChannelCrawlerAgentDomain mailAgentDomain;

    private String ipEmailReputation;
    private String ipLastDaySpamLevel;
    private int ipBlackListSummary;

    private String domainEmailReputation;
    private String domainLastDaySpamLevel;
    private int domainBlackListSummary;

    @Id
    @GeneratedValue
    @Override
    public Long getId() {
        return super.getId();
    }

    @OneToOne
    @JoinColumn(name = "mail_agent_domain_id")
    public MailChannelCrawlerAgentDomain getMailAgentDomain() {
        return mailAgentDomain;
    }

    public void setMailAgentDomain(MailChannelCrawlerAgentDomain mailAgentDomain) {
        this.mailAgentDomain = mailAgentDomain;
    }

    @Column(name = "ip_email_reputation")
    public String getIpEmailReputation() {
        return ipEmailReputation;
    }

    public void setIpEmailReputation(String ipEmailReputation) {
        this.ipEmailReputation = ipEmailReputation;
    }

    @Column(name = "ip_last_day_spam_level")
    public String getIpLastDaySpamLevel() {
        return ipLastDaySpamLevel;
    }

    public void setIpLastDaySpamLevel(String ipLastDaySpamLevel) {
        this.ipLastDaySpamLevel = ipLastDaySpamLevel;
    }

    @Column(name = "ip_blacklist_summary")
    public int getIpBlackListSummary() {
        return ipBlackListSummary;
    }

    public void setIpBlackListSummary(int ipBlackListSummary) {
        this.ipBlackListSummary = ipBlackListSummary;
    }

    @Column(name = "domain_email_reputation")
    public String getDomainEmailReputation() {
        return domainEmailReputation;
    }

    public void setDomainEmailReputation(String domainEmailReputation) {
        this.domainEmailReputation = domainEmailReputation;
    }

    @Column(name = "domain_last_day_spam_level")
    public String getDomainLastDaySpamLevel() {
        return domainLastDaySpamLevel;
    }

    public void setDomainLastDaySpamLevel(String domainLastDaySpamLevel) {
        this.domainLastDaySpamLevel = domainLastDaySpamLevel;
    }

    @Column(name = "domain_blacklist_summary")
    public int getDomainBlackListSummary() {
        return domainBlackListSummary;
    }

    public void setDomainBlackListSummary(int domainBlackListSummary) {
        this.domainBlackListSummary = domainBlackListSummary;
    }

    public int ipReputationLevel() {
        if (!StringUtils.hasText(ipEmailReputation)) {
            return 0;
        }
        int reputationLevel = 0;
        switch (ipEmailReputation) {
            case "Good":
                reputationLevel = 1;
                break;
            case "Neutral":
                reputationLevel = 2;
                break;
            case "Poor":
                reputationLevel = 3;
                break;
        }
        return reputationLevel;
    }

    public int ipSpamLevel() {
        if (!StringUtils.hasText(ipLastDaySpamLevel)) {
            return 0;
        }
        int spamLevel = 0;
        switch (ipLastDaySpamLevel) {
            case "None":
                spamLevel = 1;
                break;
            case "Medium":
                spamLevel = 2;
                break;
            case "High":
                spamLevel = 3;
                break;
            case "Very High":
                spamLevel = 4;
                break;
        }
        return spamLevel;
    }
}
