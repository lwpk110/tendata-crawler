CREATE TABLE `mail_channel_crawler_agent_domains` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_modified_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `domain_info` varchar(255) DEFAULT NULL,
  `ip_info` varchar(255) DEFAULT NULL,
  `mail_agent` varchar(255) DEFAULT NULL,
  `channel_code` int(11) DEFAULT NULL,
  `channel_description` varchar(255) DEFAULT NULL,
  `channel_id` int(11) DEFAULT NULL,
  `channel_name` varchar(255) DEFAULT NULL,
  `supplier` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `mail_channel_agent_domain_quality_monitorings` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_modified_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `domain_blacklist_summary` int(11) DEFAULT NULL,
  `domain_email_reputation` varchar(255) DEFAULT NULL,
  `domain_last_day_spam_level` varchar(255) DEFAULT NULL,
  `ip_blacklist_summary` int(11) DEFAULT NULL,
  `ip_email_reputation` varchar(255) DEFAULT NULL,
  `ip_last_day_spam_level` varchar(255) DEFAULT NULL,
  `mail_agent_domain_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd70f317htflw3185j768033ay` (`mail_agent_domain_id`),
  CONSTRAINT `FKd70f317htflw3185j768033ay` FOREIGN KEY (`mail_agent_domain_id`) REFERENCES `mail_channel_crawler_agent_domains` (`id`)
);




