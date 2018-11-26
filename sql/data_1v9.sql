/*
Navicat MySQL Data Transfer

Source Server         : thinkpad
Source Server Version : 50637
Source Host           : 192.168.1.103:3306
Source Database       : data_4

Target Server Type    : MYSQL
Target Server Version : 50637
File Encoding         : 65001

Date: 2018-11-26 21:36:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for qsession_info
-- ----------------------------
DROP TABLE IF EXISTS `qsession_info`;
CREATE TABLE `qsession_info` (
  `uid` varchar(20) CHARACTER SET utf8mb4 NOT NULL,
  `cookie` text CHARACTER SET utf8mb4,
  `params` text CHARACTER SET utf8mb4,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `flag` int(2) DEFAULT NULL,
  `msg` varchar(500) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qsession_info
-- ----------------------------
