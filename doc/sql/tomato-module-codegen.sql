drop table if exists `codegen_field_type`;
CREATE TABLE `codegen_field_type` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `column_type` varchar(200) COMMENT '字段类型',
    `column_java_type` varchar(200) COMMENT '属性类型',
    `column_java_pac` varchar(200) COMMENT '属性所在包',
    `create_time` timestamp NOT NULL DEFAULT '2010-01-01 00:00:00' COMMENT '创建时间',
    `update_time` timestamp NOT NULL DEFAULT '2010-01-01 00:00:00' COMMENT '更新时间',
    primary key (id),
    unique key (column_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字段类型管理';


INSERT INTO `codegen_field_type` (id, column_type, column_java_type, column_java_pac) VALUES (1, 'datetime', 'LocalDateTime', 'java.time.LocalDateTime');
INSERT INTO `codegen_field_type` (id, column_type, column_java_type, column_java_pac) VALUES (2, 'date', 'LocalDate', 'java.time.LocalDate');
INSERT INTO `codegen_field_type` (id, column_type, column_java_type, column_java_pac) VALUES (3, 'tinyint', 'Integer', NULL);
INSERT INTO `codegen_field_type` (id, column_type, column_java_type, column_java_pac) VALUES (4, 'smallint', 'Integer', NULL);
INSERT INTO `codegen_field_type` (id, column_type, column_java_type, column_java_pac) VALUES (5, 'mediumint', 'Integer', NULL);
INSERT INTO `codegen_field_type` (id, column_type, column_java_type, column_java_pac) VALUES (6, 'int', 'Integer', NULL);
INSERT INTO `codegen_field_type` (id, column_type, column_java_type, column_java_pac) VALUES (7, 'integer', 'Integer', NULL);
INSERT INTO `codegen_field_type` (id, column_type, column_java_type, column_java_pac) VALUES (8, 'bigint', 'Long', NULL);
INSERT INTO `codegen_field_type` (id, column_type, column_java_type, column_java_pac) VALUES (9, 'float', 'Float', NULL);
INSERT INTO `codegen_field_type` (id, column_type, column_java_type, column_java_pac) VALUES (10, 'double', 'Double', NULL);
INSERT INTO `codegen_field_type` (id, column_type, column_java_type, column_java_pac) VALUES (11, 'decimal', 'BigDecimal', 'java.math.BigDecimal');
INSERT INTO `codegen_field_type` (id, column_type, column_java_type, column_java_pac) VALUES (12, 'bit', 'Boolean', NULL);
INSERT INTO `codegen_field_type` (id, column_type, column_java_type, column_java_pac) VALUES (13, 'char', 'String', NULL);
INSERT INTO `codegen_field_type` (id, column_type, column_java_type, column_java_pac) VALUES (14, 'varchar', 'String', NULL);
INSERT INTO `codegen_field_type` (id, column_type, column_java_type, column_java_pac) VALUES (15, 'tinytext', 'String', NULL);
INSERT INTO `codegen_field_type` (id, column_type, column_java_type, column_java_pac) VALUES (16, 'text', 'String', NULL);
INSERT INTO `codegen_field_type` (id, column_type, column_java_type, column_java_pac) VALUES (17, 'mediumtext', 'String', NULL);
INSERT INTO `codegen_field_type` (id, column_type, column_java_type, column_java_pac) VALUES (18, 'longtext', 'String', NULL);
INSERT INTO `codegen_field_type` (id, column_type, column_java_type, column_java_pac) VALUES (19, 'timestamp', 'LocalDateTime', 'java.time.LocalDateTime');
INSERT INTO `codegen_field_type` (id, column_type, column_java_type, column_java_pac) VALUES (20, 'NUMBER', 'Integer', NULL);
INSERT INTO `codegen_field_type` (id, column_type, column_java_type, column_java_pac) VALUES (21, 'BINARY_INTEGER', 'Integer', NULL);
INSERT INTO `codegen_field_type` (id, column_type, column_java_type, column_java_pac) VALUES (22, 'BINARY_FLOAT', 'Float', NULL);
INSERT INTO `codegen_field_type` (id, column_type, column_java_type, column_java_pac) VALUES (23, 'BINARY_DOUBLE', 'Double', NULL);
INSERT INTO `codegen_field_type` (id, column_type, column_java_type, column_java_pac) VALUES (24, 'VARCHAR2', 'String', NULL);
INSERT INTO `codegen_field_type` (id, column_type, column_java_type, column_java_pac) VALUES (25, 'NVARCHAR', 'String', NULL);
INSERT INTO `codegen_field_type` (id, column_type, column_java_type, column_java_pac) VALUES (26, 'NVARCHAR2', 'String', NULL);
INSERT INTO `codegen_field_type` (id, column_type, column_java_type, column_java_pac) VALUES (27, 'CLOB', 'String', NULL);
INSERT INTO `codegen_field_type` (id, column_type, column_java_type, column_java_pac) VALUES (28, 'int8', 'Long', NULL);
INSERT INTO `codegen_field_type` (id, column_type, column_java_type, column_java_pac) VALUES (29, 'int4', 'Integer', NULL);
INSERT INTO `codegen_field_type` (id, column_type, column_java_type, column_java_pac) VALUES (30, 'int2', 'Integer', NULL);
INSERT INTO `codegen_field_type` (id, column_type, column_java_type, column_java_pac) VALUES (31, 'numeric', 'BigDecimal', 'java.math.BigDecimal');
