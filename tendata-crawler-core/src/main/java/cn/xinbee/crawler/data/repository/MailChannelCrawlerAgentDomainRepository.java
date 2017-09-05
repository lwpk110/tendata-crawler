package cn.xinbee.crawler.data.repository;

import cn.xinbee.crawler.data.domain.MailChannelCrawlerAgentDomain;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * {@inheritDoc}
 *
 * Created by ernest on 2017/9/5.
 */
public interface MailChannelCrawlerAgentDomainRepository extends JpaRepository<MailChannelCrawlerAgentDomain,Long> {
}
