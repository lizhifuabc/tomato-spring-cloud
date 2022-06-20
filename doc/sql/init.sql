-- 创建用户 root 1qaz@WSX
create user 'tomato'@'%' identified by 'tomato';
grant all privileges on *.* to 'tomato'@'%';
flush privileges;
-- DATABASE
CREATE DATABASE IF NOT EXISTS nacos DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_general_ci;

CREATE DATABASE IF NOT EXISTS tomato_id DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_general_ci;

CREATE DATABASE IF NOT EXISTS tomato_skill DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_general_ci;

CREATE DATABASE IF NOT EXISTS tomato_auth DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_general_ci;

CREATE DATABASE IF NOT EXISTS tomato_sys DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_general_ci;

CREATE DATABASE IF NOT EXISTS tomato_account DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_general_ci;

-- 订单
CREATE DATABASE IF NOT EXISTS tomato_order DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_general_ci;
-- 商户
CREATE DATABASE IF NOT EXISTS tomato_merchant DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_general_ci;