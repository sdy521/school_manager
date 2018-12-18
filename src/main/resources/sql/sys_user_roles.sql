/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50635
Source Host           : localhost:3306
Source Database       : study

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2018-12-05 17:34:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_user_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_roles`;
CREATE TABLE `sys_user_roles` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `sys_user_id` bigint(11) DEFAULT NULL,
  `roles_id` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_roles
-- ----------------------------
INSERT INTO `sys_user_roles` VALUES ('1', '1', '1');
INSERT INTO `sys_user_roles` VALUES ('2', '2', '2');
