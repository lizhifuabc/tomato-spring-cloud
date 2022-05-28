-- 创建用户
CREATE USER 'nacos'@'%' IDENTIFIED BY 'nacos';
CREATE USER 'tomato'@'%' IDENTIFIED BY 'tomato';
-- 用户赋权
grant select ,insert ,update on nacos.* to 'nacos'@'%' with grant option ;
grant select ,insert ,update on tomato_skill.* to 'tomato'@'%' with grant option ;