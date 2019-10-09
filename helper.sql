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

 Date: 09/10/2019 10:30:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (0001, 'admin', 'admin', 'test1', 30, '13800000000', '1', '这个人很懒，暂时还没有介绍', '2010-10-1', '管理员');
INSERT INTO `user` VALUES (0015, 'user1', '1234567890', '1111111111@qq.com', 20, '17000000000', '1', '这个人很懒，暂时还没有介绍', '1999-1-1', '用户');
INSERT INTO `user` VALUES (0016, 'user2', '1234567890', '1111111111@qq.com', 20, '13600000000', '1', '这个人很懒，暂时还没有介绍', '1999-1-1', '用户');

SET FOREIGN_KEY_CHECKS = 1;
