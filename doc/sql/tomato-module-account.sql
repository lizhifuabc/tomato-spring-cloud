use tomato_account;
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `account_id` bigint(20) NOT NULL COMMENT '账户ID',
  `balance` decimal(16,2) NOT NULL DEFAULT '0' COMMENT '余额',
  `create_time` timestamp NOT NULL DEFAULT '2010-01-01 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '2010-01-01 00:00:00' COMMENT '更新时间',
  `version` int NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_account_id` (account_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '账户表';

DROP TABLE IF EXISTS `account_his`;
CREATE TABLE `account_his` (
   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
   `account_his_id` bigint(20) NOT NULL COMMENT '账户历史ID',
   `account_id` bigint(20) NOT NULL COMMENT '账户ID',
   `before_balance` decimal(16,2)   COMMENT '发生前余额',
   `after_balance` decimal(16,2)    COMMENT '发生后余额',
   `amount` decimal(16,2) NOT NULL  COMMENT '发生金额',
   `third_no` varchar(36) NOT NULL  COMMENT '第三方流水号',
   `account_his_type` varchar(36) NOT NULL  COMMENT '类型',
   `state` tinyint NOT NULL  COMMENT '状态,10:创建,11:成功,12:失败',
   `create_time` timestamp NOT NULL DEFAULT '2010-01-01 00:00:00' COMMENT '创建时间',
   `update_time` timestamp NOT NULL DEFAULT '2010-01-01 00:00:00' COMMENT '更新时间',
   `version` int NOT NULL DEFAULT '0' COMMENT '版本号',
   PRIMARY KEY (`id`),
   UNIQUE KEY `uniq_account_his_id` (account_his_id),
   UNIQUE KEY `uniq_account_third_no` (account_id,third_no)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '账户历史表';