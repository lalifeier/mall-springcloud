CREATE TABLE `user` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `username` varchar(30) NOT NULL DEFAULT '' COMMENT '用户名',
    `password` varchar(72) NOT NULL DEFAULT '' COMMENT '密码',
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新人',
    `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新人',
    PRIMARY KEY (`id`),
    unique `unq_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
