-- 计算结果主表
CREATE TABLE `t_project_cost_calculation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `project_id` varchar(50) DEFAULT NULL COMMENT '工程ID',
  `project_name` varchar(100) DEFAULT NULL COMMENT '工程名称',
  `start_time` varchar(50) DEFAULT NULL COMMENT '开始时间',
  `end_time` varchar(50) DEFAULT NULL COMMENT '结束时间',
  `total_amount` decimal(10,2) DEFAULT '0.00' COMMENT '总金额',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) DEFAULT NULL COMMENT '创建人',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标记（0正常，1删除）',
  PRIMARY KEY (`id`),
  KEY `idx_project_id` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工程费用计算结果表';

-- 计算结果明细表
CREATE TABLE `t_project_cost_calculation_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `calculation_id` int(11) DEFAULT NULL COMMENT '计算结果主表ID',
  `machine_type_id` int(11) DEFAULT NULL COMMENT '机具类型ID',
  `machine_type_name` varchar(100) DEFAULT NULL COMMENT '机具类型名称',
  `machine_model` varchar(100) DEFAULT NULL COMMENT '机具规格',
  `machine_unit` varchar(20) DEFAULT NULL COMMENT '物资单位',
  `price` decimal(10,2) DEFAULT '0.00' COMMENT '单价',
  `current_count` int(11) DEFAULT '0' COMMENT '当前数量',
  `amount` decimal(10,2) DEFAULT '0.00' COMMENT '金额',
  PRIMARY KEY (`id`),
  KEY `idx_calculation_id` (`calculation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工程费用计算结果明细表';

-- 计算结果时间段表
CREATE TABLE `t_project_cost_calculation_segment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `calculation_detail_id` int(11) DEFAULT NULL COMMENT '计算结果明细ID',
  `start_time` varchar(50) DEFAULT NULL COMMENT '开始时间',
  `end_time` varchar(50) DEFAULT NULL COMMENT '结束时间',
  `days` int(11) DEFAULT '0' COMMENT '天数',
  `count` int(11) DEFAULT '0' COMMENT '数量',
  `amount` decimal(10,2) DEFAULT '0.00' COMMENT '金额',
  PRIMARY KEY (`id`),
  KEY `idx_calculation_detail_id` (`calculation_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工程费用计算结果时间段表'; 