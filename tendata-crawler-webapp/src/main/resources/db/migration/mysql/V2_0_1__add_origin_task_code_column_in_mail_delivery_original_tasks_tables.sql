ALTER TABLE `mail_delivery_tasks`
ADD COLUMN `original_task_code`  varchar(255) NOT NULL AFTER `version`,
ADD COLUMN `recipient_count`  int(11) NOT NULL DEFAULT '0';


CREATE TABLE `mail_delivery_task_recycle_bins` (
  `id` binary(16) NOT NULL,
  `name` varchar(100) NOT NULL,
  `delivery_status` int(11) NOT NULL,
  `sender_name` varchar(50) NOT NULL,
  `sender_email` varchar(255) DEFAULT NULL,
  `reply_name` varchar(50) DEFAULT NULL,
  `reply_email` varchar(255) DEFAULT NULL,
  `agent_send` bit(1) DEFAULT b'0',
  `scheduled` bit(1) DEFAULT NULL,
  `scheduled_date` datetime DEFAULT NULL,
  `send_date` datetime DEFAULT NULL,
  `finish_date` datetime DEFAULT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_modified_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `recycle_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `channel_id` int(11) NOT NULL,
  `passway_campaign_key` varchar(100) DEFAULT NULL,
  `task_code` varchar(255) NOT NULL,
  `channel_node_id` int(11) DEFAULT NULL,
  `mailing_id` int(11) DEFAULT NULL,
  `user_key` varchar(100) NOT NULL,
  `platform_code` varchar(100) DEFAULT NULL,
  `parent_id` binary(16) NOT NULL,
  `original_task_code` varchar(255) NOT NULL,
  `recipient_count`  int(11) NOT NULL DEFAULT '0',
  `version` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
)