package com.man.qqdog.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.man.qqdog.client.service.QUserService;
import com.man.utils.ReqParam;

@Controller
public class QqController extends BaseController {

	@Autowired
	private QUserService quserService;
	
	@RequestMapping("/queryUserPage")
	public void queryUserPage(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam params = getParams(request);
		sendDefaultJson(response,quserService.queryEsUserPage(params));
	}
}
