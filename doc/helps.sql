/*
Navicat MySQL Data Transfer

Source Server         :  本地
Source Server Version : 50067
Source Host           : localhost:3306
Source Database       : helps

Target Server Type    : MYSQL
Target Server Version : 50067
File Encoding         : 65001

Date: 2016-12-21 16:32:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for activate_code
-- ----------------------------
DROP TABLE IF EXISTS `activate_code`;
CREATE TABLE `activate_code` (
  `id` bigint(20) NOT NULL auto_increment,
  `create_date` bigint(20) default NULL,
  `last_update` bigint(20) default NULL,
  `state` char(1) default NULL COMMENT 'N新建，D删除,U表示修改',
  `from_uid` bigint(20) default NULL COMMENT 'code_num',
  `to_uid` bigint(4) default NULL COMMENT '被赠送人的uid',
  `code_num` int(4) default NULL COMMENT '激活码的数量',
  `is_from_admin` int(4) default NULL COMMENT '是否管理员赠送',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activate_code
-- ----------------------------

-- ----------------------------
-- Table structure for dynamic_award_rules
-- ----------------------------
DROP TABLE IF EXISTS `dynamic_award_rules`;
CREATE TABLE `dynamic_award_rules` (
  `id` bigint(20) NOT NULL auto_increment,
  `create_date` bigint(20) default '0',
  `last_update` bigint(20) default '0',
  `state` char(1) default NULL COMMENT '数据状态	N新建，D删除,U表示修改',
  `dynamic_id` bigint(20) default NULL,
  `direct_num` int(4) default '0' COMMENT '直推人数',
  `team_num` int(4) default NULL COMMENT '团队人数',
  `one_generation` float(4,3) default '0.050' COMMENT '5%',
  `two_generation` float(4,3) default '0.030' COMMENT '3%',
  `three_generation` float(4,3) default NULL,
  `four_generation` float(4,3) default NULL,
  `user_title_id` bigint(20) default NULL,
  `user_title` varchar(32) default NULL,
  UNIQUE KEY `id` USING BTREE (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dynamic_award_rules
-- ----------------------------

-- ----------------------------
-- Table structure for id_generator
-- ----------------------------
DROP TABLE IF EXISTS `id_generator`;
CREATE TABLE `id_generator` (
  `id` bigint(20) NOT NULL auto_increment,
  `id_name` varchar(128) default NULL,
  `id_count` bigint(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of id_generator
-- ----------------------------
INSERT INTO `id_generator` VALUES ('1', 'user_id', '2');
INSERT INTO `id_generator` VALUES ('2', 'validate_id', '1');

-- Table structure for income_calcul_log
-- ----------------------------
DROP TABLE IF EXISTS `income_calcul_log`;
CREATE TABLE `income_calcul_log` (
  `id` bigint(20) NOT NULL auto_increment,
  `create_date` bigint(20) default NULL,
  `last_update` bigint(20) default NULL,
  `income_id` bigint(20) default NULL COMMENT '收入id',
  `income_type` int(4) default NULL COMMENT '收入类型',
  `user_id` bigint(20) default NULL COMMENT '用户id',
  `money_num` float(4,0) default NULL COMMENT '收益金额',
  `org_money_num` float(4,0) default NULL,
  `income_explain` varchar(256) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of income_calcul_log
-- ----------------------------

-- ----------------------------
-- Table structure for leaving_msg
-- ----------------------------
DROP TABLE IF EXISTS `leaving_msg`;
CREATE TABLE `leaving_msg` (
  `id` bigint(20) NOT NULL auto_increment,
  `create_date` bigint(20) default NULL,
  `last_update` bigint(20) default NULL,
  `leaving_id` bigint(20) default NULL COMMENT '留言id',
  `user_id` bigint(20) default NULL COMMENT '用户id',
  `msg_content` varchar(512) default NULL COMMENT '留言内容',
  `reply_content` varchar(512) default NULL,
  `is_reply` int(4) default '0' COMMENT '是否已经回复',
  `msg_date` timestamp NULL default NULL,
  `reply_date` timestamp NULL default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of leaving_msg
-- ----------------------------

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` bigint(20) NOT NULL auto_increment,
  `create_date` bigint(20) default NULL,
  `last_update` bigint(20) default NULL,
  `state` char(1) default '' COMMENT '数据状态',
  `new_id` bigint(20) default NULL COMMENT '新闻id',
  `new_title` varchar(32) default NULL COMMENT '新闻标题',
  `new_content` varchar(512) default NULL COMMENT '新闻内容',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news
-- ----------------------------

-- ----------------------------
-- Table structure for offer_help
-- ----------------------------
DROP TABLE IF EXISTS `offer_help`;
CREATE TABLE `offer_help` (
  `id` bigint(20) NOT NULL auto_increment,
  `create_date` bigint(20) default NULL,
  `last_update` bigint(20) default NULL,
  `state` char(1) default NULL,
  `help_id` bigint(20) default NULL COMMENT '帮助id',
  `help_order` varchar(32) default NULL COMMENT '订单编号 买S001，卖R001',
  `help_type` int(4) default '0' COMMENT '帮助类型 0，提供帮忙单1，接受帮助',
  `user_id` bigint(20) default NULL,
  `user_phone` varchar(32) default NULL,
  `payment_type` varchar(32) default NULL COMMENT '0，银行，1支付宝，2微信',
  `help_status` int(4) default NULL COMMENT '帮助订单状态',
  `status_confirmation` int(4) default NULL COMMENT '确认状态',
  `money_num` float(20,0) default NULL COMMENT '订单金额',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of offer_help
-- ----------------------------

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL auto_increment,
  `create_date` bigint(20) default NULL,
  `last_update` bigint(20) default NULL,
  `state` char(1) default NULL,
  `order_id` bigint(20) default NULL,
  `order_type` int(4) default NULL COMMENT '0交易中的订单1，完成订单 2，未打款 ，3 未收到款',
  `recharge_order` varchar(32) default NULL COMMENT '充值订单编号',
  `recharge_phone` varchar(32) default NULL COMMENT '充值用户手机',
  `recharge_uid` bigint(20) default NULL,
  `withdrawals_order` varchar(32) default NULL COMMENT '提现订单编号',
  `withdrawals_phone` varchar(32) default NULL COMMENT '提现订单的手机号',
  `withdrawals_uid` bigint(20) default NULL COMMENT '提现订单的用户id',
  `money_num` int(4) default NULL COMMENT '订单金额',
  `complaint_status` int(4) default '0' COMMENT '投诉状态 0正常 1未打款 2虚假打款',
  `remittance_url` varchar(256) default NULL COMMENT '汇款截图url',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------

-- ----------------------------
-- Table structure for order_setting
-- ----------------------------
DROP TABLE IF EXISTS `order_setting`;
CREATE TABLE `order_setting` (
  `id` bigint(20) NOT NULL auto_increment,
  `create_date` bigint(20) default NULL,
  `last_update` bigint(20) default NULL,
  `state` char(1) default NULL,
  `order_setting_id` bigint(20) default NULL COMMENT '订单设置id',
  `is_order_timer` int(4) default NULL COMMENT '排单时间限制是否启用',
  `start_date` datetime default NULL COMMENT '开始时间',
  `end_date` datetime default NULL COMMENT '结束时间',
  `order_max_money` float(20,3) default NULL COMMENT '当日最大的排单金额',
  `match_date_min` int(4) default '3' COMMENT '匹配期最小值',
  `match_date_max` int(4) default '7' COMMENT '匹配期最大值',
  `freezing_time` int(4) default '168',
  `min_order_amount` int(4) default NULL COMMENT '订单最小金额,额度必现是100的倍数',
  `max_order_num` int(4) default '1' COMMENT '每个最大的排单量',
  `static_min_money` int(4) default '500' COMMENT '静态提现最小金额',
  `static_times_money` int(4) default '100' COMMENT '静态提现倍数',
  `dynamic_min_money` int(4) default '500' COMMENT '动态提现最小金额',
  `dynamic_times_money` int(4) default '100' COMMENT '动态提现倍数',
  `dynamic_max_money` int(4) default '30000' COMMENT '动态最大提现金额',
  `dynamic_deduct_ proportion` float(4,3) default '0.010' COMMENT '动态提现扣除比例',
  `interest_not_paid` float(4,3) default '0.010' COMMENT '未打款利息',
  `interest_paid` float(4,3) default '0.010' COMMENT '已打款利息',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_setting
-- ----------------------------

-- ----------------------------
-- Table structure for rotate_news
-- ----------------------------
DROP TABLE IF EXISTS `rotate_news`;
CREATE TABLE `rotate_news` (
  `id` bigint(20) NOT NULL auto_increment,
  `create_date` bigint(20) default NULL,
  `last_update` bigint(20) default NULL,
  `state` char(1) default NULL,
  `rotate_id` bigint(20) default NULL,
  `rotate_url` varchar(256) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rotate_news
-- ----------------------------

-- ----------------------------
-- Table structure for user_admin
-- ----------------------------
DROP TABLE IF EXISTS `user_admin`;
CREATE TABLE `user_admin` (
  `id` bigint(20) NOT NULL auto_increment COMMENT '自增主键',
  `state` char(1) default NULL,
  `admin_name` varchar(32) default NULL COMMENT '管理员姓名',
  `admin_pwd` varchar(32) default NULL COMMENT '管理密码',
  `create_date` bigint(20) default NULL,
  `last_update` bigint(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_admin
-- ----------------------------

-- ----------------------------
-- Table structure for user_member
-- ----------------------------
DROP TABLE IF EXISTS `user_member`;
CREATE TABLE `user_member` (
  `id` bigint(20) NOT NULL auto_increment COMMENT '自增主键',
  `create_date` bigint(20) default NULL,
  `last_update` bigint(20) default NULL,
  `state` char(1) default '' COMMENT 'N新建，D删除,U表示修改',
  `user_name` varchar(32) default '' COMMENT '用户姓名',
  `user_login_pwd` varchar(32) default '' COMMENT '登陆密码',
  `user_modify_pwd` varchar(32) default '' COMMENT 'user_modify_pwd',
  `user_head_url` varchar(256) default '' COMMENT '用户头像图片URL',
  `user_id` bigint(20) default NULL COMMENT '用户头像',
  `is_activate` int(4) default '0' COMMENT '是否激活',
  `is_freeze` int(4) default '0',
  `user_phone` varchar(32) default NULL COMMENT '用户手机',
  `user_ referee_phone` varchar(32) default NULL COMMENT '推荐人手机号',
  `referee_user_id` bigint(20) default NULL,
  `user_ carded` varchar(32) default NULL COMMENT '用户身份证号',
  `user_ carded_url` varchar(256) default NULL COMMENT '用户身份证图片URL',
  `user_qq` varchar(32) default NULL COMMENT 'qq',
  `user_bank_name` varchar(32) default NULL COMMENT '用户银行名称',
  `user_bank_account` varchar(32) default NULL COMMENT '用户银行帐号',
  `user_ payment` varchar(128) default NULL COMMENT '支付宝账号',
  `user_ weixin` varchar(128) default NULL COMMENT '用户微信',
  `ustatic_wallet` float(32,3) default NULL COMMENT '用户静态钱包',
  `udynamic_wallet` float(32,3) default NULL COMMENT '动态钱包',
  `ufrozen_wallet` float(32,3) default NULL COMMENT '冻结钱包',
  `usable_code_num` int(4) default '0' COMMENT '可用激活码的数量',
  `used_code_num` int(4) default '0' COMMENT '已用激活码的数量',
  `title_id` int(4) default '0' COMMENT '头衔等级',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_member
-- ----------------------------
-- ----------------------------
-- Table structure for validate_code
-- ----------------------------
DROP TABLE IF EXISTS `validate_code`;
CREATE TABLE `validate_code` (
  `id` bigint(20) NOT NULL auto_increment,
  `create_date` bigint(20) default NULL,
  `last_update` bigint(20) default NULL,
  `state` char(1) default NULL,
  `validate_id` bigint(20) default NULL,
  `user_phone` varchar(32) default NULL,
  `user_imei` varchar(256) default NULL,
  `validate_type` int(4) default NULL,
  `validate_code` varchar(16) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of validate_code