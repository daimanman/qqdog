package com.man.qqdog.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.man.qqdog.client.po.QsessionInfoPo;
import com.man.qqdog.client.po.QuserInfoPo;
import com.man.qqdog.client.service.QUserService;
import com.man.qqdog.client.service.QsessionService;
import com.man.utils.ObjectUtil;

@Controller
public class TestController extends BaseController{

	
	@Autowired
	private QUserService quserService;
	
	@Autowired
	private QsessionService qsessionService;
	
	
	Logger logger = LoggerFactory.getLogger(TestController.class);
	
	Logger qqLogger = LoggerFactory.getLogger("qqinfoLogger");
	

	@RequestMapping("/id")
	public void id(HttpServletResponse response) throws IOException{
		logger.info("info---DXMMM");
		logger.error("errrr-----");
		logger.debug("debug------");
		logger.warn("warn-------");
		long id = quserService.getId();
		qqLogger.info("id====={}",id);
		System.out.println(qqLogger.getName());
		sendJson(response, id);
	}
	
	@RequestMapping("/addU")
	public void addU(HttpServletResponse response) throws IOException {
		QuserInfoPo u = new QuserInfoPo();
		u.age = "1";
		u.birthday = "2018-09-09";
		u.birthyear = "1991";
		u.bloodtype = "1";
		u.career ="test";
		u.city="大理";
		u.company="tets";
		u.country="中国";
		u.createGmt=new Date();
		u.emotNum=0;
		u.flag=1;
		u.hc="昆明";
		u.hco="云南";
		u.id=quserService.getId();
		u.uid=1018765522L;
		u.nickname="do";
		sendJson(response,quserService.addQuserInfo(u));
		
	}
	
	@RequestMapping("/getQ")
	public void getQ(HttpServletRequest request,HttpServletResponse response) throws IOException, URISyntaxException{
		Map<String,Object> params = getReqParams(request);
		List headers = ObjectUtil.castListObj(params.get("requestHeaders"));
		String url = ObjectUtil.toString(params.get("url"));
		Map<String,String> cookie = parseHeaders(headers);
		Map<String,Object> paramsq =  parseParamsFromUri(url);
		String uid = ObjectUtil.toString(paramsq.get("uin"));
		logger.info("cookie {}",JSON.toJSONString(cookie));
		logger.info("paramsq {}",JSON.toJSONString(paramsq));
		logger.info("uid={}",uid);
		QsessionInfoPo info = new QsessionInfoPo();
		info.cookie = JSON.toJSONString(cookie);
		info.params = JSON.toJSONString(paramsq);
		info.createDate = new Date();
		info.uid = uid;
		info.flag = 0;
		info.updateDate = info.createDate;
		qsessionService.addQsessionInfo(info);
//		infoManager.cookiesMap.put(uid, cookie);
//		infoManager.paramsMap.put(uid,paramsq);
//		infoManager.initUids.add(uid);
		//sendJson(response, params);
		
	}
	
	
	@RequestMapping("/getSession")
	public void getSession(HttpServletRequest request,HttpServletResponse response) throws IOException {
		sendJsonWithDateFormat(response, qsessionService.getAllSession(),ObjectUtil.yyyyMMddHHmmss);
	}
	
	public Map<String,Object> parseParamsFromUri(String url) throws URISyntaxException{
	Map<String, Object> mapparams = new HashMap<String, Object>();  
    List<NameValuePair> params = URLEncodedUtils.parse(new URI(url), "UTF-8");  
    for (NameValuePair param : params) {  
        mapparams.put(param.getName(), param.getValue());  
    }  
    return mapparams;
}

public Map<String,String> parseHeaders(List headers){
	Map<String,String> headersMap = new HashMap<String,String>();
	if(null != headers && headers.size() > 0){
		for(Object ho:headers){
			Map m = ObjectUtil.castMapObj(ho);
			headersMap.put(ObjectUtil.toString(m.get("name")),ObjectUtil.toString(m.get("value")));
		}
	}
	return headersMap;
}
	
}
