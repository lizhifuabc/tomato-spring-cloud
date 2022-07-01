use tomato_merchant;
-- ----------------------------
-- 商户信息表
-- ----------------------------
drop table if exists merchant_info;
create table merchant_info (
   `id` bigint(20) not null auto_increment comment 'id',
   `version` int not null default '0' comment '版本号',
   `merchant_no`  varchar(50) not null comment '商户编号',
   `merchant_type`  tinyint(1) not null comment '商户类型',
   `merchant_name`  varchar(256) not null comment '商户名称',
   `mail`   varchar(50) not null comment '注册邮箱',
   `status` tinyint(1) null default 0 comment '状态【0->正常；1->关闭】',
   `audit_status` tinyint(1) null default 1 comment '状态【0->审核通过；1->审核中；2->审核拒绝】',
   `create_time` datetime default current_timestamp comment '创建时间',
   `update_time` datetime default current_timestamp on update current_timestamp comment '修改时间',
   primary key (`id`),
   unique key `merchant_no` (`merchant_no`),
   unique key `mail` (`mail`)
)engine=innodb default charset=utf8 comment '商户信息表';

-- ----------------------------
-- 商户费率表
-- ----------------------------
drop table if exists merchant_rate;
create table merchant_rate (
    `id` bigint(20) not null auto_increment comment 'id',
    `merchant_no`  varchar(50) not null comment '商户编号',
    `rate`   decimal(14, 4)  not null comment '费率',
    `status` tinyint(1) null default 0 comment '状态【0->正常；1->关闭】',
    `pay_type` tinyint not null  comment '支付方式',
    `create_time` datetime default current_timestamp comment '创建时间',
    `update_time` datetime default current_timestamp comment '修改时间',
    primary key (`id`),
    unique key `merchant_no` (`merchant_no`, `pay_type`)
)engine=innodb default charset=utf8 comment '商户费率表';