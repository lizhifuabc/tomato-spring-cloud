use tomato_order;
-- ----------------------------
-- 订单详情表
-- ----------------------------
drop table if exists `order_info`;
create table `order_info`  (
    `id` bigint not null auto_increment comment 'id',
    `version` int not null default '0' comment '版本号',
    `machine_ip` varchar(40) not null  comment '收单服务器ip',
    `order_no` varchar(50) not null  comment '订单号',
    `request_amount` decimal(14,4)  not null  comment '订单金额',
    `order_status` int not null  comment '订单状态',
    `account_status` varchar(36)  comment '入账状态',
    `refund_status` varchar(36)  comment '退款状态',
    `notice_status` varchar(36)  comment '通知状态',
    `create_time` datetime not null default current_timestamp comment '创建时间',
    `update_time` datetime not null default current_timestamp comment '修改时间',
    `complete_date` datetime comment '完成时间',
    `timeout_date` datetime  comment '超时时间',
    `pay_type` int not null comment '支付方式',
    `merchant_order_no` varchar(36) not null  comment '商户订单号',
    `merchant_fee` decimal(14,4)  not null comment '手续费',
    `merchant_rate` decimal(14,4)  not null comment '费率',
    `merchant_no` varchar(16)  not null comment '商户编号',
    `merchant_name` varchar(256)  comment '商户名称',
    `remark` varchar(256)  comment '备注',
    `notice_web` varchar(128)  comment '页面通知地址',
    `notice_sys` varchar(128)  comment '系统通知地址',
    `order_type` tinyint(1) not null comment '订单类型：标准版或专业版',
    `pay_no` varchar(50)  comment '支付号',

    primary key (id),
    key ti_txp_order_or (order_no),
    unique key ti_txp_order_mno (merchant_no,merchant_order_no),
    key ti_txp_order_payno (pay_no)
) engine=innodb auto_increment=4 default charset=utf8mb4 collate=utf8mb4_bin comment='订单表';