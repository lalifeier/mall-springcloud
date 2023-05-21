CREATE TABLE IF NOT EXISTS `video_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `episode_id` int(11) unsigned NOT NULL COMMENT '剧集ID',
  `hash_code` varchar(32) NOT NULL COMMENT '唯一哈希值',
  `filename` varchar(255) NOT NULL COMMENT '原始文件名',
  `size` int(11) unsigned NOT NULL COMMENT '文件大小 单位KB',
  `duration` int(11) unsigned NOT NULL COMMENT '时长 单位秒',
  `resolution_id` int(11) unsigned NOT NULL COMMENT '分辨率ID',
  `codec_format_id` int(11) unsigned NOT NULL COMMENT '编码格式ID',
  `meta_language` varchar(16) NOT NULL DEFAULT '' COMMENT '语言',
  `meta_tags` varchar(255) NOT NULL DEFAULT '' COMMENT '标签',
  `meta_category` varchar(64) NOT NULL DEFAULT '' COMMENT '类别',
  `path` varchar(255) NOT NULL COMMENT '视频路径',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_hash_code`(`hash_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频信息表';

CREATE TABLE IF NOT EXISTS `video_fragment` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `video_id` int(11) unsigned NOT NULL COMMENT '视频id',
  `fragment_no` int(11) unsigned NOT NULL COMMENT '视频分片编号',
  `start_time` int(11) unsigned NOT NULL COMMENT '开始时间 单位秒',
  `end_time` int(11) unsigned NOT NULL COMMENT '结束时间 单位秒',
  `relative_path` varchar(255) NOT NULL COMMENT '视频分片相对路径',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`),
  INDEX `index_video_id`(`video_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频分片表';

CREATE TABLE IF NOT EXISTS `video_transcode_task` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '转码任务ID',
  `video_id` INT UNSIGNED NOT NULL COMMENT '原始视频ID',
  `resolution_id` INT UNSIGNED NOT NULL COMMENT '分辨率ID',
  `codec_format_id` INT UNSIGNED NOT NULL COMMENT '编码格式ID',
  `frame_rate_id` INT UNSIGNED DEFAULT NULL COMMENT '帧速率ID',
  `status` TINYINT NOT NULL DEFAULT '0' COMMENT '转码状态：0-待处理，1-处理中，2-已完成，3-处理失败',
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` INT UNSIGNED NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` INT UNSIGNED NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` DATETIME DEFAULT NULL COMMENT '删除时间',
  `deleted_by` INT UNSIGNED NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` TINYINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`),
  INDEX `index_video_id`(`video_id`),
  INDEX `index_resolution_id`(`resolution_id`),
  INDEX `index_codec_format_id`(`codec_format_id`),
  INDEX `index_frame_rate_id`(`frame_rate_id`),
  INDEX `index_status`(`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频转码任务表';

CREATE TABLE IF NOT EXISTS `video_resolution` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` varchar(32) NOT NULL COMMENT '分辨率名称',
  `width` int(11) NOT NULL COMMENT '分辨率宽度',
  `height` int(11) NOT NULL COMMENT '分辨率高度',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频分辨率表';

CREATE TABLE IF NOT EXISTS `video_codec_format` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` varchar(32) NOT NULL COMMENT '编码器名称',
  `description` varchar(255) NOT NULL COMMENT '编码器描述',
  `extension` varchar(16) NOT NULL COMMENT '文件扩展名',
  `mime_type` varchar(64) NOT NULL COMMENT 'MIME类型',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频编码格式表';

CREATE TABLE IF NOT EXISTS `video_frame_rate` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '帧速率ID',
  `value` FLOAT UNSIGNED NOT NULL COMMENT '帧速率值',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='帧速率表';

CREATE TABLE IF NOT EXISTS `video_thumbnail` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '缩略图ID',
  `video_id` INT UNSIGNED NOT NULL COMMENT '原始视频ID',
  `url` VARCHAR(255) NOT NULL COMMENT '缩略图URL',
  `width` SMALLINT UNSIGNED NOT NULL COMMENT '缩略图宽度',
  `height` SMALLINT UNSIGNED NOT NULL COMMENT '缩略图高度',
  `size` INT UNSIGNED NOT NULL COMMENT '缩略图文件大小（字节）',
  `mime_type` VARCHAR(64) NOT NULL COMMENT '缩略图MIME类型',
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` INT UNSIGNED NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` INT UNSIGNED NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` DATETIME DEFAULT NULL COMMENT '删除时间',
  `deleted_by` INT UNSIGNED NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` TINYINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`),
  INDEX `index_video_id`(`video_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频缩略图表';
