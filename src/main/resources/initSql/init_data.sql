-- ----------------------------
-- 连接数据库名： tms
-- 连接数据库用户： root
-- 连接数据库密码： root
-- ----------------------------

-- ----------------------------
-- Table structure for tb_menu_children
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu_children`;
CREATE TABLE `tb_menu_children` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `url` varchar(128) DEFAULT NULL,
  `parent_id` int(16) DEFAULT NULL,
  `num` int(2) DEFAULT NULL,
  `des` varchar(128) DEFAULT NULL,
  `state` int(1) DEFAULT NULL COMMENT '0 :  不可以删除 1 ： 可以删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_menu_children
-- ----------------------------
INSERT INTO `tb_menu_children` VALUES ('1', '任务调度', null, '1', '1', '', '2');
INSERT INTO `tb_menu_children` VALUES ('2', '任务统计', null, '1', '2', null, '2');
INSERT INTO `tb_menu_children` VALUES ('3', '用户管理', '/user/userList', '2', '1', null, '1');
INSERT INTO `tb_menu_children` VALUES ('4', '菜单管理', '/menu/menuList', '2', '2', null, '1');
INSERT INTO `tb_menu_children` VALUES ('6', '角色管理', '/role/roleList', '2', '4', null, '1');
INSERT INTO `tb_menu_children` VALUES ('7', '日志管理', '/log/logList', '2', '5', null, '2');
INSERT INTO `tb_menu_children` VALUES ('8', '收入记录', '/cost/paymentList', '3', '1', null, '2');
INSERT INTO `tb_menu_children` VALUES ('9', '支出记录', '/cost/spendList', '3', '2', null, '2');

-- ----------------------------
-- Table structure for tb_menu_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu_info`;
CREATE TABLE `tb_menu_info` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `num` int(2) DEFAULT NULL,
  `des` varchar(128) DEFAULT NULL,
  `state` int(1) DEFAULT NULL COMMENT '0 ： 不可以删除 1 ： 可以删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_menu_info
-- ----------------------------
INSERT INTO `tb_menu_info` VALUES ('1', '任务调度', '2', '', '2');
INSERT INTO `tb_menu_info` VALUES ('2', '系统管理', '3', '', '1');
INSERT INTO `tb_menu_info` VALUES ('3', '费用管理', '1', null, '2');

-- ----------------------------
-- Table structure for tb_paydetails_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_paydetails_record`;
CREATE TABLE `tb_paydetails_record` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(128) DEFAULT NULL,
  `paydetails_id` int(16) DEFAULT NULL,
  `customer_name` varchar(128) DEFAULT NULL,
  `contact` varchar(11) DEFAULT NULL,
  `payee` varchar(128) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `payment_time` timestamp NULL DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_paydetails_record
-- ----------------------------

-- ----------------------------
-- Table structure for tb_payment_details
-- ----------------------------
DROP TABLE IF EXISTS `tb_payment_details`;
CREATE TABLE `tb_payment_details` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `payment_id` int(16) DEFAULT NULL,
  `customer_name` varchar(128) DEFAULT NULL,
  `contact` varchar(11) DEFAULT NULL,
  `payee` varchar(128) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `payment_time` timestamp NULL DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_payment_details
-- ----------------------------

-- ----------------------------
-- Table structure for tb_payment_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_payment_info`;
CREATE TABLE `tb_payment_info` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(128) DEFAULT NULL,
  `contact` varchar(11) DEFAULT NULL,
  `payee` varchar(128) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `amounts` double DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `type` int(1) DEFAULT NULL COMMENT '1 : 现金 2：支付宝  3：微信  4：转账',
  `create_time` timestamp NULL DEFAULT NULL,
  `payment_time` timestamp NULL DEFAULT NULL,
  `details_des` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_payment_info
-- ----------------------------

-- ----------------------------
-- Table structure for tb_payment_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_payment_record`;
CREATE TABLE `tb_payment_record` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(128) DEFAULT NULL,
  `payment_id` int(16) DEFAULT NULL,
  `customer_name` varchar(128) DEFAULT NULL,
  `contact` varchar(11) DEFAULT NULL,
  `amounts` double DEFAULT NULL,
  `payee` varchar(128) DEFAULT NULL,
  `type` int(1) DEFAULT NULL COMMENT '1 : 现金 2：支付宝  3：微信  4：转账',
  `create_time` timestamp NULL DEFAULT NULL,
  `payment_time` timestamp NULL DEFAULT NULL,
  `details_des` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_payment_record
-- ----------------------------

-- ----------------------------
-- Table structure for tb_role_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_info`;
CREATE TABLE `tb_role_info` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(128) DEFAULT NULL,
  `is_super` int(1) DEFAULT NULL COMMENT '1：是\r\n0：不是',
  `des` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role_info
-- ----------------------------
INSERT INTO `tb_role_info` VALUES ('1', '系统管理员', '1', '第一个系统管理员');
INSERT INTO `tb_role_info` VALUES ('2', '普通用户', '0', '第一个普通用户');

-- ----------------------------
-- Table structure for tb_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_menu`;
CREATE TABLE `tb_role_menu` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `role_id` int(16) DEFAULT NULL,
  `menu_id` int(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role_menu
-- ----------------------------
INSERT INTO `tb_role_menu` VALUES ('1', '1', '1');
INSERT INTO `tb_role_menu` VALUES ('2', '1', '2');
INSERT INTO `tb_role_menu` VALUES ('3', '2', '1');
INSERT INTO `tb_role_menu` VALUES ('4', '1', '3');
INSERT INTO `tb_role_menu` VALUES ('5', '2', '3');

-- ----------------------------
-- Table structure for tb_role_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_user`;
CREATE TABLE `tb_role_user` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `role_id` int(16) DEFAULT NULL,
  `user_id` int(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role_user
-- ----------------------------
INSERT INTO `tb_role_user` VALUES ('1', '1', '1');
INSERT INTO `tb_role_user` VALUES ('3', '2', '7');
INSERT INTO `tb_role_user` VALUES ('9', '2', '42');
INSERT INTO `tb_role_user` VALUES ('10', '2', '43');
INSERT INTO `tb_role_user` VALUES ('11', '1', '44');
INSERT INTO `tb_role_user` VALUES ('13', '2', '46');
INSERT INTO `tb_role_user` VALUES ('15', '2', '48');
INSERT INTO `tb_role_user` VALUES ('19', '1', '50');
INSERT INTO `tb_role_user` VALUES ('20', '1', '51');
INSERT INTO `tb_role_user` VALUES ('21', '2', '52');
INSERT INTO `tb_role_user` VALUES ('22', '1', '53');
INSERT INTO `tb_role_user` VALUES ('23', '2', '54');
INSERT INTO `tb_role_user` VALUES ('27', '2', '6');

-- ----------------------------
-- Table structure for tb_spend_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_spend_info`;
CREATE TABLE `tb_spend_info` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `spend_name` varchar(128) DEFAULT NULL,
  `spend_matters` varchar(128) DEFAULT NULL,
  `amount` double(16,2) DEFAULT NULL,
  `type` int(1) DEFAULT NULL,
  `spend_Time` timestamp NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `remark` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_spend_info
-- ----------------------------

-- ----------------------------
-- Table structure for tb_spend_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_spend_record`;
CREATE TABLE `tb_spend_record` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(128) DEFAULT NULL,
  `spend_id` int(16) DEFAULT NULL,
  `spend_name` varchar(128) DEFAULT NULL,
  `spend_matters` varchar(128) DEFAULT NULL,
  `amount` double(16,2) DEFAULT NULL,
  `type` int(1) DEFAULT NULL,
  `spend_Time` timestamp NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `remark` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_spend_record
-- ----------------------------
INSERT INTO `tb_spend_record` VALUES ('11', 'admin', '9', '小许2', '买菜钱22', '28.89', '2', '2019-05-25 08:00:00', '2019-05-25 14:19:32', '省略。。。');
INSERT INTO `tb_spend_record` VALUES ('12', 'admin', '9', '小许2', '买菜钱22', '28.89', '4', '2019-05-25 08:00:00', '2019-05-25 14:19:42', '省略。。。');
INSERT INTO `tb_spend_record` VALUES ('13', 'admin', '9', '小许2', '买菜钱22', '28.89', '4', '2019-05-24 08:00:00', '2019-05-25 14:19:52', '省略。。。');
INSERT INTO `tb_spend_record` VALUES ('14', 'admin', '8', '韦道儒', '3333', '23.00', '3', '2019-05-15 08:00:00', '2019-05-26 15:50:54', '');
INSERT INTO `tb_spend_record` VALUES ('15', 'admin', '1', '韦道儒', '雪糕的钱86765', '12.00', '2', '2019-05-24 08:00:00', '2019-05-26 15:51:31', '此处省略');

-- ----------------------------
-- Table structure for tb_user_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_info`;
CREATE TABLE `tb_user_info` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `user_Name` varchar(32) DEFAULT NULL,
  `real_Name` varchar(32) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `tel` varchar(16) DEFAULT NULL,
  `des` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_info
-- ----------------------------
INSERT INTO `tb_user_info` VALUES ('1', 'admin', '小韦', '123456', '18276637756', '系统开发者');
INSERT INTO `tb_user_info` VALUES ('6', 'zhangsan', '张三', '1234567890', '18276637756', '省略');
INSERT INTO `tb_user_info` VALUES ('7', 'lisi2', '李四', '123456', '18276637', '');
INSERT INTO `tb_user_info` VALUES ('42', 'xiaozhang', '小张', '123456', '18276637756', '');
INSERT INTO `tb_user_info` VALUES ('43', 'xiaosi', '小四', '123456', '18276637756', '....');
INSERT INTO `tb_user_info` VALUES ('44', 'xiaowei', '小伟', '123456', '18276637756', '');
INSERT INTO `tb_user_info` VALUES ('46', 'xiaosan', '小三', '123456', '18276637756', '');
INSERT INTO `tb_user_info` VALUES ('48', 'xiaowu', '小五', '123456', '18276637756', '');
INSERT INTO `tb_user_info` VALUES ('50', 'xiaomin', '小敏', '123456', '18276637756', '');
INSERT INTO `tb_user_info` VALUES ('51', 'xiaoliu', '小六', '123456', '18276637756', '');
INSERT INTO `tb_user_info` VALUES ('52', 'xiaowang', '小王', '123456', '18276637756', '');
INSERT INTO `tb_user_info` VALUES ('53', 'xiaorong', '小荣', '123456', '18276637756', '');
INSERT INTO `tb_user_info` VALUES ('54', 'xiaoqi', '小七', '123456', '18276637756', '');