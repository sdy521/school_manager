/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : school_manager

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-06-14 16:36:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '班级名称',
  `deleted` tinyint(1) DEFAULT NULL COMMENT '1删除0未删除',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL COMMENT '创建人id',
  `update_user` int(11) DEFAULT NULL COMMENT '修改人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES ('1', '测试班级', '0', '2019-02-26 14:43:08', '2019-02-26 15:37:04', '1', '1');
INSERT INTO `class` VALUES ('2', '测试删除', '1', '2019-02-26 15:37:21', '2019-02-26 15:38:40', '1', '1');

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '文件名称',
  `path` varchar(255) DEFAULT NULL COMMENT '文件地址',
  `code` varchar(255) DEFAULT NULL,
  `pcode` varchar(255) DEFAULT NULL COMMENT '父类为code',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `type` int(11) DEFAULT NULL COMMENT '0:文件夹 1:文件',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES ('1', 'excel', null, 'fa5d03c2-a97e-4159-be3c-a56f8bea6551', null, '2019-04-29 16:16:36', '2019-04-29 16:16:36', '0', null, '0');
INSERT INTO `file` VALUES ('2', '20190429-InputPhoneModel - 副本.xls', '/home/aubin/study_manager/filedesk/20190429-InputPhoneModel - 副本.xls', '9c65a49a-9f4f-4d1e-be7d-fe1ab7c3219a', 'fa5d03c2-a97e-4159-be3c-a56f8bea6551', '2019-04-29 16:16:46', '2019-04-29 16:22:19', '1', 'fa fa-file', '1');
INSERT INTO `file` VALUES ('3', '20190429-20190428-InputPhoneModel - 副本.xls', '/home/aubin/study_manager/filedesk/20190429-20190428-InputPhoneModel - 副本.xls', 'f07ede01-33fa-4355-99b7-61ba1220fd1c', 'fa5d03c2-a97e-4159-be3c-a56f8bea6551', '2019-04-29 16:22:29', '2019-04-29 16:29:16', '1', 'fa fa-file', '1');
INSERT INTO `file` VALUES ('4', '123.xls', '/home/aubin/study_manager/filedesk/123.xls', '4c8b6def-b637-4f19-80df-3d7b57e938e9', 'fa5d03c2-a97e-4159-be3c-a56f8bea6551', '2019-04-29 16:30:31', '2019-04-29 17:01:20', '0', 'fa fa-file', '1');
INSERT INTO `file` VALUES ('5', '123.xls', '/home/aubin/study_manager/filedesk/123.xls', '765fa3b0-ee8b-4aef-b30c-96ec2ba7b709', 'fa5d03c2-a97e-4159-be3c-a56f8bea6551', '2019-04-29 16:37:27', '2019-04-29 16:49:38', '1', 'fa fa-file', '1');
INSERT INTO `file` VALUES ('6', '123.xls', '/home/aubin/study_manager/filedesk/123.xls', '9840586b-c867-4ede-aa3e-14e0421f38d3', 'fa5d03c2-a97e-4159-be3c-a56f8bea6551', '2019-04-29 16:56:00', '2019-04-29 16:56:50', '1', 'fa fa-file', '1');

-- ----------------------------
-- Table structure for info
-- ----------------------------
DROP TABLE IF EXISTS `info`;
CREATE TABLE `info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL COMMENT '用户id',
  `sex` int(11) DEFAULT NULL COMMENT '性别(1男 0女)',
  `address` varchar(255) DEFAULT NULL COMMENT '住址',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` tinyint(1) DEFAULT NULL COMMENT '是否被删除',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of info
-- ----------------------------
INSERT INTO `info` VALUES ('1', '2', '0', '江苏省常州市', '15961170771', '2019-01-22 15:50:20', '2019-04-09 10:15:09', '0', '22');
INSERT INTO `info` VALUES ('2', '10', '0', '上海市', '15961170770', '2019-02-19 09:54:30', '2019-02-19 15:40:48', '0', '20');
INSERT INTO `info` VALUES ('5', '1', '1', '江苏省常州市', '15961170770', '2019-02-19 10:15:58', '2019-02-19 10:20:33', '0', '22');
INSERT INTO `info` VALUES ('8', '9', '0', '江苏省南京市', '15961170771', '2019-02-19 10:34:01', '2019-02-19 10:56:13', '1', '25');
INSERT INTO `info` VALUES ('9', '25', '0', '121212', '15961170771', '2019-02-19 17:13:27', '2019-02-19 17:17:16', '1', '12');
INSERT INTO `info` VALUES ('10', '26', '1', '江苏省常州市', '15961170770', '2019-02-21 17:13:50', '2019-02-21 17:13:50', '0', '22');
INSERT INTO `info` VALUES ('11', '12', '0', '江苏南京', '15263236963', '2019-04-18 14:57:41', '2019-04-18 14:57:41', '0', '22');
INSERT INTO `info` VALUES ('12', '34', '0', '江苏常州', '15961170771', '2019-06-04 16:28:30', '2019-06-04 16:28:30', '0', '20');

-- ----------------------------
-- Table structure for linux_monitor
-- ----------------------------
DROP TABLE IF EXISTS `linux_monitor`;
CREATE TABLE `linux_monitor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(255) DEFAULT NULL COMMENT 'ip地址',
  `port` varchar(255) DEFAULT NULL COMMENT '端口号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='linux系统配置表';

-- ----------------------------
-- Records of linux_monitor
-- ----------------------------
INSERT INTO `linux_monitor` VALUES ('1', '192.168.1.85', '8081');

-- ----------------------------
-- Table structure for login_log
-- ----------------------------
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `user_type` varchar(255) DEFAULT NULL COMMENT '用户类型（1老师2学生）',
  `ip` varchar(255) DEFAULT NULL COMMENT 'ip地址',
  `msg` varchar(255) DEFAULT NULL COMMENT '操作说明',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of login_log
-- ----------------------------
INSERT INTO `login_log` VALUES ('1', 'sdy', '0', '0:0:0:0:0:0:0:1', '登陆成功', '2019-05-15 13:52:26');
INSERT INTO `login_log` VALUES ('2', 'sdy', '0', '0:0:0:0:0:0:0:1', '登出成功', '2019-05-15 13:52:50');
INSERT INTO `login_log` VALUES ('3', '李小晴', '1', '0:0:0:0:0:0:0:1', '登陆成功', '2019-05-15 13:53:00');
INSERT INTO `login_log` VALUES ('4', '李小晴', '1', '0:0:0:0:0:0:0:1', '登出成功', '2019-05-15 13:53:06');
INSERT INTO `login_log` VALUES ('5', 'sdy', '0', '0:0:0:0:0:0:0:1', '登陆成功', '2019-05-15 13:53:14');
INSERT INTO `login_log` VALUES ('6', 'sdy', '0', '0:0:0:0:0:0:0:1', '登出成功', '2019-05-15 13:59:56');
INSERT INTO `login_log` VALUES ('7', 'sdy', '0', '0:0:0:0:0:0:0:1', '登陆成功', '2019-05-15 13:59:57');
INSERT INTO `login_log` VALUES ('8', 'sdy', '0', '0:0:0:0:0:0:0:1', '登陆成功', '2019-05-15 14:09:48');
INSERT INTO `login_log` VALUES ('9', 'sdy', '0', '0:0:0:0:0:0:0:1', '登出成功', '2019-05-15 14:13:15');
INSERT INTO `login_log` VALUES ('10', 'sdy', '0', '0:0:0:0:0:0:0:1', '登陆成功', '2019-05-15 14:13:16');
INSERT INTO `login_log` VALUES ('11', 'sdy', '0', '0:0:0:0:0:0:0:1', '登陆成功', '2019-05-15 14:13:45');
INSERT INTO `login_log` VALUES ('12', 'sdy', '0', '0:0:0:0:0:0:0:1', '登陆成功', '2019-06-04 14:32:50');
INSERT INTO `login_log` VALUES ('13', 'sdy', '0', '0:0:0:0:0:0:0:1', '登陆成功', '2019-06-04 15:34:22');
INSERT INTO `login_log` VALUES ('14', 'sdy', '0', '0:0:0:0:0:0:0:1', '登陆成功', '2019-06-04 15:42:58');
INSERT INTO `login_log` VALUES ('15', 'sdy', '0', '0:0:0:0:0:0:0:1', '登出成功', '2019-06-04 15:44:43');
INSERT INTO `login_log` VALUES ('16', '李小晴', '1', '0:0:0:0:0:0:0:1', '登陆成功', '2019-06-04 15:44:52');
INSERT INTO `login_log` VALUES ('17', '李小晴', '1', '0:0:0:0:0:0:0:1', '登出成功', '2019-06-04 15:45:03');
INSERT INTO `login_log` VALUES ('18', 'sdy', '0', '0:0:0:0:0:0:0:1', '登陆成功', '2019-06-04 15:45:05');
INSERT INTO `login_log` VALUES ('19', 'sdy', '0', '0:0:0:0:0:0:0:1', '登出成功', '2019-06-04 15:45:14');
INSERT INTO `login_log` VALUES ('20', '张学生', '2', '0:0:0:0:0:0:0:1', '登陆成功', '2019-06-04 15:45:22');
INSERT INTO `login_log` VALUES ('21', '张学生', '2', '0:0:0:0:0:0:0:1', '登出成功', '2019-06-04 15:45:27');
INSERT INTO `login_log` VALUES ('22', 'sdy', '0', '0:0:0:0:0:0:0:1', '登陆成功', '2019-06-04 15:45:28');
INSERT INTO `login_log` VALUES ('23', 'sdy', '0', '0:0:0:0:0:0:0:1', '登出成功', '2019-06-04 16:06:48');
INSERT INTO `login_log` VALUES ('24', 'sdy', '1', '0:0:0:0:0:0:0:1', '登陆成功', '2019-06-04 16:17:06');
INSERT INTO `login_log` VALUES ('25', 'sdy', '1', '0:0:0:0:0:0:0:1', '登出成功', '2019-06-04 16:17:10');
INSERT INTO `login_log` VALUES ('26', 'sdy', '1', '0:0:0:0:0:0:0:1', '登陆成功', '2019-06-04 16:17:19');
INSERT INTO `login_log` VALUES ('27', 'sdy', '1', '0:0:0:0:0:0:0:1', '登出成功', '2019-06-04 16:17:33');
INSERT INTO `login_log` VALUES ('28', 'sdy', '1', '0:0:0:0:0:0:0:1', '登陆成功', '2019-06-04 16:17:49');
INSERT INTO `login_log` VALUES ('29', 'sdy', '1', '0:0:0:0:0:0:0:1', '登出成功', '2019-06-04 16:17:59');
INSERT INTO `login_log` VALUES ('30', 'sdy', '1', '0:0:0:0:0:0:0:1', '登陆成功', '2019-06-04 16:20:19');
INSERT INTO `login_log` VALUES ('31', 'sdy', '1', '0:0:0:0:0:0:0:1', '登出成功', '2019-06-04 16:25:14');
INSERT INTO `login_log` VALUES ('32', 'sdy', '0', '0:0:0:0:0:0:0:1', '登陆成功', '2019-06-04 16:25:15');
INSERT INTO `login_log` VALUES ('33', 'sdy', '0', '0:0:0:0:0:0:0:1', '登出成功', '2019-06-04 16:27:47');
INSERT INTO `login_log` VALUES ('34', '张好', '2', '0:0:0:0:0:0:0:1', '登陆成功', '2019-06-04 16:28:02');
INSERT INTO `login_log` VALUES ('35', '张好', '2', '0:0:0:0:0:0:0:1', '登陆成功', '2019-06-04 16:28:34');
INSERT INTO `login_log` VALUES ('36', '张好', '2', '0:0:0:0:0:0:0:1', '登出成功', '2019-06-04 16:28:44');
INSERT INTO `login_log` VALUES ('37', 'sdy', '0', '0:0:0:0:0:0:0:1', '登陆成功', '2019-06-04 16:28:45');
INSERT INTO `login_log` VALUES ('38', 'sdy', '0', '0:0:0:0:0:0:0:1', '登出成功', '2019-06-04 16:29:37');
INSERT INTO `login_log` VALUES ('39', '张好', '2', '0:0:0:0:0:0:0:1', '登陆成功', '2019-06-04 16:29:47');
INSERT INTO `login_log` VALUES ('40', '张好', '2', '0:0:0:0:0:0:0:1', '登出成功', '2019-06-04 16:29:59');
INSERT INTO `login_log` VALUES ('41', '李小晴', '1', '0:0:0:0:0:0:0:1', '登陆成功', '2019-06-04 16:30:20');
INSERT INTO `login_log` VALUES ('42', '李小晴', '1', '0:0:0:0:0:0:0:1', '登出成功', '2019-06-04 16:30:27');
INSERT INTO `login_log` VALUES ('43', '李小晴', '1', '0:0:0:0:0:0:0:1', '登陆成功', '2019-06-04 16:30:28');
INSERT INTO `login_log` VALUES ('44', '李小晴', '1', '0:0:0:0:0:0:0:1', '登出成功', '2019-06-04 16:30:35');
INSERT INTO `login_log` VALUES ('45', 'sdy', '0', '0:0:0:0:0:0:0:1', '登陆成功', '2019-06-04 16:30:39');
INSERT INTO `login_log` VALUES ('46', '张好', '2', '0:0:0:0:0:0:0:1', '登陆成功', '2019-06-04 16:38:22');
INSERT INTO `login_log` VALUES ('47', '张好', '2', '0:0:0:0:0:0:0:1', '登出成功', '2019-06-04 16:38:32');
INSERT INTO `login_log` VALUES ('48', '李小晴', '1', '0:0:0:0:0:0:0:1', '登陆成功', '2019-06-04 16:38:35');
INSERT INTO `login_log` VALUES ('49', '李小晴', '1', '0:0:0:0:0:0:0:1', '登出成功', '2019-06-04 16:38:42');
INSERT INTO `login_log` VALUES ('50', '李小晴', '1', '0:0:0:0:0:0:0:1', '登陆成功', '2019-06-04 16:38:44');
INSERT INTO `login_log` VALUES ('51', '李小晴', '1', '0:0:0:0:0:0:0:1', '登出成功', '2019-06-04 16:38:52');
INSERT INTO `login_log` VALUES ('52', 'sdy', '0', '0:0:0:0:0:0:0:1', '登陆成功', '2019-06-04 16:38:56');
INSERT INTO `login_log` VALUES ('53', 'sdy', '0', '0:0:0:0:0:0:0:1', '登陆成功', '2019-06-14 14:08:25');
INSERT INTO `login_log` VALUES ('54', 'sdy', '0', '0:0:0:0:0:0:0:1', '登陆成功', '2019-06-14 14:38:20');
INSERT INTO `login_log` VALUES ('55', 'sdy', '0', '0:0:0:0:0:0:0:1', '登出成功', '2019-06-14 14:41:25');
INSERT INTO `login_log` VALUES ('56', 'sdy', '0', '0:0:0:0:0:0:0:1', '登陆成功', '2019-06-14 14:41:26');
INSERT INTO `login_log` VALUES ('57', 'sdy', '0', '0:0:0:0:0:0:0:1', '登陆成功', '2019-06-14 15:07:39');
INSERT INTO `login_log` VALUES ('58', 'sdy', '0', '0:0:0:0:0:0:0:1', '登陆成功', '2019-06-14 15:31:24');
INSERT INTO `login_log` VALUES ('59', '李小晴', '1', '0:0:0:0:0:0:0:1', '登陆成功', '2019-06-14 15:53:21');
INSERT INTO `login_log` VALUES ('60', 'sdy', '0', '0:0:0:0:0:0:0:1', '登陆成功', '2019-06-14 16:31:18');
INSERT INTO `login_log` VALUES ('61', '李小晴', '1', '0:0:0:0:0:0:0:1', '登陆成功', '2019-06-14 16:31:52');

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
  `sort` int(5) NOT NULL DEFAULT '1' COMMENT '显示顺序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '2018-12-26 20:51:03', '2019-06-04 16:27:34', '教师管理', '#', 'teacher', '0', '', '1', 'fa fa-tasks', '3');
INSERT INTO `menu` VALUES ('2', '2018-12-26 20:54:32', '2019-06-04 16:29:33', '教师名单', '/teacher_nameList/list', 'teacher_nameList', '0', 'teacher', '1', 'fa fa-user', '1');
INSERT INTO `menu` VALUES ('3', '2018-12-29 00:07:57', '2019-05-14 22:34:02', '系统设置', '#', 'systemSetup', '0', '', '0', 'fa fa-cogs', '10');
INSERT INTO `menu` VALUES ('4', '2018-12-29 00:09:45', '2018-12-29 16:13:16', '菜单设置', '/menu/list', 'menuSetup', '0', 'systemSetup', '0', 'fa fa-credit-card-alt', '1');
INSERT INTO `menu` VALUES ('5', '2018-12-29 11:03:56', '2019-02-21 14:56:40', '教师信息', '/teacher_info/list', 'teacherInfo', '0', 'teacher', '0', 'fa fa-newspaper-o', '2');
INSERT INTO `menu` VALUES ('6', '2018-12-29 14:40:41', '2018-12-29 16:25:47', '教师课程', '#', 'teacherClass', '1', 'teacher', '0', 'fa fa-adjust', '1');
INSERT INTO `menu` VALUES ('7', '2019-01-02 09:22:29', '2019-02-21 14:56:53', '权限管理', '/role/list', 'roleSetup', '0', 'systemSetup', '0', 'fa fa-key', '2');
INSERT INTO `menu` VALUES ('8', '2019-01-23 17:00:50', '2019-04-24 14:13:57', '增加公告', '/send/list', 'send', '0', 'news', '0', 'fa fa-bullhorn', '1');
INSERT INTO `menu` VALUES ('9', '2019-02-19 16:34:42', '2019-06-04 16:27:40', '学生管理', '#', 'student', '0', '', '2', 'fa fa-server', '4');
INSERT INTO `menu` VALUES ('10', '2019-02-19 16:48:34', '2019-06-04 16:29:00', '学生名单', '/student_nameList/list', 'student_nameList', '0', 'student', '2', 'fa fa-graduation-cap', '1');
INSERT INTO `menu` VALUES ('11', '2019-02-21 14:51:12', '2019-02-21 14:51:35', '测试菜单', '#', 'ceshi', '1', '', '0', 'fa fa-adjust', '3');
INSERT INTO `menu` VALUES ('12', '2019-02-21 16:07:38', '2019-02-21 17:21:30', '学生信息', '/student_info/list', 'student_info', '0', 'student', '0', 'fa fa-newspaper-o', '2');
INSERT INTO `menu` VALUES ('13', '2019-02-26 10:31:56', '2019-06-04 16:29:19', '班级管理', '#', 'class', '0', '', '-1', 'fa fa-reorder', '5');
INSERT INTO `menu` VALUES ('14', '2019-02-26 10:35:44', '2019-06-04 16:29:26', '班级列表', '/class/list', 'class_list', '0', 'class', '-1', 'fa fa-home', '1');
INSERT INTO `menu` VALUES ('15', '2019-04-09 13:41:46', '2019-05-14 13:48:00', '工具', '#', 'tool', '0', '', '0', 'fa fa-legal', '6');
INSERT INTO `menu` VALUES ('16', '2019-04-09 13:44:36', '2019-04-09 13:44:36', 'wordToPdf', '/wordConverterPdf/list', 'wordConverterPdf', '0', 'tool', '0', 'fa fa-exchange', '2');
INSERT INTO `menu` VALUES ('17', '2019-04-09 13:49:22', '2019-06-04 15:37:51', '首页', '/main', 'main', '0', '', '-1', 'fa fa-laptop', '1');
INSERT INTO `menu` VALUES ('18', '2019-04-09 14:21:18', '2019-05-14 13:48:17', '消息', '#', 'news', '0', '', '0', 'fa fa-comments', '8');
INSERT INTO `menu` VALUES ('19', '2019-04-11 11:05:02', '2019-05-14 13:48:05', '文件台', '#', 'filedesk', '0', '', '0', 'fa fa-paste (alias)', '7');
INSERT INTO `menu` VALUES ('20', '2019-04-11 11:07:49', '2019-04-11 11:07:49', '文件管理', '/file/list', 'filemanager', '0', 'filedesk', '0', 'fa fa-folder-open', '1');
INSERT INTO `menu` VALUES ('21', '2019-05-14 13:40:46', '2019-05-14 13:47:16', '系统监控', '/monitor/list', 'monitor', '0', '', '0', 'fa fa-tachometer', '2');
INSERT INTO `menu` VALUES ('22', '2019-05-14 22:33:54', '2019-05-14 22:33:54', '日志管理', '#', 'log', '0', '', '0', 'fa fa-edit', '9');
INSERT INTO `menu` VALUES ('23', '2019-05-14 22:35:15', '2019-05-14 22:35:15', '操作日志', '/log/operationList', 'operaton', '0', 'log', '0', 'fa fa-hand-pointer-o', '1');
INSERT INTO `menu` VALUES ('24', '2019-05-15 13:59:50', '2019-05-15 13:59:50', '登入日志', '/log/loginList', 'login', '0', 'log', '0', 'fa fa-child', '2');
INSERT INTO `menu` VALUES ('25', '2019-06-14 14:41:22', '2019-06-14 14:41:22', '地图定位', '/map/list', 'map', '0', 'tool', '0', 'fa fa-maxcdn', '2');

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
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('1', '广播测试', '<p><strong>第一条广播测试&nbsp; </strong>测试1</p>', '2019-01-25 17:54:05', '2019-01-25 17:54:05', '0');
INSERT INTO `notice` VALUES ('2', '儿童节放假', '<p>儿童节放假通知 3天</p>', '2019-01-25 17:58:43', '2019-01-25 17:58:43', '0');
INSERT INTO `notice` VALUES ('3', '团建', '<p>本周六统一去团建</p>', '2019-01-26 10:33:57', '2019-01-26 10:33:57', '0');
INSERT INTO `notice` VALUES ('4', '暑假放假通知', '<p>暑假放假从7月到8月，共1个月</p>', '2019-01-26 10:40:03', '2019-01-26 10:40:03', '0');
INSERT INTO `notice` VALUES ('7', '10周年校庆', '<p>10周年校庆10周年校庆10周年校庆10周年校庆10周年校庆</p>', '2019-01-26 10:59:33', '2019-01-26 10:59:33', '0');
INSERT INTO `notice` VALUES ('8', '毕业答辩', '<p>毕业答辩毕业答辩毕业答辩毕业答辩毕业答辩毕业答辩</p>', '2019-01-26 11:02:14', '2019-01-26 11:02:14', '0');
INSERT INTO `notice` VALUES ('63', '学校座谈会', '<p><img src=\"/imgPath\\b96ed004-c9a9-4af8-9131-318000e825c4.jpeg\" title=\"b96ed004-c9a9-4af8-9131-318000e825c4.jpeg\" alt=\"b96ed004-c9a9-4af8-9131-318000e825c4.jpeg\" style=\"width: 349px; height: 214px;\" width=\"349\" height=\"214\"/>&nbsp; <br/></p><p>学校座谈会<br/></p>', '2019-04-26 13:35:20', '2019-05-14 21:48:08', '1');

-- ----------------------------
-- Table structure for operation_log
-- ----------------------------
DROP TABLE IF EXISTS `operation_log`;
CREATE TABLE `operation_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '操作名',
  `method` varchar(255) DEFAULT NULL COMMENT '操作方法',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='操作日志表';

-- ----------------------------
-- Records of operation_log
-- ----------------------------
INSERT INTO `operation_log` VALUES ('1', '批量下载pdf', 'downloadZip', 'sdy', '2019-05-09 16:13:31');
INSERT INTO `operation_log` VALUES ('2', '批量下载pdf', 'downloadZip', 'sdy', '2019-05-09 16:17:08');
INSERT INTO `operation_log` VALUES ('3', '批量下载pdf', 'downloadZip', 'sdy', '2019-05-09 16:17:24');
INSERT INTO `operation_log` VALUES ('4', '导出教师信息', 'export', 'sdy', '2019-05-09 16:34:59');
INSERT INTO `operation_log` VALUES ('5', '导出学生信息', 'exportStudent', 'sdy', '2019-05-09 16:35:16');
INSERT INTO `operation_log` VALUES ('6', '批量下载pdf', 'downloadZip', 'sdy', '2019-05-09 17:21:24');
INSERT INTO `operation_log` VALUES ('7', '批量下载pdf', 'downloadZip', 'sdy', '2019-05-10 09:28:46');
INSERT INTO `operation_log` VALUES ('8', '批量下载pdf', 'downloadZip', 'sdy', '2019-05-10 09:32:30');
INSERT INTO `operation_log` VALUES ('9', '批量下载pdf', 'downloadZip', 'sdy', '2019-05-10 09:35:18');
INSERT INTO `operation_log` VALUES ('10', '批量下载pdf', 'downloadZip', 'sdy', '2019-05-14 16:27:57');

-- ----------------------------
-- Table structure for pdf
-- ----------------------------
DROP TABLE IF EXISTS `pdf`;
CREATE TABLE `pdf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL COMMENT '保存路径',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `deleted` tinyint(4) DEFAULT NULL COMMENT '0未删除 1删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pdf
-- ----------------------------
INSERT INTO `pdf` VALUES ('1', '20190430 151524-12.pdf', '/home/aubin/study_manager/word/pdf/20190430 151524-12.pdf', '2019-04-30 15:15:25', '2019-04-30 15:15:25', '0');
INSERT INTO `pdf` VALUES ('2', '20190505 103158-12 - 副本.pdf', 'C:\\Users\\Administrator.ZYDN-20180527PO\\Desktop\\word\\pdf\\20190505 103158-12 - 副本.pdf', '2019-05-05 10:32:01', '2019-05-05 10:32:01', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'sdy', '$2a$10$cg1yDuFH7lVcop25D5jofeNXf6k1hh0Zuh/haCcCaKBZ2w9cZx4tC', '2018-12-20 13:52:47', '2019-05-15 09:32:05', '0', '0', '1', '7f4e44df-1461-4aa8-883c-a72008a2a1f1.jpeg');
INSERT INTO `user` VALUES ('2', '李小晴', '$2a$10$yNg.No9rUK9Aj7L2uOl1.uufshVqTuXJl9WvtdKcdrOOEtwOvFt.i', '2018-12-20 16:32:30', '2019-06-14 15:52:44', '0', '1', '1', '32a56152-0941-442e-b8a7-155fe7aa5aee.jpg');
INSERT INTO `user` VALUES ('3', '孙红雷', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 12:46:52', '2019-01-02 11:14:15', '0', '1', '0', '6f213d71-825f-4e76-a128-6ba369162126.png');
INSERT INTO `user` VALUES ('34', '张好', '$2a$10$MVfIGDyiNfLpnP4l8IAsZeaYjFbXnx3NiFah7li1xWC/pna0iwwOe', '2019-06-04 16:27:02', '2019-06-04 16:28:30', '0', '2', '1', null);

-- ----------------------------
-- Table structure for user_class
-- ----------------------------
DROP TABLE IF EXISTS `user_class`;
CREATE TABLE `user_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `class_id` int(11) DEFAULT NULL COMMENT '班级id',
  `type` int(2) DEFAULT NULL COMMENT '1:教师2:学生',
  `isadmin` tinyint(1) DEFAULT NULL COMMENT '是否是班主任（1：是0：不是）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_class
-- ----------------------------

-- ----------------------------
-- Table structure for user_notice
-- ----------------------------
DROP TABLE IF EXISTS `user_notice`;
CREATE TABLE `user_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT 'user表id',
  `notice_id` int(11) DEFAULT NULL COMMENT 'notice表id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '推送时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_notice
-- ----------------------------
INSERT INTO `user_notice` VALUES ('1', '1', '63', '2019-04-26 15:56:03');
INSERT INTO `user_notice` VALUES ('2', '1', '2', '2019-04-26 16:01:48');
INSERT INTO `user_notice` VALUES ('3', '12', '2', '2019-04-26 16:01:48');
INSERT INTO `user_notice` VALUES ('4', '1', '7', '2019-04-26 17:11:32');
INSERT INTO `user_notice` VALUES ('5', '1', '63', '2019-04-26 17:11:43');
INSERT INTO `user_notice` VALUES ('6', '1', '7', '2019-04-28 10:50:11');
INSERT INTO `user_notice` VALUES ('7', '1', '8', '2019-04-28 15:32:49');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) DEFAULT NULL,
  `roles_id` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1');
INSERT INTO `user_role` VALUES ('17', '34', '3');
INSERT INTO `user_role` VALUES ('18', '3', '2');
INSERT INTO `user_role` VALUES ('19', '2', '2');
