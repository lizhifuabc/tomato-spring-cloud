-- ----------------------------
-- 系统用户信息表
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
     `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
     `sys_user_id` bigint(20) NOT NULL COMMENT '系统用户ID',
     `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
     `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
     `state` tinyint(1) NOT NULL DEFAULT 1 COMMENT '用户状态：1-正常 0-禁用',
     `deleted` tinyint(1) NULL DEFAULT 1 COMMENT '逻辑删除标识：1-未删除；0-已删除',
     `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
     PRIMARY KEY (`id`),
     UNIQUE KEY `unique_sys_user_id` (`sys_user_id`),
     UNIQUE KEY `unique_sys_username`(`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='系统用户信息表';