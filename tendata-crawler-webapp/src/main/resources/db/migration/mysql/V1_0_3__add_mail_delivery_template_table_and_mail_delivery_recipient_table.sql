CREATE TABLE `mail_delivery_task_templates` (
  `id` binary(16) NOT NULL,
  `body` longtext NOT NULL,
  `is_html` bit(1) NOT NULL,
  `subject` varchar(255) NOT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_modified_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

CREATE TABLE `mail_delivery_task_recipients` (
  `id` binary(16) NOT NULL,
  `recipient_count` int(11) NOT NULL DEFAULT '0',
  `recipients` longtext NOT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_modified_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);