package cn.xinbee.crawler.data.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * Created by ernest on 2017/9/4.
 */
@Embeddable
public class MailChannelCrawlerChannel {
    private String name;
    private String description;
    private Integer channelCode;
    private String supplier;
    private Integer id;




    @Column(name = "channel_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "channel_description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "channel_code")
    public Integer getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(Integer channelCode) {
        this.channelCode = channelCode;
    }

    @Column(name = "supplier")
    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    @Column(name = "channel_id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
