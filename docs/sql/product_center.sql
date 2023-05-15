-- 品牌管理
CREATE TABLE `product_brand` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '品牌ID',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '品牌中文名称',
  `english_name` varchar(255) DEFAULT NULL COMMENT '品牌英文名称',
  `alternate_name` varchar(255) DEFAULT NULL COMMENT '品牌别名',
  `logo_url` varchar(255) DEFAULT NULL COMMENT '品牌图标URL',
  `description` varchar(255) NOT NULL DEFAULT '' COMMENT '品牌描述',
  `sort` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '品牌状态：0 - 无效，1 - 有效',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unq_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='品牌表';

CREATE TABLE `product_brand_backend_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '品牌和后端分类映射ID',
  `backend_category_id` int(11) unsigned NOT NULL COMMENT '后端分类ID',
  `brand_id` int(11) unsigned NOT NULL COMMENT '品牌ID',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`),
  KEY `idx_backend_category_id` (`backend_category_id`),
  KEY `idx_brand_id` (`brand_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='品牌和后端分类映射表';


-- 类目管理
CREATE TABLE `product_frontend_category` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类ID',
    `parent_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '父分类ID',
    `name` varchar(255)  NOT NULL DEFAULT '' COMMENT '分类名称',
    `description` varchar(255)  NOT NULL DEFAULT '' COMMENT '分类描述',
    `logo_url` varchar(255)  NOT NULL DEFAULT '' COMMENT '类目logo',
    `path` varchar(255)  NOT NULL DEFAULT '' COMMENT '分类路径 /{pid}/{child_id}',
    `level` tinyint(4) NOT NULL DEFAULT '1' COMMENT '类目层级',
    `sort` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
    `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '类目状态：0 - 无效，1 - 有效',
    `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
    `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
    `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
    PRIMARY KEY (`id`),
    INDEX `idx_parent_id` (`parent_id`),
    KEY `idx_path` (`path`),
    KEY `idx_sort` (`sort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='前端分类表';

CREATE TABLE `product_backend_category` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类ID',
    `parent_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '父分类ID',
    `name` varchar(255)  NOT NULL DEFAULT '' COMMENT '分类名称',
    `description` varchar(255)  NOT NULL DEFAULT '' COMMENT '分类描述',
    `path` varchar(255)  NOT NULL DEFAULT '' COMMENT '分类路径 /{pid}/{child_id}',
    `level` tinyint(4) NOT NULL DEFAULT '1' COMMENT '类目层级',
    `sort` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
    `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '类目状态：0 - 无效，1 - 有效',
    `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
    `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
    `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
    PRIMARY KEY (`id`),
    INDEX `idx_parent_id` ( `parent_id`),
    KEY `idx_path` (`path`),
    KEY `idx_sort` (`sort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='后端分类表';

CREATE TABLE `product_frontend_backend_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '前后端分类关联ID',
  `frontend_category_id` int(11) unsigned NOT NULL COMMENT '前端分类ID',
  `backend_category_id` int(11) unsigned NOT NULL COMMENT '后端分类ID',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`),
  KEY `idx_frontend_category_id` (`frontend_category_id`),
  KEY `idx_backend_category_id` (`backend_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='前后端分类关联表';


-- 属性管理
CREATE TABLE product_attributes (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '属性ID',
  `attribute_name` VARCHAR(255) NOT NULL COMMENT '属性名称',
  `attribute_value_type` ENUM('单选', '多选', '自定义') NOT NULL COMMENT '属性值类型',
  `is_specification` tinyint(1) unsigned NOT NULL DEFAULT 0 COMMENT '是否规格属性',
  `attribute_group_id` INT unsigned NOT NULL COMMENT '属性组ID',
  `description` varchar(255)  NOT NULL DEFAULT '' COMMENT '属性描述',
  `is_required` TINYINT NOT NULL DEFAULT 0 COMMENT '是否必填：0-非必填，1-必填',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '属性状态：0 - 无效，1 - 有效',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`),
  INDEX `idx_attribute_name` (`attribute_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='属性表';

CREATE TABLE product_attribute_values (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '属性值ID',
  `attribute_id` INT unsigned NOT NULL COMMENT '属性ID',
  `value` VARCHAR(255) NOT NULL COMMENT '属性值',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`),
  INDEX attribute_id_index (`attribute_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='属性值表';

CREATE TABLE product_attribute_groups (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '属性组ID',
  `group_name` VARCHAR(50) NOT NULL COMMENT '属性组名称',
  `description` varchar(255)  NOT NULL DEFAULT '' COMMENT '属性组描述',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`),
  INDEX `idx_group_name` (`group_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='属性组表';

CREATE TABLE `product_backend_category_attributes` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '后端分和属性类映射ID',
  `backend_category_id` int(11) unsigned NOT NULL COMMENT '后端分类ID',
  `attribute_id` int(11) unsigned NOT NULL COMMENT '属性ID',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`),
  KEY `idx_backend_category_id` (`backend_category_id`),
  KEY `idx_attribute_id` (`attribute_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='后端分类属性映射表';
