package com.man.qqdog.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.man.qqdog.biz.mapper.LjAreaInfoPoCustomMapper;
import com.man.qqdog.client.service.LjErShouFangService;
import com.man.utils.ReqParam;

@Controller
@RequestMapping("/lj")
public class LjController extends BaseController {

	@Autowired
	public LjErShouFangService ljFangService;
	
	@Autowired
	public LjAreaInfoPoCustomMapper areaInfoMapper;
	
	@RequestMapping("/testC")
	public void testC(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam param = getParams(request);
		String url = param.getStr("url");
		sendDefaultJson(response, areaInfoMapper.getByUrl(url));
	}
	
	@RequestMapping("/saveAreaInfo")
	public void saveAreaInfo(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam param = getParams(request);
		long id = ljFangService.saveAreaInfo(param);
		sendDefaultJson(response, id);
	}
	
	@RequestMapping("/saveHouseInfo")
	public void saveHouseInfo(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam param = getParams(request);
		long id = ljFangService.saveErHouseInfo(param);
		sendDefaultJson(response, id);
	}
	
	
	@RequestMapping("/saveLjData")
	public void saveLjData(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam param = getParams(request);
		String targetType = param.getStr("targetType");
		if("area".equals(targetType)) {
			long id = ljFangService.saveAreaInfo(param);
			sendDefaultJson(response, id);
			return;
		}
		
		if("house".equals(targetType)) {
			long id = ljFangService.saveErHouseInfo(param);
			sendDefaultJson(response, id);
			return;
		}
		
		if("details".equals(targetType)) {
			int num = ljFangService.addHouseDetail(param);
			sendDefaultJson(response, num);
			return;
		}
	}
	
	
	@RequestMapping("/getNeedDetail")
	public void getNeedDetail(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam param = getParams(request);
		int num = param.getInt("num");
		
		sendDefaultJson(response, ljFangService.getNextDetailHids(num));
	}
}
