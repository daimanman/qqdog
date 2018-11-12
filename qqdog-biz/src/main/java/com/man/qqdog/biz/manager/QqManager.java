package com.man.qqdog.biz.manager;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.man.constant.QcodeEnum;
import com.man.qq.QqConfig;
import com.man.qqdog.client.po.QsessionInfoPo;
import com.man.qqdog.client.service.QsessionService;
import com.man.utils.ObjectUtil;
import com.man.utils.YhHttpUtil;

@Service
public class QqManager {

	@Autowired
	private QsessionService qsessionService;

	public Map<String, QsessionInfoPo> sessionMap = new HashMap<>();

	public LinkedList<String> msgUidsList = new LinkedList<>();

	public ConcurrentHashMap<String, Object> msgLock = new ConcurrentHashMap<>();

	public LinkedList<String> userInfoUidsList = new LinkedList<>();

	public ConcurrentHashMap<String, Object> userInfoLock = new ConcurrentHashMap<>();

	public LinkedList<String> photoUidsList = new LinkedList<>();

	public ConcurrentHashMap<String, Object> photoLock = new ConcurrentHashMap<>();

	public LinkedList<String> imgUidsList = new LinkedList<>();

	public ConcurrentHashMap<String, Object> imgLock = new ConcurrentHashMap<>();

	public LinkedList<String> emotUidsList = new LinkedList<>();

	public ConcurrentHashMap<String, Object> emotLock = new ConcurrentHashMap<>();

	public LinkedList<String> visitUidsList = new LinkedList<>();

	public ConcurrentHashMap<String, Object> visitLock = new ConcurrentHashMap<>();

	// record remove uid log
	public Logger removeLogger = LoggerFactory.getLogger("removeUidLogger");

	public Logger logger = LoggerFactory.getLogger(QqManager.class);

	public void addUids(String uid) {
		msgUidsList.remove(uid);
		msgUidsList.add(uid);

		userInfoUidsList.remove(uid);
		userInfoUidsList.add(uid);

		photoUidsList.remove(uid);
		photoUidsList.add(uid);

		imgUidsList.remove(uid);
		imgUidsList.add(uid);

		emotUidsList.remove(uid);
		emotUidsList.add(uid);

		visitUidsList.remove(uid);
		visitUidsList.add(uid);
	}

	public String getUserInfoUid() {
		if (null == userInfoUidsList || userInfoUidsList.size() == 0) {
			return null;
		}

		synchronized (userInfoLock) {
			String uid = userInfoUidsList.pop();
			userInfoUidsList.add(uid);
			return uid;
		}
	}

	public void removeUserInfoUid(String uid) {
		synchronized (userInfoLock) {
			userInfoUidsList.remove(uid);
			removeLogger.info("remove user uid {} ", uid);
		}
	}

	public String getEmotUid() {
		if (emotUidsList == null || emotUidsList.size() == 0) {
			return null;
		}
		synchronized (emotLock) {
			String uid = emotUidsList.pop();
			emotUidsList.add(uid);
			return uid;
		}
	}

	public void removeEmotUid(String uid) {
		synchronized (emotLock) {
			emotUidsList.remove(uid);
			removeLogger.info("remove emot uid {} ", uid);
		}
	}

	public String getVisitUid() {
		if (visitUidsList == null || visitUidsList.size() == 0) {
			return null;
		}
		synchronized (visitLock) {
			String uid = visitUidsList.pop();
			visitUidsList.add(uid);
			return uid;
		}
	}

	public void removeVisitUid(String uid) {
		synchronized (visitLock) {
			visitUidsList.remove(uid);
			removeLogger.info("remove visit uid {} ", uid);
		}
	}

	public String getMsgUid() {
		if (null == msgUidsList || msgUidsList.size() == 0) {
			return null;
		}
		synchronized (msgLock) {
			String uid = msgUidsList.pop();
			msgUidsList.add(uid);
			return uid;
		}
	}

	public void removeMsgUid(String uid) {
		synchronized (msgLock) {
			msgUidsList.remove(uid);
			removeLogger.info("remove msg uid {} ", uid);
		}
	}

	public String getPhotoUid() {
		if (null == photoUidsList || photoUidsList.size() == 0) {
			return null;
		}
		synchronized (photoLock) {
			String uid = photoUidsList.pop();
			photoUidsList.add(uid);
			return uid;
		}
	}

	public void removePhotoUid(String uid) {
		synchronized (photoLock) {
			photoUidsList.remove(uid);
			removeLogger.info("remove  photo uid {} ", uid);
		}
	}

	public String getImgUid() {
		if (null == imgUidsList || imgUidsList.size() == 0) {
			return null;
		}
		synchronized (imgLock) {
			String uid = imgUidsList.pop();
			imgUidsList.add(uid);
			return uid;
		}
	}

	public void removeImgUid(String uid) {
		synchronized (imgLock) {
			imgUidsList.remove(uid);
			removeLogger.info("remove img uid {} ", uid);
		}
	}

	// 初始化
	public void initSession() {
		List<QsessionInfoPo> datas = qsessionService.getAllSession();
		if (null != datas && datas.size() > 0) {
			for (QsessionInfoPo data : datas) {
				sessionMap.put(data.uid, data);
				addUids(data.uid);
			}
		}
	}

	public boolean checkOffen(String codeStr, String subcodeStr, String message) {
		return ((QcodeEnum.QOFEN_P.code + "").equals(codeStr) && subcodeStr.equals(QcodeEnum.QOFEN_P.subcode + ""))
				|| (codeStr.equals(QcodeEnum.QLOGIN_P.code + "") && subcodeStr.equals(QcodeEnum.QLOGIN_P.subcode + "")
						|| (message.indexOf("频") >= 0)

				);
	}

	public void removeUids(String uid) {
		// this.uids.remove(uid);
		// this.photoUids.remove(uid);
		// this.emotUids.remove(uid);
		// this.msgUids.remove(uid);

		removeUserInfoUid(uid);
		removeEmotUid(uid);
		removePhotoUid(uid);
		removeMsgUid(uid);
	}

	public Map<String, Object> crawlQzoneBaseInfoContent(String uid) {
		if (userInfoUidsList == null || userInfoUidsList.size() == 0) {
			logger.info("################### baseInfo is over ######################");
			return null;
		}
		String effectUid = getUserInfoUid();
		QsessionInfoPo sessionInfo = sessionMap.get(effectUid);
		Map<String, Object> originMap = sessionInfo.paramsMap;
		Map<String, Object> newParamMap = new HashMap<String, Object>();
		newParamMap.put("g_tk", originMap.get("g_tk"));
		newParamMap.put("qzonetoken", originMap.get("qzonetoken"));
		newParamMap.put("format", "json");
		newParamMap.put("uin", uid);
		newParamMap.put("vuin", effectUid);
		newParamMap.put("fupdate", 1);

		// 获取会话信息
		Map<String, String> cookieMap = sessionInfo.cookieMap;

		String content = YhHttpUtil.sendHttpGetWithRetry(QqConfig.QQ_BASEINFO_URL, newParamMap, cookieMap);
		try {
			Map<String, Object> contentMap = JSON.parseObject(content, Map.class);
			Object code = contentMap.get("code");
			Object subcode = contentMap.get("subcode");
			String codeStr = code != null ? code.toString() : "";
			String subcodeStr = subcode != null ? subcode.toString() : "";
			String message = ObjectUtil.toString(contentMap.get("message"));
			// 检查是否调用频繁
			boolean checkFlag = checkOffen(codeStr, subcodeStr, message);
			contentMap.put("checkFlag", checkFlag);
			while (checkFlag && (userInfoUidsList.size()) > 0) {
				logger.info("【basinfo " + effectUid + "】=" + content);
				removeUids(effectUid);
				return crawlQzoneBaseInfoContent(uid);
			}
			return contentMap;
		} catch (Exception e) {
			Map<String, Object> eMap = new HashMap<String, Object>();
			eMap.put("gateWay", true);
			e.printStackTrace();
			logger.info("basinfo------{}", content);
			return eMap;
		}
	}

	// get msg
	public Map<String, Object> crawlQzoneMsgInfoContent(String uid, int pos) {
		if (msgUidsList == null || msgUidsList.size() == 0) {
			logger.info("################### msgInfo is over ######################");
			return null;
		}

		String effectUid = getMsgUid();
		QsessionInfoPo session = sessionMap.get(effectUid);
		logger.info("current uid is {}", effectUid);
		// 获取参数
		Map<String, Object> originMap = session.paramsMap;
		Map<String, Object> newParamMap = new HashMap<String, Object>();
		logger.info("parmas = {}", JSON.toJSONString(originMap));
		newParamMap.put("g_tk", originMap.get("g_tk"));
		newParamMap.put("qzonetoken", originMap.get("qzonetoken"));
		newParamMap.put("format", "json");
		// newParamMap.put("pos", pos);
		newParamMap.put("num", QqConfig.DEFAULT_MSG_NUM);
		newParamMap.put("hostUin", uid);
		newParamMap.put("uin", effectUid);
		newParamMap.put("inCharset", "utf-8");
		newParamMap.put("outCharset", "utf-8");
		newParamMap.put("start", pos);
		newParamMap.put("essence", 1);
		newParamMap.put("ref", "qzone");

		// 获取会话信息
		Map<String, String> cookieMap = session.cookieMap;

		String content = YhHttpUtil.sendHttpGetWithRetry(QqConfig.QQ_MSGINFO_URL, newParamMap, cookieMap);
		Map<String, Object> contentMap = JSON.parseObject(content, Map.class);
		Object code = contentMap.get("code");
		Object subcode = contentMap.get("subcode");
		String codeStr = code != null ? code.toString() : "";
		String subcodeStr = subcode != null ? subcode.toString() : "";
		String message = ObjectUtil.toString(contentMap.get("message"));
		while (checkOffen(codeStr, subcodeStr, message) && (msgUidsList.size()) > 0) {
			removeMsgUid(effectUid);
			logger.info("【msgInfo " + effectUid + "】=" + content);
			return crawlQzoneMsgInfoContent(uid, pos);
		}
		return contentMap;
	}

	// get photo into
	public Map<String, Object> crawlQzonePhotoInfo(String uid) {

		if (photoUidsList == null || photoUidsList.size() == 0) {
			logger.info("################### photoInfo is over ######################");
			return null;
		}
		String effectUid = getPhotoUid();
		QsessionInfoPo session = sessionMap.get(effectUid);
		// 获取参数
		Map<String, Object> originMap = session.paramsMap;
		Map<String, Object> newParamMap = new HashMap<String, Object>();
		newParamMap.put("g_tk", originMap.get("g_tk"));
		newParamMap.put("qzonetoken", originMap.get("qzonetoken"));
		newParamMap.put("format", "json");
		newParamMap.put("hostUin", uid);
		newParamMap.put("uin", effectUid);
		newParamMap.put("appid", "4");
		newParamMap.put("inCharset", "utf-8");
		newParamMap.put("outCharset", "utf-8");
		newParamMap.put("source", "qzone");
		newParamMap.put("plat", "qzone");
		newParamMap.put("notice", "0");
		newParamMap.put("filter", "1");
		newParamMap.put("handset", "4");
		newParamMap.put("pageNumModeSort", "40");
		newParamMap.put("pageNumModeClass", "15");
		newParamMap.put("needUserInfo", "1");
		newParamMap.put("idcNum", "0");

		// 获取会话信息
		Map<String, String> cookieMap = session.cookieMap;

		String content = YhHttpUtil.sendHttpGetWithRetry(QqConfig.QQ_PHOTOINFO_URL, newParamMap, cookieMap);
		Map<String, Object> contentMap = JSON.parseObject(content, Map.class);
		Object code = contentMap.get("code");
		Object subcode = contentMap.get("subcode");
		String codeStr = code != null ? code.toString() : "";
		String subcodeStr = subcode != null ? subcode.toString() : "";
		String message = ObjectUtil.toString(contentMap.get("message"));
		boolean checkFlag = checkOffen(codeStr, subcodeStr, message);
		contentMap.put("checkFlag", checkFlag);
		while (checkFlag && (photoUidsList.size()) > 0) {
			logger.info("【photoInfo " + effectUid + "】=" + content);
			removePhotoUid(effectUid);
			return crawlQzonePhotoInfo(uid);
		}
		return contentMap;
	}

	// get img info 相册中图片
	public Map<String, Object> crawlQzoneImgInfo(String uid, int pos, String topicId) {
		if (photoUidsList == null || photoUidsList.size() == 0) {
			logger.info("################### imgInfo is over ######################");
			return null;
		}
		String effectUid = getPhotoUid();
		QsessionInfoPo session = sessionMap.get(effectUid);
		// 获取参数
		Map<String, Object> originMap = session.paramsMap;
		Map<String, Object> newParamMap = new HashMap<String, Object>();
		newParamMap.put("g_tk", originMap.get("g_tk"));
		newParamMap.put("qzonetoken", originMap.get("qzonetoken"));
		newParamMap.put("format", "json");
		newParamMap.put("num", QqConfig.DEFAULT_MSG_NUM);
		newParamMap.put("hostUin", uid);
		newParamMap.put("topicId", topicId);
		newParamMap.put("uin", effectUid);
		newParamMap.put("pageNum", QqConfig.DEFAULT_IMG_NUM);
		newParamMap.put("pageStart", pos);
		newParamMap.put("mode", "0");
		newParamMap.put("idcNum", "0");
		newParamMap.put("noTopic", "0");
		newParamMap.put("skipCmtCount", "0");
		newParamMap.put("singleurl", "1");
		newParamMap.put("batchId", "");
		newParamMap.put("notice", "0");
		newParamMap.put("appid", "4");
		newParamMap.put("inCharset", "utf-8");
		newParamMap.put("outCharset", "utf-8");
		newParamMap.put("source", "qzone");
		newParamMap.put("plat", "qzone");
		newParamMap.put("outstyle", "json");
		newParamMap.put("format", "json");
		newParamMap.put("json_esc", "1");

		// 获取会话信息
		Map<String, String> cookieMap = session.cookieMap;

		String content = YhHttpUtil.sendHttpGetWithRetry(QqConfig.QQ_IMGINFO_URL, newParamMap, cookieMap);
		Map<String, Object> contentMap = JSON.parseObject(content, Map.class);
		Object code = contentMap.get("code");
		Object subcode = contentMap.get("subcode");
		String codeStr = code != null ? code.toString() : "";
		String subcodeStr = subcode != null ? subcode.toString() : "";
		String message = ObjectUtil.toString(contentMap.get("message"));
		while (checkOffen(codeStr, subcodeStr, message) && (photoUidsList.size()) > 0) {
			removePhotoUid(effectUid);
			logger.info("【imgInfo " + effectUid + "】=" + content);
			return crawlQzoneImgInfo(uid, pos, topicId);
		}
		return contentMap;
	}

	// get emot 说说
	public Map<String, Object> crwalQzoneEmotInfoContent(String uid, int pos) {

		if (emotUidsList == null || emotUidsList.size() == 0) {
			logger.info("################### emotinfo is over ######################");
			return null;
		}

		String effectUid = getEmotUid();
		QsessionInfoPo session = sessionMap.get(effectUid);
		// 获取参数
		Map<String, Object> originMap = session.paramsMap;
		Map<String, Object> newParamMap = new HashMap<String, Object>();
		newParamMap.put("g_tk", originMap.get("g_tk"));
		newParamMap.put("qzonetoken", originMap.get("qzonetoken"));
		newParamMap.put("format", "json");
		newParamMap.put("pos", pos);
		newParamMap.put("num", QqConfig.DEFAULT_NUM);
		newParamMap.put("code_version", QqConfig.CODE_VERSION);
		newParamMap.put("replynum", QqConfig.REPLYNUM);
		newParamMap.put("ftype", 0);
		newParamMap.put("sort", 0);
		newParamMap.put("need_private_comment", 1);
		newParamMap.put("uin", uid);

		// 获取会话信息
		Map<String, String> cookieMap = session.cookieMap;

		String content = YhHttpUtil.sendHttpGetWithRetry(QqConfig.QQ_EMOTINFO_URL, newParamMap, cookieMap);
		Map<String, Object> contentMap = JSON.parseObject(content, Map.class);
		Object code = contentMap.get("code");
		Object subcode = contentMap.get("subcode");
		String codeStr = code != null ? code.toString() : "";
		String subcodeStr = subcode != null ? subcode.toString() : "";
		String message = ObjectUtil.toString(contentMap.get("message"));
		while (checkOffen(codeStr, subcodeStr, message) && (emotUidsList.size()) > 0) {
			logger.info("【emotInfo " + effectUid + "】=" + content);
			removeEmotUid(effectUid);
			return crwalQzoneEmotInfoContent(uid, pos);
		}
		return contentMap;
	}

	public Map<String, Object> crawlQzoneVisitInfoContent(String uid) {
		if (visitUidsList == null || visitUidsList.size() == 0) {
			return null;
		}
		String effectUid = getVisitUid();
		QsessionInfoPo session = sessionMap.get(effectUid);
		// 获取参数
		Map<String, Object> originMap = session.paramsMap;
		Map<String, Object> newParamMap = new HashMap<String, Object>();
		newParamMap.put("g_tk", originMap.get("g_tk"));
		newParamMap.put("qzonetoken", originMap.get("qzonetoken"));
		newParamMap.put("format", "json");
		newParamMap.put("uin", uid);
		newParamMap.put("mask", "2");
		newParamMap.put("mod", "2");
		newParamMap.put("fupdate", 1);

		// 获取会话信息
		Map<String, String> cookieMap = session.cookieMap;

		String content = YhHttpUtil.sendHttpGetWithRetry(QqConfig.QQ_VISITINFO_URL, newParamMap, cookieMap);
		try {
			Map<String, Object> contentMap = JSON.parseObject(content, Map.class);
			Object code = contentMap.get("code");
			Object subcode = contentMap.get("subcode");
			String codeStr = code != null ? code.toString() : "";
			String subcodeStr = subcode != null ? subcode.toString() : "";
			String message = ObjectUtil.toString(contentMap.get("message"));
			boolean checkFlag = checkOffen(codeStr, subcodeStr, message);
			contentMap.put("checkFlag", checkFlag);
			while (checkFlag && (visitUidsList.size()) > 0) {
				logger.info("【visitInfo " + effectUid + "】=" + content);
				removeVisitUid(effectUid);
				// removeUids(effectUid);
				return crawlQzoneVisitInfoContent(uid);
			}
			return contentMap;
		} catch (Exception e) {
			Map<String, Object> eMap = new HashMap<String, Object>();
			eMap.put("gateWay", true);
			e.printStackTrace();
			logger.info("visitInfo------" + content);
			return eMap;
		}
	}
	
	//TODO 此方法不可用 
	public String sendEmot(String uid,String content) {
		QsessionInfoPo session = sessionMap.get(uid);
		if(null == session) {
			return "<h1> "+uid+"uid please login first </h1>";
		}
		Map<String,Object> originMap = session.paramsMap;
		Map<String,String> cookieMap = session.cookieMap;
		
		Map<String, Object> newParamMap = new HashMap<String, Object>();
		newParamMap.put("g_tk", originMap.get("g_tk"));
		newParamMap.put("qzonetoken", originMap.get("qzonetoken"));
		
		
		newParamMap.put("syn_tweet_verson",1);
		newParamMap.put("paramstr", 1);
		newParamMap.put("pic_template", "");
		newParamMap.put("richval", "");
		newParamMap.put("special_url","");
		
		newParamMap.put("subrichtype","");
		newParamMap.put("con","qm"+content);
		newParamMap.put("feedversion", "1");
		newParamMap.put("ver", "1");
		newParamMap.put("ugc_right","1");
		
		newParamMap.put("to_sign","1");
		newParamMap.put("hostuin",uid);
		newParamMap.put("feedversion", "1");
		newParamMap.put("code_version", "1");
		newParamMap.put("format","fs");
		newParamMap.put("qzreferrer","https://user.qzone.qq.com/1843594995?ADUIN=1843594995&ADSESSION=1541985158&ADTAG=CLIENT.QQ.5575_MyInfo_PersonalInfo.0&ADPUBNO=26809&source=namecardstar");
		String resp = YhHttpUtil.sendHttpGetWithRetry(QqConfig.QQ_SENT_EMOT_URL, newParamMap, cookieMap);
		logger.info(resp);
		return resp;
	}
	
	
	
	
	
	
	
	
	

}
