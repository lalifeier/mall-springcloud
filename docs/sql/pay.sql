-- 支付渠道
CREATE TABLE IF NOT EXISTS `pay_channel` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '支付渠道ID',
  `name` VARCHAR(50) NOT NULL COMMENT '支付渠道名称',
  `description` VARCHAR(255) DEFAULT NULL COMMENT '支付渠道描述',
  `logo` VARCHAR(255) DEFAULT NULL COMMENT '支付渠道LOGO',
  `status` TINYINT(1) NOT NULL DEFAULT '1' COMMENT '支付渠道状态，1表示启用，0表示禁用',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支付渠道表';

-- -- 支付应用
-- CREATE TABLE IF NOT EXISTS `pay_app` (
--   `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '应用ID',
--   `name` int(11) NOT NULL COMMENT '应用名称',
--   `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态：0未激活，1已激活',
--   `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
--   `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
--   `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
--   `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新人',
-- --   `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
--   PRIMARY KEY (`id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支付应用表';

-- -- 支付渠道
-- CREATE TABLE IF NOT EXISTS `app_channel` (
--   `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '应用支付渠道ID',
--   `app_id` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '应用ID',
--   `channel_id` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '渠道ID',
--   `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
--   `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
--   `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
--   `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新人',
-- --   `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
--   PRIMARY KEY (`id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='应用支付渠道表';

-- 支付宝参数
CREATE TABLE alipay_payment_parameters (
    app_id VARCHAR(255) COMMENT '应用ID',
    out_trade_no VARCHAR(255) COMMENT '商户订单号',
    total_amount DECIMAL(10,2) COMMENT '订单总金额',
    subject VARCHAR(255) COMMENT '订单标题',
    body VARCHAR(255) COMMENT '订单描述',
    product_code VARCHAR(255) COMMENT '产品编号',
    goods_detail VARCHAR(255) COMMENT '商品详情',
    passback_params VARCHAR(255) COMMENT '回传参数',
    extend_params VARCHAR(255) COMMENT '扩展参数',
    timeout_express VARCHAR(255) COMMENT '超时时间',
    seller_id VARCHAR(255) COMMENT '卖家支付宝用户号',
    auth_token VARCHAR(255) COMMENT '授权令牌',
    notify_url VARCHAR(255) COMMENT '异步通知地址',
    return_url VARCHAR(255) COMMENT '同步通知地址',
    enable_pay_channels VARCHAR(255) COMMENT '可用渠道',
    disable_pay_channels VARCHAR(255) COMMENT '禁用渠道',
    store_id VARCHAR(255) COMMENT '商户门店编号',
    qr_pay_mode VARCHAR(255) COMMENT '扫码支付方式',
    qrcode_width INT COMMENT '动态生成二维码宽度',
    need_buyer_realnamed VARCHAR(255) COMMENT '是否需要买家实名认证',
    hb_fq_num INT COMMENT '花呗分期数',
    hb_fq_seller_percent DECIMAL(10,2) COMMENT '花呗分期商家承担手续费比例'
);

-- 微信支付参数
CREATE TABLE wechat_payment_parameters (
    appid VARCHAR(255) COMMENT '应用ID',
    mch_id VARCHAR(255) COMMENT '商户号',
    nonce_str VARCHAR(255) COMMENT '随机字符串',
    sign_type VARCHAR(255) COMMENT '签名类型',
    sign VARCHAR(255) COMMENT '签名',
    body VARCHAR(255) COMMENT '商品描述',
    out_trade_no VARCHAR(255) COMMENT '商户订单号',
    total_fee INT COMMENT '总金额',
    spbill_create_ip VARCHAR(255) COMMENT '终端IP',
    notify_url VARCHAR(255) COMMENT '通知地址',
    trade_type VARCHAR(255) COMMENT '交易类型',
    openid VARCHAR(255) COMMENT '用户标识'
);

CREATE TABLE IF NOT EXISTS `pay_transaction` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '交易ID',
  `pay_method_id` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '支付方式id，可以用来识别支付，如：支付宝、微信、Paypal等',
  `app_order_id` VARCHAR(64) NOT NULL COMMENT '应用方订单号',

  `transaction_no` VARCHAR(64) NOT NULL COMMENT '交易编号',
  `status` TINYINT NOT NULL DEFAULT 0 COMMENT '交易状态 0待支付 1已支付 3交易关闭 4支付失败',

  `app_id` VARCHAR(32) NOT NULL COMMENT '应用id',

  `amount` decimal(12,4) NOT NULL DEFAULT '0.0000' COMMENT '交易金额',
  `currency` CHAR(3) NOT NULL DEFAULT 'CNY' COMMENT '支付的货币类型',
  `notify_url` VARCHAR(255) NOT NULL COMMENT '回调地址',
  `notify_time` datetime DEFAULT NULL COMMENT '异步通知时间',
  `trade_no` VARCHAR(64) NOT NULL COMMENT '第三方系统流水编号',
  `expire_time` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '交易结束时间',
  `paid_time` datetime DEFAULT NULL COMMENT '支付成功时间',
  `complete_time` datetime DEFAULT NULL COMMENT '交易完成时间',


  -- `pay_channel` VARCHAR(64) NOT NULL COMMENT '选择的支付渠道，比如：支付宝中的花呗、信用卡等',
  -- `return_url` VARCHAR(255) NOT NULL COMMENT '支付后跳转url',
  -- `email` VARCHAR(64) NOT NULL COMMENT '用户的邮箱',
  -- `sign_type` VARCHAR(10) NOT NULL DEFAULT 'RSA' COMMENT '采用的签方式：MD5 RSA RSA2 HASH-MAC等',
  -- `intput_charset` CHAR(5) NOT NULL DEFAULT 'UTF-8' COMMENT '字符集编码方式',
  -- `payment_time` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '第三方支付成功的时间',
  -- `notify_time` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '收到异步通知的时间',
  -- `finish_time` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '通知上游系统的时间',
  -- `transaction_code` VARCHAR(64) NOT NULL COMMENT '真实给第三方的交易code，异步通知的时候更新',


  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `updated_by` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '更新人',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0未删除，1已删除',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `unq_transaction_id` (`transaction_id`),
  INDEX `idx_trade_no` (`trade_no`),
  INDEX `idx_created_time` (`created_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支付流水表';

CREATE TABLE IF NOT EXISTS `pay_transaction_extension` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `transaction_id` VARCHAR(64) NOT NULL COMMENT '系统唯一交易id',
  `pay_method_id` INT UNSIGNED NOT NULL DEFAULT 0,
  `transaction_code` VARCHAR(64) NOT NULL COMMENT '生成传输给第三方的订单号',
  `call_num` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '发起调用的次数',
  `extension_data` TEXT NOT NULL COMMENT '扩展内容，需要保存：transaction_code 与 trade no 的映射关系，异步通知的时候填充',
  `create_at` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `create_ip` INT UNSIGNED NOT NULL COMMENT '创建ip',
  PRIMARY KEY (`id`),
  INDEX `idx_trads` (`transaction_id`),
  UNIQUE INDEX `uniq_code` (`transaction_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COMMENT = '交易扩展表';

CREATE TABLE IF NOT EXISTS `pay_log_data` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `app_id` VARCHAR(32) NOT NULL COMMENT '应用id',
  `app_order_id` VARCHAR(64) NOT NULL COMMENT '应用方订单号',
  `transaction_id` VARCHAR(64) NOT NULL COMMENT '本次交易唯一id，整个支付系统唯一，生成他的原因主要是 order_id对于其它应用来说可能重复',
  `request_header` TEXT NOT NULL COMMENT '请求的header头',
  `request_params` TEXT NOT NULL COMMENT '支付的请求参数',
  `log_type` VARCHAR(10) NOT NULL COMMENT '日志类型，payment:支付; refund:退款; notify:异步通知; return:同步通知; query:查询',
  `create_at` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `create_ip` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建ip',
  PRIMARY KEY (`id`),
  INDEX `idx_tradt` (`transaction_id`, `log_type`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COMMENT = '交易日志表';


CREATE TABLE IF NOT EXISTS `pay_repeat_transaction` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `app_id` VARCHAR(32) NOT NULL COMMENT '应用的id',
  `transaction_id` VARCHAR(64) NOT NULL COMMENT '系统唯一识别交易号',
  `transaction_code` VARCHAR(64) NOT NULL COMMENT '支付成功时，该笔交易的 code',
  `trade_no` VARCHAR(64) NOT NULL COMMENT '第三方对应的交易号',
  `pay_method_id` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '支付方式',
  `total_fee` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '交易金额',
  `scale` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '小数位数',
  `currency_code` CHAR(3) NOT NULL DEFAULT 'CNY' COMMENT '支付选择的币种，CNY、HKD、USD等',
  `payment_time` INT NOT NULL COMMENT '第三方交易时间',
  `repeat_type` TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '重复类型：1同渠道支付、2不同渠道支付',
  `repeat_status` TINYINT UNSIGNED DEFAULT 0 COMMENT '处理状态,0:未处理；1:已处理',
  `create_at` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_at` INT UNSIGNED NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  INDEX `idx_trad` ( `transaction_id`),
  INDEX `idx_method` (`pay_method_id`),
  INDEX `idx_time` (`create_at`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COMMENT = '重复支付交易';


CREATE TABLE IF NOT EXISTS `pay_notify_app_log` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `app_id` VARCHAR(32) NOT NULL COMMENT '应用id',
  `pay_method_id` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '支付方式',
  `transaction_id` VARCHAR(64) NOT NULL COMMENT '交易号',
  `transaction_code` VARCHAR(64) NOT NULL COMMENT '支付成功时，该笔交易的 code',
  `sign_type` VARCHAR(10) NOT NULL DEFAULT 'RSA' COMMENT '采用的签名方式：MD5 RSA RSA2 HASH-MAC等',
  `input_charset` CHAR(5) NOT NULL DEFAULT 'UTF-8',
  `total_fee` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '涉及的金额，无小数',
  `scale` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '小数位数',
  `pay_channel` VARCHAR(64) NOT NULL COMMENT '支付渠道',
  `trade_no` VARCHAR(64) NOT NULL COMMENT '第三方交易号',
  `payment_time` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '支付时间',
  `notify_type` VARCHAR(10) NOT NULL DEFAULT 'paid' COMMENT '通知类型，paid/refund/canceled',
  `notify_status` VARCHAR(7) NOT NULL DEFAULT 'INIT' COMMENT '通知支付调用方结果；INIT:初始化，PENDING: 进行中；  SUCCESS：成功；  FAILED：失败',
  `create_at` INT UNSIGNED NOT NULL DEFAULT 0,
  `update_at` INT UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `idx_trad` (`transaction_id`),
  INDEX `idx_app` (`app_id`, `notify_status`),
  INDEX `idx_time` (`create_at`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COMMENT = '支付调用方记录';


CREATE TABLE IF NOT EXISTS `pay_refund` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `app_id` VARCHAR(64) NOT NULL COMMENT '应用id',
  `app_refund_no` VARCHAR(64) NOT NULL COMMENT '上游的退款id',
  `transaction_id` VARCHAR(64) NOT NULL COMMENT '交易号',
  `trade_no` VARCHAR(64) NOT NULL COMMENT '第三方交易号',
  `refund_no` VARCHAR(64) NOT NULL COMMENT '支付平台生成的唯一退款单号',
  `pay_method_id` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '支付方式',
  `pay_channel` VARCHAR(64) NOT NULL COMMENT '选择的支付渠道，比如：支付宝中的花呗、信用卡等',
  `refund_fee` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '退款金额',
  `scale` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '小数位数',
  `refund_reason` VARCHAR(128) NOT NULL COMMENT '退款理由',
  `currency_code` CHAR(3) NOT NULL DEFAULT 'CNY' COMMENT '币种，CNY  USD HKD',
  `refund_type` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '退款类型；0:业务退款; 1:重复退款',
  `refund_method` TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '退款方式：1自动原路返回; 2人工打款',
  `refund_status` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '0未退款; 1退款处理中; 2退款成功; 3退款不成功',
  `create_at` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_at` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '更新时间',
  `create_ip` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '请求源ip',
  `update_ip` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '请求源ip',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uniq_refno` (`refund_no`),
  INDEX `idx_trad` (`transaction_id`),
  INDEX `idx_status` (`refund_status`),
  INDEX `idx_ctime` (`create_at`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COMMENT = '退款记录';


-- ## 支付宝支付

-- [文档](https://opendocs.alipay.com/apis/)

-- ## 微信支付

-- [文档](https://pay.weixin.qq.com/wiki/doc/apiv3/index.shtml)
