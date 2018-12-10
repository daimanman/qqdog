/*
Navicat MySQL Data Transfer

Source Server         : DXM
Source Server Version : 50704
Source Host           : localhost:3306
Source Database       : cdata_2

Target Server Type    : MYSQL
Target Server Version : 50704
File Encoding         : 65001

Date: 2018-12-07 21:38:03
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
-- Records of qemot_comment
-- ----------------------------

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
-- Records of qemot_comment_reply
-- ----------------------------

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
-- Records of qemot_info
-- ----------------------------

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
-- Records of qemot_pic
-- ----------------------------

-- ----------------------------
-- Table structure for qimg_video
-- ----------------------------
DROP TABLE IF EXISTS `qimg_video`;
CREATE TABLE `qimg_video` (
  `id` bigint(20) DEFAULT NULL,
  `uid` bigint(20) DEFAULT NULL,
  `img_id` bigint(20) DEFAULT NULL,
  `pickey` varchar(100) DEFAULT NULL,
  `cover_height` double(5,0) DEFAULT NULL,
  `cover_width` double(5,0) DEFAULT NULL,
  `duration` int(10) DEFAULT NULL,
  `format` varchar(50) DEFAULT NULL,
  `size` bigint(10) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  `vid` varchar(100) DEFAULT NULL,
  `video_share_h5` varchar(300) DEFAULT NULL,
  `video_type` int(2) DEFAULT NULL,
  `video_url` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of qimg_video
-- ----------------------------

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
-- Records of qmsg_info
-- ----------------------------

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
-- Records of qmsg_info_reply
-- ----------------------------

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
  `lloc` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `sloc` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `raw` varchar(255) DEFAULT NULL,
  `raw_upload` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qphoto_img
-- ----------------------------

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
-- Records of qphoto_info
-- ----------------------------

-- ----------------------------
-- Table structure for qq
-- ----------------------------
DROP TABLE IF EXISTS `qq`;
CREATE TABLE `qq` (
  `uid` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

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
INSERT INTO `qq` VALUES ('506518319 ', '411');
INSERT INTO `qq` VALUES ('259574694', '411');
INSERT INTO `qq` VALUES ('2246566657 ', '411');
INSERT INTO `qq` VALUES ('1700057380', '411');
INSERT INTO `qq` VALUES ('480801170', '411');

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
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qsession_info
-- ----------------------------
INSERT INTO `qsession_info` VALUES ('1018793423', '{\"Cookie\":\"RK=KajNbU9QFQ; ptcz=c66cbd2992feb7c86370ba8becb4a3333d1619f03fbca425be49cc757c4ebcd8; pgv_pvid=8632765126; qz_screen=1366x768; QZ_FE_WEBP_SUPPORT=1; pgv_pvi=4630548480; __Q_w_s__QZN_TodoMsgCnt=1; __Q_w_s_hat_seed=1; randomSeed=166021; cpu_performance_v8=45; pt2gguin=o1018793423; uin=o1018793423; skey=@tMjf0EOvf; p_uin=o1018793423; pt4_token=WSKs1Ob0o4rAud-SYfrc4Qk1fFAN--zg4zgpmPoXOH4_; p_skey=dQ0tlCfS0F-SFU0eXucQE1aKu2OMNeSvORnlh4jTxQY_; x-stgw-ssl-info=14afebf7a502c3f3ffc51965ffce581b|0.150|1544186889.701|1|.|Y|TLSv1.2|ECDHE-RSA-AES128-GCM-SHA256|52000|h2|0; pgv_si=s6147768320; pgv_info=ssid=s7124647185; Loading=Yes\",\"Accept\":\"*/*\",\"User-Agent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36\",\"Referer\":\"https://user.qzone.qq.com/1018793423/infocenter\",\"Accept-Encoding\":\"gzip, deflate, br\",\"Accept-Language\":\"zh-CN,zh;q=0.9\"}', '{\"board_id\":\"2420\",\"uin\":\"1018793423\",\"qzonetoken\":\"ca13ef47c1e7bc6722c63fe0693eb258a0c826172b2db05d4b74ccc3c6d90210df817e61a9154b2c\",\"need_cnt\":\"65536\",\"g_tk\":\"659354116\"}', '2018-12-07 20:49:31', '2018-12-07 20:49:31', '0', null);

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
-- Records of qtask_info
-- ----------------------------
INSERT INTO `qtask_info` VALUES ('2243414853', null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `qtask_info` VALUES ('3079693469', null, null, null, null, null, null, null, null, null, null, null, '0');

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
-- Records of quser_info
-- ----------------------------

-- ----------------------------
-- Table structure for quser_info_n
-- ----------------------------
DROP TABLE IF EXISTS `quser_info_n`;
CREATE TABLE `quser_info_n` (
  `uid` bigint(20) NOT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of quser_info_n
-- ----------------------------
