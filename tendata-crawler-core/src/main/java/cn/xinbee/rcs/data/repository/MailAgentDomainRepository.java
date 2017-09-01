package cn.xinbee.rcs.data.repository;

import cn.xinbee.rcs.data.domain.MailAgentDomain;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * {@inheritDoc}
 *
 * Created by ernest on 2017/8/24.
 */
public interface MailAgentDomainRepository extends CrudRepository<MailAgentDomain, Integer> {

    List<MailAgentDomain> findAllByDisabledFalseAndDeletedFalseAndIpInfoNotNull();

    MailAgentDomain findByChannelId(int channelId);
}
