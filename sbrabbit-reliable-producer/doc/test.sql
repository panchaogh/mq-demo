CREATE TABLE `t_order` (
  `id` varchar(255) NOT NULL COMMENT '订单Id',
  `name` varchar(255) DEFAULT NULL COMMENT '订单名称',
  `MessageId` varchar(255) NOT NULL COMMENT '消息唯一Id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `broker_message_log` (
  `message_id` varchar(128) NOT NULL COMMENT '消息唯一Id',
  `message` varchar(4000) DEFAULT NULL COMMENT '消息内容',
  `try_count` int(4) DEFAULT '0' COMMENT '重试次数',
  `status` varchar(10) DEFAULT '' COMMENT '消息投递状态（0 投递中 1 投递成功 2 投递失败）',
  `next_retry` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下一次重试时间 或 超时时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

