/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : study

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-12-26 23:25:36
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
  `pcode` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '2018-12-26 20:51:03', '2018-12-26 20:51:07', '教师管理', '#', 'teacher', '0', '');
INSERT INTO `menu` VALUES ('2', '2018-12-26 20:54:32', '2018-12-26 20:54:35', '教师名单', '/teacher_nameList/list', 'teacher_nameList', '0', 'teacher');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `role` VALUES ('2', 'ROLE_USER');

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'root', '$2a$10$kGH66YEK6P2UC2Kg/PsYBuQr27/lOLHfAPr3f6Yh0ZCSMXw68wDxS', '2018-12-20 13:52:47', '2018-12-20 13:52:49', '0', '0', '1');
INSERT INTO `user` VALUES ('2', '李老师', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-20 16:32:30', '2018-12-20 16:32:32', '0', '1', '1');
INSERT INTO `user` VALUES ('3', '孙老师', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 12:46:52', '2018-12-25 12:46:49', '0', '1', '0');
INSERT INTO `user` VALUES ('4', '张老师', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 12:46:44', '2018-12-25 12:46:46', '0', '1', '1');
INSERT INTO `user` VALUES ('5', '赵老师', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 12:46:54', '2018-12-25 12:46:56', '0', '1', '1');
INSERT INTO `user` VALUES ('6', '占老师', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 11:22:20', '2018-12-25 11:22:20', '0', '1', '1');
INSERT INTO `user` VALUES ('7', '钱老师', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 12:46:26', '2018-12-25 12:46:26', '0', '1', '1');
INSERT INTO `user` VALUES ('8', '吴老师', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 13:37:32', '2018-12-25 13:37:32', '0', '1', '1');
INSERT INTO `user` VALUES ('9', '周老师', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 13:38:33', '2018-12-25 15:27:06', '0', '1', '1');
INSERT INTO `user` VALUES ('10', '郑老师', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 13:39:04', '2018-12-25 15:27:38', '0', '1', '1');
INSERT INTO `user` VALUES ('11', '王老师', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 13:39:16', '2018-12-25 13:39:16', '1', '1', '1');
INSERT INTO `user` VALUES ('12', '梅老师', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 13:49:54', '2018-12-26 16:16:05', '0', '1', '1');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) DEFAULT NULL,
  `roles_id` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1');
INSERT INTO `user_role` VALUES ('2', '2', '2');
