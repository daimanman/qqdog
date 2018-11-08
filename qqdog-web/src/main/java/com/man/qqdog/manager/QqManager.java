package com.man.qqdog.manager;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class QqManager {

	// 存储QQ Cookies
	public Map<String, Map<String, String>> cookiesMap = new HashMap<String, Map<String, String>>();

	// 说说
	public LinkedList<String> emotUidsList = new LinkedList<String>();

	// 留言
	public LinkedList<String> msgUidsList = new LinkedList<String>();

	// 相册
	public LinkedList<String> photoUidsList = new LinkedList<String>();

	// 照片
	public LinkedList<String> imgUidsList = new LinkedList<String>();

	// 说说 url
	public String qqEmotInfoUrl = "https://h5.qzone.qq.com/proxy/domain/taotao.qq.com/cgi-bin/emotion_cgi_msglist_v6";

	// 基本信息
	public String qqBaseInfoUrl = "https://h5.qzone.qq.com/proxy/domain/base.qzone.qq.com/cgi-bin/user/cgi_userinfo_get_all";

	// 留言
	public String qqMsgInfoUrl = "https://h5.qzone.qq.com/proxy/domain/m.qzone.qq.com/cgi-bin/new/get_msgb";

	// 相册
	public String qqPhotoInfoUrl = "https://h5.qzone.qq.com/proxy/domain/alist.photo.qq.com/fcgi-bin/fcg_list_album_v3";

	// 获取相册图片
	public String qqImgInfoUrl = "https://h5.qzone.qq.com/proxy/domain/plist.photo.qzone.qq.com/fcgi-bin/cgi_list_photo";

	// 访客
	public String qqVisitInfoUrl = "https://h5.qzone.qq.com/proxy/domain/g.qzone.qq.com/cgi-bin/friendshow/cgi_get_visitor_simple";

	// emot num
	public static int DEFAULT_NUM = 30;

	// msg num
	public static int DEFAULT_MSG_NUM = 20;

	// img num

	public static int DEFAULT_IMG_NUM = 500;

	public static int CODE_VERSION = 1;

	public static int REPLYNUM = 100;

	public static String ALLOW_ACCESS = "1";
	
	

}
