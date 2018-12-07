package com.man.qqdog.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import com.man.qqdog.client.service.QUserService;
import com.man.qqdog.client.service.QemotService;
import com.man.qqdog.client.service.QphotoInfoService;
import com.man.utils.ReqParam;

@Controller
@CrossOrigin(origins = "*", maxAge = 36000)
public class QqController extends BaseController {

	@Autowired
	private QUserService quserService;
	
	@Autowired
	private QphotoInfoService qphotoInfoService;
	
	@Autowired
	private QemotService qemotService;
	
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
	
	
}
