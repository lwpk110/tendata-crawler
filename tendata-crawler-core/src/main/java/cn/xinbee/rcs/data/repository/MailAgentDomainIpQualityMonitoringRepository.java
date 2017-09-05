package cn.xinbee.rcs.data.repository;

import cn.xinbee.rcs.data.domain.MailChannelAgentDomainQualityMonitoring;
import java.util.List;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * {@inheritDoc}
 *
 * Created by ernest on 2017/8/23.
 */
public interface MailAgentDomainIpQualityMonitoringRepository extends JpaRepository<MailChannelAgentDomainQualityMonitoring, Long> {

    List<MailChannelAgentDomainQualityMonitoring> findByCreatedDateAfterAndCreatedDateBeforeOrderByCreatedDate(DateTime start, DateTime end);
}
