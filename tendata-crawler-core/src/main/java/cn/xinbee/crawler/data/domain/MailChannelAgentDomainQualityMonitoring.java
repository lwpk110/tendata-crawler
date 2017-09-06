package cn.xinbee.crawler.data.domain;

import cn.xinbee.crawler.statistics.util.StatisticsUtils;
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
    private Integer ipBlackListSummary;

    private String domainEmailReputation;
    private String domainLastDaySpamLevel;
    private Integer domainBlackListSummary;

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
    public Integer getIpBlackListSummary() {
        return ipBlackListSummary;
    }

    public void setIpBlackListSummary(Integer ipBlackListSummary) {
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
    public Integer getDomainBlackListSummary() {
        return domainBlackListSummary;
    }

    public void setDomainBlackListSummary(Integer domainBlackListSummary) {
        this.domainBlackListSummary = domainBlackListSummary;
    }

    public int ipReputationLevel() {
        if (!StringUtils.hasText(ipEmailReputation)) {
            return 0;
        }
        return StatisticsUtils.getReputationVal(ipEmailReputation);
    }

    public int ipSpamLevel() {
        if (!StringUtils.hasText(ipLastDaySpamLevel)) {
            return 0;
        }
        return StatisticsUtils.getSpamLevelVal(ipLastDaySpamLevel);
    }
}
