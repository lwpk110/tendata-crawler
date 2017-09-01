package cn.xinbee.rcs.service;

import cn.xinbee.rcs.data.domain.MailAgentDomain;

public interface MailAgentDomainService extends EntityService<MailAgentDomain,Integer> {

    MailAgentDomain getByChannelId(int channelId);
}
