package com.man.qqdog.biz.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.man.constant.QcodeEnum;
import com.man.qq.QqConfig;
import com.man.qqdog.biz.utils.QqModelTransform;
import com.man.qqdog.client.po.QimgVideoPo;
import com.man.qqdog.client.po.QphotoImgPo;
import com.man.qqdog.client.po.QphotoInfoPo;
import com.man.qqdog.client.po.QsessionInfoPo;
import com.man.qqdog.client.po.QtaskInfoPo;
import com.man.qqdog.client.po.QuserInfoPo;
import com.man.qqdog.client.service.QUserService;
import com.man.qqdog.client.service.QemotService;
import com.man.qqdog.client.service.QmsgService;
import com.man.qqdog.client.service.QphotoInfoService;
import com.man.qqdog.client.service.QsessionService;
import com.man.qqdog.client.service.QtaskInfoService;
import com.man.utils.ObjectUtil;
import com.man.utils.YhHttpUtil;

@Service
public class QqManager {

	@Autowired
	private QsessionService qsessionService;

	@Autowired
	private QUserService quserService;

	@Autowired
	private QtaskInfoService qtaskService;

	@Autowired
	private QmsgService qmsgService;

	@Autowired
	private QemotService qemotService;

	@Autowired
	private QphotoInfoService qphotoInfoService;

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

	// 记录最后uidsSize 最后一次集合为空的时间戳
	public Map<String, Long> lastClearMap = new HashMap<String, Long>();

	// 等待最长时间 15m
	public static long MAX_WAIT_TIME = 1000 * 60 * 15;
	public static String BASE_KEY = "base_key";// 基本信息key
	public static String MSG_KEY = "msg_key";// msg key
	public static String PHOTO_KEY = "photo_key";// photo key
	public static String EMOT_KEY = "emto_key";// emot key

	// record remove uid log
	public Logger removeLogger = LoggerFactory.getLogger("removeUidLogger");

	public Logger logger = LoggerFactory.getLogger(QqManager.class);

	public Logger okLogger = LoggerFactory.getLogger("okUidLogger");

	// 标记 effectUid 访问 QQ 时获取内容为空时的计数
	public ConcurrentHashMap<String, Integer> resultBlankMap = new ConcurrentHashMap<>();

	// uid 起始位置
	public long startUid;

	private Set<Long> noVisitSet = new HashSet<>(100);

	private ConcurrentHashMap<String, Object> noVisitSetLock = new ConcurrentHashMap<>();

	private int MAX_BLANK_TRY = 100;

	public void addNoVisit(long uid) {
		synchronized (noVisitSetLock) {
			noVisitSet.add(uid);
			if (noVisitSet.size() >= 5) {
				quserService.batchInsertUidsN(noVisitSet);
				noVisitSet = new HashSet<>(5);
			}
		}
	}

	public void initStartUid(long uid) {
		this.startUid = uid;
	}

	public synchronized long getNextUid() {
		return ++startUid;
	}

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

	public void removeEmotUid(String uid, String content) {
		dealEffectUid(uid, content);
		synchronized (emotLock) {
			if (emotUidsList.size() > 0) {
				emotUidsList.remove(uid);
			}
			removeLogger.info("remove emot uid {} ", uid);
			if (emotUidsList.size() == 0) {
				lastClearMap.put(EMOT_KEY, System.currentTimeMillis());
			}
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

	public void removeMsgUid(String uid, String content) {
		dealEffectUid(uid, content);
		synchronized (msgLock) {
			if (msgUidsList.size() > 0) {
				msgUidsList.remove(uid);
			}
			removeLogger.info("remove msg uid {} ", uid);
			if (msgUidsList.size() == 0) {
				lastClearMap.put(MSG_KEY, System.currentTimeMillis());
			}
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

	public void removeAllUids(String uid) {
		if (userInfoUidsList.size() > 0) {
			userInfoUidsList.remove(uid);
		}
		if (emotUidsList.size() > 0) {
			emotUidsList.remove(uid);
		}
		if (photoUidsList.size() > 0) {
			photoUidsList.remove(uid);
		}
		if (msgUidsList.size() > 0) {
			msgUidsList.remove(uid);
		}

	}

	private void dealEffectUid(String effectUid, String content) {
		if (content == null || "".equals(content.trim())) {
			return;
		}
		removeLogger.info("dealEffectUid effectUid={} content={}", effectUid, content);
		if (content.indexOf("登录") > 0) {
			removeAllUids(effectUid);
			logger.error("effectUid = {} content = {}", effectUid, content);
			QsessionInfoPo po = new QsessionInfoPo();
			po.flag = -1;
			po.updateDate = new Date();
			po.msg = content;
			po.uid = effectUid;
			qsessionService.updateQsessionInfo(po);
		}
	}

	public void removePhotoUid(String uid, String content) {
		dealEffectUid(uid, content);
		synchronized (photoLock) {
			if (photoUidsList.size() > 0) {
				photoUidsList.remove(uid);
			}
			removeLogger.info("remove  photo uid {} ", uid);
			if (photoUidsList.size() == 0) {
				lastClearMap.put(PHOTO_KEY, System.currentTimeMillis());
			}
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

	private void initList() {
		userInfoUidsList = new LinkedList<>();
		resetUids();
	}

	// 初始化
	public void initSession() {
		List<QsessionInfoPo> datas = qsessionService.getAllSession();
		if (null != datas && datas.size() > 0) {
			initList();
			for (QsessionInfoPo data : datas) {
				sessionMap.put(data.uid, data);
				addUids(data.uid);
			}
		}
	}
	
	public void initSessionByDatas(List<QsessionInfoPo> list) {
		List<QsessionInfoPo> datas = list;
		if (null != datas && datas.size() > 0) {
			initList();
			for (QsessionInfoPo data : datas) {
				sessionMap.put(data.uid, data);
				addUids(data.uid);
			}
		}
	}

	private void resetUids() {
		emotUidsList = new LinkedList<>();
		photoUidsList = new LinkedList<>();
		msgUidsList = new LinkedList<>();
	}

	public void resetSession() {
		List<QsessionInfoPo> datas = qsessionService.getAllSession();
		if (null != datas && datas.size() > 0) {
			resetUids();
			for (QsessionInfoPo data : datas) {
				sessionMap.put(data.uid, data);
				addUids(data.uid);
			}
		}
	}

	public int checkRemove(String effectUid) {
		Integer num = resultBlankMap.get(effectUid);
		int blankNum = num == null ? 1 : num + 1;
		resultBlankMap.put(effectUid, blankNum);
		return blankNum;
	}

	public void clearRemove(String effectUid) {
		resultBlankMap.put(effectUid, 0);
	}

	public boolean checkOffen(String codeStr, String subcodeStr, String message) {
		return ((QcodeEnum.QOFEN_P.code + "").equals(codeStr) && subcodeStr.equals(QcodeEnum.QOFEN_P.subcode + ""))
				|| (codeStr.equals(QcodeEnum.QLOGIN_P.code + "") && subcodeStr.equals(QcodeEnum.QLOGIN_P.subcode + "")
						|| (message.indexOf("频") >= 0)

				);
	}

	public boolean checkLogin(String codeStr, String subcodeStr, String message) {
		return ((QcodeEnum.QOFEN_P.code + "").equals(codeStr) && subcodeStr.equals(QcodeEnum.QOFEN_P.subcode + ""))
				|| (codeStr.equals(QcodeEnum.QLOGIN_P.code + "") && subcodeStr.equals(QcodeEnum.QLOGIN_P.subcode + ""));
	}

	public boolean checkOptOffen(String content) {
		return (content.indexOf("频")) >= 0;
	}

	public void removeUids(String uid, String content) {
		dealEffectUid(uid, content);
		removeLogger.info("uid={} content={} ", uid, content);
		removeUserInfoUid(uid);
		removeEmotUid(uid, "");
		removePhotoUid(uid, "");
		removeMsgUid(uid, "");
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
		Map contentMap = null;
		try {
			contentMap = JSON.parseObject(content, Map.class);
			Object code = contentMap.get("code");
			Object subcode = contentMap.get("subcode");
			String codeStr = code != null ? code.toString() : "";
			String subcodeStr = subcode != null ? subcode.toString() : "";
			String message = ObjectUtil.toString(contentMap.get("message"));
			// 检查是否调用频繁
			boolean loginChek = checkLogin(codeStr, subcodeStr, message);
			contentMap.put("checkFlag", loginChek);

			while (loginChek && (userInfoUidsList.size()) > 0) {
				logger.info("【basinfo " + effectUid + "】=" + content);
				removeUids(effectUid, content);
				return crawlQzoneBaseInfoContent(uid);
			}
			if (checkOptOffen(message)) {
				// 操作频繁
				logger.error("userinfo 操作频繁 effectUid={}  sleep 15min scontent={} ", effectUid, content);
				Thread.sleep(1000 * 15 * 60);
				return crawlQzoneBaseInfoContent(uid);
			}
		} catch (Exception e) {
			// removeUids(effectUid);
			int num = checkRemove(effectUid);
			if (num >= MAX_BLANK_TRY) {
				removeUids(effectUid, "MAX_BLANK_TRY");
			}
			logger.error("effectUid={} targetUid={} content= 【{}】 blankNum={} ", effectUid, uid, content, num);
			logger.error("err exception ", e);
			return crawlQzoneBaseInfoContent(uid);
		}
		clearRemove(effectUid);
		return contentMap;
	}

	// get msg
	public Map<String, Object> crawlQzoneMsgInfoContent(String uid, int pos) {
		if (msgUidsList == null || msgUidsList.size() == 0) {
			logger.info("################### msgInfo is over ######################");
			return null;
		}

		String effectUid = getMsgUid();
		QsessionInfoPo session = sessionMap.get(effectUid);
		// logger.info("current uid is {}", effectUid);
		// 获取参数
		Map<String, Object> originMap = session.paramsMap;
		Map<String, Object> newParamMap = new HashMap<String, Object>();
		// logger.info("parmas = {}", JSON.toJSONString(originMap));
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
		Map<String, Object> contentMap = null;
		String content = YhHttpUtil.sendHttpGetWithRetry(QqConfig.QQ_MSGINFO_URL, newParamMap, cookieMap);
		try {
			contentMap = JSON.parseObject(content, Map.class);
			Object code = contentMap.get("code");
			Object subcode = contentMap.get("subcode");
			String codeStr = code != null ? code.toString() : "";
			String subcodeStr = subcode != null ? subcode.toString() : "";
			String message = ObjectUtil.toString(contentMap.get("message"));
			while (checkOffen(codeStr, subcodeStr, message) && (msgUidsList.size()) > 0) {
				removeMsgUid(effectUid, content);
				logger.info("【msgInfo " + effectUid + "】=" + content);
				return crawlQzoneMsgInfoContent(uid, pos);
			}
		} catch (Exception e) {
			int num = checkRemove(effectUid);
			if (num >= MAX_BLANK_TRY) {
				removeMsgUid(effectUid, "MAX_BLANK_TRY");
			}
			logger.error("effectUid={} targetUid={} msg content 【{}】 blankNum={}", effectUid, uid, content, num);
			logger.error("msg exception ", e);
			return crawlQzoneMsgInfoContent(uid, pos);
		}
		clearRemove(effectUid);
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
		Map<String, Object> contentMap = null;
		try {
			contentMap = JSON.parseObject(content, Map.class);
			Object code = contentMap.get("code");
			Object subcode = contentMap.get("subcode");
			String codeStr = code != null ? code.toString() : "";
			String subcodeStr = subcode != null ? subcode.toString() : "";
			String message = ObjectUtil.toString(contentMap.get("message"));
			boolean checkFlag = checkOffen(codeStr, subcodeStr, message);
			contentMap.put("checkFlag", checkFlag);
			while (checkFlag && (photoUidsList.size()) > 0) {
				logger.info("【photoInfo " + effectUid + "】=" + content);
				removePhotoUid(effectUid, content);
				return crawlQzonePhotoInfo(uid);
			}
		} catch (Exception e) {
			int num = checkRemove(effectUid);
			if (num >= MAX_BLANK_TRY) {
				removePhotoUid(effectUid, "MAX_BLANK_TRY");
			}
			logger.error("effectUid={} targetUid={} photo content 【{}】 blanNum={} ", effectUid, uid, content, num);
			logger.error("photo error ", e);
			return crawlQzonePhotoInfo(uid);
		}
		clearRemove(effectUid);
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
//		g_tk: 1054386212
//		callback: shine0_Callback
//		t: 637219642
//		mode: 0
//		idcNum: 4
//		hostUin: 1003769227
//		topicId: V13nQSkZ0GqAiT
//		noTopic: 0
//		uin: 3246615477
//		pageStart: 0
//		pageNum: 30
//		skipCmtCount: 0
//		singleurl: 1
//		batchId: 
//		notice: 0
//		appid: 4
//		inCharset: utf-8
//		outCharset: utf-8
//		source: qzone
//		plat: qzone
//		outstyle: json
//		format: jsonp
//		json_esc: 1
//		question: 
//		answer: 
//		callbackFun: shine0
		// 获取参数
		Map<String, Object> originMap = session.paramsMap;
		if(null == originMap) {
			originMap  = JSON.parseObject(session.params,Map.class);
		}
		if(null == session.cookieMap) {
			session.cookieMap = JSON.parseObject(session.cookie,Map.class);
		}
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
		//newParamMap.put("idcNum", "4");
		newParamMap.put("noTopic", "0");
		newParamMap.put("skipCmtCount", "0");
		newParamMap.put("singleurl", "1");
		newParamMap.put("batchId", "");
		newParamMap.put("appid", "4");
		newParamMap.put("inCharset", "utf-8");
		newParamMap.put("outCharset", "utf-8");
		newParamMap.put("source", "qzone");
		newParamMap.put("plat", "qzone");
		newParamMap.put("outstyle", "json");
		newParamMap.put("format", "json");
		newParamMap.put("notice","0");//new 
		newParamMap.put("json_esc", "1");

		// 获取会话信息
		Map<String, String> cookieMap = session.cookieMap;

		String content = YhHttpUtil.sendHttpGetWithRetry(QqConfig.QQ_IMGINFO_URL, newParamMap, cookieMap);
		Map<String, Object> contentMap = null;
		try {
			contentMap = JSON.parseObject(content, Map.class);
			Object code = contentMap.get("code");
			Object subcode = contentMap.get("subcode");
			String codeStr = code != null ? code.toString() : "";
			String subcodeStr = subcode != null ? subcode.toString() : "";
			String message = ObjectUtil.toString(contentMap.get("message"));
			while (checkOffen(codeStr, subcodeStr, message) && (photoUidsList.size()) > 0) {
				removePhotoUid(effectUid, content);
				logger.info("【imgInfo " + effectUid + "】=" + content);
				return crawlQzoneImgInfo(uid, pos, topicId);
			}
		} catch (Exception e) {
			int num = checkRemove(effectUid);
			if (num >= MAX_BLANK_TRY) {
				removePhotoUid(effectUid, "MAX_BLANK_TRY");
			}
			logger.error("effectUid={} targetUid={} topicId={} img error content 【{}】 blankNum={}", effectUid, uid,
					topicId, content, num);
			logger.error(" img error ", e);
			return crawlQzoneImgInfo(uid, pos, topicId);
		}
		clearRemove(effectUid);
		return contentMap;
	}
	
	// get img info 相册中图片
		public String crawlQzoneImgInfo_test(String uid, int pos, String topicId) {
			if (photoUidsList == null || photoUidsList.size() == 0) {
				logger.info("################### imgInfo is over ######################");
				return null;
			}
			String effectUid = getPhotoUid();
			QsessionInfoPo session = sessionMap.get(effectUid);
//			g_tk: 1054386212
//			callback: shine0_Callback
//			t: 637219642
//			mode: 0
//			idcNum: 4
//			hostUin: 1003769227
//			topicId: V13nQSkZ0GqAiT
//			noTopic: 0
//			uin: 3246615477
//			pageStart: 0
//			pageNum: 30
//			skipCmtCount: 0
//			singleurl: 1
//			batchId: 
//			notice: 0
//			appid: 4
//			inCharset: utf-8
//			outCharset: utf-8
//			source: qzone
//			plat: qzone
//			outstyle: json
//			format: jsonp
//			json_esc: 1
//			question: 
//			answer: 
//			callbackFun: shine0
			// 获取参数
			Map<String, Object> originMap = session.paramsMap;
			if(null == originMap) {
				originMap  = JSON.parseObject(session.params,Map.class);
			}
			if(null == session.cookieMap) {
				session.cookieMap = JSON.parseObject(session.cookie,Map.class);
			}
			Map<String, Object> newParamMap = new HashMap<String, Object>();
			newParamMap.put("g_tk", originMap.get("g_tk"));
			//newParamMap.put("qzonetoken", originMap.get("qzonetoken"));
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
			newParamMap.put("appid", "4");
			newParamMap.put("inCharset", "utf-8");
			newParamMap.put("outCharset", "utf-8");
			newParamMap.put("source", "qzone");
			newParamMap.put("plat", "qzone");
			newParamMap.put("outstyle", "json");
			newParamMap.put("format", "json");
			newParamMap.put("notice","0");//new 
			newParamMap.put("json_esc", "1");
			
			
			newParamMap.put("question", "");
			newParamMap.put("answer", "");
			newParamMap.put("format", "jsonp");
			newParamMap.put("callback", "shine0_Callback");
			newParamMap.put("idcNum", "4");
			
			

			// 获取会话信息
			Map<String, String> cookieMap = session.cookieMap;
			cookieMap.put("referer", "https://qzs.qq.com/qzone/photo/v7/page/photo.html?init=photo.v7/module/photoList2/index&navBar=1&normal=1&aid=V13nQSkZ0GqAiT");
			//:authority: h5.qzone.qq.com
			//:method: GET
			//:path: /proxy/domain/photo.qzone.qq.com/fcgi-bin/cgi_list_photo?g_tk=1054386212&callback=shine0_Callback&t=637219642&mode=0&idcNum=4&hostUin=1003769227&topicId=V13nQSkZ0GqAiT&noTopic=0&uin=3246615477&pageStart=0&pageNum=30&skipCmtCount=0&singleurl=1&batchId=&notice=0&appid=4&inCharset=utf-8&outCharset=utf-8&source=qzone&plat=qzone&outstyle=json&format=jsonp&json_esc=1&question=&answer=&callbackFun=shine0&_=1547433170944
			cookieMap.put(":authority","h5.qzone.qq.com");
			cookieMap.put(":method","GET");
			cookieMap.put(":path","/proxy/domain/photo.qzone.qq.com/fcgi-bin/cgi_list_photo?g_tk=1054386212&callback=shine0_Callback&t=637219642&mode=0&idcNum=4&hostUin=1003769227&topicId=V13nQSkZ0GqAiT&noTopic=0&uin=3246615477&pageStart=0&pageNum=30&skipCmtCount=0&singleurl=1&batchId=&notice=0&appid=4&inCharset=utf-8&outCharset=utf-8&source=qzone&plat=qzone&outstyle=json&format=jsonp&json_esc=1&question=&answer=&callbackFun=shine0&_=1547433170944");
			String content = YhHttpUtil.sendHttpGetWithRetry(QqConfig.QQ_IMGINFO_URL, newParamMap, cookieMap);
			return content;
		}

	// 获取相册里的视频文件
	public Map<String, Object> crawlImgVedio(String uid, String topicId, String picKey) {
		if (photoUidsList == null || photoUidsList.size() == 0) {
			logger.info("################### crawlImgVedio imgInfo is over ######################");
			return null;
		}
		String effectUid = getPhotoUid();
		QsessionInfoPo session = sessionMap.get(effectUid);
		// 获取参数
		Map<String, Object> originMap = session.paramsMap;

		Map<String, Object> params = new HashMap<>();
		params.put("g_tk", originMap.get("g_tk"));
		params.put("callback", "viewer_Callback");
		// params.put("t","972306368");
		params.put("topicId", topicId);
		params.put("picKey", picKey);
		params.put("shootTime", "");
		params.put("cmtOrder", "1");
		params.put("fupdate", "1");
		params.put("plat", "qzone");
		params.put("source", "qzone");
		params.put("cmtNum", "10");
		params.put("likeNum", "5");
		params.put("inCharset", "utf-8");
		params.put("outCharset", "utf-8");
		params.put("callbackFun", "viewer");
		params.put("offset", "0");
		params.put("number", "15");
		params.put("uin", effectUid);
		params.put("appid", "4");
		params.put("isFirst", "1");
		params.put("hostUin", uid);
		params.put("sortOrder", "1");
		params.put("showMode", "1");
		params.put("need_private_comment", "1");
		params.put("prevNum", "0");
		params.put("postNum", "0");
		params.put("format", "json");

		// 获取会话信息
		Map<String, String> cookieMap = session.cookieMap;

		String content = YhHttpUtil.sendHttpGetWithRetry(QqConfig.QQ_IMG_VEDIO_URL, params, cookieMap);
		Map<String, Object> contentMap = null;
		try {
			contentMap = JSON.parseObject(content, Map.class);
			Object code = contentMap.get("code");
			Object subcode = contentMap.get("subcode");
			String codeStr = code != null ? code.toString() : "";
			String subcodeStr = subcode != null ? subcode.toString() : "";
			String message = ObjectUtil.toString(contentMap.get("message"));
			while (checkOffen(codeStr, subcodeStr, message) && (photoUidsList.size()) > 0) {
				removePhotoUid(effectUid, content);
				logger.info("【crawlImgVedio imgInfo " + effectUid + "】=" + content);
				return crawlImgVedio(effectUid, topicId, picKey);
			}
		} catch (Exception e) {
			int num = checkRemove(effectUid);
			if (num >= MAX_BLANK_TRY) {
				removePhotoUid(effectUid, "crawlImgVedio MAX_BLANK_TRY");
			}
			logger.error("crawlImgVedio effectUid={} targetUid={} topicId={} img error content 【{}】 blankNum={}", effectUid, uid,
					topicId, content, num);
			logger.error(" crawlImgVedio  error ", e);
			return crawlImgVedio(effectUid, topicId, picKey);
		}
		clearRemove(effectUid);
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
		newParamMap.put("num", QqConfig.DEFAULT_EMOT_NUM);
		newParamMap.put("code_version", QqConfig.CODE_VERSION);
		newParamMap.put("replynum", QqConfig.REPLYNUM);
		newParamMap.put("ftype", 0);
		newParamMap.put("sort", 0);
		newParamMap.put("need_private_comment", 1);
		newParamMap.put("uin", uid);

		// 获取会话信息
		Map<String, String> cookieMap = session.cookieMap;

		String content = YhHttpUtil.sendHttpGetWithRetry(QqConfig.QQ_EMOTINFO_URL, newParamMap, cookieMap);
		Map<String, Object> contentMap = null;
		try {
			contentMap = JSON.parseObject(content, Map.class);
			Object code = contentMap.get("code");
			Object subcode = contentMap.get("subcode");
			String codeStr = code != null ? code.toString() : "";
			String subcodeStr = subcode != null ? subcode.toString() : "";
			String message = ObjectUtil.toString(contentMap.get("message"));
			while (checkOffen(codeStr, subcodeStr, message) && (emotUidsList.size()) > 0) {
				logger.info("【emotInfo " + effectUid + "】=" + content);
				removeEmotUid(effectUid, content);
				return crwalQzoneEmotInfoContent(uid, pos);
			}
		} catch (Exception e) {
			int num = checkRemove(effectUid);
			if (num >= MAX_BLANK_TRY) {
				removeEmotUid(effectUid, "MAX_BLANK_TRY");
			}
			logger.error("effectUid={} targetUid={} emot content 【{}】 blankNum={} ", effectUid, uid, content, num);
			logger.error("emot error ", e);
			return crwalQzoneEmotInfoContent(uid, pos);
		}
		clearRemove(effectUid);
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

	// TODO 此方法不可用
	public String sendEmot(String uid, String content) {
		QsessionInfoPo session = sessionMap.get(uid);
		if (null == session) {
			return "<h1> " + uid + "uid please login first </h1>";
		}
		Map<String, Object> originMap = session.paramsMap;
		Map<String, String> cookieMap = session.cookieMap;

		Map<String, Object> newParamMap = new HashMap<String, Object>();
		newParamMap.put("g_tk", originMap.get("g_tk"));
		newParamMap.put("qzonetoken", originMap.get("qzonetoken"));

		newParamMap.put("syn_tweet_verson", 1);
		newParamMap.put("paramstr", 1);
		newParamMap.put("pic_template", "");
		newParamMap.put("richval", "");
		newParamMap.put("special_url", "");

		newParamMap.put("subrichtype", "");
		newParamMap.put("con", "qm" + content);
		newParamMap.put("feedversion", "1");
		newParamMap.put("ver", "1");
		newParamMap.put("ugc_right", "1");

		newParamMap.put("to_sign", "1");
		newParamMap.put("hostuin", uid);
		newParamMap.put("feedversion", "1");
		newParamMap.put("code_version", "1");
		newParamMap.put("format", "fs");
		newParamMap.put("qzreferrer",
				"https://user.qzone.qq.com/1843594995?ADUIN=1843594995&ADSESSION=1541985158&ADTAG=CLIENT.QQ.5575_MyInfo_PersonalInfo.0&ADPUBNO=26809&source=namecardstar");
		String resp = YhHttpUtil.sendHttpGetWithRetry(QqConfig.QQ_SENT_EMOT_URL, newParamMap, cookieMap);
		logger.info(resp);
		return resp;
	}

	public int downAllMsg(long uid) {
		QtaskInfoPo taskInfo = new QtaskInfoPo();
		taskInfo.uid = uid;
		taskInfo.msgStart = 0;
		int msgTotalNum = 0;
		qtaskService.insertQtaskInfo(taskInfo);
		// msg start
		Map<String, Object> firstMsgMap = crawlQzoneMsgInfoContent(uid + "", 0);
		if (firstMsgMap != null) {
			Map<String, Object> dataMap = ObjectUtil.castMapObj(firstMsgMap.get("data"));
			// 总记录数
			msgTotalNum = ObjectUtil.getInt(dataMap, "total");
			// 计算页数
			int msgPageNum = (msgTotalNum + QqConfig.DEFAULT_MSG_NUM - 1) / QqConfig.DEFAULT_MSG_NUM;
			logger.info("msg uid={} total={} totalPage={} pos={} msgUidSize={}", uid, msgTotalNum, msgPageNum, 0,
					msgUidsList.size());
			// 存储第一页
			qmsgService.saveMsgList(dataMap, uid);
			taskInfo.msgStart = 1;
			taskInfo.msgTotal = msgTotalNum;
			taskInfo.msgGet = 1;
			taskInfo.msgPage = msgPageNum;
			if (msgPageNum > 1) {
				int startPos = QqConfig.DEFAULT_MSG_NUM;
				for (int startPage = 2; startPage <= msgPageNum; startPage++) {
					startPos = (startPage - 1) * QqConfig.DEFAULT_MSG_NUM;
					firstMsgMap = crawlQzoneMsgInfoContent(uid + "", startPos);
					if (firstMsgMap != null) {
						dataMap = ObjectUtil.castMapObj(firstMsgMap.get("data"));
						qmsgService.saveMsgList(dataMap, uid);
						taskInfo.msgGet = startPage;
						logger.info("msg uid={} total={} totalPage={} pos={} msgUidSize={}", uid, msgTotalNum,
								msgPageNum, startPos, msgUidsList.size());
					}
				}
			}
		}
		qtaskService.updateByPrimaryKeySelective(taskInfo);
		// msg end
		return msgTotalNum;

	}

	public void downAllEmot(long uid) {
		QtaskInfoPo taskInfo = new QtaskInfoPo();
		taskInfo.uid = uid;
		taskInfo.emotStart = 0;
		qtaskService.insertQtaskInfo(taskInfo);
		// emot start
		Map<String, Object> firstEmotMap = crwalQzoneEmotInfoContent(uid + "", 0);
		if (firstEmotMap != null) {
			qemotService.saveEmotInfoMap(firstEmotMap, uid);
			int emotTotalNum = ObjectUtil.getInt(firstEmotMap, "total");
			int emotPageNum = (emotTotalNum + QqConfig.DEFAULT_EMOT_REAL_NUM - 1) / QqConfig.DEFAULT_EMOT_REAL_NUM;
			int emotStartPos = QqConfig.DEFAULT_EMOT_REAL_NUM;
			logger.info("emot uid={} total={} totalPage={} pos={} emotUidSize={}", uid, emotTotalNum, emotPageNum, 0,
					emotUidsList.size());
			taskInfo.emotStart = 1;
			taskInfo.emotTotal = emotTotalNum;
			taskInfo.emotGet = 1;
			taskInfo.emotPage = emotPageNum;
			for (int startPage = 2; startPage <= emotPageNum; startPage++) {
				emotStartPos = (startPage - 1) * QqConfig.DEFAULT_EMOT_REAL_NUM;
				firstEmotMap = crwalQzoneEmotInfoContent(uid + "", emotStartPos);
				if (null != firstEmotMap) {
					logger.info("emot uid={} total={} totalPage={} pos={} emotUidSize={}", uid, emotTotalNum,
							emotPageNum, emotStartPos, emotUidsList.size());
					qemotService.saveEmotInfoMap(firstEmotMap, uid);
					taskInfo.emotGet = startPage;
				}
			}
		}
		qtaskService.updateByPrimaryKeySelective(taskInfo);
		// emot end
	}

	public List<Map<String, Object>> parsePhotos(Map<String, Object> photoDataMap, long uid) {
		List<Map<String, Object>> photoList = new ArrayList<>();
		List<Map<String, Object>> albumListModeSortList = ObjectUtil.castListObj(photoDataMap.get("albumListModeSort"));
		if (ObjectUtil.getSize(albumListModeSortList) > 0) {
			photoList.addAll(albumListModeSortList);
		}
		List<Map<String, Object>> albumListModeClassList = ObjectUtil
				.castListObj(photoDataMap.get("albumListModeClass"));
		if (ObjectUtil.getSize(albumListModeClassList) > 0) {
			for (Object obj : albumListModeClassList) {
				Map photoMap = ObjectUtil.castMapObj(obj);
				List pList = ObjectUtil.castListObj(photoMap.get("albumList"));
				logger.info("uid={} albumListModeClass===>albumList size {} ", uid, ObjectUtil.getSize(pList));
				if (ObjectUtil.getSize(pList) > 0) {
					photoList.addAll(pList);
				}
			}
		}
		return photoList;
	}

	public void downAllPhoto(long uid) {
		QtaskInfoPo taskInfo = new QtaskInfoPo();
		taskInfo.uid = uid;
		taskInfo.photoStart = 0;
		qtaskService.insertQtaskInfo(taskInfo);
		Map<String, Object> photoFirstMap = crawlQzonePhotoInfo(uid + "");
		if (null == photoFirstMap) {
			logger.error("photoUidsSize={} get photo is null maybe photoUids is null option too offen {} ",
					photoUidsList.size(), uid);
			return;
		}
		Map<String, Object> photoDataMap = ObjectUtil.castMapObj(photoFirstMap.get("data"));
		List<Map<String, Object>> photoList = parsePhotos(photoDataMap, uid);
		int photoSize = ObjectUtil.getSize(photoList);
		logger.info("uid={} photoSize={} photoUidSize={} ", uid, photoSize, photoUidsList.size());

		List<QphotoInfoPo> photoPoList = new ArrayList<>(photoSize);
		for (Map<String, Object> photoMap : photoList) {
			QphotoInfoPo po = QqModelTransform.converPhotoInfo(photoMap);
			po.uid = uid + "";
			po.id = qphotoInfoService.getId();
			photoPoList.add(po);
		}
		// insert batch
		qphotoInfoService.addPhotoBatch(photoPoList);

		QphotoInfoPo updatePo = new QphotoInfoPo();

		// down all img
		for (QphotoInfoPo photo : photoPoList) {
			if (QqConfig.ALLOW_ACCESS.equals(photo.allowAccess)) {
				int getNum = downAllPhotoImg(photo.topicid, uid, photo.id);
				updatePo.id = photo.id;
				updatePo.getnum = getNum;
				qphotoInfoService.updatePhotoSelective(updatePo);
			}
		}
	}

	// down all img of photo
	public int downAllPhotoImg(String topicId, long uid, long photoId) {
		Map<String, Object> imgFirstResultMap = crawlQzoneImgInfo(uid + "", 0, topicId);
		if (null == imgFirstResultMap) {
			return 0;
		}
		Map<String, Object> dataMap = ObjectUtil.castMapObj(imgFirstResultMap.get("data"));
		int imgTotalNum = ObjectUtil.getInt(dataMap, "totalInAlbum");
		if (imgTotalNum == 0) {
			return 0;
		}
		int getNumTotal = 0;
		List<Map<String, Object>> photoMapList = ObjectUtil.castListObj(dataMap.get("photoList"));
		int imgSize = ObjectUtil.getSize(photoMapList);
		getNumTotal += imgSize;
		List<QphotoImgPo> imgPoList = new ArrayList<>(imgSize);
		for (Map<String, Object> imgMap : photoMapList) {
			QphotoImgPo imgPo = QqModelTransform.converPhotoImgPo(imgMap);
			imgPo.id = qphotoInfoService.getId();
			imgPo.photoId = photoId;
			imgPo.uid = uid + "";
			imgPoList.add(imgPo);
		}
		logger.info("photo uid={} totalNum={} getNum={} photoUidSize={}", uid, imgTotalNum, getNumTotal,
				photoUidsList.size());
		int totalPage = (imgTotalNum + QqConfig.DEFAULT_IMG_NUM - 1) / QqConfig.DEFAULT_IMG_NUM;
		int pos = 0;
		Map<String,Object> videoContentMap = null;
		List<QimgVideoPo> imgVideos = new ArrayList<>();
		for (int startPage = 2; startPage <= totalPage; startPage++) {
			pos = (startPage - 1) * QqConfig.DEFAULT_IMG_NUM;
			imgFirstResultMap = crawlQzoneImgInfo(uid + "", pos, topicId);
			if (imgFirstResultMap == null) {
				break;
			}
			photoMapList = ObjectUtil.castListObj(dataMap.get("photoList"));
			imgSize = ObjectUtil.getSize(photoMapList);
			getNumTotal += imgSize;
			for (Map<String, Object> imgMap : photoMapList) {
				QphotoImgPo imgPo = QqModelTransform.converPhotoImgPo(imgMap);
				imgPo.id = qphotoInfoService.getId();
				imgPo.photoId = photoId;
				imgPo.uid = uid + "";
				imgPoList.add(imgPo);
				
				
			}
			logger.info("photo uid={} totalNum={} getNum={} photoUidSize={}", uid, imgTotalNum, getNumTotal,
					photoUidsList.size());
		}
		qphotoInfoService.addPhotoImgBatch(imgPoList);
		
		for(QphotoImgPo imgPo:imgPoList){
			if("true".equals(imgPo.isVideo)) {
				videoContentMap = crawlImgVedio(imgPo.uid,topicId,imgPo.sloc);
				QimgVideoPo video = QqModelTransform.converQimgVideo(videoContentMap);
				//logger.info("video oks {}  ",JSON.toJSONString(video));
				if(null != video) {
					video.id = qphotoInfoService.getId();
					video.imgId = imgPo.id;
					video.pickey = imgPo.sloc;
					video.uid = uid;
					video.photoId=imgPo.photoId;
					video.topicid = topicId;
					imgVideos.add(video);
				}
			}
		}
		
		qphotoInfoService.addImgVideoBatch(imgVideos);
		return getNumTotal;
	}

	// get all info of uid include msg,baseinfo ,emot,photo
	public void downAllQqInfo(long uid) {

		Map<String, Object> contentMap = crawlQzoneBaseInfoContent(uid + "");
		if (null == contentMap) {
			return;
		}
		// 无访问权限
		String ncode = "-4009";
		if (contentMap.get("gateWay") != null) {
			logger.error("err ******* 4009 ");
			return;
		}
		Object code = contentMap.get("code");
		String codeStr = code != null ? code.toString() : "";
		if (!"0".equals(codeStr) && ncode.equals(codeStr)) {
			// get baseinfo is error
			addNoVisit(uid);
			logger.info("uid={} is not visit ", uid);
			return;
		}

		okLogger.info("get qq {} ", uid);

		QtaskInfoPo taskInfo = new QtaskInfoPo();
		taskInfo.uid = uid;
		taskInfo.msgStart = 0;
		taskInfo.emotStart = 0;
		taskInfo.photoStart = 0;
		// baseinfo
		if ("0".equals(codeStr)) {
			QuserInfoPo quserInfoPo = QqModelTransform.converQuserInfo(contentMap);
			quserInfoPo.id = quserService.getId();
			quserInfoPo.uid = uid;
			quserService.addQuserInfo(quserInfoPo);
			qtaskService.insertQtaskInfo(taskInfo);
		}

		if (emotUidsList.size() == 0 || msgUidsList.size() == 0 || photoUidsList.size() == 0) {
			logger.info("******************uids is over sleep 15min emotUidSize={},msgUidSize={} photoUidSize={}",
					emotUidsList.size(), msgUidsList.size(), photoUidsList.size());
			try {
				Thread.sleep(1000 * 60 * 15);
				// 重置msg emot photo uids
				resetSession();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (photoUidsList.size() > 0) {
			downAllPhoto(uid);
		}
		if (emotUidsList.size() > 0) {
			downAllEmot(uid);
		}
		if (msgUidsList.size() > 0) {
			downAllMsg(uid);
		}
		
		

	}

	public void testException() {
		String ssk = "{dsdsdks";
		try {
			Map<String, Object> map = JSON.parseObject(ssk, Map.class);
		} catch (Exception e) {
			logger.error("content=== {} ", ssk);
			logger.error("testException error ", e);
		}
	}

}
