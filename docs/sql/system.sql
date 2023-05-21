-- 系统管理
CREATE TABLE `system` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '系统名称',
  `description` varchar(255) NOT NULL DEFAULT '' COMMENT '系统描述',
  `domain` varchar(255) NOT NULL DEFAULT '' COMMENT '系统域名',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '状态 0:禁用 1:启用',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='后台管理系统表';

-- 字典管理
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '字典类型id',
  `system_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '系统ID',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '字典名称',
  `type` varchar(100) NOT NULL DEFAULT '' COMMENT '字典类型',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '状态 0:禁用 1:启用',
  `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_type` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典类型表';

DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '字典数据id',
  `dict_type_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '字典类型id',
  `label` varchar(100) NOT NULL DEFAULT '' COMMENT '字典标签',
  `value` varchar(100) NOT NULL DEFAULT '' COMMENT '字典键值',
  `sort` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `is_default` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否默认值 0:否 1:是',
  `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '状态 0:禁用 1:启用',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`),
  INDEX `idx_dict_type_id`(`dict_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci  COMMENT='字典数据表';
