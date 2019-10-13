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

 Date: 13/10/2019 22:28:14
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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (1, '测试题目1', '测试文章1', '2019-10-11 17:36:57', 'user1', '1号楼');

-- ----------------------------
-- Table structure for bulletin
-- ----------------------------
DROP TABLE IF EXISTS `bulletin`;
CREATE TABLE `bulletin`  (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告内容',
  `time` datetime NOT NULL COMMENT '公告时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bulletin
-- ----------------------------
INSERT INTO `bulletin` VALUES (2, '测试公告', '2019-10-11 20:42:02');
INSERT INTO `bulletin` VALUES (3, '测试公告1', '2019-10-13 20:19:02');

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (0001, 'admin', 'admin', 'test1', 30, '13800000000', '1', '这个人很懒，暂时还没有介绍', '2010-10-1', '管理员');
INSERT INTO `user` VALUES (0015, 'user1', '1234567890', '123456789@qq.com', 20, '17012345678', '1', '这个人很懒，暂时还没有介绍', '1999-1-1', '用户');
INSERT INTO `user` VALUES (0016, 'user2', '1234567890', '123456@qq.com', 20, '13701234567', '1', '这个人很懒，暂时还没有介绍', '1999-1-1', '用户');
INSERT INTO `user` VALUES (0018, 'user3', '1234567890', '123@qq.com', 20, '123456789', '1', '这个人很懒，暂时还没有介绍', '1999-1-1', '用户');

SET FOREIGN_KEY_CHECKS = 1;
