package com.man.qqdog.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import com.man.qqdog.biz.mapper.EleCityInfoPoCustomMapper;
import com.man.qqdog.biz.mapper.ElePoiInfoPoCustomMapper;
import com.man.qqdog.client.po.EleCityInfoPo;
import com.man.qqdog.client.po.ElePoiInfoPo;
import com.man.qqdog.client.service.EleService;
import com.man.utils.ReqParam;

@RequestMapping("/ele")
@CrossOrigin(origins = "*", maxAge = 36000)
@Controller
public class EleController extends  BaseController {

	
	@Autowired
	public EleService eleService;
	
	@Autowired
	public  EleCityInfoPoCustomMapper cityMapper;
	
	@RequestMapping("/saveCitys")
	public void saveCitys(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam param = getParams(request);
		eleService.addCityInfos(param);
		sendDefaultJson(response, "is ok");
	}
	
	
	
	@RequestMapping("/saveCityPoints")
	public void saveCityPoints(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam param = getParams(request);
		eleService.saveCityPoints(param);
		sendDefaultJson(response,"is ok");
	}
	
	@RequestMapping("/getNextPoi")
	public void getNextPoi(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam param = getParams(request);
		sendDefaultJson(response,eleService.getNextPoi(param.getInt("cityId")));
	}
	
	@Autowired
	private ElePoiInfoPoCustomMapper poiMapper;
	@RequestMapping("/updatePoint")
	public void updatePoint(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam param = getParams(request);
		long id = param.getLong("id");
		ElePoiInfoPo po = new ElePoiInfoPo();
		po.id = id;
		po.num = param.getInt("totalNum");
		po.flag = 100;
		po.geohash = param.getStr("geohash");
		po.url = param.getStr("url");
		poiMapper.updateSelective(po);
		sendDefaultJson(response, "is ok");
	}
	
	
	@RequestMapping("/saveMeishiInfo")
	public void saveMeishiInfo(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam param = getParams(request);
		eleService.saveMeishiDatas(param);
		sendDefaultJson(response,"is ok");
	}
	
	@RequestMapping("/getTopK")
	public  void getTopK(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam param = getParams(request);
		Map<String,Object> result = new HashMap<>();
		String cityName = param.getStr("cityName");
		EleCityInfoPo city = cityMapper.getCityByName(cityName);
		if(null != city) {
			param.put("cityId",city.id);
			result.put("city",city);
			result.put("pois",eleService.getTopK(param));
		}
		sendDefaultJson(response, result);
		
	}
	
	
	
}
