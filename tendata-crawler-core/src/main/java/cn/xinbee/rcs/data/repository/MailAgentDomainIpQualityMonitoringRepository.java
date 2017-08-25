package cn.xinbee.rcs.data.repository;

import cn.xinbee.rcs.data.domain.MailAgentDomainQualityMonitoring;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * {@inheritDoc}
 *
 * Created by ernest on 2017/8/23.
 */
public interface MailAgentDomainIpQualityMonitoringRepository extends JpaRepository<MailAgentDomainQualityMonitoring, Long> {
}
