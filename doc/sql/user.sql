-- 创建用户
CREATE USER 'nacos'@'%' IDENTIFIED BY 'nacos';
-- 用户赋权
grant select ,insert ,update on nacos.* to 'nacos'@'%' with grant option ;