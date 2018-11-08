package com.man.qqdog.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.man.qqdog.biz.mapper.QuserInfoPoMapper;
import com.man.qqdog.biz.service.QUserServiceImpl;

@Controller
public class TestController extends BaseController{

	@Autowired
	private QuserInfoPoMapper userInfoMapper;
	
	@Autowired
	private QUserServiceImpl quserService;
	
	@RequestMapping("/test01")
	public void test01(HttpServletResponse response) throws IOException{
		sendJson(response, "test01");
	}
	
	@RequestMapping("/test02")
	public void test02(HttpServletResponse response) throws IOException{
		sendJson(response, userInfoMapper.getTen());
	}
	

	@RequestMapping("/id")
	public void id(HttpServletResponse response) throws IOException{
		sendJson(response, quserService.getId());
	}
	
}
