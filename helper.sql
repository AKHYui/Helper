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

 Date: 19/02/2020 20:42:07
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
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user`(`user`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (0, '测试1', '测试1', '2019-11-16 18:18:54', 'user1', 'aa');
INSERT INTO `article` VALUES (2, '测试案例1', '这个用于测试，检查功能完整性', '2019-11-16 18:18:55', 'user1', '1号楼');
INSERT INTO `article` VALUES (3, '测试案例2', '这个用于测试，检查功能完整性', '2019-11-16 18:18:55', 'user1', '1号楼');
INSERT INTO `article` VALUES (4, '测试案例3', '这个用于测试，检查功能完整性', '2019-11-16 18:18:55', 'user1', '1号楼');
INSERT INTO `article` VALUES (5, '测试案例4', '这个用于测试，检查功能完整性', '2019-11-16 18:18:55', 'user1', '1号楼');
INSERT INTO `article` VALUES (6, '测试案例5', '这个用于测试，检查功能完整性', '2019-11-16 18:18:55', 'user1', '1号楼');
INSERT INTO `article` VALUES (7, '测试案例6', '这个用于测试，检查功能完整性', '2019-11-16 18:18:55', 'user1', '1号楼');

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
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, '测试评论1', '2019-11-16 18:18:54', 'user1', '测试案例1');
INSERT INTO `comment` VALUES (2, '测试评论2', '2019-11-16 18:18:55', 'user1', '测试案例1');
INSERT INTO `comment` VALUES (3, '测试评论3', '2019-11-16 18:18:56', 'user1', '测试案例1');
INSERT INTO `comment` VALUES (4, '测试评论4', '2019-11-16 18:18:57', 'user1', '测试案例1');
INSERT INTO `comment` VALUES (5, '测试评论5', '2019-11-16 18:18:58', 'user1', '测试案例1');
INSERT INTO `comment` VALUES (6, '测试评论6', '2019-11-16 18:18:59', 'user1', '测试案例1');
INSERT INTO `comment` VALUES (7, '测试评论7', '2019-11-16 18:19:00', 'user1', '测试案例1');

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
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (0001, 'admin', 'admin', 'test1', 30, '13800000000', '1', '这个人很懒，暂时还没有介绍', '2010-10-1', '管理员');
INSERT INTO `user` VALUES (0025, 'user25', '11111111111111111', '591276457@qq.com', 20, '13753518074', '1', '这个人很懒，暂时还没有介绍', '1999-1-1', '用户');
INSERT INTO `user` VALUES (0028, 'user28', '33333333333', 'zzk4093977@gmail.com', 20, '13753518074', '1', '这个人很懒，暂时还没有介绍', '1999-1-1', '用户');
INSERT INTO `user` VALUES (0034, 'user34', '121212121212121', '121212@qq.com', 25, '13753518074', '1', '这个人很懒，暂时还没有介绍', '1990-11-10', '用户');
INSERT INTO `user` VALUES (0035, 'user35', '123456789', '1111111111@qq.com', 20, '6128880580', '1', '这个人很懒，暂时还没有介绍', '1999-1-1', '用户');
INSERT INTO `user` VALUES (0043, 'user1000', '123', '123@qq.com', 0, '0', '未知', '这个人很懒，暂时还没有介绍', '位置', '用户');

SET FOREIGN_KEY_CHECKS = 1;
