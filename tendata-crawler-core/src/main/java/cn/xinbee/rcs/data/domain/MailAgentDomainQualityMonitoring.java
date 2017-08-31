package cn.xinbee.rcs.data.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by jeashi on 2016/6/23.
 */
@Entity
public class MailAgentDomainQualityMonitoring extends AbstractEntityAuditable<Long> {

    private static final long serialVersionUID = 1L;

    private MailAgentDomain mailAgentDomain;

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

    @ManyToOne
    @JoinColumn(name = "mail_agent_domain_id")
    public MailAgentDomain getMailAgentDomain() {
        return mailAgentDomain;
    }

    public void setMailAgentDomain(MailAgentDomain mailAgentDomain) {
        this.mailAgentDomain = mailAgentDomain;
    }

/*    @Column(name = "email_reputation")

    @Column(name = "last_day_spam_level")

    @Column(name = "blacklist_summary") }*/

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
}
