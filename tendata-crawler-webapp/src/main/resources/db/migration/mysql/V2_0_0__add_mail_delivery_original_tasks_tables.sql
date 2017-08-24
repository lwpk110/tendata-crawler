CREATE TABLE `mail_delivery_original_tasks` (
  `id` binary(16) NOT NULL,
  `name` varchar(100) NOT NULL,
  `delivery_status` int(11) NOT NULL,
  `sender_name` varchar(50) DEFAULT NULL,
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
  `channel_id` int(5),
  `original_task_code` varchar(255) NOT NULL,
  `user_key` varchar(100) NOT NULL,
  `platform_code` varchar(100) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `mail_delivery_original_task_templates` (
  `id` binary(16) NOT NULL,
  `body` longtext,
  `is_html` bit(1) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_modified_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

CREATE TABLE `mail_delivery_original_task_recipients` (
  `id` binary(16) NOT NULL,
  `recipient_count` int(11) NOT NULL DEFAULT '0',
  `recipients` longtext NOT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_modified_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);
