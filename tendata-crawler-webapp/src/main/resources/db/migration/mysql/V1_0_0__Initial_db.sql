CREATE TABLE `mail_agent_domains` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `channel_id` int(11) NOT NULL,
  `disabled` bit(1) DEFAULT b'0',
  `domain_info` varchar(255) DEFAULT NULL,
  `mail_agent` varchar(255) DEFAULT NULL,
  `use_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `passway_campaigns` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `deleted` bit(1) DEFAULT b'0',
  `disabled` bit(1) DEFAULT b'0',
  `passway_campaign_key` varchar(100) NOT NULL,
  `total_use` int(11) DEFAULT NULL,
  `use_date` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `balance` int(11) NOT NULL,
  `last_login_at` datetime DEFAULT NULL,
  `login_count` int(11) NOT NULL,
  `total_cost` bigint(20) NOT NULL,
  `total_deposit` bigint(20) NOT NULL,
  `username` varchar(255) NOT NULL,
  `version` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
);

CREATE TABLE `user_transaction_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `recipient_count` int(11) NOT NULL,
  `transaction_type` varchar(50) NOT NULL,
  `user_id` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `mail_delivery_channels` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `disabled` bit(1) DEFAULT NULL,
  `fee` int(11) NOT NULL,
  `max_num_limit` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sequence` int(11) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `channel_code` int(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_channel_code` (`channel_code`) USING BTREE
) ;

CREATE TABLE `mail_delivery_channel_nodes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `config_props` varchar(2000) NOT NULL,
  `disabled` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `need_campaigns` bit(1) DEFAULT NULL,
  `server_key` varchar(255) DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL,
  `channel_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cwybnrdy3xqk77uwu97jecmj5` (`channel_id`),
  CONSTRAINT `FK_cwybnrdy3xqk77uwu97jecmj5` FOREIGN KEY (`channel_id`) REFERENCES `mail_delivery_channels` (`id`)
) ;