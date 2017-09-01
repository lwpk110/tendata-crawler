package cn.xinbee.rcs.service;

import cn.xinbee.rcs.data.domain.MailAgentDomain;
import cn.xinbee.rcs.data.repository.MailAgentDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MailAgentDomainServiceImpl extends EntityServiceSupport<MailAgentDomain,Integer,MailAgentDomainRepository> implements MailAgentDomainService {

    @Autowired
    public MailAgentDomainServiceImpl(MailAgentDomainRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public MailAgentDomain getByChannelId(int channelId) {
        return getRepository().findByChannelId(channelId);
    }
}
