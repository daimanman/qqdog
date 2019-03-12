package com.man.qqdog.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import com.man.qqdog.biz.mapper.QuserInfoPoMapper;
import com.man.utils.ObjectUtil;
@Controller
@CrossOrigin(origins = "*", maxAge = 36000)
public class RenRenController extends BaseController {
	@Autowired
	private QuserInfoPoMapper userInfoMapper;
	
	Logger logger = LoggerFactory.getLogger(RenRenController.class);
	@RequestMapping("/rrsql")
	public void rrDepart(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String,Object> params = getParams(request);
		try {
			userInfoMapper.execSql(ObjectUtil.getStr(params,"sql").replaceAll("[']+", "'"));
		}catch(Exception e) {
			e.printStackTrace();
			logger.error("HIHG_ERR {} ",params.get("fileName"));
			logger.error("ERR_SQL {} ",params.get("sql"));
		}
	}
	
	@RequestMapping("/testLh")
	public void testLh(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String,Object> params = getReqParams(request);
		sendDefaultJson(response, params);		
	}
	
}
