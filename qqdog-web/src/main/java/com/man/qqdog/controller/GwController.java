package com.man.qqdog.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.man.qqdog.client.service.ShiCiService;
import com.man.utils.ReqParam;

@Controller
@CrossOrigin(origins = "*", maxAge = 36000)
@RequestMapping("/gw")
public class GwController extends BaseController {

	@Autowired
	public ShiCiService shiCiService;
	
	public Set<String> types = new HashSet<>(Arrays.asList("detail","info"));
	
	Logger logger = LoggerFactory.getLogger(GwController.class);
	
	@RequestMapping("/saveData")
	public void saveData(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam reqParams = getParams(request);
		String type = reqParams.getStr("type");
		
		logger.info("save Data {} ",JSON.toJSONString(reqParams));
		
		if("author".equals(type)) {
			shiCiService.saveAuthor(reqParams);
			sendDefaultJson(response,"is ok");
			return;
		}
		
		if(types.contains(type)) {
			shiCiService.saveShiInfo(reqParams);
			sendDefaultJson(response,"is ok");
			return;
		}
		
	}
}
