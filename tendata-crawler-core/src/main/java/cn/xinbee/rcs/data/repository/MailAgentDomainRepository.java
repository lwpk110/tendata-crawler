package cn.xinbee.rcs.data.repository;

import cn.xinbee.rcs.data.domain.MailAgentDomain;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * {@inheritDoc}
 *
 * Created by ernest on 2017/8/24.
 */
public interface MailAgentDomainRepository extends JpaRepository<MailAgentDomain, Long> {

    List<MailAgentDomain> findAllByDisabledFalseAndDeletedFalseAndIpInfoNotNull();
}
