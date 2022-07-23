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
      `merchant_name` varchar(256) not null comment '商户名称',
      `urgent` tinyint(1) not null default 1 comment '是否加急【0->是；1->否】',
      `account_name` varchar(64) not null comment '银行卡账户名称',
      `account_no` varchar(64) not null comment '银行卡号',
      `bank_no` varchar(32) not null comment '银行编码',
      `bank_name` varchar(64) not null comment '银行名称',
      `branch_bank_name` varchar(128) default '' comment '分行名称',
      `province` varchar(32) not null comment '省份编码',
      `city` varchar(32) not null comment '城市编码',
      `notify_address` varchar(256) default '' comment '通知地址',
      `create_batch` tinyint(1) not null default 0 comment '是否创建了批次【0->是；1->否】',
      `channel_code` varchar(32) not null comment '打款渠道',
      `batch_no` varchar(64)  comment '批次号',
      `remit_status` tinyint(1) not null default 0 comment '打款状态【0->打款中；1->打款成功；2->打款失败；3->人工处理】',
      `direct_remit` tinyint(1) not null default 1 comment '是否直接打款【0->是；1->否】',
      `exception_code` varchar(16) default '' comment '异常码',
      `exception_info` varchar(640) default '' comment '异常信息',
      `request_amount` decimal(14,4)  not null  comment '打款金额',
      `complete_time` datetime comment '完成时间',
      `create_time` datetime not null default current_timestamp comment '创建时间',
      `update_time` datetime not null default current_timestamp on update current_timestamp comment '修改时间',
      primary key (id),
      unique key ti_txp_order_or (remit_order_no),
      unique key ti_txp_order_mno (merchant_no,remit_request_no)
)engine=innodb auto_increment=1 default charset=utf8mb4 collate=utf8mb4_bin comment='打款订单表';

-- ----------------------------
-- 打款批次表
-- ----------------------------
drop table if exists `remit_batch_info`;
create table `remit_batch_info`(
    `id` bigint not null auto_increment comment 'id',
    `version` int not null default '0' comment '版本号',
    `batch_no` varchar(64) not null comment '批次号',
    `channel_code` varchar(32) not null comment '打款渠道',
    `remit_status` tinyint(1) not null default 0 comment '打款状态【0->打款中；1->打款成功；2->打款失败；3->人工处理】',
    `remit_count` int not null comment '打款笔数',
    `remit_sum_amount` decimal(14,4) not null comment '打款总金额',
    `complete_time` datetime comment '完成时间',
    `create_time` datetime not null default current_timestamp comment '创建时间',
    `update_time` datetime not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (id),
    unique key ti_txp_order_or (batch_no)
)engine=innodb auto_increment=1 default charset=utf8mb4 collate=utf8mb4_bin comment='打款批次表';

-- ----------------------------
-- 打款渠道表
-- ----------------------------
drop table if exists `remit_channel_info`;
create table `remit_channel_info`(
     `id` bigint not null auto_increment comment 'id',
     `version` int not null default '0' comment '版本号',
     `channel_code` varchar(32) not null comment '渠道编码',
     `channel_name` varchar(64) not null comment '渠道名称',
     `channel_status`      tinyint(1) not null default 0 comment '渠道状态【0->开；1->关】',
     `channel_cost` decimal(14,4) not null default 0 comment '渠道成本',
     `bank_account_no` varchar(64) not null default '' comment '所在银行账号',
     `channel_speed`      tinyint(1) not null default 0 comment '渠道速度【0->快；1->慢】',
     `create_time` datetime not null default current_timestamp comment '创建时间',
     `update_time` datetime not null default current_timestamp on update current_timestamp comment '修改时间',
     primary key (id),
     unique key ti_txp_channel_code (channel_code)
)engine=innodb auto_increment=1 default charset=utf8mb4 collate=utf8mb4_bin comment='打款渠道表';

-- ----------------------------
-- 商户绑定打款渠道表
-- ----------------------------
drop table if exists `remit_merchant_info`;
create table  `remit_merchant_info`(
       `id` bigint not null auto_increment comment 'id',
       `version` int not null default '0' comment '版本号',
       `channel_code` varchar(32) not null comment '渠道编码',
       `merchant_no` varchar(16)  not null comment '商户编号',
       `merchant_name` varchar(256)  not null comment '商户名称',
       `bind_status`  tinyint(1) not null default 0 comment '绑定状态【0->开；1->关】',
       `create_time` datetime not null default current_timestamp comment '创建时间',
       `update_time` datetime not null default current_timestamp on update current_timestamp comment '修改时间',
       primary key (id),
       unique key ti_txp_order_mno (merchant_no,channel_code)
)engine=innodb auto_increment=1 default charset=utf8mb4 collate=utf8mb4_bin comment='商户绑定打款渠道表';