package com.man.qqdog.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.man.es.manager.ElasticSearchManager;
import com.man.qq.QqConfig;
import com.man.qqdog.biz.manager.QqManager;
import com.man.qqdog.biz.manager.StartCrawlThread;
import com.man.qqdog.biz.mapper.QemotInfoPoMapper;
import com.man.qqdog.biz.mapper.QuserInfoPoMapper;
import com.man.qqdog.client.po.QemotInfoPo;
import com.man.qqdog.client.po.QsessionInfoPo;
import com.man.qqdog.client.po.QuserInfoPo;
import com.man.qqdog.client.service.QUserService;
import com.man.qqdog.client.service.QsessionService;
import com.man.utils.ObjectUtil;
import com.man.utils.ReqParam;
import com.man.utils.ResultJson;
import com.man.utils.YhHttpUtil;

@Controller
@CrossOrigin(origins = "*", maxAge = 36000)
public class TestController extends BaseController {

	Map<String,Boolean> map = new HashMap<>();
	
	@Autowired
	private QUserService quserService;

	@Autowired
	private QsessionService qsessionService;

	Logger logger = LoggerFactory.getLogger(TestController.class);

	Logger qqLogger = LoggerFactory.getLogger("qqinfoLogger");

	Logger removeUidLogger = LoggerFactory.getLogger("removeUidLogger");
	
	@Autowired
	private QemotInfoPoMapper emotMapper;

	@Autowired
	private QqManager qqManager;
	
	@Autowired
	private QuserInfoPoMapper userInfoMapper;

	public Logger okLogger = LoggerFactory.getLogger("okUidLogger");
	
	public int startFlag = 0;
	
	@Autowired
	private ElasticSearchManager esManager;
	
	private Set<String> notUids = new HashSet<>(Arrays.asList("10187934231","1164019679"));
	
	@RequestMapping("/id")
	public void id(HttpServletResponse response) throws IOException {
		logger.info("info---DXMMM");
		logger.error("errrr-----");
		logger.debug("debug------");
		logger.warn("warn-------");
		long id = quserService.getId();
		qqLogger.info("id====={}", id);
		removeUidLogger.info("remove uid {} ", id);
		okLogger.info("ok uid {} ",id);
		qqManager.testException();
		System.out.println(qqLogger.getName());
		sendJson(response, id);
	}

	@RequestMapping("/showUids")
	public void showUids(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, LinkedList<String>> map = new HashMap<>();
		map.put("msg", qqManager.msgUidsList);
		map.put("emots", qqManager.emotUidsList);
		map.put("img", qqManager.imgUidsList);
		map.put("photo", qqManager.photoUidsList);
		map.put("userInfo", qqManager.userInfoUidsList);
		sendJson(response, map);
	}
	

	@RequestMapping("/addU")
	public void addU(HttpServletResponse response,HttpServletRequest request) throws IOException {
		ResultJson<QuserInfoPo> result = new ResultJson<>();
		ReqParam param = getParams(request);
		QuserInfoPo u = new QuserInfoPo();
		u.age = "1";
		u.birthday = "2018-09-09";
		u.birthyear = "1991";
		u.bloodtype = "1";
		u.career = "test";
		u.city = "大理";
		u.company = "tets";
		u.country = "中国";
		u.createGmt = new Date();
		u.emotNum = 0;
		u.flag = 1;
		u.hc = "昆明";
		u.hco = "云南";
		u.id = quserService.getId();
		u.uid = param.getLong("uid");
		u.nickname = "do98";
		u.describe="dsds";
		quserService.addQuserInfo(u);
		sendJson(response,result.success("操作成功",u));

	}

	@RequestMapping("/getQ")
	public void getQ(HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException {
		Map<String, Object> params = getReqParams(request);
		List headers = ObjectUtil.castListObj(params.get("requestHeaders"));
		String url = ObjectUtil.toString(params.get("url"));
		Map<String, String> cookie = parseHeaders(headers);
		Map<String, Object> paramsq = parseParamsFromUri(url);
		String uid = ObjectUtil.toString(paramsq.get("uin"));
		logger.info("cookie {}", JSON.toJSONString(cookie));
		logger.info("paramsq {}", JSON.toJSONString(paramsq));
		logger.info("uid={}", uid);
		if(notUids.contains(uid)) {
			return;
		}
		QsessionInfoPo info = new QsessionInfoPo();
		info.cookie = JSON.toJSONString(cookie);
		info.params = JSON.toJSONString(paramsq);
		info.createDate = new Date();
		info.uid = uid;
		info.flag = 0;
		info.updateDate = info.createDate;
		qsessionService.addQsessionInfo(info);
		qqManager.initSession();
		// infoManager.cookiesMap.put(uid, cookie);
		// infoManager.paramsMap.put(uid,paramsq);
		// infoManager.initUids.add(uid);
		// sendJson(response, params);

	}

	@RequestMapping("/getSession")
	public void getSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
		sendJsonWithDateFormat(response, qsessionService.getAllSession(), ObjectUtil.yyyyMMddHHmmss);
	}

	public Map<String, Object> parseParamsFromUri(String url) throws URISyntaxException {
		Map<String, Object> mapparams = new HashMap<String, Object>();
		List<NameValuePair> params = URLEncodedUtils.parse(new URI(url), "UTF-8");
		for (NameValuePair param : params) {
			mapparams.put(param.getName(), param.getValue());
		}
		return mapparams;
	}

	public Map<String, String> parseHeaders(List headers) {
		Map<String, String> headersMap = new HashMap<String, String>();
		if (null != headers && headers.size() > 0) {
			for (Object ho : headers) {
				Map m = ObjectUtil.castMapObj(ho);
				headersMap.put(ObjectUtil.toString(m.get("name")), ObjectUtil.toString(m.get("value")));
			}
		}
		return headersMap;
	}
	//test base info 
	@RequestMapping("/getInfo")
	public void getInfo(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String,Object> params = getReqParams(request);
		String uid = ObjectUtil.toString(params.get("uid"),"");
		sendJson(response,qqManager.crawlQzoneBaseInfoContent(uid));
	}
	
	@RequestMapping("/saveInfo")
	public void saveInfo(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam params = getParams(request);
		long uid = params.getLong("uid");
		qqManager.downAllQqInfo(uid);
		sendJson(response,"ok");
	}
	
	@RequestMapping("/getMsg")
	public void getMsg(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam params = getParams(request);
		String uid = params.getStr("uid");
		int pos = params.getInt("pos");
		sendJson(response,qqManager.crawlQzoneMsgInfoContent(uid, pos));
	}
	
	@RequestMapping("/getPhoto")
	public void getPhoto(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam params = getParams(request);
		String uid = params.getStr("uid");
		sendDefaultJson(response, qqManager.crawlQzonePhotoInfo(uid));
	}
	
	@RequestMapping("/getImg")
	public void getImg(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam params = getParams(request);
		String uid = params.getStr("uid");
		String topicId = params.getStr("topicId");
		int pos = params.getInt("pos");
		sendDefaultJson(response, qqManager.crawlQzoneImgInfo(uid, pos, topicId));
	}
	
	@RequestMapping("/sendEmot")
	public void sendEmot(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam params = getParams(request);
		String uid = params.getStr("uid");
		String content = params.getStr("content");
		sendHtml(response, qqManager.sendEmot(uid, content));
	}
	
	@RequestMapping("/getEmot")
	public void getEmot(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam params = getParams(request);
		String uid = params.getStr("uid");
		int pos = params.getInt("pos");
		sendDefaultJson(response, qqManager.crwalQzoneEmotInfoContent(uid, pos));
	}
	
	@RequestMapping("/emotBatch")
	public void emotBatch(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam params = getParams(request);
		int num = params.getInt("num");
		List<QemotInfoPo> datas = new ArrayList<>(num);
		for(int i = 0;i<num;i++) {
			QemotInfoPo data = new QemotInfoPo();
			data.id = quserService.getId();
			data.content="dsukds"+i;
			datas.add(data);
		}
		emotMapper.insertQemotInfoBatch(datas);
		sendDefaultJson(response, "ok");
	}
	
	@RequestMapping("/batchUserN")
	public void batchUserN(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam param = getParams(request);
		int  n = param.getInt("n");
		Set<Long> sets = new HashSet<Long>();
		for(long i = 0;i<n;i++) {
			sets.add(i);
		}
		userInfoMapper.batchInsertUidsN(sets);
		sendDefaultJson(response, "ok");
	}
	
	@RequestMapping("/getMaxUid")
	public void getMaxUid(HttpServletRequest request,HttpServletResponse response) throws IOException{
		sendDefaultJson(response, userInfoMapper.getMaxUid());
	}
	
	
	@RequestMapping("/downEmot")
	public void downEmot(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam params = getParams(request);
		long uid = params.getLong("uid");
		if(uid > 0) {
			qqManager.downAllEmot(uid);
		}
		sendDefaultJson(response, "ok");
	}
	@RequestMapping("/downMsg")
	public void downMsg(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam params = getParams(request);
		long uid = params.getLong("uid");
		if(uid > 0) {
			qqManager.downAllMsg(uid);
		}
		sendDefaultJson(response, "ok");
	}
	
	@RequestMapping("/downPhoto")
	public void downPhoto(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam params = getParams(request);
		long uid = params.getLong("uid");
		if(uid > 0) {
			qqManager.downAllPhoto(uid);
		}
		sendDefaultJson(response, "ok");
	}
	
	@RequestMapping("/downImg")
	public void downImg(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam params = getParams(request);
		long uid = params.getLong("uid");
		String topicId = params.getStr("topicId");
		if(uid > 0) {
			qqManager.downAllPhotoImg(topicId, uid,100);
		}
		sendDefaultJson(response, "ok");
	}
	
	
	@RequestMapping("/downQQ")
	public void downQQ(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam params = getParams(request);
		long uid = params.getLong("uid");
		long start = System.currentTimeMillis();
		if(uid > 0) {
			qqManager.downAllQqInfo(uid);
		}
		long end = System.currentTimeMillis();
		ResultJson<String> result = new ResultJson<>();
		result.data=(end-start)/1000 +"s";
		sendDefaultJson(response,result);
	}
	
	@RequestMapping("/startWork")
	public void startWork(HttpServletRequest request,HttpServletResponse response) throws IOException{
		long maxUid = quserService.getMaxUid();
		int size = getParams(request).getInt("threads");
		if(startFlag == 0) {
			qqManager.initStartUid(maxUid);
			startFlag  = 1;
			int uidSize = qqManager.userInfoUidsList.size();
			if(uidSize == 0){
				sendDefaultJson(response, "uid size 0 err");
				return;
			}
			if(size <= 0) {
				size = 2;
			}
			for(int i = 0;i<size;i++) {
				Thread t = new Thread(new StartCrawlThread(qqManager));
				t.start();
			}
		}
		sendDefaultJson(response, size+" begin work maxUid="+maxUid);
	}
	@RequestMapping("/start")
	public void start(HttpServletRequest request,HttpServletResponse response) throws IOException{
		long maxUid = quserService.getMaxUid();
		startFlag = 0;
		sendDefaultJson(response, "begin work maxUid="+maxUid);
	}
	
	@RequestMapping("/showUid")
	public void showUid(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String,Object> map = new HashMap<>();
		map.put("userSize",qqManager.userInfoUidsList.size());
		map.put("msgSize",qqManager.msgUidsList.size());
		map.put("emotSize",qqManager.emotUidsList.size());
		map.put("photoSize",qqManager.photoUidsList.size());
		
		map.put("user",qqManager.userInfoUidsList);
		map.put("msg",qqManager.msgUidsList);
		map.put("emot",qqManager.emotUidsList);
		map.put("photo",qqManager.photoUidsList);
		
		sendDefaultJson(response, map);
		
	}
	
	
	@RequestMapping("/setSession")
	public void setSession(HttpServletRequest request,HttpServletResponse response) throws IOException{
		qqManager.initSession();
		sendDefaultJson(response,"ok");
	}
	
	@RequestMapping("/zt")
	public void zanTing(HttpServletResponse response) throws IOException {
		StartCrawlThread.zanTing = 1;
		sendDefaultJson(response, "ok");
	}
	
	
	@RequestMapping("/upSe")
	public void upSe(HttpServletRequest request,HttpServletResponse response) throws IOException{
		QsessionInfoPo session = new QsessionInfoPo();
		session.uid = "12121";
		session.flag = 0;
		session.msg="dxm";
		qsessionService.addQsessionInfo(session);
		
		session.updateDate = new Date();
		session.flag = -1;
		session.msg="msg";
		qsessionService.updateQsessionInfo(session);
		sendDefaultJson(response, session);
	}
	
	@RequestMapping("getEn")
	public void testEmotNum(HttpServletResponse response) throws IOException{
		sendDefaultJson(response, QqConfig.DEFAULT_EMOT_NUM);
	}
	
	@RequestMapping("/importData")
	public void importData(HttpServletRequest request,HttpServletResponse response) throws IOException{
//		ReqParam params = getParams(request);
//		String tableName = params.getStr("tableName");
//		long startId = params.getLong("startId");
//		long endId = params.getLong("endId");
//		List<Map<String,Object>> datas = userInfoMapper.getEsData(tableName, startId, endId);
//		esManager.multiIndex(tableName+"_idx",tableName,datas,true);
//		sendDefaultJson(response, datas);
	}
	
	@RequestMapping("/importEsData")
	public void importEsData(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam params = getParams(request);
		String tableName = params.getStr("tableName");
		if(map.get(tableName) == null ) {
			map.put(tableName,true);
			new Thread(new Runnable() {
				@Override
				public void run() {
					quserService.importEsData(params);
				}
			}).start();
			
		}
		
	}
	
	
	@RequestMapping("/getEsField")
	public void getEsField(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String tableName = getParams(request).getStr("tableName");
		String idxName = tableName+"_idx";
		sendDefaultJson(response,esManager.getIndexFields(idxName, tableName));
	}
	
	private Map<String,String> mp = new HashMap<>(); 
	@RequestMapping("/cd")
	public void testRsa(HttpServletRequest request,HttpServletResponse response) throws IOException, InterruptedException{
		ReqParam paras = getParams(request);
		String file = paras.getStr("file");
		int size = paras.getInt("num");
		Set<String> keys = mp.keySet();
		if(!keys.contains(file)) {
				String ss = IOUtils.toString(new FileInputStream(file),"utf-8");
				mp.put(file,ss);
		}
		//if(keys.contains(file)) {
		for(int i = 0;i<= size;i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					long start = System.currentTimeMillis();
					cd(mp.get(file));
					long end = System.currentTimeMillis();
					System.out.println("total time = "+(end-start)/1000);
				}
			}).start();
			Thread.sleep(3000*10);
		}
			
		//}
		
	}
	
	private void cd(String ss) {
		String url = "www.baidu.com";
		Map<String,String> headers = new HashMap<>();
		headers.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");
		Map<String,Object> params = new HashMap<>();
			params.put("signature",ss);
				String resp;
				try {
					resp = YhHttpUtil.sendHttpPost(url, params,headers);
					System.out.println(resp);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	
	@RequestMapping("/maxid")
	public void getMaxId(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam params = getParams(request);
		String index = params.getStr("index");
		String type = params.getStr("type");
		sendDefaultJson(response, esManager.getMaxId(index, type));
	}
	
	
	
	
	
	
	
}
