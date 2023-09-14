CREATE TABLE IF NOT EXISTS `district` (
  `id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '行政区域唯一标识',
  `code` VARCHAR(20) NOT NULL COMMENT '行政区域编码',
  `name` VARCHAR(100) NOT NULL COMMENT '行政区域名称',
  `parent_id` int(11) unsigned NOT NULL DEFAULT '0'  COMMENT '父级行政区域的唯一标识',
  `level` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '行政区域级别 0：country(国家) 1：province(省/直辖市) 2：city(市) 3：district(区/县) 4：street(乡镇/街道)',
  `sort` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='行政区域表';
