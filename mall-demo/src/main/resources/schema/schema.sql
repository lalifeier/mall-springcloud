CREATE TABLE IF NOT EXISTS `t_book` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `name` varchar(255)  NOT NULL DEFAULT '' COMMENT '书名',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `is_deleted` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`),
  KEY `idx_name` (`name`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='图书表';
