/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50515
 Source Host           : localhost:3306
 Source Schema         : helper

 Target Server Type    : MySQL
 Target Server Version : 50515
 File Encoding         : 65001

 Date: 26/02/2020 20:34:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '帖子的id',
  `title` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `text` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `time` datetime NOT NULL COMMENT '发布时间',
  `user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发布人',
  `addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '当前地点',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user`(`user`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (8, '欢迎使用', '欢迎使用信院互帮圈平台，在这个平台您可以发布求助，也可以回应求助，求助内容禁止违反校规。', '2019-11-16 18:18:54', 'admin', '平台本部', '/Helper/upload/image/welcome.jpg');

-- ----------------------------
-- Table structure for bulletin
-- ----------------------------
DROP TABLE IF EXISTS `bulletin`;
CREATE TABLE `bulletin`  (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告内容',
  `time` datetime NOT NULL COMMENT '公告时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bulletin
-- ----------------------------
INSERT INTO `bulletin` VALUES (7, '后台管理系统的公告', '2019-11-22 23:08:10');
INSERT INTO `bulletin` VALUES (8, '公告', '2019-11-22 23:09:45');
INSERT INTO `bulletin` VALUES (9, '公告2', '2020-01-22 18:54:05');
INSERT INTO `bulletin` VALUES (10, '公告3', '2020-01-22 18:54:14');
INSERT INTO `bulletin` VALUES (11, '公告4', '2020-01-22 18:54:20');
INSERT INTO `bulletin` VALUES (12, '公告5', '2020-01-22 19:01:53');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '评论的id',
  `text` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论的内容',
  `time` datetime NOT NULL COMMENT '评论的时间',
  `user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论人',
  `atitle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '对应的发布标题',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user`(`user`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (9, 'user1000的第一条测试', '2020-02-23 18:17:42', 'user1000', '欢迎使用');
INSERT INTO `comment` VALUES (12, '欢迎~', '2020-02-25 18:45:33', 'lin', '欢迎使用');

-- ----------------------------
-- Table structure for favorite
-- ----------------------------
DROP TABLE IF EXISTS `favorite`;
CREATE TABLE `favorite`  (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `atitle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of favorite
-- ----------------------------
INSERT INTO `favorite` VALUES (12, 'user1000', '欢迎使用');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(4) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '索引，自动生成',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `age` int(3) NOT NULL COMMENT '年龄',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话号码',
  `sex` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别',
  `jieshao` tinytext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '介绍',
  `birth` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '生日',
  `permit` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '头像',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (0001, 'admin', 'admin', '591276457@qq.com', 0, '0', '男', '这个人很懒，暂时还没有介绍', '2020', '管理员', '/Helper/upload/icon/icon.jpg');
INSERT INTO `user` VALUES (0043, 'user1000', '123', '591276457@qq.com', 23, '13753518074', '男', '这个是user1000的自我介绍', '1997', '用户', '/Helper/upload/icon/449724.jpg');
INSERT INTO `user` VALUES (0044, 'user1', '123456789', '987654321@helper.com', 20, '17000000000', '未知', '这个是自我介绍', '2000', '用户', '/Helper/upload/icon/icon.jpg');
INSERT INTO `user` VALUES (0045, 'lin', '123', 'lin@qq.com', 20, '17000000000', '男', '这个人很懒，暂时还没有介绍', '2000', '用户', '/Helper/upload/icon/73875216_p0_master1200.jpg');

SET FOREIGN_KEY_CHECKS = 1;
