package cn.xinbee.rcs.data.repository;

import cn.xinbee.rcs.data.domain.MailAgentDomain;
import cn.xinbee.rcs.data.domain.MailAgentDomainQualityMonitoring;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * {@inheritDoc}
 *
 * Created by ernest on 2017/8/23.
 */
public interface MailAgentDomainIpQualityMonitoringRepository extends JpaRepository<MailAgentDomainQualityMonitoring, Long> {

    List<MailAgentDomainQualityMonitoring> findByMailAgentDomainAndCreatedDateAfterAndCreatedDateBeforeOrderByCreatedDate(MailAgentDomain mailAgentDomain,
                                                                                                         DateTime start,
                                                                                                         DateTime end);
}
