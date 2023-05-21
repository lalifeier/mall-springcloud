-- 用户管理
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `account_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '账号id',
  `name` varchar(30) NOT NULL DEFAULT '' COMMENT '姓名',
  `phone` varchar(15) NOT NULL DEFAULT '' COMMENT '手机号',
  `email` varchar(30) NOT NULL DEFAULT '' COMMENT '邮箱',
  `nickname` varchar(30) NOT NULL DEFAULT '' COMMENT '昵称',
  `avatar` varchar(255) NOT NULL DEFAULT '' COMMENT '头像'
  `gender` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '性别 1:男性 2:女性',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) NOT NULL DEFAULT '0' COMMENT '更新人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_account_id` (`account_id`),
  KEY `idx_phone` (`phone`),
  KEY `idx_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

CREATE TABLE IF NOT EXISTS `user_group` (
  `user_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
  `group_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '用户组ID',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`user_id`, `user_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户用户组关联表';

CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
  `role_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '角色ID',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`user_id`, `role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';

-- 用户组管理
CREATE TABLE IF NOT EXISTS `group` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '父级ID',
  `name` varchar(30) NOT NULL DEFAULT '' COMMENT '用户组名称',
  `sort` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '状态 0:禁用 1:启用',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`),
  INDEX idx_parent_id(`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户组表';

CREATE TABLE IF NOT EXISTS `group_role` (
  `group_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '用户组ID',
  `role_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '角色ID',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`group_id`, `role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户组角色关联表';

-- 角色管理
CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name` varchar(30) NOT NULL DEFAULT '' COMMENT '角色名称',
  `sort` tinyint(11) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '状态 0:禁用 1:启用',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';


-- 部门管理
CREATE TABLE IF NOT EXISTS `department` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '父级ID',
  `name` varchar(30) NOT NULL DEFAULT '' COMMENT '部门名称',
  `sort` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '状态 0:禁用 1:启用',
  `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) NOT NULL DEFAULT '0' COMMENT '更新人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门表';

CREATE TABLE IF NOT EXISTS `user_department` (
  `user_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
  `department_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '部门ID',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) NOT NULL DEFAULT '0' COMMENT '更新人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`user_id`, `department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户部门关联表';

-- 岗位管理
CREATE TABLE IF NOT EXISTS `position` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `dept_id` int(11) NOT NULL COMMENT '所属部门ID'
  `name` varchar(30) NOT NULL DEFAULT '' COMMENT '岗位名称',
  `sort` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '状态 0:禁用 1:启用',
  `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) NOT NULL DEFAULT '0' COMMENT '更新人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='岗位表';

CREATE TABLE IF NOT EXISTS `user_position` (
  `user_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
  `position_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '岗位ID',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) NOT NULL DEFAULT '0' COMMENT '更新人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`user_id`, `position_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户岗位关联表';



-- 菜单管理
CREATE TABLE `menu` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `system_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '系统ID',
  `parent_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '父菜单ID',
  `name` varchar(128) NOT NULL DEFAULT '' COMMENT '菜单名称',
  `component` varchar(255) NOT NULL DEFAULT '' COMMENT '组件路径',
  `url` varchar(30) NOT NULL DEFAULT '' COMMENT '菜单URL',
  `icon` varchar(255) NOT NULL DEFAULT '' COMMENT '菜单图标',
  `is_external` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否外链菜单 0:否 1:是',
  `is_cache` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否缓存 0:否 1:是',
  `is_affix` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否固定到标签栏 0:否 1:是',
  `is_breadcrumb` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否显示面包屑 0:否 1:是',
  `is_hidden` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否隐藏 0:否 1:是',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `description` varchar(255) NOT NULL DEFAULT '' COMMENT '描述',
  `sort` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '状态 0:禁用 1:启用',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';

-- 按钮管理
CREATE TABLE `button` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `menu_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '菜单ID',
  `name` varchar(128) NOT NULL DEFAULT '' COMMENT '按钮名称',
  `sort` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '状态 0:禁用 1:启用',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='按钮表';

-- 权限管理
CREATE TABLE IF NOT EXISTS `menu_permission` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `role_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '角色ID',
  `menu_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '菜单ID',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单权限表';

CREATE TABLE IF NOT EXISTS `button_permission` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `role_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '角色ID',
  `menu_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '菜单ID',
  `button_id` int(11) unsigned DEFAULT NULL COMMENT '按钮ID',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='按钮权限表';

-- 接口管理
CREATE TABLE `api` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `group` varchar(32) NOT NULL DEFAULT '' COMMENT '接口分组',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '接口名称',
  `path` varchar(255) NOT NULL DEFAULT '' COMMENT '接口路径',
  `method` varchar(16) NOT NULL DEFAULT '' COMMENT '接口请求方式',
  `description` varchar(255) NOT NULL DEFAULT '' COMMENT '接口描述',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '状态 0:禁用 1:启用',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci  COMMENT='接口表';

CREATE TABLE IF NOT EXISTS `api_permission` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `role_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '角色ID',
  `api_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '接口ID',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='接口权限表';





DROP TABLE IF EXISTS `casbin_rules`;
CREATE TABLE `casbin_rules` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ptype` varchar(255)  NOT NULL DEFAULT '',
  `v0` varchar(255)  NOT NULL DEFAULT '',
  `v1` varchar(255)  NOT NULL DEFAULT '',
  `v2` varchar(255)  NOT NULL DEFAULT '',
  `v3` varchar(255)  NOT NULL DEFAULT '',
  `v4` varchar(255)  NOT NULL DEFAULT '',
  `v5` varchar(255)  NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unq_casbin_rule` (`ptype`,`v0`,`v1`,`v2`,`v3`,`v4`,`v5`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
