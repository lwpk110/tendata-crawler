CREATE TABLE `task_process_logs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_modified_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `retry_count` int(11) DEFAULT NULL,
  `task_id` varchar(255) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `total_count` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
)