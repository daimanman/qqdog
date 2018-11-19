/*
Navicat MySQL Data Transfer

Source Server         : thinkpad
Source Server Version : 50637
Source Host           : 192.168.1.101:3306
Source Database       : data_2

Target Server Type    : MYSQL
Target Server Version : 50637
File Encoding         : 65001

Date: 2018-11-19 19:53:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for qemot_comment
-- ----------------------------
DROP TABLE IF EXISTS `qemot_comment`;
CREATE TABLE `qemot_comment` (
  `id` bigint(20) NOT NULL,
  `emot_id` bigint(20) DEFAULT NULL,
  `tid` varchar(50) DEFAULT NULL,
  `uid` varchar(100) DEFAULT NULL,
  `muid` varchar(100) DEFAULT NULL,
  `mname` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4,
  `create_time2` varchar(50) DEFAULT NULL,
  `create_time` varchar(50) DEFAULT NULL,
  `reply_num` varchar(3) DEFAULT NULL,
  `create_time1` varchar(50) DEFAULT NULL,
  `create_gmt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for qemot_comment_reply
-- ----------------------------
DROP TABLE IF EXISTS `qemot_comment_reply`;
CREATE TABLE `qemot_comment_reply` (
  `id` bigint(20) NOT NULL,
  `comment_id` bigint(20) DEFAULT NULL COMMENT '评论id',
  `uid` varchar(250) DEFAULT NULL COMMENT '评论者id',
  `content` text CHARACTER SET utf8mb4,
  `create_time0` varchar(50) DEFAULT NULL,
  `create_time2` varchar(50) DEFAULT NULL,
  `create_time3` varchar(35) DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `source_name` varchar(255) DEFAULT NULL,
  `tid` varchar(50) DEFAULT NULL,
  `create_gmt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for qemot_info
-- ----------------------------
DROP TABLE IF EXISTS `qemot_info`;
CREATE TABLE `qemot_info` (
  `id` bigint(20) NOT NULL,
  `cmtnum` int(4) DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4,
  `createTime` varchar(35) DEFAULT NULL,
  `created_time` varchar(20) DEFAULT NULL,
  `editMask` varchar(35) DEFAULT NULL,
  `fwdnum` int(3) DEFAULT NULL,
  `name` varchar(300) CHARACTER SET utf8mb4 DEFAULT NULL,
  `pictotal` int(4) DEFAULT NULL,
  `right` varchar(50) DEFAULT NULL,
  `rt_sum` varchar(10) DEFAULT NULL,
  `secret` varchar(10) DEFAULT NULL,
  `tid` varchar(100) DEFAULT NULL,
  `ugc_right` varchar(100) DEFAULT NULL,
  `uid` varchar(30) DEFAULT NULL,
  `wbid` varchar(100) DEFAULT NULL,
  `lbs_id` varchar(50) DEFAULT NULL,
  `lbs_idname` varchar(255) DEFAULT NULL,
  `lbs_name` varchar(255) DEFAULT NULL,
  `lbs_pos_x` varchar(30) DEFAULT NULL,
  `lbs_pos_y` varchar(30) DEFAULT NULL,
  `source_name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `create_gmt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for qemot_pic
-- ----------------------------
DROP TABLE IF EXISTS `qemot_pic`;
CREATE TABLE `qemot_pic` (
  `id` bigint(20) NOT NULL,
  `tid` varchar(100) DEFAULT NULL,
  `emot_id` bigint(20) DEFAULT NULL,
  `uid` varchar(20) DEFAULT NULL,
  `height` varchar(6) DEFAULT NULL,
  `width` varchar(6) DEFAULT NULL,
  `url1` varchar(300) DEFAULT NULL,
  `url2` varchar(300) DEFAULT NULL,
  `url3` varchar(300) DEFAULT NULL,
  `create_gmt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for qmsg_info
-- ----------------------------
DROP TABLE IF EXISTS `qmsg_info`;
CREATE TABLE `qmsg_info` (
  `id` bigint(20) NOT NULL,
  `msgid` varchar(20) DEFAULT NULL,
  `secret` varchar(20) DEFAULT NULL,
  `pasterid` varchar(50) DEFAULT NULL,
  `bmp` varchar(255) DEFAULT NULL,
  `pubtime` varchar(100) DEFAULT NULL,
  `modifytime` varchar(100) DEFAULT NULL,
  `effect` varchar(10) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `uin` varchar(50) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `capacity` varchar(255) DEFAULT NULL,
  `html_content` longtext,
  `uid` varchar(50) DEFAULT NULL,
  `create_gmt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for qmsg_info_reply
-- ----------------------------
DROP TABLE IF EXISTS `qmsg_info_reply`;
CREATE TABLE `qmsg_info_reply` (
  `id` bigint(20) NOT NULL,
  `content` mediumtext,
  `time` varchar(50) DEFAULT NULL,
  `nick` varchar(255) DEFAULT NULL,
  `msg_id` bigint(20) DEFAULT NULL,
  `create_gmt` datetime DEFAULT NULL,
  `uin` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for qphoto_img
-- ----------------------------
DROP TABLE IF EXISTS `qphoto_img`;
CREATE TABLE `qphoto_img` (
  `id` bigint(20) NOT NULL,
  `batchId` varchar(20) DEFAULT NULL,
  `browser` varchar(20) DEFAULT NULL,
  `cameratype` varchar(255) DEFAULT NULL,
  `cp_flag` varchar(20) DEFAULT NULL,
  `cp_x` varchar(20) DEFAULT NULL,
  `cp_y` varchar(20) DEFAULT NULL,
  `desc` text,
  `forum` varchar(20) DEFAULT NULL,
  `frameno` varchar(100) DEFAULT NULL,
  `height` varchar(20) DEFAULT NULL,
  `is_video` varchar(20) DEFAULT NULL,
  `modifytime` varchar(50) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `origin_url` text,
  `owner` varchar(50) DEFAULT NULL,
  `ownername` varchar(100) DEFAULT NULL,
  `photocubage` varchar(50) DEFAULT NULL,
  `phototype` varchar(25) DEFAULT NULL,
  `picrefer` varchar(20) DEFAULT NULL,
  `platformId` varchar(20) DEFAULT NULL,
  `platformSubId` varchar(20) DEFAULT NULL,
  `poiname` text,
  `pre` varchar(300) DEFAULT NULL,
  `rawshoottime` varchar(50) DEFAULT NULL,
  `uploadtime` varchar(50) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `width` varchar(20) DEFAULT NULL,
  `photo_id` bigint(20) DEFAULT NULL,
  `uid` varchar(20) DEFAULT NULL,
  `create_gmt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for qphoto_info
-- ----------------------------
DROP TABLE IF EXISTS `qphoto_info`;
CREATE TABLE `qphoto_info` (
  `id` bigint(20) NOT NULL,
  `allow_access` varchar(20) DEFAULT NULL,
  `anonymity` varchar(20) DEFAULT NULL,
  `bitmap` varchar(50) DEFAULT NULL,
  `classid` varchar(20) DEFAULT NULL,
  `comment` varchar(20) DEFAULT NULL,
  `createtime` varchar(50) DEFAULT NULL,
  `desc` text,
  `handset` varchar(100) DEFAULT NULL,
  `topicid` varchar(255) DEFAULT NULL,
  `lastuploadtime` varchar(50) DEFAULT NULL,
  `modifytime` varchar(50) DEFAULT NULL,
  `name` text,
  `order` varchar(20) DEFAULT NULL,
  `pre` text,
  `priv` varchar(20) DEFAULT NULL,
  `totalnum` int(10) DEFAULT NULL,
  `viewtype` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `uid` varchar(20) DEFAULT NULL,
  `create_gmt` datetime DEFAULT NULL,
  `getnum` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for qq
-- ----------------------------
DROP TABLE IF EXISTS `qq`;
CREATE TABLE `qq` (
  `uid` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
  `msg` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for qtask_info
-- ----------------------------
DROP TABLE IF EXISTS `qtask_info`;
CREATE TABLE `qtask_info` (
  `uid` bigint(20) NOT NULL,
  `msg_total` int(10) DEFAULT NULL COMMENT 'msg总数',
  `msg_page` int(10) DEFAULT NULL,
  `msg_get` int(10) DEFAULT NULL COMMENT '已经get的条数',
  `msg_start` int(1) DEFAULT NULL COMMENT '是否开始了0:未开始 1:已经开始',
  `emot_total` int(10) DEFAULT NULL,
  `emot_page` int(10) DEFAULT NULL,
  `emot_get` int(10) DEFAULT NULL,
  `emot_start` int(1) DEFAULT NULL,
  `photo_total` int(10) DEFAULT NULL,
  `photo_get` int(10) DEFAULT NULL,
  `photo_page` int(10) DEFAULT NULL,
  `photo_start` int(10) DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for quser_info
-- ----------------------------
DROP TABLE IF EXISTS `quser_info`;
CREATE TABLE `quser_info` (
  `id` bigint(20) NOT NULL,
  `uid` bigint(20) DEFAULT NULL,
  `nickname` varchar(200) CHARACTER SET utf8mb4 DEFAULT NULL,
  `sex` varchar(5) DEFAULT NULL,
  `sex_type` varchar(5) DEFAULT NULL,
  `age` varchar(5) DEFAULT NULL,
  `birthyear` varchar(16) DEFAULT NULL,
  `birthday` varchar(16) DEFAULT NULL,
  `bloodtype` varchar(5) DEFAULT NULL,
  `country` varchar(25) DEFAULT NULL,
  `province` varchar(25) DEFAULT NULL,
  `city` varchar(25) DEFAULT NULL,
  `career` varchar(255) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `hc` varchar(30) DEFAULT NULL,
  `hp` varchar(30) DEFAULT NULL,
  `hco` varchar(30) DEFAULT NULL,
  `marriage` varchar(6) DEFAULT NULL,
  `msg_num` int(10) DEFAULT NULL,
  `emot_num` int(10) DEFAULT NULL COMMENT '说说num',
  `flag` int(2) DEFAULT NULL,
  `photo_num` int(10) DEFAULT NULL,
  `img_num` int(10) DEFAULT NULL,
  `create_gmt` datetime DEFAULT NULL,
  `spacename` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '空间名称',
  `describe` varchar(255) DEFAULT NULL COMMENT '空间签名',
  `cco` varchar(255) DEFAULT NULL COMMENT '公司国',
  `cp` varchar(255) DEFAULT NULL COMMENT '公司省',
  `cc` varchar(255) DEFAULT NULL COMMENT '公司市',
  `cb` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '区域',
  `signature` mediumtext CHARACTER SET utf8mb4,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='空间name';

-- ----------------------------
-- Table structure for quser_info_n
-- ----------------------------
DROP TABLE IF EXISTS `quser_info_n`;
CREATE TABLE `quser_info_n` (
  `uid` bigint(20) NOT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
