use tomato_order;
-- ----------------------------
-- 订单详情表
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
      `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
      `version` int NOT NULL DEFAULT '0' COMMENT '版本号',
      `order_status` int NOT NULL DEFAULT 101 COMMENT '订单状态',
      `pay_type` tinyint NULL  COMMENT '支付方式',
      `order_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '订单号',
      `order_source` tinyint NOT NULL DEFAULT 1 COMMENT '订单来源',
      `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '订单备注',
      `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除【0->正常；1->已删除】',
      `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
      `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',

      `total_amount` bigint NOT NULL DEFAULT 0 COMMENT '订单总额（分）',
      `total_quantity` int NOT NULL DEFAULT 0 COMMENT '商品总数',
      `member_id` bigint NOT NULL DEFAULT 0 COMMENT '会员id',
      `freight_amount` bigint NOT NULL DEFAULT 0 COMMENT '运费金额（分）',
      `pay_amount` bigint NOT NULL DEFAULT 0 COMMENT '应付总额（分）',
      `pay_time` datetime NULL DEFAULT NULL COMMENT '支付时间',
      `out_trade_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信支付等第三方支付平台的商户订单号',
      `transaction_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信支付订单号',
      `out_refund_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户退款单号',
      `refund_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信退款单号',
      `delivery_time` datetime NULL DEFAULT NULL COMMENT '发货时间',
      `receive_time` datetime NULL DEFAULT NULL COMMENT '确认收货时间',
      `comment_time` datetime NULL DEFAULT NULL COMMENT '评价时间',

      PRIMARY KEY (`id`),
      UNIQUE INDEX `index_order_id`(`order_id`) USING BTREE COMMENT '订单号唯一索引',
      UNIQUE INDEX `index_otn`(`out_trade_no`) USING BTREE COMMENT '商户订单号唯一索引',
      UNIQUE INDEX `index_ti`(`transaction_id`) USING BTREE COMMENT '商户支付单号唯一索引',
      UNIQUE INDEX `index_orn`(`out_refund_no`) USING BTREE COMMENT '商户退款单号唯一索引',
      UNIQUE INDEX `index_ri`(`refund_id`) USING BTREE COMMENT '退款单号唯一索引'
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单详情表';