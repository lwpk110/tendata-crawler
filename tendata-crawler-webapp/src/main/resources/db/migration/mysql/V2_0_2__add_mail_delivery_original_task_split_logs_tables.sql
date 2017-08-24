CREATE TABLE `mail_delivery_original_task_split_logs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `original_task_id` varchar(36),
  `retry_no` int(4) NOT NULL,
  `succeeded` bit(1) DEFAULT b'0',
  `version` bigint(20) NOT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_modified_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);