/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : 127.0.0.1:3306
 Source Schema         : bdata_1

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 21/11/2018 12:22:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for qq
-- ----------------------------
DROP TABLE IF EXISTS `qq`;
CREATE TABLE `qq`  (
  `uid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qq
-- ----------------------------
INSERT INTO `qq` VALUES ('168033989', '411');
INSERT INTO `qq` VALUES ('1143886181', '411');
INSERT INTO `qq` VALUES ('1748723478', '411');
INSERT INTO `qq` VALUES ('1843594995', '26');
INSERT INTO `qq` VALUES ('1934843846', '411');
INSERT INTO `qq` VALUES ('2083499983', '411');
INSERT INTO `qq` VALUES ('2109529257', '26');
INSERT INTO `qq` VALUES ('2684839141', '2635');
INSERT INTO `qq` VALUES ('2875593464', '411');
INSERT INTO `qq` VALUES ('2972638700', '411');
INSERT INTO `qq` VALUES ('3079693469', '411');
INSERT INTO `qq` VALUES ('3120418574', '411');
INSERT INTO `qq` VALUES ('3235875136', '411');
INSERT INTO `qq` VALUES ('3356337217', '411');
INSERT INTO `qq` VALUES ('3565759691', '411s');
INSERT INTO `qq` VALUES ('1276742580', '987654321manxiao');
INSERT INTO `qq` VALUES ('1332466059 ', '411');
INSERT INTO `qq` VALUES ('2433276560 ', '411');
INSERT INTO `qq` VALUES ('848443394 ', '411');

SET FOREIGN_KEY_CHECKS = 1;
