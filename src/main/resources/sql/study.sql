/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50635
Source Host           : localhost:3306
Source Database       : study

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2019-01-23 11:18:18
*/

SET FOREIGN_KEY_CHECKS=0;

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

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
INSERT INTO `user` VALUES ('1', 'admin', '$2a$10$onBleK8v36y2k55lW/Zybuo8oW/SVSkTie8nyWmmsmavVEUKHDhcW', '2018-12-20 13:52:47', '2019-01-22 16:27:32', '0', '0', '1', '00748db2-98d0-4972-8ba3-16ca7f77dc79.jpg');
INSERT INTO `user` VALUES ('2', '李老师', '$2a$10$PpXy0.e29/1SlPCVNjApquwaDbjsYe8CTrnCm9tyTt7D7X8ucjtbi', '2018-12-20 16:32:30', '2019-01-03 16:31:25', '0', '1', '1', 'cf511708-5901-4865-8cc4-ff9d55f2eeb7.jpg');
INSERT INTO `user` VALUES ('3', '孙老师', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 12:46:52', '2019-01-02 11:14:15', '0', '1', '0', null);
INSERT INTO `user` VALUES ('4', '张老师', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 12:46:44', '2018-12-25 12:46:46', '0', '1', '1', null);
INSERT INTO `user` VALUES ('5', '赵老师', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 12:46:54', '2018-12-25 12:46:56', '0', '1', '1', null);
INSERT INTO `user` VALUES ('6', '占老师', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 11:22:20', '2018-12-25 11:22:20', '0', '1', '1', null);
INSERT INTO `user` VALUES ('7', '钱老师', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 12:46:26', '2018-12-25 12:46:26', '0', '1', '1', null);
INSERT INTO `user` VALUES ('8', '吴老师', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 13:37:32', '2018-12-25 13:37:32', '0', '1', '1', null);
INSERT INTO `user` VALUES ('9', '周老师', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 13:38:33', '2018-12-25 15:27:06', '0', '1', '1', null);
INSERT INTO `user` VALUES ('10', '郑老师', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 13:39:04', '2018-12-25 15:27:38', '0', '1', '1', null);
INSERT INTO `user` VALUES ('11', '王老师', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 13:39:16', '2018-12-25 13:39:16', '1', '1', '1', null);
INSERT INTO `user` VALUES ('12', '梅老师', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 13:49:54', '2018-12-26 16:16:05', '0', '1', '1', null);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL COMMENT '用户id',
  `sex` int(11) DEFAULT NULL COMMENT '性别(1男 0女)',
  `type` int(11) DEFAULT NULL COMMENT '类别(1老师 2学生)',
  `address` varchar(255) DEFAULT NULL COMMENT '住址',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` tinyint(1) DEFAULT NULL COMMENT '是否被删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', '2', '0', '1', '江苏省常州市新北区', '15236326963', '2019-01-22 15:50:20', '2019-01-23 10:11:33', '0');

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
