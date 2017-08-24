package cn.xinbee.rcs.data.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by jeashi on 2016/6/23.
 */
@Entity
public class MailAgentDomain extends AbstractEntityAuditable<Integer> {

    private static final long serialVersionUID = 1L;

    private boolean disabled;
    private String mailAgent;
    private String domainInfo;
    private String ipInfo;
    private Integer useCount;
    private boolean deleted;
    private int channelId;


    @Id
    @GeneratedValue
    public Integer getId() {
        return super.getId();
    }

    @Column(name = "disabled")
    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
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

    @Column(name = "use_count")
    public Integer getUseCount() {
        return useCount;
    }

    public void setUseCount(Integer useCount) {
        this.useCount = useCount;
    }

    @Column(name = "deleted")
    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Column(name = "channel_id")
    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }
}
