CREATE TABLE IF NOT EXISTS `user` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `account_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '账号id',
  `nickname` varchar(30) NOT NULL DEFAULT '' COMMENT '昵称',
  `avatar` varchar(255) NOT NULL DEFAULT '' COMMENT '头像',
  `gender` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '性别 1:男性 2:女性',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (id),
  KEY `idx_account_id` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频用户表';

CREATE TABLE IF NOT EXISTS `video` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '视频ID',
  `title` varchar(255) NOT NULL COMMENT '视频标题',
  `description` text COMMENT '视频描述',
  `series_id` int(11) NOT NULL COMMENT '所属系列ID',
  `video_info_id` int(11) unsigned NOT NULL COMMENT '视频信息ID',
  `original_url` varchar(255) NOT NULL COMMENT '原始视频URL',
  `thumbnail_url` varchar(255) NOT NULL COMMENT '视频缩略图地址',
  `category_id` int(11) NOT NULL COMMENT '分类ID',
  `region_id` int(11) NOT NULL COMMENT '地区ID',
  `year_id` int(11) NOT NULL COMMENT '年份ID',
  `payment_type_id` int(11) NOT NULL COMMENT '付费类型ID',
  `uploader_id` int(11) unsigned DEFAULT NULL COMMENT '上传者ID',
  `publish_time` datetime NOT NULL COMMENT '发布时间',
  `is_live` TINYINT(1) UNSIGNED NOT NULL DEFAULT '0' COMMENT '是否直播：0否，1是',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频信息表';

CREATE TABLE IF NOT EXISTS `video_series` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '视频系列ID',
  `title` varchar(255) NOT NULL COMMENT '系列标题',
  `description` text COMMENT '系列描述',
  `thumbnail_url` varchar(255) NOT NULL COMMENT '系列缩略图地址',
  `cover_url` varchar(255) DEFAULT NULL COMMENT '系列封面图片URL',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频系列信息表';

CREATE TABLE IF NOT EXISTS `video_episodes` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '视频剧集ID',
  `series_id` int(11) NOT NULL COMMENT '所属系列ID',
  `video_id` int(11) NOT NULL COMMENT '视频ID',
  `title` varchar(255) NOT NULL COMMENT '剧集标题',
  `description` text COMMENT '剧集描述',
  `episode_title` varchar(255) NOT NULL COMMENT '剧集名称',
  `publish_time` datetime NOT NULL COMMENT '发布时间',
  `duration` int(11) NOT NULL DEFAULT '0' COMMENT '剧集时长',
  `thumbnail_url` varchar(255) NOT NULL COMMENT '缩略图URL',
  `video_info_id` int(11) unsigned NOT NULL COMMENT '视频信息ID',
  `original_url` varchar(255) NOT NULL COMMENT '原始视频URL',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频剧集信息表';

CREATE TABLE IF NOT EXISTS `video_categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `name` varchar(255) NOT NULL COMMENT '分类名称',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父分类ID',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频分类表';

CREATE TABLE IF NOT EXISTS `video_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标签ID',
  `name` varchar(255) NOT NULL COMMENT '标签名称',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频标签表';

CREATE TABLE IF NOT EXISTS `video_tag_relations` (
  `tag_id` int(11) NOT NULL COMMENT '标签ID',
  `video_id` int(11) NOT NULL COMMENT '视频ID',
  PRIMARY KEY (`tag_id`, `video_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频标签关系表';

CREATE TABLE IF NOT EXISTS `video_region` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '地区ID',
  `name` varchar(255) NOT NULL COMMENT '地区名称',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频地区表';

CREATE TABLE IF NOT EXISTS `video_year` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '年份ID',
  `year` int(4) NOT NULL COMMENT '年份',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频年份表';


CREATE TABLE IF NOT EXISTS `video_payment_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '付费类型ID',
  `name` varchar(255) NOT NULL COMMENT '付费类型名称',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频付费类型表';

CREATE TABLE IF NOT EXISTS `video_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `video_id` int(11) NOT NULL COMMENT '视频ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `content` text COMMENT '评论内容',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父评论ID',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频评论表';


CREATE TABLE IF NOT EXISTS `video_play_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '播放记录ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `video_id` int(11) NOT NULL COMMENT '视频ID',
  `play_time` int(11) NOT NULL COMMENT '播放时长（秒）',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `video_id` (`video_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频播放记录表';

CREATE TABLE IF NOT EXISTS `video_favorites` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '播放记录ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `video_id` int(11) NOT NULL COMMENT '视频ID',
  `collect_time` datetime NOT NULL COMMENT '收藏时间',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `video_id` (`video_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频收藏夹表';

CREATE TABLE IF NOT EXISTS `live_streams` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `name` varchar(255) NOT NULL COMMENT '直播流名称',
  `description` text NOT NULL COMMENT '直播流描述',
  `stream_url` varchar(255) NOT NULL COMMENT '直播流URL',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='直播流表';


-- ## 数据库自增 ID 生成唯一 ID https://hashids.org/
