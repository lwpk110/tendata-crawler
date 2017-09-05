package cn.xinbee.rcs.data.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by jeashi on 2016/6/23.
 */
@Entity
public class MailChannelCrawlerAgentDomain extends AbstractEntityAuditable<Long> {

    private static final long serialVersionUID = 1L;

    private String mailAgent;
    private String domainInfo;
    private String ipInfo;

    private MailChannelCrawlerChannel mailDeliveryChannel;

    private MailChannelAgentDomainQualityMonitoring mailChannelAgentDomainQualityMonitoring;

    @Override
    @Id
    @GeneratedValue
    public Long getId() {
        return super.getId();
    }

    @Column(name = "mail_agent")
    public String getMailAgent() {
        return mailAgent;
    }

    public void setMailAgent(String mailAgent) {
        this.mailAgent = mailAgent;
    }

    @Column(name = "domain_info")
    public String getDomainInfo() {
        return domainInfo;
    }

    public void setDomainInfo(String domainInfo) {
        this.domainInfo = domainInfo;
    }

    @Column(name = "ip_info")
    public String getIpInfo() {
        return ipInfo;
    }

    public void setIpInfo(String ipInfo) {
        this.ipInfo = ipInfo;
    }

    @NotNull
    @Embedded
    public MailChannelCrawlerChannel getMailDeliveryChannel() {
        return mailDeliveryChannel;
    }

    public void setMailDeliveryChannel(
        MailChannelCrawlerChannel mailDeliveryChannel) {
        this.mailDeliveryChannel = mailDeliveryChannel;
    }

    @OneToOne(mappedBy="mailAgentDomain")
    public MailChannelAgentDomainQualityMonitoring getMailChannelAgentDomainQualityMonitoring() {
        return mailChannelAgentDomainQualityMonitoring;
    }

    public void setMailChannelAgentDomainQualityMonitoring(
        MailChannelAgentDomainQualityMonitoring mailChannelAgentDomainQualityMonitoring) {
        this.mailChannelAgentDomainQualityMonitoring = mailChannelAgentDomainQualityMonitoring;
    }
}
