CREATE TABLE IF NOT EXISTS users (
  username VARCHAR(50)  NOT NULL,
  password VARCHAR(100) NOT NULL,
  enabled  INT      NOT NULL DEFAULT 1,
  PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS authorities (
  username  VARCHAR(50) NOT NULL,
  authority VARCHAR(50) NOT NULL,
  FOREIGN KEY (username) REFERENCES users (username)
);


-- 用户管理
CREATE TABLE IF NOT EXISTS `userPrincipal`
(
  `id`         int(11) unsigned    NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `username`   varchar(30)         NOT NULL DEFAULT '' COMMENT '姓名',
  `password`   varchar(60)         NOT NULL DEFAULT '' COMMENT '密码',
  `phone`      varchar(15)         NOT NULL DEFAULT '' COMMENT '手机号',
  `email`      varchar(30)         NOT NULL DEFAULT '' COMMENT '邮箱',
  `nickname`   varchar(30)         NOT NULL DEFAULT '' COMMENT '昵称',
  `avatar`     varchar(255)        NOT NULL DEFAULT '' COMMENT '头像',
  `gender`     tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '性别 1:男性 2:女性',
  `created_at` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11)             NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11)             NOT NULL DEFAULT '0' COMMENT '更新人',
  `is_deleted` tinyint(1)          NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_username` (`username`),
  KEY `idx_phone` (`phone`),
  KEY `idx_email` (`email`)
  ) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';

CREATE TABLE IF NOT EXISTS `user_group`
(
  `user_id`    int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '用户ID',
  `group_id`   int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '用户组ID',
  `created_at` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime                     DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`user_id`, `group_id`)
  ) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户用户组关联表';

CREATE TABLE IF NOT EXISTS `user_role`
(
  `user_id`    int(11) unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
  `role_id`    int(11) unsigned NOT NULL DEFAULT '0' COMMENT '角色ID',
  `created_at` datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime                  DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`user_id`, `role_id`)
  ) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户角色关联表';

-- 用户组管理
CREATE TABLE IF NOT EXISTS `group`
(
  `id`         int(11) unsigned    NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent_id`  int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '父级ID',
  `name`       varchar(30)         NOT NULL DEFAULT '' COMMENT '用户组名称',
  `sort`       tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `status`     tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '状态 0:禁用 1:启用',
  `remark`     varchar(255)        NOT NULL DEFAULT '' COMMENT '备注',
  `created_at` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '修改人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`),
  INDEX idx_parent_id (`parent_id`)
  ) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户组表';

CREATE TABLE IF NOT EXISTS `group_role`
(
  `group_id`   int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '用户组ID',
  `role_id`    int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '角色ID',
  `created_at` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '修改人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`group_id`, `role_id`)
  ) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户组角色关联表';

-- 角色管理
CREATE TABLE IF NOT EXISTS `role`
(
  `id`         int(11) unsigned     NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name`       varchar(30)          NOT NULL DEFAULT '' COMMENT '角色名称',
  `sort`       tinyint(11) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `status`     tinyint(1) unsigned  NOT NULL DEFAULT '0' COMMENT '状态 0:禁用 1:启用',
  `remark`     varchar(255)         NOT NULL DEFAULT '' COMMENT '备注',
  `created_at` datetime             NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned     NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime             NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned     NOT NULL DEFAULT '0' COMMENT '修改人',
  `is_deleted` tinyint(1) unsigned  NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`)
  ) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='角色表';


-- 部门管理
CREATE TABLE IF NOT EXISTS `department`
(
  `id`         int(11) unsigned    NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent_id`  int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '父级ID',
  `name`       varchar(30)         NOT NULL DEFAULT '' COMMENT '部门名称',
  `sort`       tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `status`     tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '状态 0:禁用 1:启用',
  `remark`     varchar(500)        NOT NULL DEFAULT '' COMMENT '备注',
  `created_at` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11)             NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11)             NOT NULL DEFAULT '0' COMMENT '更新人',
  `is_deleted` tinyint(1)          NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
  ) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='部门表';

CREATE TABLE IF NOT EXISTS `user_department`
(
  `user_id`       int(11) unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
  `department_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '部门ID',
  `created_at`    datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by`    int(11)          NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at`    datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by`    int(11)          NOT NULL DEFAULT '0' COMMENT '更新人',
  `is_deleted`    tinyint(1)       NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`user_id`, `department_id`)
  ) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户部门关联表';

-- 岗位管理
CREATE TABLE IF NOT EXISTS `position`
(
  `id`         int(11) unsigned    NOT NULL AUTO_INCREMENT COMMENT 'id',
  `dept_id`    int(11)             NOT NULL COMMENT '所属部门ID',
  `name`       varchar(30)         NOT NULL DEFAULT '' COMMENT '岗位名称',
  `sort`       tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `status`     tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '状态 0:禁用 1:启用',
  `remark`     varchar(500)        NOT NULL DEFAULT '' COMMENT '备注',
  `created_at` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11)             NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11)             NOT NULL DEFAULT '0' COMMENT '更新人',
  `is_deleted` tinyint(1)          NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
  ) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='岗位表';

CREATE TABLE IF NOT EXISTS `user_position`
(
  `user_id`     int(11) unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
  `position_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '岗位ID',
  `created_at`  datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by`  int(11)          NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at`  datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by`  int(11)          NOT NULL DEFAULT '0' COMMENT '更新人',
  `is_deleted`  tinyint(1)       NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`user_id`, `position_id`)
  ) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户岗位关联表';


-- 菜单管理
CREATE TABLE IF NOT EXISTS `menu`
(
  `id`            int(11) unsigned    NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `system_id`     int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '系统ID',
  `parent_id`     int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '父菜单ID',
  `name`          varchar(128)        NOT NULL DEFAULT '' COMMENT '菜单名称',
  `component`     varchar(255)        NOT NULL DEFAULT '' COMMENT '组件路径',
  `url`           varchar(30)         NOT NULL DEFAULT '' COMMENT '菜单URL',
  `icon`          varchar(255)        NOT NULL DEFAULT '' COMMENT '菜单图标',
  `is_external`   tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否外链菜单 0:否 1:是',
  `is_cache`      tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否缓存 0:否 1:是',
  `is_affix`      tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否固定到标签栏 0:否 1:是',
  `is_breadcrumb` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否显示面包屑 0:否 1:是',
  `is_hidden`     tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否隐藏 0:否 1:是',
  `remark`        varchar(255)        NOT NULL DEFAULT '' COMMENT '备注',
  `description`   varchar(255)        NOT NULL DEFAULT '' COMMENT '描述',
  `sort`          tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `status`        tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '状态 0:禁用 1:启用',
  `created_at`    datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by`    int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at`    datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by`    int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '修改人',
  `is_deleted`    tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`)
  ) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='菜单表';

-- 按钮管理
CREATE TABLE IF NOT EXISTS `button`
(
  `id`         int(11) unsigned    NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `menu_id`    int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '菜单ID',
  `name`       varchar(128)        NOT NULL DEFAULT '' COMMENT '按钮名称',
  `sort`       tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `status`     tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '状态 0:禁用 1:启用',
  `created_at` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '修改人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`)
  ) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='按钮表';

-- 权限管理
CREATE TABLE IF NOT EXISTS `menu_permission`
(
  `id`         int unsigned        NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `role_id`    int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '角色ID',
  `menu_id`    int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '菜单ID',
  `created_at` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime                     DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`)
  ) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='菜单权限表';

CREATE TABLE IF NOT EXISTS `button_permission`
(
  `id`         int unsigned        NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `role_id`    int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '角色ID',
  `menu_id`    int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '菜单ID',
  `button_id`  int(11) unsigned             DEFAULT NULL COMMENT '按钮ID',
  `created_at` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '修改人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`)
  ) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='按钮权限表';

-- 接口管理
CREATE TABLE IF NOT EXISTS `api`
(
  `id`          int(11) unsigned    NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `group`       varchar(32)         NOT NULL DEFAULT '' COMMENT '接口分组',
  `name`        varchar(32)         NOT NULL DEFAULT '' COMMENT '接口名称',
  `path`        varchar(255)        NOT NULL DEFAULT '' COMMENT '接口路径',
  `method`      varchar(16)         NOT NULL DEFAULT '' COMMENT '接口请求方式',
  `description` varchar(255)        NOT NULL DEFAULT '' COMMENT '接口描述',
  `status`      tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '状态 0:禁用 1:启用',
  `created_at`  datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by`  int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at`  datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by`  int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '修改人',
  `is_deleted`  tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`)
  ) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='接口表';

CREATE TABLE IF NOT EXISTS `api_permission`
(
  `id`         int unsigned        NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `role_id`    int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '角色ID',
  `api_id`     int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '接口ID',
  `created_at` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '修改人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`)
  ) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='接口权限表';

CREATE TABLE IF NOT EXISTS oauth2_registered_client
(
  id                            varchar(100)  NOT NULL,
  client_id                     varchar(100)  NOT NULL,
  client_id_issued_at           timestamp          DEFAULT CURRENT_TIMESTAMP NOT NULL,
  client_secret                 varchar(200)       DEFAULT NULL,
  client_secret_expires_at      timestamp     NULL DEFAULT NULL,
  client_name                   varchar(200)  NOT NULL,
  client_authentication_methods varchar(1000) NOT NULL,
  authorization_grant_types     varchar(1000) NOT NULL,
  redirect_uris                 varchar(1000)      DEFAULT NULL,
  scopes                        varchar(1000) NOT NULL,
  client_settings               varchar(2000) NOT NULL,
  token_settings                varchar(2000) NOT NULL,
  PRIMARY KEY (id)
  ) COMMENT ='OAuth2注册客户端表';

CREATE TABLE IF NOT EXISTS oauth2_authorization_consent
(
  registered_client_id varchar(100)  NOT NULL,
  principal_name       varchar(200)  NOT NULL,
  authorities          varchar(1000) NOT NULL,
  PRIMARY KEY (registered_client_id, principal_name)
  ) COMMENT ='OAuth2授权同意表';

CREATE TABLE IF NOT EXISTS oauth2_authorization
(
  id                            varchar(100) NOT NULL,
  registered_client_id          varchar(100) NOT NULL,
  principal_name                varchar(200) NOT NULL,
  authorization_grant_type      varchar(100) NOT NULL,
  authorized_scopes             varchar(1000)     DEFAULT NULL,
  attributes                    blob              DEFAULT NULL,
  state                         varchar(500)      DEFAULT NULL,
  authorization_code_value      blob              DEFAULT NULL,
  authorization_code_issued_at  timestamp    NULL DEFAULT NULL,
  authorization_code_expires_at timestamp    NULL DEFAULT NULL,
  authorization_code_metadata   blob              DEFAULT NULL,
  access_token_value            blob              DEFAULT NULL,
  access_token_issued_at        timestamp    NULL DEFAULT NULL,
  access_token_expires_at       timestamp    NULL DEFAULT NULL,
  access_token_metadata         blob              DEFAULT NULL,
  access_token_type             varchar(100)      DEFAULT NULL,
  access_token_scopes           varchar(1000)     DEFAULT NULL,
  oidc_id_token_value           blob              DEFAULT NULL,
  oidc_id_token_issued_at       timestamp    NULL DEFAULT NULL,
  oidc_id_token_expires_at      timestamp    NULL DEFAULT NULL,
  oidc_id_token_metadata        blob              DEFAULT NULL,
  refresh_token_value           blob              DEFAULT NULL,
  refresh_token_issued_at       timestamp    NULL DEFAULT NULL,
  refresh_token_expires_at      timestamp    NULL DEFAULT NULL,
  refresh_token_metadata        blob              DEFAULT NULL,
  PRIMARY KEY (id)
  ) COMMENT ='OAuth2授权表';
