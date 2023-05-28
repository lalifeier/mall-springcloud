CREATE TABLE IF NOT EXISTS `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '地址ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `consignee` VARCHAR(255) NOT NULL COMMENT '收货人姓名',
  `email` VARCHAR(255) NOT NULL COMMENT '收货人邮箱',
  `phone` VARCHAR(255) NOT NULL COMMENT '收货人手机号',
  `country_id` INT UNSIGNED NOT NULL COMMENT '国家ID',
  `country_name` VARCHAR(255) NOT NULL COMMENT '国家名称',
  `province_id` INT UNSIGNED NOT NULL COMMENT '省份ID',
  `province_name` VARCHAR(255) NOT NULL COMMENT '省份名称',
  `city_id` INT UNSIGNED NOT NULL COMMENT '城市ID',
  `city_name` VARCHAR(255) NOT NULL COMMENT '城市名称',
  `district_id` INT UNSIGNED NOT NULL COMMENT '区县ID',
  `district_name` VARCHAR(255) NOT NULL COMMENT '区县名称',
  `street_id` INT UNSIGNED NOT NULL COMMENT '街道ID',
  `street_name` VARCHAR(255) NOT NULL COMMENT '街道名称',
  `detailed_address` VARCHAR(255) NOT NULL COMMENT '详细地址',
  `postal_code` VARCHAR(255) NOT NULL COMMENT '邮编',
  `is_default` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否默认地址',
  `label` VARCHAR(255) NOT NULL COMMENT '地址类型标签，家、公司等',
  `longitude` DECIMAL(10,6) NOT NULL COMMENT '经度',
  `latitude` DECIMAL(10,6) NOT NULL COMMENT '纬度',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='地址表';

CREATE TABLE IF NOT EXISTS `invoice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '发票ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `title` varchar(100) NOT NULL COMMENT '发票抬头',
  `type` int(11) NOT NULL COMMENT '发票类型，1-普通发票，2-增值税发票',
  `content` varchar(100) NOT NULL COMMENT '发票内容',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='发票表';

CREATE TABLE IF NOT EXISTS `payment_method` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '支付方式ID',
  `name` VARCHAR(50) NOT NULL COMMENT '支付方式名称',
  `description` VARCHAR(255) NOT NULL COMMENT '支付方式描述',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COMMENT = '支付方式表';

CREATE TABLE IF NOT EXISTS `order_payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL COMMENT '订单ID',
  `pay_method_id` INT UNSIGNED NOT NULL COMMENT '支付方式ID',
  `payment_no` varchar(255) DEFAULT NULL COMMENT '交易单号',
  `transaction_no` varchar(255) DEFAULT NULL COMMENT '第三方支付流水号',
  `amount` decimal(12,4) NOT NULL DEFAULT '0.0000' COMMENT '支付金额',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态：0未支付 1已支付',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单支付表';


CREATE TABLE IF NOT EXISTS `order_discount` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` int(11) NOT NULL COMMENT '订单ID',
  `discount_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '优惠类型：1满减、2折扣、3优惠券、4积分兑换、5礼品卡',
  `discount_amount` decimal(12,4) NOT NULL DEFAULT '0.0000' COMMENT '优惠金额',
  `discount_info` varchar(255) DEFAULT NULL COMMENT '优惠信息',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单优惠表';

CREATE TABLE IF NOT EXISTS `orders` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `order_no` VARCHAR(100) NOT NULL COMMENT '订单编号',
  `customer_id` INT UNSIGNED NOT NULL COMMENT '客户ID',
  `supplier_id` INT UNSIGNED NOT NULL COMMENT '商家ID',
  `status` TINYINT NOT NULL DEFAULT '0' COMMENT '订单状态 0未付款,1已付款,2已发货,3已签收,-1退货申请,-2退货中,-3已退货,-4取消交易 -5撤销申请',
  `shipping_status` TINYINT NOT NULL DEFAULT '0' COMMENT '物流状态 0 待发货 1 已发货 2 已签收 3 配送中 4 派件中 5 已退回',
  `after_sales_status` TINYINT NOT NULL DEFAULT '0' COMMENT '售后状态 0 无售后记录 1 已发起售后 2 售后处理中 3 售后完成',
  `product_count` INT UNSIGNED NOT NULL DEFAULT '0' COMMENT '商品数量',
  `product_total_amount` DECIMAL(10,2) NOT NULL COMMENT '商品总价',
  `order_total_amount` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
  `address_id` INT UNSIGNED NOT NULL COMMENT '收货地址ID',
  `shipping_fee` DECIMAL(10,2) NOT NULL COMMENT '运费金额',
  `shipping_time` DATETIME DEFAULT NULL COMMENT '发货时间',
  `payment_id` INT UNSIGNED DEFAULT NULL COMMENT '支付方式ID',
  `discount_id` INT UNSIGNED DEFAULT NULL COMMENT '优惠ID',
  `invoice_type_id` INT UNSIGNED NOT NULL COMMENT '发票类型ID',
  `is_invoice` TINYINT NOT NULL DEFAULT '0' COMMENT '是否需要发票，0不需要，1需要',
  `comment` VARCHAR(255) NOT NULL COMMENT '订单备注',
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` INT UNSIGNED NOT NULL COMMENT '创建人ID',
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` INT UNSIGNED NOT NULL COMMENT '修改人ID',
  `deleted_at` DATETIME DEFAULT NULL COMMENT '删除时间',
  `deleted_by` INT UNSIGNED NOT NULL DEFAULT '0' COMMENT '删除人ID',
  `is_deleted` TINYINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '是否删除，0未删除，1已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unq_order_no` (`order_no`),
  KEY `idx_customer_id` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

CREATE TABLE IF NOT EXISTS `order_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单明细ID',
  `order_id` int(11) NOT NULL COMMENT '订单编号',
  `product_id` int(11) NOT NULL COMMENT '商品编号',
  `product_sku_id` int(11) NOT NULL COMMENT '商品SKU编号',
  `name` varchar(255) NOT NULL COMMENT '商品名称'
  `quantity` int(11) NOT NULL COMMENT '购买数量',
  `price` decimal(10,2) NOT NULL COMMENT '单价',
  `discount_rate` decimal(5,2) NOT NULL DEFAULT '0.00' COMMENT '折扣比例',
  `discount_amount` decimal(12,4) NOT NULL DEFAULT '0.0000' COMMENT '折扣金额',
  `actual_amount` decimal(12,4) NOT NULL DEFAULT '0.0000' COMMENT '实付金额',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '修改人',
  `deleted_at` datetime DEFAULT NULL COMMENT '删除时间',
  `deleted_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单明细表';


-- 设计规则
-- 1.日期：年月日时分秒 //14或8(只要日期)位
-- 2.随机数字 //6或2位
-- 3.商家id //哪个商家的数据
-- 4.用户id //哪个用户的数据。取后4位
-- 5.商品id
-- 6.业务id字段 //支付通道
-- 7.其他唯一id字段
