-- ----------------------------
-- 打款订单表
-- ----------------------------
drop table if exists `remit_order_info`;
create table `remit_order_info`(
      `id` bigint not null auto_increment comment 'id',
      `version` int not null default '0' comment '版本号',
      `remit_request_no` varchar(64) not null comment '打款请求流水号',
      `remit_order_no` varchar(36) not null  comment '打款系统唯一流水号',
      `merchant_no` varchar(16)  not null comment '商户编号',
      `merchant_name` varchar(256)  comment '商户名称',
      `urgent` tinyint(1) not null default 1 comment '是否加急【0->是；1->否】',
      `account_name` varchar(64) not null comment '银行卡账户名称',
      `account_no` varchar(64) not null comment '银行卡号',
      `bank_no` varchar(32) not null comment '银行编码',
      `bank_name` varchar(64) not null comment '银行名称',
      `branch_bank_name` varchar(128) default '' comment '分行名称',
      `province` varchar(32) not null comment '省份编码',
      `city` varchar(32) not null comment '城市编码',
      `notifyAddress` varchar(256) default '' comment '通知地址',
      `create_batch` tinyint(1) not null default 0 comment '是否创建了批次【0->是；1->否】',
      `request_amount` decimal(14,4)  not null  comment '打款金额',
      `create_time` datetime not null default current_timestamp comment '创建时间',
      `update_time` datetime not null default current_timestamp on update current_timestamp comment '修改时间',
      primary key (id),
      unique key ti_txp_order_or (remit_order_no),
      unique key ti_txp_order_mno (merchant_no,remit_request_no)
)engine=innodb auto_increment=1 default charset=utf8mb4 collate=utf8mb4_bin comment='打款订单表';