/*
Navicat MySQL Data Transfer

Source Server         : thinkpad
Source Server Version : 50637
Source Host           : 192.168.1.103:3306
Source Database       : data_4

Target Server Type    : MYSQL
Target Server Version : 50637
File Encoding         : 65001

Date: 2018-11-26 21:46:25
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
INSERT INTO `qsession_info` VALUES ('1143886181', '{\"Cookie\":\"RK=KajNbU9QFQ; ptcz=c66cbd2992feb7c86370ba8becb4a3333d1619f03fbca425be49cc757c4ebcd8; pgv_pvid=8632765126; qz_screen=1366x768; QZ_FE_WEBP_SUPPORT=1; pgv_pvi=4630548480; __Q_w_s__QZN_TodoMsgCnt=1; __Q_w_s_hat_seed=1; randomSeed=166021; Loading=Yes; pgv_info=ssid=s7726885128; cpu_performance_v8=14; pt2gguin=o1143886181; uin=o1143886181; skey=@F9MTS8NSX; p_uin=o1143886181; pt4_token=pQa9PFLraKLF9YPGQle5y2PZaIA5DjkF8rWG85Lwl4A_; p_skey=6h2NnvvCU70LCIBL-eLhBOT4KJXmq6CC81wABddnDRc_\",\"Accept\":\"*/*\",\"User-Agent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36\",\"Referer\":\"https://user.qzone.qq.com/1143886181/infocenter\",\"Accept-Encoding\":\"gzip, deflate, br\",\"Accept-Language\":\"zh-CN,zh;q=0.9\"}', '{\"board_id\":\"2685\",\"uin\":\"1143886181\",\"qzonetoken\":\"6e3080c482533be1c46fc22597661b09dd10b5abdf3251189f6884acd6ab4f9f9e1315e5a6c1e60e\",\"need_cnt\":\"2\",\"g_tk\":\"828261689\"}', '2018-11-26 21:38:07', '2018-11-26 21:38:07', '0', null);
INSERT INTO `qsession_info` VALUES ('1276742580', '{\"Cookie\":\"RK=KajNbU9QFQ; ptcz=c66cbd2992feb7c86370ba8becb4a3333d1619f03fbca425be49cc757c4ebcd8; pgv_pvid=8632765126; qz_screen=1366x768; QZ_FE_WEBP_SUPPORT=1; pgv_pvi=4630548480; __Q_w_s__QZN_TodoMsgCnt=1; __Q_w_s_hat_seed=1; randomSeed=166021; Loading=Yes; pgv_info=ssid=s7726885128; cpu_performance_v8=9; osstat=26; __layoutStat=26; pt2gguin=o1276742580; uin=o1276742580; skey=@cDmgC02EJ; p_uin=o1276742580; pt4_token=WhhaoVMZXJLQLtw62YO3ONhaTLSyCqXij7A7wUKdHBY_; p_skey=mk6v*AXxS3Vbn1Sjo8ESZYlt6JuANbP0WDM3gZ4yivs_; x-stgw-ssl-info=2979a13b26643b41650f6fccdf138a0a|0.149|1543239669.669|195|.|I|TLSv1.2|ECDHE-RSA-AES128-GCM-SHA256|71500|h2|0\",\"Accept\":\"*/*\",\"User-Agent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36\",\"Referer\":\"https://user.qzone.qq.com/1276742580?source=panelstar\",\"Accept-Encoding\":\"gzip, deflate, br\",\"Accept-Language\":\"zh-CN,zh;q=0.9\"}', '{\"board_id\":\"2685\",\"uin\":\"1276742580\",\"qzonetoken\":\"6f0f5dc54465e4ed496879dcf4b23cfc122ea34031e97cc8d8bbcc853e69594f609c53c6c936cb47\",\"need_cnt\":\"2\",\"g_tk\":\"755351088\"}', '2018-11-26 21:39:59', '2018-11-26 21:39:59', '0', null);
INSERT INTO `qsession_info` VALUES ('168033989', '{\"Cookie\":\"RK=KajNbU9QFQ; ptcz=c66cbd2992feb7c86370ba8becb4a3333d1619f03fbca425be49cc757c4ebcd8; pgv_pvid=8632765126; qz_screen=1366x768; QZ_FE_WEBP_SUPPORT=1; pgv_pvi=4630548480; __Q_w_s__QZN_TodoMsgCnt=1; __Q_w_s_hat_seed=1; randomSeed=166021; Loading=Yes; pgv_info=ssid=s7726885128; cpu_performance_v8=9; osstat=26; __layoutStat=26; x-stgw-ssl-info=2979a13b26643b41650f6fccdf138a0a|0.149|1543239669.669|195|.|I|TLSv1.2|ECDHE-RSA-AES128-GCM-SHA256|71500|h2|0; pt2gguin=o0168033989; uin=o0168033989; skey=@mkSVKfTPD; p_uin=o0168033989; pt4_token=gOiRho8So1XpoA5okF*F3PRSej8mGIsZW0Lkfjasv44_; p_skey=JUs1zvm6SWjAc2VkpBeohq*SClvM1s8G5lFjN68MN1c_\",\"Accept\":\"*/*\",\"User-Agent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36\",\"Referer\":\"https://user.qzone.qq.com/168033989/infocenter\",\"Accept-Encoding\":\"gzip, deflate, br\",\"Accept-Language\":\"zh-CN,zh;q=0.9\"}', '{\"board_id\":\"2685\",\"uin\":\"168033989\",\"qzonetoken\":\"bd196348cb92a760c442458b148c7c9a0de9f2dc6882eeb6d180f699dc68e95601938ac161317c\",\"need_cnt\":\"2\",\"g_tk\":\"662495794\"}', '2018-11-26 21:40:55', '2018-11-26 21:40:55', '0', null);
INSERT INTO `qsession_info` VALUES ('1748723478', '{\"Cookie\":\"RK=KajNbU9QFQ; ptcz=c66cbd2992feb7c86370ba8becb4a3333d1619f03fbca425be49cc757c4ebcd8; pgv_pvid=8632765126; qz_screen=1366x768; QZ_FE_WEBP_SUPPORT=1; pgv_pvi=4630548480; __Q_w_s__QZN_TodoMsgCnt=1; __Q_w_s_hat_seed=1; randomSeed=166021; Loading=Yes; pgv_info=ssid=s7726885128; cpu_performance_v8=14; pt2gguin=o1748723478; uin=o1748723478; skey=@KPVTcQpQw; p_uin=o1748723478; pt4_token=l-mNrop95r1Lbs6g2WbBLFMa1Lq6U95u0r092ZBDPLA_; p_skey=cFdcRKMBVfTeNZEocBeNZ6j-h3K0GJ3cidXpz-esXEs_\",\"Accept\":\"*/*\",\"User-Agent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36\",\"Referer\":\"https://user.qzone.qq.com/1748723478/infocenter\",\"Accept-Encoding\":\"gzip, deflate, br\",\"Accept-Language\":\"zh-CN,zh;q=0.9\"}', '{\"board_id\":\"2420\",\"uin\":\"1748723478\",\"qzonetoken\":\"4b7be1760ed8b25ff955ac81808a57e990176eb558e9b61e45fdf8805eb7b532f5c1f4edd0b538ee\",\"need_cnt\":\"65536\",\"g_tk\":\"1476906897\"}', '2018-11-26 21:36:49', '2018-11-26 21:36:49', '0', null);
INSERT INTO `qsession_info` VALUES ('1843594995', '{\"Cookie\":\"RK=KajNbU9QFQ; ptcz=c66cbd2992feb7c86370ba8becb4a3333d1619f03fbca425be49cc757c4ebcd8; pgv_pvid=8632765126; qz_screen=1366x768; QZ_FE_WEBP_SUPPORT=1; pgv_pvi=4630548480; __Q_w_s__QZN_TodoMsgCnt=1; __Q_w_s_hat_seed=1; randomSeed=166021; Loading=Yes; pgv_info=ssid=s7726885128; cpu_performance_v8=14; pt2gguin=o1843594995; uin=o1843594995; skey=@jF1HPRIIH; p_uin=o1843594995; pt4_token=6eExwIhSX*6gwL6uPfixF94Obox9pKr9gWD4X5yR*Oc_; p_skey=ngNAJeKfnX0koRxc8hQupny*MNexXIjIpGkUQN-AC8c_\",\"Accept\":\"*/*\",\"User-Agent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36\",\"Referer\":\"https://user.qzone.qq.com/1843594995/infocenter\",\"Accept-Encoding\":\"gzip, deflate, br\",\"Accept-Language\":\"zh-CN,zh;q=0.9\"}', '{\"board_id\":\"2685\",\"uin\":\"1843594995\",\"qzonetoken\":\"9f915c8b14679589dd08416653aeeeb8ed60be5a5b438f3cfbf050528c34881c6293cb2729fc2634\",\"need_cnt\":\"2\",\"g_tk\":\"242337434\"}', '2018-11-26 21:37:04', '2018-11-26 21:37:04', '0', null);
INSERT INTO `qsession_info` VALUES ('1934843846', '{\"Cookie\":\"RK=KajNbU9QFQ; ptcz=c66cbd2992feb7c86370ba8becb4a3333d1619f03fbca425be49cc757c4ebcd8; pgv_pvid=8632765126; qz_screen=1366x768; QZ_FE_WEBP_SUPPORT=1; pgv_pvi=4630548480; __Q_w_s__QZN_TodoMsgCnt=1; __Q_w_s_hat_seed=1; randomSeed=166021; cpu_performance_v8=25; pt2gguin=o1934843846; uin=o1934843846; skey=@6XubV9Ti4; p_uin=o1934843846; Loading=Yes; pt4_token=HFAhnr7acv5EpCeVEAEloEKyXXN-3hkzRxjRMUViOMw_; p_skey=VMpSMBasaIP0NpTQ0pSBla6fnXuRGmlrHDclAo05kCI_; pgv_info=ssid=s7726885128\",\"Accept\":\"*/*\",\"User-Agent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36\",\"Referer\":\"https://user.qzone.qq.com/1934843846/infocenter\",\"Accept-Encoding\":\"gzip, deflate, br\",\"Accept-Language\":\"zh-CN,zh;q=0.9\"}', '{\"board_id\":\"2685\",\"uin\":\"1934843846\",\"qzonetoken\":\"ecc063d81d8283a24c91c53faf8d0e2e81ef5f5d454cc3d28ff6c828b6b49259077149bd1c3bd008\",\"need_cnt\":\"2\",\"g_tk\":\"977170014\"}', '2018-11-26 21:36:08', '2018-11-26 21:36:08', '0', null);
INSERT INTO `qsession_info` VALUES ('2083499983', '{\"Cookie\":\"RK=KajNbU9QFQ; ptcz=c66cbd2992feb7c86370ba8becb4a3333d1619f03fbca425be49cc757c4ebcd8; pgv_pvid=8632765126; qz_screen=1366x768; QZ_FE_WEBP_SUPPORT=1; pgv_pvi=4630548480; __Q_w_s__QZN_TodoMsgCnt=1; __Q_w_s_hat_seed=1; randomSeed=166021; Loading=Yes; pgv_info=ssid=s7726885128; cpu_performance_v8=14; pt2gguin=o2083499983; uin=o2083499983; skey=@fIUXplP33; p_uin=o2083499983; pt4_token=2FSJQe8jWI93f2ijfEub8IV2Wg2CgpM6p-wQa2B95Xg_; p_skey=W0MKTwGwNXklXjJtR1WuvMslsHzoh9FeiCA7-AD6w-g_\",\"Accept\":\"*/*\",\"User-Agent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36\",\"Referer\":\"https://user.qzone.qq.com/2083499983/infocenter\",\"Accept-Encoding\":\"gzip, deflate, br\",\"Accept-Language\":\"zh-CN,zh;q=0.9\"}', '{\"board_id\":\"2685\",\"uin\":\"2083499983\",\"qzonetoken\":\"c19b16553e495d7e53e90712071f2039ddac051c4bf3becff38dcd40ec0bbccd5ebe0dee1e54957d\",\"need_cnt\":\"2\",\"g_tk\":\"958798209\"}', '2018-11-26 21:37:49', '2018-11-26 21:37:49', '0', null);
INSERT INTO `qsession_info` VALUES ('2109529257', '{\"Cookie\":\"RK=KajNbU9QFQ; ptcz=c66cbd2992feb7c86370ba8becb4a3333d1619f03fbca425be49cc757c4ebcd8; pgv_pvid=8632765126; qz_screen=1366x768; QZ_FE_WEBP_SUPPORT=1; pgv_pvi=4630548480; __Q_w_s__QZN_TodoMsgCnt=1; __Q_w_s_hat_seed=1; randomSeed=166021; Loading=Yes; pgv_info=ssid=s7726885128; cpu_performance_v8=9; osstat=26; __layoutStat=26; x-stgw-ssl-info=2979a13b26643b41650f6fccdf138a0a|0.149|1543239669.669|195|.|I|TLSv1.2|ECDHE-RSA-AES128-GCM-SHA256|71500|h2|0; pt2gguin=o2109529257; uin=o2109529257; skey=@HgfzCAXX5; p_uin=o2109529257; pt4_token=n9JLWHZgNe6d2Te9YlhUkwv7SSG95*UARyMHOuqjVpk_; p_skey=nYoo1w1BjOSSGKltPdDIoL*noNeNW4GNqmLRB01BgJ8_\",\"Accept\":\"*/*\",\"User-Agent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36\",\"Referer\":\"https://user.qzone.qq.com/2109529257/infocenter\",\"Accept-Encoding\":\"gzip, deflate, br\",\"Accept-Language\":\"zh-CN,zh;q=0.9\"}', '{\"board_id\":\"2685\",\"uin\":\"2109529257\",\"qzonetoken\":\"119e252772b113e5068e66aac18fb22e21e3bf12ac5458c0f755d675920bf8c2dc8a7d5d6513ffe3\",\"need_cnt\":\"2\",\"g_tk\":\"500612483\"}', '2018-11-26 21:41:41', '2018-11-26 21:41:41', '0', null);
INSERT INTO `qsession_info` VALUES ('2433276560', '{\"Cookie\":\"RK=KajNbU9QFQ; ptcz=c66cbd2992feb7c86370ba8becb4a3333d1619f03fbca425be49cc757c4ebcd8; pgv_pvid=8632765126; qz_screen=1366x768; QZ_FE_WEBP_SUPPORT=1; pgv_pvi=4630548480; __Q_w_s__QZN_TodoMsgCnt=1; __Q_w_s_hat_seed=1; randomSeed=166021; Loading=Yes; pgv_info=ssid=s7726885128; cpu_performance_v8=9; osstat=26; pt2gguin=o2433276560; uin=o2433276560; skey=@NWYqCfKCj; p_uin=o2433276560; pt4_token=v*Hdivo58pnMllJ3ml5NDqpndm-hlW6hg1V3y2c*7Oc_; p_skey=I22v2lzeDQT399wB8qBJJoWTqGh4iHwlrSP2dLDXS6M_; x-stgw-ssl-info=d53a7d049c29d2e8bbf21e9012063169|0.139|1543239613.784|159|.|I|TLSv1.2|ECDHE-RSA-AES128-GCM-SHA256|78500|h2|0\",\"Accept\":\"*/*\",\"User-Agent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36\",\"Referer\":\"https://user.qzone.qq.com/2433276560?source=panelstar\",\"Accept-Encoding\":\"gzip, deflate, br\",\"Accept-Language\":\"zh-CN,zh;q=0.9\"}', '{\"board_id\":\"2685\",\"uin\":\"2433276560\",\"qzonetoken\":\"f7468a5599629d878a940d5c6128100a00b73ce5b858902b31312fe20603722f6cf5884198919404\",\"need_cnt\":\"2\",\"g_tk\":\"417856149\"}', '2018-11-26 21:39:03', '2018-11-26 21:39:03', '0', null);
INSERT INTO `qsession_info` VALUES ('2972638700', '{\"Cookie\":\"RK=KajNbU9QFQ; ptcz=c66cbd2992feb7c86370ba8becb4a3333d1619f03fbca425be49cc757c4ebcd8; pgv_pvid=8632765126; qz_screen=1366x768; QZ_FE_WEBP_SUPPORT=1; pgv_pvi=4630548480; __Q_w_s__QZN_TodoMsgCnt=1; __Q_w_s_hat_seed=1; randomSeed=166021; Loading=Yes; pgv_info=ssid=s7726885128; cpu_performance_v8=9; osstat=26; x-stgw-ssl-info=d53a7d049c29d2e8bbf21e9012063169|0.139|1543239613.784|159|.|I|TLSv1.2|ECDHE-RSA-AES128-GCM-SHA256|78500|h2|0; pt2gguin=o2972638700; uin=o2972638700; skey=@1j6KBsyz8; p_uin=o2972638700; pt4_token=lUqn9q5JjUYpO0N2W3pmi1XVwKg4d9Loqcv-PCfAcos_; p_skey=Vn2fLYSrX21gsB3e5Mn7lBsAaVRH46FWH9vAmqOSb-c_\",\"Accept\":\"*/*\",\"User-Agent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36\",\"Referer\":\"https://user.qzone.qq.com/2972638700/infocenter\",\"Accept-Encoding\":\"gzip, deflate, br\",\"Accept-Language\":\"zh-CN,zh;q=0.9\"}', '{\"board_id\":\"2685\",\"uin\":\"2972638700\",\"qzonetoken\":\"9fb614c9780bc88400f8818bea104f7678c4081d1f21541ca3c674681cadc51f88f161c4005f39e7\",\"need_cnt\":\"2\",\"g_tk\":\"203928036\"}', '2018-11-26 21:39:25', '2018-11-26 21:39:25', '0', null);
INSERT INTO `qsession_info` VALUES ('3079693469', '{\"Cookie\":\"RK=KajNbU9QFQ; ptcz=c66cbd2992feb7c86370ba8becb4a3333d1619f03fbca425be49cc757c4ebcd8; pgv_pvid=8632765126; qz_screen=1366x768; QZ_FE_WEBP_SUPPORT=1; pgv_pvi=4630548480; __Q_w_s__QZN_TodoMsgCnt=1; __Q_w_s_hat_seed=1; randomSeed=166021; Loading=Yes; pgv_info=ssid=s7726885128; cpu_performance_v8=9; pt2gguin=o3079693469; uin=o3079693469; skey=@VJgTCIqky; p_uin=o3079693469; pt4_token=MpRHO9OjfvLb-RyRhgg56IZQzzB8w-1R44PYbH1Mcgw_; p_skey=TnOKH3nbXgoQ3ql1jt34TSINZXe-YK4uNuwIVjti4W0_\",\"Accept\":\"*/*\",\"User-Agent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36\",\"Referer\":\"https://user.qzone.qq.com/3079693469/infocenter\",\"Accept-Encoding\":\"gzip, deflate, br\",\"Accept-Language\":\"zh-CN,zh;q=0.9\"}', '{\"board_id\":\"2685\",\"uin\":\"3079693469\",\"qzonetoken\":\"6ac1c845314d4e473e5ac38aadbdd3d57221106aa5399027b00064a91a6efb97859caeb1c6aa4c85\",\"need_cnt\":\"2\",\"g_tk\":\"789330042\"}', '2018-11-26 21:38:26', '2018-11-26 21:38:26', '0', null);
INSERT INTO `qsession_info` VALUES ('3422469468', '{\"Cookie\":\"RK=KajNbU9QFQ; ptcz=c66cbd2992feb7c86370ba8becb4a3333d1619f03fbca425be49cc757c4ebcd8; pgv_pvid=8632765126; qz_screen=1366x768; QZ_FE_WEBP_SUPPORT=1; pgv_pvi=4630548480; __Q_w_s__QZN_TodoMsgCnt=1; __Q_w_s_hat_seed=1; randomSeed=166021; Loading=Yes; pgv_info=ssid=s7726885128; cpu_performance_v8=9; osstat=26; __layoutStat=26; pgv_si=s9143037952; pt2gguin=o3422469468; uin=o3422469468; skey=@x6krTuMlL; p_uin=o3422469468; pt4_token=Ip2wMrnJXwFHjlFHK8iAHLgs5w9w7KpIWeqklTysb6w_; p_skey=odrJyZ7hincFxvyLEGmbYS-cxazXjCzCk1w0QEAT1Tg_; x-stgw-ssl-info=38941e60a4f60dc9ffe44b549fc46ac1|0.126|1543239947.817|249|.|I|TLSv1.2|ECDHE-RSA-AES128-GCM-SHA256|92000|h2|0\",\"Accept\":\"*/*\",\"User-Agent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36\",\"Referer\":\"https://user.qzone.qq.com/3422469468?source=panelstar\",\"Accept-Encoding\":\"gzip, deflate, br\",\"Accept-Language\":\"zh-CN,zh;q=0.9\"}', '{\"board_id\":\"2685\",\"uin\":\"3422469468\",\"qzonetoken\":\"fa7aa5bbf74369eb4922c795bf85abfe87e7967fc62854782a932aa5d45c16735b9251507196422e\",\"need_cnt\":\"2\",\"g_tk\":\"387903518\"}', '2018-11-26 21:44:37', '2018-11-26 21:44:37', '0', null);
INSERT INTO `qsession_info` VALUES ('848443394', '{\"Cookie\":\"RK=KajNbU9QFQ; ptcz=c66cbd2992feb7c86370ba8becb4a3333d1619f03fbca425be49cc757c4ebcd8; pgv_pvid=8632765126; qz_screen=1366x768; QZ_FE_WEBP_SUPPORT=1; pgv_pvi=4630548480; __Q_w_s__QZN_TodoMsgCnt=1; __Q_w_s_hat_seed=1; randomSeed=166021; Loading=Yes; pgv_info=ssid=s7726885128; cpu_performance_v8=9; osstat=26; pt2gguin=o0848443394; uin=o0848443394; skey=@ut1Fu7SF1; p_uin=o0848443394; pt4_token=Yl5hX9iSqX2sMm7m4*bIE3mX3TIdD8nvuWiBibNV*30_; p_skey=sMXPpYRC*8hDVD6rFYMty4k1Q-0JI2rlZQ8pXCx-mnk_; x-stgw-ssl-info=90093cecda287a9eea1851fa3ee7af06|0.143|1543239590.774|143|.|I|TLSv1.2|ECDHE-RSA-AES128-GCM-SHA256|75000|h2|0\",\"Accept\":\"*/*\",\"User-Agent\":\"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36\",\"Referer\":\"https://user.qzone.qq.com/848443394?source=panelstar\",\"Accept-Encoding\":\"gzip, deflate, br\",\"Accept-Language\":\"zh-CN,zh;q=0.9\"}', '{\"board_id\":\"2685\",\"uin\":\"848443394\",\"qzonetoken\":\"2f135972cc5b40d078157cc3c985286739c33c86eb120e9e00f20498a588bd6622e6c808983be4\",\"need_cnt\":\"2\",\"g_tk\":\"1584661901\"}', '2018-11-26 21:38:40', '2018-11-26 21:38:40', '0', null);