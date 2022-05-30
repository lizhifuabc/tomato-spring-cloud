-- ----------------------------
-- 秒杀活动记录
-- ----------------------------
DROP TABLE IF EXISTS `skill_activity`;
CREATE TABLE `skill_activity`  (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `activity_id` bigint(20) NOT NULL COMMENT '活动ID',
    `activity_name` varchar(64) CHARACTER SET utf8mb4 not null COMMENT '活动名称',
    `activity_desc` varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '活动描述',
    `start_time` datetime not null COMMENT '开始时间',
    `end_time` datetime not null COMMENT '结束时间',
    `state` tinyint(1) default 1 COMMENT '1-开启  0-关闭',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `unique_activity_id` (`activity_id`)
)ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='秒杀活动记录';


-- ----------------------------
-- 秒杀活动商品记录
-- ----------------------------
DROP TABLE IF EXISTS `skill_activity_relation`;
CREATE TABLE `skill_activity_relation`  (
     `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
     `activity_relation_id` bigint(20) NOT NULL COMMENT '秒杀活动商品记录ID',
     `activity_id` bigint(20) NOT NULL COMMENT '活动ID',
     `sku_id` bigint(20) NOT NULL COMMENT '商品id',
     `skill_price` bigint(20) NOT NULL  COMMENT '秒杀价格',
     `skill_count` int NOT NULL  COMMENT '秒杀总量',
     `skill_surplus_count` int(11) NOT NULL COMMENT '秒杀剩余量',
     `skill_limit` int NOT NULL  COMMENT '每人限购数量',
     `skill_sort` int NULL DEFAULT NULL COMMENT '排序',
     `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
     PRIMARY KEY (`id`),
     UNIQUE KEY `unique_activity_relation_id` (`activity_relation_id`)
)ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='秒杀活动商品记录';

-- ----------------------------
-- 用户参与活动记录
-- ----------------------------
DROP TABLE IF EXISTS `skill_activity_user`;
CREATE TABLE `skill_activity_user` (
      `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
      `activity_user_id` bigint(20) NOT NULL COMMENT '用户参与活动记录ID',
      `activity_relation_id` bigint(20) NOT NULL COMMENT '秒杀活动商品记录ID',
      `user_id` bigint(20) NOT NULL COMMENT '用户ID',
      `activity_count` int(11) NOT NULL COMMENT '秒杀次数',
      `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
      `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
      PRIMARY KEY (`id`),
      UNIQUE KEY `unique_activity_user_id` (`activity_user_id`),
      UNIQUE KEY `unique_activity_relation_id` (`activity_user_id`,`activity_relation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户参与活动记录';