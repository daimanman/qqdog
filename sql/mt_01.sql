/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50723
Source Host           : 127.0.0.1:3306
Source Database       : mt_01

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-04-19 10:00:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mt_bt
-- ----------------------------
DROP TABLE IF EXISTS `mt_bt`;
CREATE TABLE `mt_bt` (
  `id` bigint(20) NOT NULL,
  `title` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  `tag` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `hash` varchar(255) DEFAULT NULL,
  `dml_date` varchar(100) DEFAULT NULL,
  `link` varchar(500) DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  `sort_name` varchar(255) DEFAULT NULL,
  `torrent` text,
  `tid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for mt_city_cookie
-- ----------------------------
DROP TABLE IF EXISTS `mt_city_cookie`;
CREATE TABLE `mt_city_cookie` (
  `cookie` text,
  `city_id` int(11) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `update_date` datetime DEFAULT NULL,
  `flag` int(2) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for mt_city_info
-- ----------------------------
DROP TABLE IF EXISTS `mt_city_info`;
CREATE TABLE `mt_city_info` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pinyin` varchar(255) DEFAULT NULL,
  `acronym` varchar(255) DEFAULT NULL,
  `rank` varchar(255) DEFAULT NULL,
  `firstchar` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `crawl_flag` int(2) DEFAULT '0' COMMENT '是否爬取过 1：是 0：否',
  `center_flag` int(2) DEFAULT '0' COMMENT '是否省会城市 1：是 0：否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for mt_city_info_area
-- ----------------------------
DROP TABLE IF EXISTS `mt_city_info_area`;
CREATE TABLE `mt_city_info_area` (
  `id` int(11) NOT NULL,
  `city_id` int(11) DEFAULT NULL,
  `city_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `meishi_num` int(10) DEFAULT NULL,
  `has_child` int(1) DEFAULT NULL COMMENT '1:ok 0：no',
  `crawl_flag` int(1) DEFAULT NULL,
  `get_num` int(10) DEFAULT NULL,
  `total_pn` int(4) DEFAULT NULL,
  `get_pn` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `city_id_idx` (`city_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for mt_img
-- ----------------------------
DROP TABLE IF EXISTS `mt_img`;
CREATE TABLE `mt_img` (
  `item_id` bigint(20) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '0 detail img',
  `url` varchar(255) DEFAULT NULL,
  `city_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for mt_meishi_cmt
-- ----------------------------
DROP TABLE IF EXISTS `mt_meishi_cmt`;
CREATE TABLE `mt_meishi_cmt` (
  `id` bigint(20) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_url` varchar(255) DEFAULT NULL,
  `avg_price` double DEFAULT NULL,
  `comment` text,
  `merchant_comment` text,
  `pic_urls` text,
  `comment_time` bigint(20) DEFAULT NULL,
  `reply_cnt` int(3) DEFAULT NULL,
  `zan_cnt` int(3) DEFAULT NULL,
  `read_cnt` int(3) DEFAULT NULL,
  `hilignt` varchar(255) DEFAULT NULL,
  `user_level` int(3) DEFAULT NULL,
  `user_id` bigint(15) DEFAULT NULL,
  `utype` int(3) DEFAULT NULL,
  `star` int(4) DEFAULT NULL,
  `quality` tinyint(4) DEFAULT NULL,
  `already_zzz` tinyint(4) DEFAULT NULL,
  `review_id` bigint(15) DEFAULT NULL,
  `menu` varchar(255) DEFAULT NULL,
  `did` bigint(15) DEFAULT NULL,
  `deal_endtime` bigint(20) DEFAULT NULL,
  `anonymous` tinyint(4) DEFAULT NULL,
  `poi_id` bigint(20) DEFAULT NULL,
  `city_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for mt_meishi_cmt_1
-- ----------------------------
DROP TABLE IF EXISTS `mt_meishi_cmt_1`;
CREATE TABLE `mt_meishi_cmt_1` (
  `id` bigint(20) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_url` varchar(255) DEFAULT NULL,
  `avg_price` double DEFAULT NULL,
  `comment` text,
  `merchant_comment` text,
  `pic_urls` text,
  `comment_time` bigint(20) DEFAULT NULL,
  `reply_cnt` int(3) DEFAULT NULL,
  `zan_cnt` int(3) DEFAULT NULL,
  `read_cnt` int(3) DEFAULT NULL,
  `hilignt` varchar(255) DEFAULT NULL,
  `user_level` int(3) DEFAULT NULL,
  `user_id` bigint(15) DEFAULT NULL,
  `utype` int(3) DEFAULT NULL,
  `star` int(4) DEFAULT NULL,
  `quality` tinyint(4) DEFAULT NULL,
  `already_zzz` tinyint(4) DEFAULT NULL,
  `review_id` bigint(15) DEFAULT NULL,
  `menu` varchar(255) DEFAULT NULL,
  `did` bigint(15) DEFAULT NULL,
  `deal_endtime` bigint(20) DEFAULT NULL,
  `anonymous` tinyint(4) DEFAULT NULL,
  `poi_id` bigint(20) DEFAULT NULL,
  `city_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for mt_meishi_cmt_10
-- ----------------------------
DROP TABLE IF EXISTS `mt_meishi_cmt_10`;
CREATE TABLE `mt_meishi_cmt_10` (
  `id` bigint(20) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_url` varchar(255) DEFAULT NULL,
  `avg_price` double DEFAULT NULL,
  `comment` text,
  `merchant_comment` text,
  `pic_urls` text,
  `comment_time` bigint(20) DEFAULT NULL,
  `reply_cnt` int(3) DEFAULT NULL,
  `zan_cnt` int(3) DEFAULT NULL,
  `read_cnt` int(3) DEFAULT NULL,
  `hilignt` varchar(255) DEFAULT NULL,
  `user_level` int(3) DEFAULT NULL,
  `user_id` bigint(15) DEFAULT NULL,
  `utype` int(3) DEFAULT NULL,
  `star` int(4) DEFAULT NULL,
  `quality` tinyint(4) DEFAULT NULL,
  `already_zzz` tinyint(4) DEFAULT NULL,
  `review_id` bigint(15) DEFAULT NULL,
  `menu` varchar(255) DEFAULT NULL,
  `did` bigint(15) DEFAULT NULL,
  `deal_endtime` bigint(20) DEFAULT NULL,
  `anonymous` tinyint(4) DEFAULT NULL,
  `poi_id` bigint(20) DEFAULT NULL,
  `city_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for mt_meishi_cmt_20
-- ----------------------------
DROP TABLE IF EXISTS `mt_meishi_cmt_20`;
CREATE TABLE `mt_meishi_cmt_20` (
  `id` bigint(20) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_url` varchar(255) DEFAULT NULL,
  `avg_price` double DEFAULT NULL,
  `comment` text,
  `merchant_comment` text,
  `pic_urls` text,
  `comment_time` bigint(20) DEFAULT NULL,
  `reply_cnt` int(3) DEFAULT NULL,
  `zan_cnt` int(3) DEFAULT NULL,
  `read_cnt` int(3) DEFAULT NULL,
  `hilignt` varchar(255) DEFAULT NULL,
  `user_level` int(3) DEFAULT NULL,
  `user_id` bigint(15) DEFAULT NULL,
  `utype` int(3) DEFAULT NULL,
  `star` int(4) DEFAULT NULL,
  `quality` tinyint(4) DEFAULT NULL,
  `already_zzz` tinyint(4) DEFAULT NULL,
  `review_id` bigint(15) DEFAULT NULL,
  `menu` varchar(255) DEFAULT NULL,
  `did` bigint(15) DEFAULT NULL,
  `deal_endtime` bigint(20) DEFAULT NULL,
  `anonymous` tinyint(4) DEFAULT NULL,
  `poi_id` bigint(20) DEFAULT NULL,
  `city_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for mt_meishi_cmt_235
-- ----------------------------
DROP TABLE IF EXISTS `mt_meishi_cmt_235`;
CREATE TABLE `mt_meishi_cmt_235` (
  `id` bigint(20) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_url` varchar(255) DEFAULT NULL,
  `avg_price` double DEFAULT NULL,
  `comment` text,
  `merchant_comment` text,
  `pic_urls` text,
  `comment_time` bigint(20) DEFAULT NULL,
  `reply_cnt` int(3) DEFAULT NULL,
  `zan_cnt` int(3) DEFAULT NULL,
  `read_cnt` int(3) DEFAULT NULL,
  `hilignt` varchar(255) DEFAULT NULL,
  `user_level` int(3) DEFAULT NULL,
  `user_id` bigint(15) DEFAULT NULL,
  `utype` int(3) DEFAULT NULL,
  `star` int(4) DEFAULT NULL,
  `quality` tinyint(4) DEFAULT NULL,
  `already_zzz` tinyint(4) DEFAULT NULL,
  `review_id` bigint(15) DEFAULT NULL,
  `menu` varchar(255) DEFAULT NULL,
  `did` bigint(15) DEFAULT NULL,
  `deal_endtime` bigint(20) DEFAULT NULL,
  `anonymous` tinyint(4) DEFAULT NULL,
  `poi_id` bigint(20) DEFAULT NULL,
  `city_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for mt_meishi_cmt_236
-- ----------------------------
DROP TABLE IF EXISTS `mt_meishi_cmt_236`;
CREATE TABLE `mt_meishi_cmt_236` (
  `id` bigint(20) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_url` varchar(255) DEFAULT NULL,
  `avg_price` double DEFAULT NULL,
  `comment` text,
  `merchant_comment` text,
  `pic_urls` text,
  `comment_time` bigint(20) DEFAULT NULL,
  `reply_cnt` int(3) DEFAULT NULL,
  `zan_cnt` int(3) DEFAULT NULL,
  `read_cnt` int(3) DEFAULT NULL,
  `hilignt` varchar(255) DEFAULT NULL,
  `user_level` int(3) DEFAULT NULL,
  `user_id` bigint(15) DEFAULT NULL,
  `utype` int(3) DEFAULT NULL,
  `star` int(4) DEFAULT NULL,
  `quality` tinyint(4) DEFAULT NULL,
  `already_zzz` tinyint(4) DEFAULT NULL,
  `review_id` bigint(15) DEFAULT NULL,
  `menu` varchar(255) DEFAULT NULL,
  `did` bigint(15) DEFAULT NULL,
  `deal_endtime` bigint(20) DEFAULT NULL,
  `anonymous` tinyint(4) DEFAULT NULL,
  `poi_id` bigint(20) DEFAULT NULL,
  `city_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for mt_meishi_cmt_30
-- ----------------------------
DROP TABLE IF EXISTS `mt_meishi_cmt_30`;
CREATE TABLE `mt_meishi_cmt_30` (
  `id` bigint(20) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_url` varchar(255) DEFAULT NULL,
  `avg_price` double DEFAULT NULL,
  `comment` text,
  `merchant_comment` text,
  `pic_urls` text,
  `comment_time` bigint(20) DEFAULT NULL,
  `reply_cnt` int(3) DEFAULT NULL,
  `zan_cnt` int(3) DEFAULT NULL,
  `read_cnt` int(3) DEFAULT NULL,
  `hilignt` varchar(255) DEFAULT NULL,
  `user_level` int(3) DEFAULT NULL,
  `user_id` bigint(15) DEFAULT NULL,
  `utype` int(3) DEFAULT NULL,
  `star` int(4) DEFAULT NULL,
  `quality` tinyint(4) DEFAULT NULL,
  `already_zzz` tinyint(4) DEFAULT NULL,
  `review_id` bigint(15) DEFAULT NULL,
  `menu` varchar(255) DEFAULT NULL,
  `did` bigint(15) DEFAULT NULL,
  `deal_endtime` bigint(20) DEFAULT NULL,
  `anonymous` tinyint(4) DEFAULT NULL,
  `poi_id` bigint(20) DEFAULT NULL,
  `city_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for mt_meishi_cmt_42
-- ----------------------------
DROP TABLE IF EXISTS `mt_meishi_cmt_42`;
CREATE TABLE `mt_meishi_cmt_42` (
  `id` bigint(20) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_url` varchar(255) DEFAULT NULL,
  `avg_price` double DEFAULT NULL,
  `comment` text,
  `merchant_comment` text,
  `pic_urls` text,
  `comment_time` bigint(20) DEFAULT NULL,
  `reply_cnt` int(3) DEFAULT NULL,
  `zan_cnt` int(3) DEFAULT NULL,
  `read_cnt` int(3) DEFAULT NULL,
  `hilignt` varchar(255) DEFAULT NULL,
  `user_level` int(3) DEFAULT NULL,
  `user_id` bigint(15) DEFAULT NULL,
  `utype` int(3) DEFAULT NULL,
  `star` int(4) DEFAULT NULL,
  `quality` tinyint(4) DEFAULT NULL,
  `already_zzz` tinyint(4) DEFAULT NULL,
  `review_id` bigint(15) DEFAULT NULL,
  `menu` varchar(255) DEFAULT NULL,
  `did` bigint(15) DEFAULT NULL,
  `deal_endtime` bigint(20) DEFAULT NULL,
  `anonymous` tinyint(4) DEFAULT NULL,
  `poi_id` bigint(20) DEFAULT NULL,
  `city_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for mt_meishi_cmt_557
-- ----------------------------
DROP TABLE IF EXISTS `mt_meishi_cmt_557`;
CREATE TABLE `mt_meishi_cmt_557` (
  `id` bigint(20) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_url` varchar(255) DEFAULT NULL,
  `avg_price` double DEFAULT NULL,
  `comment` text,
  `merchant_comment` text,
  `pic_urls` text,
  `comment_time` bigint(20) DEFAULT NULL,
  `reply_cnt` int(3) DEFAULT NULL,
  `zan_cnt` int(3) DEFAULT NULL,
  `read_cnt` int(3) DEFAULT NULL,
  `hilignt` varchar(255) DEFAULT NULL,
  `user_level` int(3) DEFAULT NULL,
  `user_id` bigint(15) DEFAULT NULL,
  `utype` int(3) DEFAULT NULL,
  `star` int(4) DEFAULT NULL,
  `quality` tinyint(4) DEFAULT NULL,
  `already_zzz` tinyint(4) DEFAULT NULL,
  `review_id` bigint(15) DEFAULT NULL,
  `menu` varchar(255) DEFAULT NULL,
  `did` bigint(15) DEFAULT NULL,
  `deal_endtime` bigint(20) DEFAULT NULL,
  `anonymous` tinyint(4) DEFAULT NULL,
  `poi_id` bigint(20) DEFAULT NULL,
  `city_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for mt_meishi_cmt_57
-- ----------------------------
DROP TABLE IF EXISTS `mt_meishi_cmt_57`;
CREATE TABLE `mt_meishi_cmt_57` (
  `id` bigint(20) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_url` varchar(255) DEFAULT NULL,
  `avg_price` double DEFAULT NULL,
  `comment` text,
  `merchant_comment` text,
  `pic_urls` text,
  `comment_time` bigint(20) DEFAULT NULL,
  `reply_cnt` int(3) DEFAULT NULL,
  `zan_cnt` int(3) DEFAULT NULL,
  `read_cnt` int(3) DEFAULT NULL,
  `hilignt` varchar(255) DEFAULT NULL,
  `user_level` int(3) DEFAULT NULL,
  `user_id` bigint(15) DEFAULT NULL,
  `utype` int(3) DEFAULT NULL,
  `star` int(4) DEFAULT NULL,
  `quality` tinyint(4) DEFAULT NULL,
  `already_zzz` tinyint(4) DEFAULT NULL,
  `review_id` bigint(15) DEFAULT NULL,
  `menu` varchar(255) DEFAULT NULL,
  `did` bigint(15) DEFAULT NULL,
  `deal_endtime` bigint(20) DEFAULT NULL,
  `anonymous` tinyint(4) DEFAULT NULL,
  `poi_id` bigint(20) DEFAULT NULL,
  `city_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for mt_meishi_cmt_59
-- ----------------------------
DROP TABLE IF EXISTS `mt_meishi_cmt_59`;
CREATE TABLE `mt_meishi_cmt_59` (
  `id` bigint(20) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_url` varchar(255) DEFAULT NULL,
  `avg_price` double DEFAULT NULL,
  `comment` text,
  `merchant_comment` text,
  `pic_urls` text,
  `comment_time` bigint(20) DEFAULT NULL,
  `reply_cnt` int(3) DEFAULT NULL,
  `zan_cnt` int(3) DEFAULT NULL,
  `read_cnt` int(3) DEFAULT NULL,
  `hilignt` varchar(255) DEFAULT NULL,
  `user_level` int(3) DEFAULT NULL,
  `user_id` bigint(15) DEFAULT NULL,
  `utype` int(3) DEFAULT NULL,
  `star` int(4) DEFAULT NULL,
  `quality` tinyint(4) DEFAULT NULL,
  `already_zzz` tinyint(4) DEFAULT NULL,
  `review_id` bigint(15) DEFAULT NULL,
  `menu` varchar(255) DEFAULT NULL,
  `did` bigint(15) DEFAULT NULL,
  `deal_endtime` bigint(20) DEFAULT NULL,
  `anonymous` tinyint(4) DEFAULT NULL,
  `poi_id` bigint(20) DEFAULT NULL,
  `city_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for mt_meishi_cmt_73
-- ----------------------------
DROP TABLE IF EXISTS `mt_meishi_cmt_73`;
CREATE TABLE `mt_meishi_cmt_73` (
  `id` bigint(20) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_url` varchar(255) DEFAULT NULL,
  `avg_price` double DEFAULT NULL,
  `comment` text,
  `merchant_comment` text,
  `pic_urls` text,
  `comment_time` bigint(20) DEFAULT NULL,
  `reply_cnt` int(3) DEFAULT NULL,
  `zan_cnt` int(3) DEFAULT NULL,
  `read_cnt` int(3) DEFAULT NULL,
  `hilignt` varchar(255) DEFAULT NULL,
  `user_level` int(3) DEFAULT NULL,
  `user_id` bigint(15) DEFAULT NULL,
  `utype` int(3) DEFAULT NULL,
  `star` int(4) DEFAULT NULL,
  `quality` tinyint(4) DEFAULT NULL,
  `already_zzz` tinyint(4) DEFAULT NULL,
  `review_id` bigint(15) DEFAULT NULL,
  `menu` varchar(255) DEFAULT NULL,
  `did` bigint(15) DEFAULT NULL,
  `deal_endtime` bigint(20) DEFAULT NULL,
  `anonymous` tinyint(4) DEFAULT NULL,
  `poi_id` bigint(20) DEFAULT NULL,
  `city_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for mt_meishi_cmt_97
-- ----------------------------
DROP TABLE IF EXISTS `mt_meishi_cmt_97`;
CREATE TABLE `mt_meishi_cmt_97` (
  `id` bigint(20) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_url` varchar(255) DEFAULT NULL,
  `avg_price` double DEFAULT NULL,
  `comment` text,
  `merchant_comment` text,
  `pic_urls` text,
  `comment_time` bigint(20) DEFAULT NULL,
  `reply_cnt` int(3) DEFAULT NULL,
  `zan_cnt` int(3) DEFAULT NULL,
  `read_cnt` int(3) DEFAULT NULL,
  `hilignt` varchar(255) DEFAULT NULL,
  `user_level` int(3) DEFAULT NULL,
  `user_id` bigint(15) DEFAULT NULL,
  `utype` int(3) DEFAULT NULL,
  `star` int(4) DEFAULT NULL,
  `quality` tinyint(4) DEFAULT NULL,
  `already_zzz` tinyint(4) DEFAULT NULL,
  `review_id` bigint(15) DEFAULT NULL,
  `menu` varchar(255) DEFAULT NULL,
  `did` bigint(15) DEFAULT NULL,
  `deal_endtime` bigint(20) DEFAULT NULL,
  `anonymous` tinyint(4) DEFAULT NULL,
  `poi_id` bigint(20) DEFAULT NULL,
  `city_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for mt_meishi_deal
-- ----------------------------
DROP TABLE IF EXISTS `mt_meishi_deal`;
CREATE TABLE `mt_meishi_deal` (
  `id` bigint(20) DEFAULT NULL,
  `front_img_url` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `sold_num` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `value_price` double DEFAULT NULL,
  `poi_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for mt_meishi_info
-- ----------------------------
DROP TABLE IF EXISTS `mt_meishi_info`;
CREATE TABLE `mt_meishi_info` (
  `id` bigint(15) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `cmt_get` int(10) DEFAULT NULL,
  `cmt_num` int(10) DEFAULT NULL,
  `avg_price` double DEFAULT NULL,
  `avg_score` double DEFAULT NULL,
  `front_img` varchar(255) DEFAULT NULL,
  `area_id` int(11) DEFAULT NULL,
  `area_name` varchar(255) DEFAULT NULL,
  `area_pid` int(11) DEFAULT NULL,
  `lat` double DEFAULT NULL,
  `lng` double DEFAULT NULL,
  `city_id` int(11) DEFAULT NULL,
  `city_name` varchar(255) DEFAULT NULL,
  `show_status` int(2) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `open_time` varchar(255) DEFAULT NULL,
  `brand_id` int(11) DEFAULT NULL,
  `brand_name` varchar(255) DEFAULT NULL,
  `has_food_safe_info` int(1) DEFAULT NULL,
  `is_mei_shi` int(1) DEFAULT NULL,
  `extra_info` varchar(255) DEFAULT NULL,
  `deal_num` int(3) DEFAULT NULL,
  `down` int(2) DEFAULT '0',
  KEY `id_idx` (`id`) USING BTREE,
  KEY `city_id_idx` (`city_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for mt_meishi_tag
-- ----------------------------
DROP TABLE IF EXISTS `mt_meishi_tag`;
CREATE TABLE `mt_meishi_tag` (
  `poi_id` int(11) DEFAULT NULL,
  `tag` varchar(255) DEFAULT NULL,
  `count_num` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for mt_tag
-- ----------------------------
DROP TABLE IF EXISTS `mt_tag`;
CREATE TABLE `mt_tag` (
  `id` bigint(20) NOT NULL,
  `tag` varchar(255) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
  `lbs_idname` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `lbs_name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
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
-- Table structure for qimg_video
-- ----------------------------
DROP TABLE IF EXISTS `qimg_video`;
CREATE TABLE `qimg_video` (
  `id` bigint(20) NOT NULL,
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
  `video_url` varchar(500) DEFAULT NULL,
  `photo_id` bigint(20) DEFAULT NULL,
  `topicid` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
  `lloc` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `sloc` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `raw` varchar(255) DEFAULT NULL,
  `raw_upload` int(2) DEFAULT NULL,
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
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

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
