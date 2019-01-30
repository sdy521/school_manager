/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50635
Source Host           : localhost:3306
Source Database       : study

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2019-01-30 14:42:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for info
-- ----------------------------
DROP TABLE IF EXISTS `info`;
CREATE TABLE `info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL COMMENT '用户id',
  `sex` int(11) DEFAULT NULL COMMENT '性别(1男 0女)',
  `type` int(11) DEFAULT NULL COMMENT '类别(1老师 2学生)',
  `address` varchar(255) DEFAULT NULL COMMENT '住址',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` tinyint(1) DEFAULT NULL COMMENT '是否被删除',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of info
-- ----------------------------
INSERT INTO `info` VALUES ('1', '2', '0', '1', '常州市新北区', '15961170771', '2019-01-22 15:50:20', '2019-01-30 09:41:32', '0', '22');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `name` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  `code` varchar(255) NOT NULL,
  `deleted` tinyint(4) NOT NULL DEFAULT '0',
  `pcode` varchar(255) NOT NULL DEFAULT '',
  `type` int(4) NOT NULL DEFAULT '0' COMMENT '0：管理员1老师2学生',
  `icon` varchar(255) NOT NULL DEFAULT '' COMMENT '图标',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '2018-12-26 20:51:03', '2018-12-29 16:28:17', '教师管理', '#', 'teacher', '0', '', '1', 'fa fa-tasks');
INSERT INTO `menu` VALUES ('2', '2018-12-26 20:54:32', '2019-01-02 10:59:17', '教师名单', '/teacher_nameList/list', 'teacher_nameList', '0', 'teacher', '0', 'fa fa-user');
INSERT INTO `menu` VALUES ('3', '2018-12-29 00:07:57', '2018-12-29 16:13:03', '系统设置', '#', 'systemSetup', '0', '', '0', 'fa fa-cogs');
INSERT INTO `menu` VALUES ('4', '2018-12-29 00:09:45', '2018-12-29 16:13:16', '菜单设置', '/menu/list', 'menuSetup', '0', 'systemSetup', '0', 'fa fa-credit-card-alt');
INSERT INTO `menu` VALUES ('5', '2018-12-29 11:03:56', '2018-12-29 16:11:58', '教师信息', '/teacher_info/list', 'teacherInfo', '0', 'teacher', '1', 'fa fa-newspaper-o');
INSERT INTO `menu` VALUES ('6', '2018-12-29 14:40:41', '2018-12-29 16:25:47', '教师课程', '#', 'teacherClass', '1', 'teacher', '0', 'fa fa-adjust');
INSERT INTO `menu` VALUES ('7', '2019-01-02 09:22:29', '2019-01-02 09:32:04', '权限管理', '/role/list', 'roleSetup', '0', 'systemSetup', '0', 'fa fa-key');
INSERT INTO `menu` VALUES ('8', '2019-01-23 17:00:50', '2019-01-23 17:00:50', '发送公告', '/send/list', 'send', '0', 'systemSetup', '0', 'fa fa-bullhorn');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `deleted` tinyint(1) DEFAULT NULL COMMENT '0未删除 1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('1', '广播测试', '<p><strong>第一条广播测试&nbsp; </strong>测试1</p>', '2019-01-25 17:54:05', '2019-01-25 17:54:05', '0');
INSERT INTO `notice` VALUES ('2', '儿童节放假', '<p>儿童节放假通知 3天</p>', '2019-01-25 17:58:43', '2019-01-25 17:58:43', '0');
INSERT INTO `notice` VALUES ('3', '团建', '<p>本周六统一去团建</p>', '2019-01-26 10:33:57', '2019-01-26 10:33:57', '0');
INSERT INTO `notice` VALUES ('4', '暑假放假通知', '<p>暑假放假从7月到8月，共1个月</p>', '2019-01-26 10:40:03', '2019-01-26 10:40:03', '0');
INSERT INTO `notice` VALUES ('5', '寒假放假通知', '<p>寒假放假通知寒假放假通知寒假放假通知寒假放假通知寒假放假通知寒假放假通知</p>', '2019-01-26 10:55:14', '2019-01-26 10:55:14', '0');
INSERT INTO `notice` VALUES ('6', '校庆', '<p>校庆校庆校庆校庆校庆校庆</p>', '2019-01-26 10:56:02', '2019-01-26 10:56:02', '0');
INSERT INTO `notice` VALUES ('7', '10周年校庆', '<p>10周年校庆10周年校庆10周年校庆10周年校庆10周年校庆</p>', '2019-01-26 10:59:33', '2019-01-26 10:59:33', '0');
INSERT INTO `notice` VALUES ('8', '毕业答辩', '<p>毕业答辩毕业答辩毕业答辩毕业答辩毕业答辩毕业答辩</p>', '2019-01-26 11:02:14', '2019-01-26 11:02:14', '0');
INSERT INTO `notice` VALUES ('9', '毕业论文', '<p>毕业论文毕业论文毕业论文毕业论文毕业论文毕业论文</p>', '2019-01-26 11:03:56', '2019-01-26 11:03:56', '0');
INSERT INTO `notice` VALUES ('10', '1212', '<p>1212121</p>', '2019-01-26 11:12:35', '2019-01-28 09:42:06', '1');
INSERT INTO `notice` VALUES ('11', '2233', '<p>2233</p>', '2019-01-26 11:12:49', '2019-01-28 09:42:02', '1');
INSERT INTO `notice` VALUES ('12', '666777', '<p>666777</p>', '2019-01-26 11:13:33', '2019-01-28 09:41:59', '1');
INSERT INTO `notice` VALUES ('13', '777', '<p>888</p>', '2019-01-26 11:14:11', '2019-01-28 09:41:56', '1');
INSERT INTO `notice` VALUES ('14', '123', '<p>123</p>', '2019-01-26 11:28:31', '2019-01-28 09:41:50', '1');
INSERT INTO `notice` VALUES ('15', '321', '<p>321</p>', '2019-01-26 11:29:15', '2019-01-28 09:41:47', '1');
INSERT INTO `notice` VALUES ('16', '321321', '<p>123123</p>', '2019-01-26 11:30:06', '2019-01-28 09:41:44', '1');
INSERT INTO `notice` VALUES ('17', '12332', '<p>32123</p>', '2019-01-26 11:30:28', '2019-01-28 09:41:41', '1');
INSERT INTO `notice` VALUES ('18', '6556', '<p>123</p>', '2019-01-26 11:30:50', '2019-01-28 09:41:20', '1');
INSERT INTO `notice` VALUES ('19', '1111', '<p>1111</p>', '2019-01-26 12:20:01', '2019-01-28 09:40:49', '1');
INSERT INTO `notice` VALUES ('20', '666', '<p>666</p>', '2019-01-26 12:21:02', '2019-01-29 09:43:53', '0');
INSERT INTO `notice` VALUES ('21', '888', '<p>888</p>', '2019-01-26 12:21:52', '2019-01-26 12:21:52', '0');
INSERT INTO `notice` VALUES ('22', '新年通知', '<p>祝大家新年快乐。</p>', '2019-01-30 14:39:44', '2019-01-30 14:39:44', '0');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `role` VALUES ('2', 'ROLE_TEACHER');
INSERT INTO `role` VALUES ('3', 'ROLE_STUDENT');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '教师名称',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL COMMENT '1:删除 0:未删除',
  `type` int(4) NOT NULL COMMENT '0:管理员1:老师2:学生',
  `enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '0:禁用1:可用',
  `img` varchar(255) DEFAULT NULL COMMENT '图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '$2a$10$onBleK8v36y2k55lW/Zybuo8oW/SVSkTie8nyWmmsmavVEUKHDhcW', '2018-12-20 13:52:47', '2019-01-30 11:17:39', '0', '0', '1', '71cff773-51e1-48a3-832c-30080db55ebf.jpg');
INSERT INTO `user` VALUES ('2', '李小晴', '$2a$10$5qc/k8HC14RyfFgOLzt56es./12ENozxIRAQGC9075pjArUwXxxrW', '2018-12-20 16:32:30', '2019-01-30 09:41:32', '0', '1', '1', null);
INSERT INTO `user` VALUES ('3', '孙红雷', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 12:46:52', '2019-01-02 11:14:15', '0', '1', '0', null);
INSERT INTO `user` VALUES ('4', '张梅', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 12:46:44', '2018-12-25 12:46:46', '0', '1', '1', null);
INSERT INTO `user` VALUES ('5', '赵四', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 12:46:54', '2018-12-25 12:46:56', '0', '1', '1', null);
INSERT INTO `user` VALUES ('6', '占号', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 11:22:20', '2018-12-25 11:22:20', '0', '1', '1', null);
INSERT INTO `user` VALUES ('7', '钱发', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 12:46:26', '2018-12-25 12:46:26', '0', '1', '1', null);
INSERT INTO `user` VALUES ('8', '吴刚', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 13:37:32', '2018-12-25 13:37:32', '0', '1', '1', null);
INSERT INTO `user` VALUES ('9', '周小', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 13:38:33', '2018-12-25 15:27:06', '0', '1', '1', null);
INSERT INTO `user` VALUES ('10', '郑雪', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 13:39:04', '2018-12-25 15:27:38', '0', '1', '1', null);
INSERT INTO `user` VALUES ('11', '王红', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 13:39:16', '2018-12-25 13:39:16', '1', '1', '1', null);
INSERT INTO `user` VALUES ('12', '梅雪', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 13:49:54', '2018-12-26 16:16:05', '0', '1', '1', null);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) DEFAULT NULL,
  `roles_id` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1');
INSERT INTO `user_role` VALUES ('2', '2', '2');
INSERT INTO `user_role` VALUES ('3', '3', '2');
INSERT INTO `user_role` VALUES ('4', '4', '2');
INSERT INTO `user_role` VALUES ('5', '5', '2');
INSERT INTO `user_role` VALUES ('6', '6', '2');
INSERT INTO `user_role` VALUES ('7', '7', '2');
INSERT INTO `user_role` VALUES ('8', '8', '2');
INSERT INTO `user_role` VALUES ('9', '9', '2');
INSERT INTO `user_role` VALUES ('10', '10', '2');
INSERT INTO `user_role` VALUES ('11', '11', '2');
