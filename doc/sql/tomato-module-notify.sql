use tomato_notify;
-- 商户通知记录表
drop table if exists `notify_record`;
create table `notify_record` (
   `notify_id` bigint(20) not null auto_increment comment '商户通知记录id',
   `order_no` varchar(36) not null  comment '订单号',
   `merchant_order_no` varchar(36) not null  comment '商户订单号',
   `merchant_no` varchar(16)  not null comment '商户编号',
   `notify_url` text not null comment '通知地址',
   `res_result` text default null comment '通知响应结果',
   `notify_count` int(11) not null default '0' comment '通知次数',
   `notify_count_limit` int(11) not null default '6' comment '最大通知次数, 默认6次',
   `notify_status` tinyint(1) default 0 comment '通知状态,0-通知中,1-通知成功,2-通知失败',
   `create_time` datetime not null default current_timestamp comment '创建时间',
   `update_time` datetime not null default current_timestamp on update current_timestamp comment '修改时间',
   `complete_date` datetime comment '完成时间',
   primary key (`notify_id`),
   unique key `uni_order_no` (`order_no`)
) engine=innodb auto_increment=1001 default charset=utf8mb4 comment='商户通知记录表';