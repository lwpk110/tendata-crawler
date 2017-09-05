package cn.xinbee.rcs.api.channel.domain;

import cn.xinbee.rcs.data.domain.MailChannelCrawlerAgentDomain;
import java.util.List;

/**
 *
 * Created by ernest on 2017/9/4.
 */
public interface MailChannelManager {

    List<MailChannelCrawlerAgentDomain> getAllDomains();

    List<MailChannelCrawlerAgentDomain> getDomains(Integer channelId);
}
