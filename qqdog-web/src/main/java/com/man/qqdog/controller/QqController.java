package com.man.qqdog.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import com.man.qqdog.client.service.QUserService;
import com.man.qqdog.client.service.QemotService;
import com.man.qqdog.client.service.QmsgService;
import com.man.qqdog.client.service.QphotoInfoService;
import com.man.utils.ReqParam;
import com.man.utils.ResultJson;

@Controller
@CrossOrigin(origins = "*", maxAge = 36000)
public class QqController extends BaseController {

	@Autowired
	private QUserService quserService;
	
	@Autowired
	private QphotoInfoService qphotoInfoService;
	
	@Autowired
	private QemotService qemotService;
	
	@Autowired
	private QmsgService qmsgService;
	
	@RequestMapping("/queryUserPage")
	public void queryUserPage(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam params = getParams(request);
		sendDefaultJson(response,quserService.queryEsUserPage(params));
	}
	
	@RequestMapping("/queryPhotoPage")
	public void queryPhotoPage(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam params = getParams(request);
		sendDefaultJson(response,qphotoInfoService.queryEsPhotoPage(params));
	}
	
	@RequestMapping("/queryPhotoImgPage")
	public void queryPhotoImgPage(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam params = getParams(request);
		sendDefaultJson(response,qphotoInfoService.queryEsImgPage(params));
	}
	
	@RequestMapping("/queryEmotInfoPage")
	public void queryEmotInfoPage(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam params = getParams(request);
		sendDefaultJson(response, qemotService.queryEsEmotPage(params));
	}
	
	@RequestMapping("/getUserInfo")
	public void getUserInfo(HttpServletResponse response,HttpServletRequest request) throws IOException{
		ReqParam params = getParams(request);
		String uid = params.getStr("uid");
		Map<String,Object> userInfo = quserService.getEsUserInfo(uid);
		long photoNum = qphotoInfoService.getPhotoNum(uid);
		long msgNum = qmsgService.getMsgNum(uid);
		long emotNum = qemotService.getEmotNum(uid);
		userInfo.put("photo_num",photoNum);
		userInfo.put("msg_num",msgNum);
		userInfo.put("emot_num",emotNum);
		ResultJson<Map<String,Object>> resultJson = new ResultJson<>();
		resultJson.data = userInfo;
		sendDefaultJson(response, resultJson);
	}
	
	
}
