/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : study

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-04-12 17:18:46
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES ('1', 'word文件', null, '3e692811-18f3-4d86-8dfc-eb55759d5b5d', null, '2019-04-12 17:16:22', '2019-04-12 17:16:22', '0', null, '0');
INSERT INTO `file` VALUES ('2', 'excel文件', null, 'f7ae54d8-ac9e-4680-a3e7-eeaa4a6643fa', null, '2019-04-12 17:16:30', '2019-04-12 17:16:30', '0', null, '0');
INSERT INTO `file` VALUES ('3', '其他', null, 'cf75ffd8-3b0f-4848-9c34-9a25dd67344a', null, '2019-04-12 17:16:35', '2019-04-12 17:16:35', '0', null, '0');
INSERT INTO `file` VALUES ('4', '2019/04/12', null, '4995c6c8-cbc5-415a-9e98-83c585b3ca74', '3e692811-18f3-4d86-8dfc-eb55759d5b5d', '2019-04-12 17:16:47', '2019-04-12 17:16:47', '0', null, '0');
INSERT INTO `file` VALUES ('5', '20190412-InputPhoneModel - 副本.xls', 'C:\\Users\\Administrator.ZYDN-20180527PO\\Desktop\\filedesk\\20190412-InputPhoneModel - 副本.xls', '19931869-1b6a-4a06-a44a-307e6698c700', '4995c6c8-cbc5-415a-9e98-83c585b3ca74', '2019-04-12 17:17:02', '2019-04-12 17:17:16', '1', 'fa fa-file', '1');
INSERT INTO `file` VALUES ('6', '2019/04/12', null, 'be9ddd6c-6ff6-4bf2-81c7-c94d9427cd7a', 'f7ae54d8-ac9e-4680-a3e7-eeaa4a6643fa', '2019-04-12 17:17:27', '2019-04-12 17:17:27', '0', null, '0');
INSERT INTO `file` VALUES ('7', 'test.xls', 'C:\\Users\\Administrator.ZYDN-20180527PO\\Desktop\\filedesk\\test.xls', 'b4588bad-b69a-4eab-8b24-22b0d8fa5236', 'be9ddd6c-6ff6-4bf2-81c7-c94d9427cd7a', '2019-04-12 17:17:37', '2019-04-12 17:17:45', '0', 'fa fa-file', '1');
INSERT INTO `file` VALUES ('8', 'test.docx', 'C:\\Users\\Administrator.ZYDN-20180527PO\\Desktop\\filedesk\\test.docx', 'c0ae8b2d-2309-4204-8587-7a84d7e80d4e', '4995c6c8-cbc5-415a-9e98-83c585b3ca74', '2019-04-12 17:18:09', '2019-04-12 17:18:20', '0', 'fa fa-file', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of info
-- ----------------------------
INSERT INTO `info` VALUES ('1', '2', '0', '江苏省常州市', '15961170771', '2019-01-22 15:50:20', '2019-04-09 10:15:09', '0', '22');
INSERT INTO `info` VALUES ('2', '10', '0', '上海市', '15961170770', '2019-02-19 09:54:30', '2019-02-19 15:40:48', '0', '20');
INSERT INTO `info` VALUES ('5', '1', '1', '江苏省常州市', '15961170770', '2019-02-19 10:15:58', '2019-02-19 10:20:33', '0', '22');
INSERT INTO `info` VALUES ('8', '9', '0', '江苏省南京市', '15961170771', '2019-02-19 10:34:01', '2019-02-19 10:56:13', '1', '25');
INSERT INTO `info` VALUES ('9', '25', '0', '121212', '15961170771', '2019-02-19 17:13:27', '2019-02-19 17:17:16', '1', '12');
INSERT INTO `info` VALUES ('10', '26', '1', '江苏省常州市', '15961170770', '2019-02-21 17:13:50', '2019-02-21 17:13:50', '0', '22');

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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '2018-12-26 20:51:03', '2019-04-09 13:50:40', '教师管理', '#', 'teacher', '0', '', '0', 'fa fa-tasks', '2');
INSERT INTO `menu` VALUES ('2', '2018-12-26 20:54:32', '2019-02-21 14:56:31', '教师名单', '/teacher_nameList/list', 'teacher_nameList', '0', 'teacher', '0', 'fa fa-user', '1');
INSERT INTO `menu` VALUES ('3', '2018-12-29 00:07:57', '2019-04-11 11:05:08', '系统设置', '#', 'systemSetup', '0', '', '0', 'fa fa-cogs', '8');
INSERT INTO `menu` VALUES ('4', '2018-12-29 00:09:45', '2018-12-29 16:13:16', '菜单设置', '/menu/list', 'menuSetup', '0', 'systemSetup', '0', 'fa fa-credit-card-alt', '1');
INSERT INTO `menu` VALUES ('5', '2018-12-29 11:03:56', '2019-02-21 14:56:40', '教师信息', '/teacher_info/list', 'teacherInfo', '0', 'teacher', '0', 'fa fa-newspaper-o', '2');
INSERT INTO `menu` VALUES ('6', '2018-12-29 14:40:41', '2018-12-29 16:25:47', '教师课程', '#', 'teacherClass', '1', 'teacher', '0', 'fa fa-adjust', '1');
INSERT INTO `menu` VALUES ('7', '2019-01-02 09:22:29', '2019-02-21 14:56:53', '权限管理', '/role/list', 'roleSetup', '0', 'systemSetup', '0', 'fa fa-key', '2');
INSERT INTO `menu` VALUES ('8', '2019-01-23 17:00:50', '2019-04-09 14:21:55', '发送公告', '/send/list', 'send', '0', 'news', '0', 'fa fa-bullhorn', '1');
INSERT INTO `menu` VALUES ('9', '2019-02-19 16:34:42', '2019-04-09 13:50:45', '学生管理', '#', 'student', '0', '', '0', 'fa fa-server', '3');
INSERT INTO `menu` VALUES ('10', '2019-02-19 16:48:34', '2019-02-19 16:48:34', '学生名单', '/student_nameList/list', 'student_nameList', '0', 'student', '0', 'fa fa-graduation-cap', '1');
INSERT INTO `menu` VALUES ('11', '2019-02-21 14:51:12', '2019-02-21 14:51:35', '测试菜单', '#', 'ceshi', '1', '', '0', 'fa fa-adjust', '3');
INSERT INTO `menu` VALUES ('12', '2019-02-21 16:07:38', '2019-02-21 17:21:30', '学生信息', '/student_info/list', 'student_info', '0', 'student', '0', 'fa fa-newspaper-o', '2');
INSERT INTO `menu` VALUES ('13', '2019-02-26 10:31:56', '2019-04-09 13:50:51', '班级管理', '#', 'class', '0', '', '0', 'fa fa-reorder', '4');
INSERT INTO `menu` VALUES ('14', '2019-02-26 10:35:44', '2019-02-26 10:35:44', '班级列表', '/class/list', 'class_list', '0', 'class', '0', 'fa fa-home', '1');
INSERT INTO `menu` VALUES ('15', '2019-04-09 13:41:46', '2019-04-09 13:50:56', '工具', '#', 'tool', '0', '', '0', 'fa fa-legal', '5');
INSERT INTO `menu` VALUES ('16', '2019-04-09 13:44:36', '2019-04-09 13:44:36', 'wordToPdf', '/wordConverterPdf/list', 'wordConverterPdf', '0', 'tool', '0', 'fa fa-exchange', '2');
INSERT INTO `menu` VALUES ('17', '2019-04-09 13:49:22', '2019-04-09 13:49:22', '首页', '/main', 'main', '0', '', '0', 'fa fa-laptop', '1');
INSERT INTO `menu` VALUES ('18', '2019-04-09 14:21:18', '2019-04-09 14:21:18', '消息', '#', 'news', '0', '', '0', 'fa fa-comments', '6');
INSERT INTO `menu` VALUES ('19', '2019-04-11 11:05:02', '2019-04-11 11:08:28', '文件台', '#', 'filedesk', '0', '', '0', 'fa fa-paste (alias)', '7');
INSERT INTO `menu` VALUES ('20', '2019-04-11 11:07:49', '2019-04-11 11:07:49', '文件管理', '/file/list', 'filemanager', '0', 'filedesk', '0', 'fa fa-folder-open', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

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
INSERT INTO `notice` VALUES ('23', '测试', '<p>测试</p>', '2019-01-30 16:49:25', '2019-01-30 16:56:27', '1');
INSERT INTO `notice` VALUES ('24', '测试测试', '<p>测试测试测试测试</p>', '2019-01-31 11:14:53', '2019-01-31 11:14:53', '0');
INSERT INTO `notice` VALUES ('25', '测试', '<p><img src=\"http://img.baidu.com/hi/jx2/j_0001.gif\"/><img src=\"http://img.baidu.com/hi/jx2/j_0003.gif\"/></p>', '2019-02-01 14:19:02', '2019-02-01 15:50:42', '1');
INSERT INTO `notice` VALUES ('26', '表情测试', '<p><img src=\"http://img.baidu.com/hi/jx2/j_0002.gif\"/><img src=\"http://img.baidu.com/hi/jx2/j_0003.gif\"/><img src=\"http://img.baidu.com/hi/jx2/j_0002.gif\"/></p>', '2019-02-01 15:51:17', '2019-02-01 15:51:17', '0');
INSERT INTO `notice` VALUES ('27', '', '', '2019-02-01 16:07:12', '2019-02-01 16:07:26', '1');
INSERT INTO `notice` VALUES ('28', '1212', '<p><img src=\"http://img.baidu.com/hi/jx2/j_0013.gif\"/></p>', '2019-02-01 16:08:29', '2019-02-01 16:08:29', '0');
INSERT INTO `notice` VALUES ('29', '开学通知', '<p><img src=\"http://img.baidu.com/hi/jx2/j_0003.gif\"/>假期结束，正式开学<img src=\"http://img.baidu.com/hi/jx2/j_0006.gif\"/></p>', '2019-02-13 13:42:50', '2019-02-13 13:49:28', '0');
INSERT INTO `notice` VALUES ('30', '测试', '<p><img src=\"http://img.baidu.com/hi/jx2/j_0003.gif\"/>新学期加油</p>', '2019-02-13 14:17:27', '2019-02-13 14:31:26', '0');
INSERT INTO `notice` VALUES ('31', '', '', '2019-02-13 14:17:43', '2019-02-13 14:18:10', '1');
INSERT INTO `notice` VALUES ('32', '测试图片', '<p><img src=\"/imgPath\\e873a44d-bef1-4307-b634-fa2d755f6a2d.jpeg\" title=\"e873a44d-bef1-4307-b634-fa2d755f6a2d.jpeg\" alt=\"e873a44d-bef1-4307-b634-fa2d755f6a2d.jpeg\"/><img src=\"/imgPath\\a1bc067b-eaeb-4824-879f-c47dd2f70d3f.jpeg\" title=\"a1bc067b-eaeb-4824-879f-c47dd2f70d3f.jpeg\" alt=\"a1bc067b-eaeb-4824-879f-c47dd2f70d3f.jpeg\"/></p>', '2019-02-13 16:09:14', '2019-02-14 11:04:40', '1');
INSERT INTO `notice` VALUES ('33', '12121', '<p><img src=\"C:\\Users\\Administrator.ZYDN-20180527PO\\Desktop\\img\\69e12f1d-0242-4cc4-bbf7-a9306af1bdb0.jpg\"/><img src=\"/imgPath\\4f040229-330f-4713-9ac8-ac97c41e5dc1.jpeg\" title=\"4f040229-330f-4713-9ac8-ac97c41e5dc1.jpeg\" alt=\"4f040229-330f-4713-9ac8-ac97c41e5dc1.jpeg\"/><img src=\"http://img.baidu.com/hi/jx2/j_0002.gif\"/></p>', '2019-02-13 16:34:47', '2019-02-14 11:04:37', '1');
INSERT INTO `notice` VALUES ('34', '测试测试', '<p>测试121212121</p><p style=\"text-align:center\"><img src=\"/imgPath\\30fcb519-7272-434d-a756-063c58d13007.jpeg\" title=\"30fcb519-7272-434d-a756-063c58d13007.jpeg\" alt=\"30fcb519-7272-434d-a756-063c58d13007.jpeg\"/></p><p><br/></p>', '2019-02-13 16:56:22', '2019-02-14 11:04:31', '0');
INSERT INTO `notice` VALUES ('35', '图片', '<p><img src=\"/imgPath\\f7fa5466-ba39-4081-adfb-5cdb91c48e5f.jpeg\" title=\"f7fa5466-ba39-4081-adfb-5cdb91c48e5f.jpeg\" alt=\"f7fa5466-ba39-4081-adfb-5cdb91c48e5f.jpeg\"/></p>', '2019-02-14 11:19:13', '2019-02-19 11:22:28', '1');
INSERT INTO `notice` VALUES ('36', '1212', '<p><video class=\"edui-upload-video  vjs-default-skin    video-js\" controls=\"\" preload=\"none\" width=\"420\" height=\"280\" src data-setup=\"{}\"></video></p>', '2019-02-14 13:56:42', '2019-02-14 14:50:57', '1');
INSERT INTO `notice` VALUES ('37', '12121', '<p>\n    <video class=\"edui-upload-video  vjs-default-skin video-js\" controls=\"\" preload=\"none\" width=\"420\" height=\"280\" src=\"/imgPath\\ba0e0256-ffe0-462f-b83b-d37c188e4667.mp4\" data-setup=\"{}\"></video>\n</p>', '2019-02-14 13:58:06', '2019-02-14 14:50:55', '1');
INSERT INTO `notice` VALUES ('38', '12121', '<p><video class=\"edui-upload-video  vjs-default-skin video-js\" controls=\"\" preload=\"none\" width=\"420\" height=\"280\" src=\"/imgPath\\9f50ef8f-8efb-4efb-942f-572d7db1e3fe.mp4\" data-setup=\"{}\"></video></p>', '2019-02-14 13:59:07', '2019-02-14 14:50:53', '1');
INSERT INTO `notice` VALUES ('39', '12121', '<p><img src=\"/imgPath\\de36bf83-7fb9-4f0f-a04f-7aba6964a5b1.jpeg\" title=\"de36bf83-7fb9-4f0f-a04f-7aba6964a5b1.jpeg\" alt=\"de36bf83-7fb9-4f0f-a04f-7aba6964a5b1.jpeg\"/></p>', '2019-02-14 13:59:47', '2019-02-19 11:22:25', '1');
INSERT INTO `notice` VALUES ('40', '121', '<p><video class=\"edui-upload-video  vjs-default-skin video-js\" controls=\"\" preload=\"none\" width=\"115\" height=\"87\" src=\"/imgPath\\776d7188-8d4d-4ae2-9c46-4016d5fe7961.mp4\" data-setup=\"{}\"></video></p>', '2019-02-14 14:01:29', '2019-02-14 14:50:50', '1');
INSERT INTO `notice` VALUES ('41', '12121', '<p><video class=\"edui-upload-video  vjs-default-skin video-js\" controls=\"\" preload=\"none\" width=\"50\" height=\"50\" src=\"/imgPath\\22df7894-a15a-43f4-80c4-0a380ff1aa9c.mp4\" data-setup=\"{}\"></video></p>', '2019-02-14 14:08:01', '2019-02-14 14:50:47', '1');
INSERT INTO `notice` VALUES ('42', '121', '<p><video class=\"edui-upload-video  vjs-default-skin         video-js\" style=\"float: right\" controls=\"\" preload=\"none\" width=\"26\" height=\"22\" src data-setup=\"{}\"></video></p>', '2019-02-14 14:16:28', '2019-02-14 14:50:45', '1');
INSERT INTO `notice` VALUES ('43', '12121', '<p style=\"text-align:center\"><video class=\"edui-upload-video  vjs-default-skin video-js\" controls=\"\" preload=\"none\" width=\"420\" height=\"280\" src=\"/imgPath\\1198f237-b5da-43d1-8706-0507b2b5dd3b.mp4\"><source src=\"/imgPath\\1198f237-b5da-43d1-8706-0507b2b5dd3b.mp4\" type=\"video/mp4\"/></video></p><p><br/></p>', '2019-02-14 14:38:10', '2019-02-14 14:50:42', '1');
INSERT INTO `notice` VALUES ('44', '12121', '<p><img src=\"/imgPath\\d7f0cb90-60cf-422a-ab36-40b3e612648c.jpeg\" title=\"d7f0cb90-60cf-422a-ab36-40b3e612648c.jpeg\" alt=\"d7f0cb90-60cf-422a-ab36-40b3e612648c.jpeg\" width=\"56\" height=\"52\"/></p>', '2019-02-14 14:54:08', '2019-02-19 11:22:23', '1');
INSERT INTO `notice` VALUES ('45', '12121', '<p><video class=\"edui-upload-video  vjs-default-skin video-js\" style=\"float: right\" controls=\"\" preload=\"none\" width=\"5\" height=\"5\" src=\"/imgPath\\9a1e8ec6-334d-484d-a25f-b41015f5ad2b.mp4\"><source src=\"/imgPath\\9a1e8ec6-334d-484d-a25f-b41015f5ad2b.mp4\" type=\"video/mp4\"/></video></p>', '2019-02-14 15:05:05', '2019-02-14 15:22:25', '1');
INSERT INTO `notice` VALUES ('46', '12121', '<p>\n    <video class=\"edui-upload-video  vjs-default-skin  video-js\" controls=\"\" preload=\"none\" width=\"420\" height=\"280\" src=\"/imgPath\\3749baef-3a52-4fff-9fb4-ac2a72dc8194.mp4\">\n       \n    </video>\n</p>', '2019-02-14 15:07:23', '2019-02-14 15:21:25', '1');
INSERT INTO `notice` VALUES ('47', '121212', '<p>\n    <video controls=\"\" width=\"420\" height=\"280\">\n        <source src=\"/imgPath\\42e2cb53-ce0b-4992-9f71-36d72f285db3.mp4\" type=\"video/mp4\"/>\n    </video>\n</p>', '2019-02-14 15:10:35', '2019-02-14 15:21:22', '1');
INSERT INTO `notice` VALUES ('48', '321', '<p>\n    <video controls=\"\"  width=\"158\" height=\"100\">\n        <source src=\"/imgPath\\dba02af2-3c6e-4338-a4dd-46a909f6e37a.mp4\" type=\"video/mp4\"/>\n    </video>\n</p>', '2019-02-14 15:11:22', '2019-02-14 15:21:20', '1');
INSERT INTO `notice` VALUES ('49', '视频测试', '<p><video class=\"edui-upload-video  vjs-default-skin video-js\" controls=\"\" preload=\"none\" width=\"422\" height=\"187\" src=\"/imgPath\\eb18ff23-7c1f-4d36-91f2-a9b06d05f1c4.mp4\"><source src=\"/imgPath\\eb18ff23-7c1f-4d36-91f2-a9b06d05f1c4.mp4\" type=\"video/mp4\"/></video></p>', '2019-02-14 15:21:50', '2019-02-14 17:28:04', '1');
INSERT INTO `notice` VALUES ('50', '12121', '<p><video class=\"edui-upload-video  vjs-default-skin       video-js\" controls=\"\" preload=\"none\" width=\"178\" height=\"130\" src=\"/imgPath\\755509cb-d850-4328-8207-78cd44f600ac.mp4\" data-setup=\"{}\"><source src=\"/imgPath\\755509cb-d850-4328-8207-78cd44f600ac.mp4\" type=\"video/mp4\"/></video></p>', '2019-02-14 15:30:40', '2019-02-19 11:22:20', '1');
INSERT INTO `notice` VALUES ('51', '121212', '<p><video class=\"edui-upload-video  vjs-default-skin                    video-js\" controls=\"\" preload=\"none\" width=\"264\" height=\"175\" src=\"/imgPath\\91753b61-2848-4005-ac01-e10d33efe8a6.mp4\" data-setup=\"{}\"><source src=\"/imgPath\\91753b61-2848-4005-ac01-e10d33efe8a6.mp4\" type=\"video/mp4\"/></video></p>', '2019-02-14 15:49:48', '2019-02-19 11:22:18', '1');
INSERT INTO `notice` VALUES ('52', '111', '<p><video class=\"edui-upload-video  vjs-default-skin video-js\" controls=\"\" preload=\"none\" width=\"989\" height=\"484\" src=\"/imgPath\\04d71b04-471d-4746-9d17-af415b081b34.mp4\" data-setup=\"{}\"><source src=\"/imgPath\\04d71b04-471d-4746-9d17-af415b081b34.mp4\" type=\"video/mp4\"/></video></p>', '2019-02-14 16:03:41', '2019-02-14 17:28:09', '1');
INSERT INTO `notice` VALUES ('53', '12121', '<p><embed type=\"application/x-shockwave-flash\" class=\"edui-faked-music\" pluginspage=\"http://www.macromedia.com/go/getflashplayer\" src=\"http://box.baidu.com/widget/flash/bdspacesong.swf?from=tiebasongwidget&url=&name=%E6%99%B4%E5%A4%A9&artist=%E5%91%A8%E6%9D%B0%E4%BC%A6&extra=%E5%8F%B6%E6%83%A0%E7%BE%8E&autoPlay=false&loop=true\" width=\"400\" height=\"95\" align=\"none\" wmode=\"transparent\" play=\"true\" loop=\"false\" menu=\"false\" allowscriptaccess=\"never\" allowfullscreen=\"true\"/></p>', '2019-02-14 16:25:28', '2019-02-14 17:28:18', '1');
INSERT INTO `notice` VALUES ('54', '12121', '<p><video class=\"edui-upload-video  vjs-default-skin video-js\" controls=\"\" preload=\"none\" width=\"202\" height=\"151\" src=\"/imgPath\\bb0ef7d0-8805-44b4-9604-44e4a0e683d7.mp4\" data-setup=\"{}\"><source src=\"/imgPath\\bb0ef7d0-8805-44b4-9604-44e4a0e683d7.mp4\" type=\"video/mp4\"/></video></p>', '2019-02-14 17:26:25', '2019-02-14 17:28:07', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'sdy', '$2a$10$cg1yDuFH7lVcop25D5jofeNXf6k1hh0Zuh/haCcCaKBZ2w9cZx4tC', '2018-12-20 13:52:47', '2019-04-12 09:21:06', '0', '0', '1', 'f0630498-a4a5-449b-9a4c-39ee60843078.jpg');
INSERT INTO `user` VALUES ('2', '李小晴', '$2a$10$xxL9kkxgsp2P4fBJOI7y/u5AVNefy4lQS31shjYHFjsy3TGVEQHsi', '2018-12-20 16:32:30', '2019-03-15 15:15:06', '0', '1', '1', '6f213d71-825f-4e76-a128-6ba369162126.png');
INSERT INTO `user` VALUES ('3', '孙红雷', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 12:46:52', '2019-01-02 11:14:15', '0', '1', '0', null);
INSERT INTO `user` VALUES ('4', '张梅', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 12:46:44', '2018-12-25 12:46:46', '0', '1', '1', null);
INSERT INTO `user` VALUES ('5', '赵四', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 12:46:54', '2018-12-25 12:46:56', '0', '1', '1', null);
INSERT INTO `user` VALUES ('6', '占号', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 11:22:20', '2018-12-25 11:22:20', '0', '1', '1', null);
INSERT INTO `user` VALUES ('7', '钱发', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 12:46:26', '2018-12-25 12:46:26', '0', '1', '1', null);
INSERT INTO `user` VALUES ('8', '吴刚', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 13:37:32', '2018-12-25 13:37:32', '0', '1', '1', null);
INSERT INTO `user` VALUES ('9', '周小', '$2a$10$qVKxQuv9hZNaJQHg.7cO2u9FYQhsdLuc/bSIuch/ihrBXPDF067w2', '2018-12-25 13:38:33', '2019-02-19 10:56:14', '1', '1', '1', null);
INSERT INTO `user` VALUES ('10', '郑雪', '$2a$10$uQB9Nh/BYiPeaWrJdv/akORO1Z/54hvN6mt1pcIpAhX9UufaGOC6G', '2018-12-25 13:39:04', '2019-02-19 09:54:30', '0', '1', '1', null);
INSERT INTO `user` VALUES ('11', '王红', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 13:39:16', '2018-12-25 13:39:16', '1', '1', '1', null);
INSERT INTO `user` VALUES ('12', '梅雪', '$2a$10$C7ZKo0I4KSYTZyuylRlzZewrgCbkSLkd7hJ6mYiH7d1ntGdQ2z7dm', '2018-12-25 13:49:54', '2018-12-26 16:16:05', '0', '1', '1', null);
INSERT INTO `user` VALUES ('14', '张张', '$2a$10$F9xfVttFqLGf5OUcmTSoaucpPnUYLYpFTlsNwmhA58FtZ/I9F32l6', '2019-02-18 18:13:09', '2019-02-18 18:46:20', '1', '1', '1', null);
INSERT INTO `user` VALUES ('15', '哈哈', '$2a$10$uxVzILF7v6zyR89HWC3VReArNtICMHWR1799fOU1n.yp6beFPiEH6', '2019-02-18 18:13:10', '2019-02-18 18:46:16', '1', '1', '1', null);
INSERT INTO `user` VALUES ('16', '张张', '$2a$10$yTXe23zJ5pTfo1zpDi5oI.AIL1RFa5NGx0MUTBbjiAcSwg6CvqZK.', '2019-02-18 18:14:36', '2019-02-18 18:14:47', '1', '1', '1', null);
INSERT INTO `user` VALUES ('17', '哈哈', '$2a$10$8K0UK5RgH4VEeQdvhNjeA.CLgDyrFp19a8HVYBkSQeNuVbFKTorsK', '2019-02-18 18:14:36', '2019-02-18 18:14:43', '1', '1', '1', null);
INSERT INTO `user` VALUES ('18', '张张', '$2a$10$Owg/0wAQ3FHCOwRKPkZ/puKmQXUxLF86FIGdJQjsNcKQ11yskI8lK', '2019-02-18 18:47:20', '2019-02-18 18:47:46', '1', '1', '1', null);
INSERT INTO `user` VALUES ('19', '哈哈', '$2a$10$gJaEGwAfQ02XAIBH3iUcWuWXq8/r8qXr.8nsvLY.UZFFFKQ.Unr0i', '2019-02-18 18:47:21', '2019-02-18 18:47:21', '1', '2', '1', null);
INSERT INTO `user` VALUES ('20', '张张', '$2a$10$TtGKwKPmS9Z9F4IZer.vEeG7m2oxPaaVvtZoLQb/xX.CaR8yF4UJ2', '2019-02-18 18:48:05', '2019-02-18 18:48:05', '0', '1', '0', null);
INSERT INTO `user` VALUES ('21', '哈哈', '$2a$10$sSERJ8ltb.6IeDkLnKXnouUUakhnVe2HVkEBaD0GBJuPnmBFFHT7y', '2019-02-18 18:48:05', '2019-02-19 17:08:50', '0', '2', '1', null);
INSERT INTO `user` VALUES ('22', '某某', '$2a$10$9KIejyTVmUwZlSQMWpmKv.vpjY13fSuNmYFlft0tbBchDT0qdYeVS', '2019-02-19 09:39:18', '2019-02-19 09:39:18', '0', '1', '1', null);
INSERT INTO `user` VALUES ('23', '某某2', '$2a$10$i6wHuWZpfxRGppV7GXT/L.RG4VBn8irsEAZ2TLhC5dEyq/mKTfQ7S', '2019-02-19 09:47:48', '2019-02-19 09:47:48', '0', '1', '1', null);
INSERT INTO `user` VALUES ('24', '某某2', '$2a$10$RV5hbKmX5xOtfIVcnsbCyuzn7xrG0vpwWwGp6WA0KuPTDwgL0jK8e', '2019-02-19 09:48:04', '2019-02-19 09:48:15', '1', '1', '0', null);
INSERT INTO `user` VALUES ('25', '测试', '$2a$10$J8PT5xRry95vjnbfiIboA.zlwXqrvcGcOfzKt9gsAwCQfIHMQvFyK', '2019-02-19 17:12:46', '2019-02-19 17:17:17', '1', '2', '1', null);
INSERT INTO `user` VALUES ('26', '张学生', '$2a$10$Kw3BbqQJvd/IzM5bfmronO9e0s6.X1JJz8iLuyefdgUNBakcc5EKq', '2019-02-19 17:15:35', '2019-02-21 17:13:50', '0', '2', '1', null);
INSERT INTO `user` VALUES ('27', '某某1', '$2a$10$gveUErRZel.z8zqIE/dgDuwxnLIOJ7XNVudokD.6G6oSJG6RLDdxG', '2019-02-21 11:11:08', '2019-02-21 11:11:08', '0', '1', '1', null);
INSERT INTO `user` VALUES ('28', '某某1', '$2a$10$/h8FnmyWMsiCBOqUqZtPcOtTbThUVZq1Pry02T5HdZvroci0cykAa', '2019-02-21 11:16:42', '2019-02-21 11:16:42', '0', '2', '1', null);
INSERT INTO `user` VALUES ('29', '121', '$2a$10$.IE3RXGYZ7t4zEp4NFT0/.TJXTlsWyqb.wJECnWSwl3QEsj6WsD.q', '2019-02-21 11:16:42', '2019-02-21 11:16:42', '0', '2', '1', null);
INSERT INTO `user` VALUES ('30', '某某', '$2a$10$wjBU9kJJ2LDFoIfwWlooX.tLW34L9YrIiJzUr5eYzLYZlZScMJQKO', '2019-02-21 11:18:58', '2019-02-21 14:06:25', '1', '2', '1', null);
INSERT INTO `user` VALUES ('31', '某某', '$2a$10$i7DCoSUZ/qLchSsK.twwJuQ8tlknnzn35i9EPKlHyMdrHOp4Jq3N2', '2019-02-21 11:20:18', '2019-02-21 11:20:18', '0', '2', '0', null);
INSERT INTO `user` VALUES ('32', '某某', '$2a$10$ICCy4XFGO4Ph7euYpOxj..9arRm66VRTCy2TJZvzGXW9cblAyJM/a', '2019-02-21 11:21:11', '2019-02-21 14:06:22', '1', '2', '0', null);
INSERT INTO `user` VALUES ('33', '某某', '$2a$10$WjEWmOoeAEPdgGUbxwnpv.MuDf1C4XX2CnCuYoMizthhxQ/s8/Y.C', '2019-02-21 11:24:16', '2019-02-21 14:06:20', '1', '2', '0', null);

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
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) DEFAULT NULL,
  `roles_id` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

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
INSERT INTO `user_role` VALUES ('12', '25', '3');
INSERT INTO `user_role` VALUES ('13', '26', '3');
