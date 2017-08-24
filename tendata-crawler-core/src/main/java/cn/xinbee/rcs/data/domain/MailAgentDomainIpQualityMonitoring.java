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
public class MailAgentDomainIpQualityMonitoring extends AbstractEntityAuditable<Long> {

    private static final long serialVersionUID = 1L;

    private MailAgentDomain mailAgentDomain;

    private String emailReputation;
    private String lastDaySpamLevel;
    private int blackListSummary;


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

    @Column(name = "email_reputation")
    public String getEmailReputation() {
        return emailReputation;
    }

    public void setEmailReputation(String emailReputation) {
        this.emailReputation = emailReputation;
    }

    @Column(name = "last_day_spam_level")
    public String getLastDaySpamLevel() {
        return lastDaySpamLevel;
    }

    public void setLastDaySpamLevel(String lastDaySpamLevel) {
        this.lastDaySpamLevel = lastDaySpamLevel;
    }

    @Column(name = "blacklist_summary")
    public int getBlackListSummary() {
        return blackListSummary;
    }

    public void setBlackListSummary(int blackListSummary) {
        this.blackListSummary = blackListSummary;
    }
}
