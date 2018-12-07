/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50723
Source Host           : 127.0.0.1:3306
Source Database       : bdata_3

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2018-12-07 11:05:21
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
INSERT INTO `qphoto_info` VALUES ('5730152210039808', '1', '0', '11111111', '4294967295', '0', '1387681468', '', '1', 'V12VniOF4eo0zR', '1531036321', '1542643569', '手机相册', '4294967295', 'http://b389.photo.store.qq.com/psb?/V12VniOF4eo0zR/Vj6naHnelgHNkw08e4wGRW5Ple9Z1X2GRCb1XQG9Za0!/a/dIUBAAAAAAAA', '1', '332', '0', '2243414853', '2018-12-07 10:29:49', '332');
INSERT INTO `qphoto_info` VALUES ('5730152210039809', '1', '0', '10000000', '106', '17', '1409445499', '2014/8/31早晨散步所拍', '0', 'V12VniOF40mHG4', '1409445602', '1525960965', '东湖公园闻桂花', '0', 'http://b387.photo.store.qq.com/psb?/V12VniOF40mHG4/4sE8rnzxst*iSl*BVlnyt2JOmlQ2mINhSI9iVgkiQkE!/a/dOifseZzBAAA', '1', '9', '0', '2243414853', '2018-12-07 10:29:49', '9');
INSERT INTO `qphoto_info` VALUES ('5730152210039810', '1', '6', '10000000', '100', '6', '1463886849', '2016.5.21在清泉敬老院看到一片向日葵', '0', 'V12VniOF3Aq7nw', '1463887211', '1508063122', '清泉向日癸', '0', 'http://b267.photo.store.qq.com/psb?/V12VniOF3Aq7nw/IGRQRwWmwHL.Uayd924AZIGuLzoWtk*2J*wwFbxvwS0!/a/dAsBAAAAAAAA', '1', '10', '6', '2243414853', '2018-12-07 10:29:49', '10');

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
INSERT INTO `qsession_info` VALUES ('1700057380', '{\"Cookie\":\"pgv_pvid=4558450288; RK=WbiFaU9QQw; ptcz=d423ebe94d6a17de39bb5f22c4cb78d3b41959d3b007ee44f794d6baae3459cf; pgv_pvi=2434416640; qz_screen=1536x864; QZ_FE_WEBP_SUPPORT=1; tvfe_boss_uuid=35ad5a42718df061; AMCV_248F210755B762187F000101%40AdobeOrg=-1891778711%7CMCIDTS%7C17717%7CMCMID%7C63191310701835526532311349328044808507%7CMCAAMLH-1531281679%7C11%7CMCAAMB-1531281679%7CRKhpRz8krg2tLO6pguXWp5olkAcUniQYPHaMWWgdJ3xzPWQmdj0y%7CMCOPTOUT-1530684079s%7CNONE%7CMCAID%7CNONE%7CMCSYNCSOP%7C411-17724%7CvVersion%7C2.4.0; __Q_w_s_hat_seed=1; _ga=amp-JC1Sf57ydpFesQlxbjbt9Q; 3g_guest_id=-8690779826006523904; g_ut=2; __Q_w_s__QZN_TodoMsgCnt=1; randomSeed=885641; ptui_loginuin=1143886181; luin=o2083499983; lskey=00010000f71b573d00533e14d0a36359cb4f9c35aa1b6a214744a2774050062c16d42f34f8a60d5e6415a538; o_cookie=1018793423; pac_uid=1_1018793423; Loading=Yes; osstat=7; __layoutStat=7; ptisp=ctc; pgv_info=ssid=s8038443676; 848443394_todaycount=0; 848443394_totalcount=0; rv2=802C8C4E9F9F846AB2FDC4BA146B38A8E8B8B848375D9070FB; property20=EE68E447042507E755F81360E9C378FF0FCA045AE72849B2CF247C123EA6E4CDC0A125DE39A51513; pgv_si=s7601724416; pt2gguin=o1700057380; uin=o1700057380; skey=@738A29Rrg; p_uin=o1700057380; 1700057380_todaycount=0; 1700057380_totalcount=0; cpu_performance_v8=15; pt4_token=QwhtPkAOEaBQQd5OxcTRf3TYIGomOAvdXz7bnKji67k_; p_skey=q3DpwVa0iWBXwwvR6xBrmA6J8Na7yC2B6kSvfqH7JQk_; x-stgw-ssl-info=af4c5cba82cba9a9e949c0cced7f5663|0.105|1544149543.215|1|r|I|TLSv1.2|ECDHE-RSA-AES128-GCM-SHA256|51500|h2|0\",\"Accept\":\"*/*\",\"User-Agent\":\"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36\",\"Referer\":\"https://user.qzone.qq.com/1700057380?source=panelstar\",\"Accept-Encoding\":\"gzip, deflate, br\",\"Accept-Language\":\"zh-CN,zh;q=0.9\"}', '{\"board_id\":\"2685\",\"uin\":\"1700057380\",\"qzonetoken\":\"fe01d41cab22e2411b719a4cae37a9db8917c55deb082d7275e484d20ed33893bff8e3f6d849a4985f99db\",\"need_cnt\":\"2\",\"g_tk\":\"1688911694\"}', '2018-12-07 10:25:47', '2018-12-07 10:25:47', '0', null);
INSERT INTO `qsession_info` VALUES ('848443394', '{\"Cookie\":\"pgv_pvid=4558450288; RK=WbiFaU9QQw; ptcz=d423ebe94d6a17de39bb5f22c4cb78d3b41959d3b007ee44f794d6baae3459cf; pgv_pvi=2434416640; qz_screen=1536x864; QZ_FE_WEBP_SUPPORT=1; tvfe_boss_uuid=35ad5a42718df061; AMCV_248F210755B762187F000101%40AdobeOrg=-1891778711%7CMCIDTS%7C17717%7CMCMID%7C63191310701835526532311349328044808507%7CMCAAMLH-1531281679%7C11%7CMCAAMB-1531281679%7CRKhpRz8krg2tLO6pguXWp5olkAcUniQYPHaMWWgdJ3xzPWQmdj0y%7CMCOPTOUT-1530684079s%7CNONE%7CMCAID%7CNONE%7CMCSYNCSOP%7C411-17724%7CvVersion%7C2.4.0; __Q_w_s_hat_seed=1; _ga=amp-JC1Sf57ydpFesQlxbjbt9Q; 3g_guest_id=-8690779826006523904; g_ut=2; __Q_w_s__QZN_TodoMsgCnt=1; randomSeed=885641; ptui_loginuin=1143886181; luin=o2083499983; lskey=00010000f71b573d00533e14d0a36359cb4f9c35aa1b6a214744a2774050062c16d42f34f8a60d5e6415a538; o_cookie=1018793423; pac_uid=1_1018793423; Loading=Yes; osstat=7; __layoutStat=7; ptisp=ctc; pgv_info=ssid=s8038443676; 848443394_todaycount=0; 848443394_totalcount=0; rv2=802C8C4E9F9F846AB2FDC4BA146B38A8E8B8B848375D9070FB; property20=EE68E447042507E755F81360E9C378FF0FCA045AE72849B2CF247C123EA6E4CDC0A125DE39A51513; pgv_si=s7601724416; 1700057380_todaycount=0; 1700057380_totalcount=0; cpu_performance_v8=15; pt2gguin=o0848443394; uin=o0848443394; skey=@9wHPf43qF; p_uin=o0848443394; pt4_token=oZZjxDAlTlklARtBaUcWFJ7kPDK75qECN9FH5ynR3JI_; p_skey=yGAtwZj-KRqtg0FPyk0JMLr6Vh56F7khKgV3fnibhGI_; x-stgw-ssl-info=6e0e7e4cf1bc9db4fb1bd0592a13d600|0.137|1544149547.441|16|r|I|TLSv1.2|ECDHE-RSA-AES128-GCM-SHA256|47500|h2|0\",\"Accept\":\"*/*\",\"User-Agent\":\"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36\",\"Referer\":\"https://user.qzone.qq.com/848443394?source=panelstar\",\"Accept-Encoding\":\"gzip, deflate, br\",\"Accept-Language\":\"zh-CN,zh;q=0.9\"}', '{\"board_id\":\"2685\",\"uin\":\"848443394\",\"qzonetoken\":\"31dde84c8412ccae27ec804f6e091f5b7772c6ab506ebf32b89ff14d2398a158bf68164d581855e4c5d2\",\"need_cnt\":\"2\",\"g_tk\":\"1896401018\"}', '2018-12-07 10:25:51', '2018-12-07 10:25:51', '0', null);

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
