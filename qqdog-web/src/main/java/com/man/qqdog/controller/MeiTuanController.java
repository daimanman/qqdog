package com.man.qqdog.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.man.constants.MtConstant;
import com.man.qqdog.biz.mapper.MtCityInfoPoMapper;
import com.man.qqdog.client.dto.MtCrawlInfoDto;
import com.man.qqdog.client.po.MtCityInfoAreaPo;
import com.man.qqdog.client.po.MtCityInfoPo;
import com.man.qqdog.client.service.MtCityInfoService;
import com.man.utils.ObjectUtil;
import com.man.utils.ReqParam;

@Controller
@RequestMapping("/mt")
@CrossOrigin(origins = "*", maxAge = 36000)
public class MeiTuanController extends BaseController {

	@Autowired
	private MtCityInfoService mtCityInfoService;
	
	@Autowired
	private MtCityInfoPoMapper mtCityInfoPoMapper;
		
	@RequestMapping("/data")
	public void receiveData(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam param = getParams(request);
		System.out.println(JSON.toJSONString(param.getMap("poiLists")));
		sendDefaultJson(response,"is ok ");
	}
	
	
	
	@RequestMapping("/addCityInfo")
	public void addCityInfo(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String json = getStreamAsString(request);
		int num = 0;
		if(null != json && json.length() > 0) {
			List<MtCityInfoPo> datas = JSON.parseArray(json,MtCityInfoPo.class);
			num = mtCityInfoService.addCityInfoList(datas);
		}
		sendDefaultJson(response, num);
	}
	
	@RequestMapping("/parseCityArea")
	public void parseCityArea(HttpServletRequest request,HttpServletResponse response) throws IOException{
		MtCrawlInfoDto result = new MtCrawlInfoDto();
		
		ReqParam param = getParams(request);
		int ci = param.getInt("ci");
		String cityName = param.getStr("cityName");
		List<Map<String,Object>> areas = param.getList("areas");
		
		MtCityInfoPo cityInfo = mtCityInfoService.getCityInfoById(ci);
		
		if(cityInfo.crawlFlag == MtConstant.CRAWL_FLAG_OK) {
			result.msg = cityName+"has crawl ";
			MtCityInfoPo nextCityInfo = mtCityInfoPoMapper.getNextCityCrawl();
			if(null != nextCityInfo) {
				result.nextUrl = "http://"+nextCityInfo.url.trim()+"/meishi";
				
			}
			sendDefaultJson(response, result);
			return;
		}
		
		
		List<MtCityInfoAreaPo> datas = new ArrayList<>();
		for(Map<String,Object> area : areas ) {
			int rootId = ObjectUtil.getInt(area,"id");
			String rootName = ObjectUtil.getStr(area,"name");
			List<Map<String,Object>> subAreas = ObjectUtil.castListObj(area.get("subAreas"));
			for(Map<String,Object> subArea : subAreas ) {
				MtCityInfoAreaPo data = new MtCityInfoAreaPo();
				int id = ObjectUtil.getInt(subArea,"id");
				String name = ObjectUtil.getStr(subArea,"name");
				int pid = rootId;
				if(id == rootId) {
					name = rootName;
					pid = ci;
				}
				String url = ObjectUtil.getStr(subArea,"url");
				
				
				data.cityId = ci;
				data.cityName = cityName;
				data.id = id;
				data.name = name;
				data.pid = pid;
				data.url = url;
				datas.add(data);
			}
		}
		
		int num = mtCityInfoService.addCityInfoAreaList(datas);
		MtCityInfoPo cityUpdate = new MtCityInfoPo();
		cityUpdate.id = ci;
		cityUpdate.crawlFlag = MtConstant.CRAWL_FLAG_OK;
		mtCityInfoService.updateMtCityInfoSelectiveById(cityUpdate);
		
		MtCityInfoPo nextCityInfo = mtCityInfoPoMapper.getNextCityCrawl();
		if(null != nextCityInfo) {
			result.nextUrl = "http://"+nextCityInfo.url.trim()+"/meishi";
		}
		sendDefaultJson(response,result);
	}
	
	
	
	
	
	@RequestMapping("/getNextCityUrl")
	public void getNextCityUrl(HttpServletRequest request,HttpServletResponse response ) throws IOException{
		ReqParam param = getParams(request);
		int ci = param.getInt("ci");
		int crawlFlag = param.getInt("crawlFlag");
		
		MtCityInfoPo cityUpdate = new MtCityInfoPo();
		cityUpdate.id = ci;
		cityUpdate.crawlFlag = crawlFlag;
		mtCityInfoService.updateMtCityInfoSelectiveById(cityUpdate);
		
		MtCrawlInfoDto result = new MtCrawlInfoDto();
		
		MtCityInfoPo nextCityInfo = mtCityInfoPoMapper.getNextCityCrawl();
		if(null != nextCityInfo) {
			result.nextUrl = "http://"+nextCityInfo.url.trim()+"/meishi";
			result.ci = nextCityInfo.id;
		}
		sendDefaultJson(response, nextCityInfo);
	}
	
	@RequestMapping("/saveMeishiInfo")
	public void saveMeishiInfo(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam param = getParams(request);
		
		
	}
	
}
