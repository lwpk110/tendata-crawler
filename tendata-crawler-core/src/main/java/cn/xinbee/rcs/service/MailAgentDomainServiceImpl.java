package cn.xinbee.rcs.service;

import cn.xinbee.rcs.data.domain.MailChannelCrawlerAgentDomain;
import cn.xinbee.rcs.data.repository.MailChannelCrawlerAgentDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailAgentDomainServiceImpl
    extends EntityServiceSupport<MailChannelCrawlerAgentDomain, Long, MailChannelCrawlerAgentDomainRepository>
    implements MailAgentDomainService {

    @Autowired
    public MailAgentDomainServiceImpl(MailChannelCrawlerAgentDomainRepository repository) {
        super(repository);
    }




}
