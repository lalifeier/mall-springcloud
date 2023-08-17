CREATE TABLE IF NOT EXISTS `order_refund` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` int(11) NOT NULL COMMENT '订单ID',
  `pay_method_id` INT UNSIGNED NOT NULL COMMENT '支付方式ID',
  `refund_no` varchar(255) DEFAULT NULL COMMENT '退款单号',
  `transaction_no` varchar(255) DEFAULT NULL COMMENT '第三方支付流水号',
  `amount` decimal(12,4) NOT NULL DEFAULT '0.0000' COMMENT '退款金额',
  `refund_start_time` datetime DEFAULT NULL COMMENT '退款开始时间',
  `refund_end_time` datetime DEFAULT NULL COMMENT '退款结束时间',
  `refund_reason` VARCHAR(255) DEFAULT NULL COMMENT '退款原因：记录商家退款被拒绝或退款金额被调整的原因',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态：0未审核 1已审核 -1审核不通过 2已退款',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单退款表';

CREATE TABLE IF NOT EXISTS `order_return` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '退货ID',
  `order_id` INT UNSIGNED NOT NULL COMMENT '订单ID',
  `customer_id` INT UNSIGNED NOT NULL COMMENT '客户ID',
  `supplier_id` INT UNSIGNED NOT NULL COMMENT '商家ID',
  `reason` VARCHAR(255) NOT NULL COMMENT '退货原因：记录客户退货请求的原因',
  `refund_id` INT UNSIGNED DEFAULT NULL COMMENT '退款方式ID',
  `status` TINYINT NOT NULL DEFAULT '0' COMMENT '退货状态，0未处理，1已受理，2已完成，-1已拒绝',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单退货表';

CREATE TABLE IF NOT EXISTS `order_return_item` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '退货明细ID',
  `order_id` INT UNSIGNED NOT NULL COMMENT '退款订单ID',
  `product_id` int(11) NOT NULL COMMENT '商品编号',
  `product_sku_id` int(11) NOT NULL COMMENT '商品SKU编号',
  `name` varchar(255) NOT NULL COMMENT '商品名称',
  `quantity` INT UNSIGNED NOT NULL DEFAULT '1' COMMENT '退货数量',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单退货明细表';
