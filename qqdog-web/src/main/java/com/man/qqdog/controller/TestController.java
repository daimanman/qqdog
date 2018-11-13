package com.man.qqdog.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.man.qqdog.biz.manager.QqManager;
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

@Controller
public class TestController extends BaseController {

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

	@RequestMapping("/id")
	public void id(HttpServletResponse response) throws IOException {
		logger.info("info---DXMMM");
		logger.error("errrr-----");
		logger.debug("debug------");
		logger.warn("warn-------");
		long id = quserService.getId();
		qqLogger.info("id====={}", id);
		removeUidLogger.info("remove uid {} ", id);
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
}
