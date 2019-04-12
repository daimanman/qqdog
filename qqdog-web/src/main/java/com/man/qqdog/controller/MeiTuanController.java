package com.man.qqdog.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.man.constants.MtConstant;
import com.man.qqdog.biz.manager.MtManager;
import com.man.qqdog.biz.mapper.DbMapper;
import com.man.qqdog.biz.mapper.MtCityCookiePoMapper;
import com.man.qqdog.biz.mapper.MtCityInfoPoMapper;
import com.man.qqdog.biz.mapper.MtMeishiInfoPoMapper;
import com.man.qqdog.biz.utils.FileCharsetDetector;
import com.man.qqdog.client.dto.MtCmtLog;
import com.man.qqdog.client.dto.MtCmtResult;
import com.man.qqdog.client.dto.MtCrawlInfoDto;
import com.man.qqdog.client.dto.MtNextDetailDto;
import com.man.qqdog.client.po.MtCityCookiePo;
import com.man.qqdog.client.po.MtCityInfoAreaPo;
import com.man.qqdog.client.po.MtCityInfoPo;
import com.man.qqdog.client.po.MtImgPo;
import com.man.qqdog.client.po.MtMeishiDealPo;
import com.man.qqdog.client.po.MtMeishiInfoPo;
import com.man.qqdog.client.po.MtMeishiTagPo;
import com.man.qqdog.client.service.MtCityInfoService;
import com.man.qqdog.client.service.MtMeishiInfoService;
import com.man.utils.IdWorker;
import com.man.utils.ObjectUtil;
import com.man.utils.ReqParam;

@Controller
@RequestMapping("/mt")
@CrossOrigin(origins = "*", maxAge = 360000000)
public class MeiTuanController extends BaseController {

	@Autowired
	private MtCityInfoService mtCityInfoService;
	
	@Autowired
	private MtCityInfoPoMapper mtCityInfoPoMapper;
	
	@Autowired
	private MtMeishiInfoService mtMeishiInfoService;
	
	@Autowired
	private MtMeishiInfoPoMapper mtMeishiInfoPoMapper;
	
	@Autowired
	private MtManager mtManager;
	
	Logger logger = LoggerFactory.getLogger(MeiTuanController.class);
		
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
	
	private List<MtMeishiInfoPo> parsePoiInfos(List<Map<String,Object>> poiInfos,MtCityInfoAreaPo areaPo){
		List<MtMeishiInfoPo> datas = new ArrayList<>();
		if(null != poiInfos && poiInfos.size() > 0) {
			for(Map<String,Object> map:poiInfos) {
				MtMeishiInfoPo data = new MtMeishiInfoPo();
				data.address = ObjectUtil.getStr(map,"address");
				data.areaId = areaPo.id;
				data.areaName = areaPo.name;
				data.areaPid = areaPo.pid;
				data.avgPrice = ObjectUtil.getDouble(map,"avgPrice");
				data.avgScore = ObjectUtil.getDouble(map,"avgScore");
				data.cityId = areaPo.cityId;
				data.cityName = areaPo.cityName;
				data.cmtNum = ObjectUtil.getInt(map,"allCommentNum");
				data.frontImg = ObjectUtil.getStr(map,"frontImg");
				data.id = ObjectUtil.getLong(map,"poiId");
				data.title = ObjectUtil.getStr(map,"title");
				datas.add(data);
			}
		}
		return datas;
	}
	
    
	@RequestMapping("/saveMeishiInfo")
	public void saveMeishiInfo(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam param = getParams(request);
		int cityId = param.getInt("ci");
		int areaId = param.getInt("areaId");
		mtManager.urlLog.lasttime = System.currentTimeMillis();
		mtManager.urlLog.lasturl = param.getStr("curHref");
		mtManager.urlLog.areaId = areaId;
		mtManager.urlLog.cityId = cityId;
		
		MtCityInfoAreaPo areaPo = mtCityInfoService.getCityAreaById(areaId);
		MtCrawlInfoDto data = new MtCrawlInfoDto();
		int pageSize = 15;
		int pn = param.getInt("pn");
		if(areaPo == null) {
			data.code = -1;
			data.msg="areaId="+areaId+"查询为空";
			logger.error("未查询到 areaId={} cityId={}",areaId,cityId);
			sendDefaultJson(response, data);
			return;
		}
		
		if(ObjectUtil.parseInt(areaPo.getPn) >= pn) {
			data.code = -1;
			data.msg = String.format("cityId=%d areaId=%d pn=%d 已经爬取过请不要在重复爬取",cityId,areaId,pn);
			logger.error(data.msg);
			sendDefaultJson(response, getNextAreaMeishi());
			return;
		}
		
		
		Map<String,Object> poiListsMap = param.getMap("poiLists");
		int totalCounts = ObjectUtil.getInt(poiListsMap,"totalCounts");
		List<Map<String,Object>> poiInfos = ObjectUtil.castListObj(poiListsMap.get("poiInfos"));
		int poiInfoSize = ObjectUtil.getSize(poiInfos);
		
		int totalPn = (totalCounts+pageSize-1)/pageSize;
		
		// add batch
		List<MtMeishiInfoPo> datas = parsePoiInfos(poiInfos, areaPo);
		mtMeishiInfoService.addMeishiInfoBatch(datas);
		mtManager.initCmtLog(datas);
		
		// update areaPo
		MtCityInfoAreaPo updateAreaPo = new MtCityInfoAreaPo();
		updateAreaPo.id = areaId;
		updateAreaPo.meishiNum = totalCounts;
		updateAreaPo.totalPn = totalPn;
		updateAreaPo.getPn = pn;
		updateAreaPo.getNum = ObjectUtil.parseInt(areaPo.getNum)+poiInfoSize;
		mtCityInfoService.updateMtCityInfoAreaSelectiveById(updateAreaPo);
		
		logger.info("crawl totalCounts={}  cityId={} cityName={} areaId={} areaName={} totalPn={} curPn={} curGetSize={} ",totalCounts,cityId,areaPo.cityName,areaId,areaPo.name,totalPn,pn,poiInfoSize);
		//获取下一个
		sendDefaultJson(response, getNextAreaMeishi());
	}
	private MtCrawlInfoDto getNextAreaMeishi() {
		MtCrawlInfoDto data = new MtCrawlInfoDto();
		MtCityInfoAreaPo areaInfo = mtCityInfoService.getNextCrawlArea();
		if(null != areaInfo) {
			data.cityName = areaInfo.cityName;
			data.areaId =  areaInfo.id;
			data.areaName = areaInfo.name;
			data.ci = areaInfo.cityId;
			data.curPn = ObjectUtil.parseInt(areaInfo.getPn,0)+1;
			if(!areaInfo.url.endsWith("/")) {
				areaInfo.url += "/";
			}
			data.nextUrl = areaInfo.url+"pn"+data.curPn+"/";
		}else {
			data.code = -1 ;
		}
		return data;
	}
	@RequestMapping("/getNextCityAreaMeishi")
	public void getNextCityAreaMeishi(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		MtCrawlInfoDto data = getNextAreaMeishi();
		
		sendDefaultJson(response, data);
	}
	
	
	@Autowired
	private DbMapper dbMapper;
	@RequestMapping("/createTb")
	public void createTb(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<Integer> cityIds = mtCityInfoPoMapper.getAllCityId();
		for(Integer cityId : cityIds) {
			dbMapper.createMeiCmtTable(cityId);
		}
		sendDefaultJson(response,"ok");
	}
	
	
	
	
	@RequestMapping("/getMeishi")
	public void getMeishi(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam param = getParams(request);
		int id = param.getInt("id");
		sendDefaultJson(response, mtMeishiInfoPoMapper.getMeishiInfoById(id));
	}
	
	
	private MtMeishiInfoPo parseDetail(Map<String,Object> data,int dealNum) {
		MtMeishiInfoPo info = new MtMeishiInfoPo();
		info.id = ObjectUtil.getLong(data,"poiId");
		info.avgPrice = ObjectUtil.getDouble(data,"avgPrice");
		info.avgScore = ObjectUtil.getDouble(data,"avgScore");
		info.brandId = ObjectUtil.getInt(data,"brandId");
		info.brandName = ObjectUtil.getStr(data,"brandName");
		info.dealNum = dealNum;
		info.title = ObjectUtil.getStr(data,"name");
		info.address = ObjectUtil.getStr(data,"address");
		List<Map<String,Object>> extraInfos = ObjectUtil.castListObj(data.get("extraInfos"));
		if(ObjectUtil.getSize(extraInfos) > 0) {
			List<String> extras = new ArrayList<>();
			for(Map<String,Object> extraInfo:extraInfos) {
				extras.add(ObjectUtil.getStr(extraInfo,"text"));
			}
			info.extraInfo = JSON.toJSONString(extras);
		}
		info.hasFoodSafeInfo = ObjectUtil.getBool(data,"hasFoodSafeInfo") ? 1 : 0;
		info.isMeiShi = ObjectUtil.getBool(data,"isMeiShi") ? 1 : 0;
		info.lat = ObjectUtil.getDouble(data,"latitude");
		info.lng = ObjectUtil.getDouble(data,"longitude");
		info.openTime = ObjectUtil.getStr(data,"openTime");
		info.phone = ObjectUtil.getStr(data,"phone");
		info.showStatus = ObjectUtil.getInt(data,"showStatus");
		return info;
	}
	
	public List<MtMeishiDealPo> parseDeals(List<Map<String,Object>> datas,long poiId){
		List<MtMeishiDealPo> dealList = new ArrayList<>();
		for(Map<String,Object> data : datas) {
			MtMeishiDealPo po = new MtMeishiDealPo();
			po.frontImgUrl = ObjectUtil.getStr(data,"frontImgUrl");
			po.id = ObjectUtil.getLong(data,"id");
			po.poiId = poiId;
			po.price = ObjectUtil.getDouble(data,"price");
			po.soldNum = ObjectUtil.getInt(data,"soldNum");
			po.title = ObjectUtil.getStr(data,"title");
			po.valuePrice = ObjectUtil.getDouble(data,"value");
			dealList.add(po);
		}
		return dealList;
	}
	
	private List<MtImgPo> parseMtImg(List imgs,int cityId,int type,long itemId){
		List<MtImgPo> imgPos = new ArrayList<>();
		if(ObjectUtil.getSize(imgs) > 0) {
			for(Object img : imgs) {
				MtImgPo imgPo = new MtImgPo();
				imgPo.cityId = cityId;
				imgPo.itemId = itemId;
				imgPo.type = type;
				imgPo.url = ObjectUtil.toString(img);
				imgPos.add(imgPo);
			}
		}
		return imgPos;
	}
	@RequestMapping("/saveMeishiDetail")
	public void saveMeishiDetail(HttpServletRequest request,HttpServletResponse response) throws IOException{
		MtCrawlInfoDto result  = new MtCrawlInfoDto();
		ReqParam param = getParams(request);
		long poiId = param.getLong("poiId");
		int cityId = param.getInt("ci");
		result.poiId = poiId;
		mtManager.removeNextList(poiId);
		MtMeishiInfoPo info = mtMeishiInfoPoMapper.getMeishiInfoById(poiId);
		if(info.down == 100 ) {
			sendDefaultJson(response, "poiid="+poiId+" down=100 ");
			return;
		}
		
		
		Map<String,Object> detailInfo = param.getMap("detailInfo");
		
		Map<String,Object> dealListMap = param.getMap("dealList");
		
		List<Map<String,Object>> deals = ObjectUtil.castListObj(dealListMap.get("deals"));
		
		MtMeishiInfoPo detail = parseDetail(detailInfo,ObjectUtil.getSize(deals));
		if(null != info ) {
			cityId = info.cityId;
		}
		detail.cityId = cityId;
		
		if(null == info) {
//			logger.error("poiId={} is null ",poiId);
//			result.msg = "query is null";
//			sendDefaultJson(response, result);
//			return;
			List<MtMeishiInfoPo> datas = new ArrayList<MtMeishiInfoPo>();
			detail.cmtNum = 6;
			datas.add(detail);
			mtMeishiInfoService.addMeishiInfoBatch(datas);
			//info = detail;
		}else {
				try {
					logger.info("save detail poiid={} ",poiId);
					detail.down = 100;
					mtMeishiInfoPoMapper.updateMeishiInfoSelective(detail);
				}catch(Exception e) {
					logger.error("updateMeishiInfoSelective poiId={} e={}",poiId,e);
				}
		}
		
		
		
		
		if(info != null && info.showStatus != null) {
			logger.error("poiId={} is updated ,please not update again",poiId);
			result.msg = "s updated ,please not update again";
			sendDefaultJson(response, result);
			return;
		}
		
		List<MtMeishiDealPo> dealPos = parseDeals(deals, poiId);
		if(ObjectUtil.getSize(dealPos) > 0) {
			try {
				mtMeishiInfoPoMapper.insertMtMeishiDealBatch(dealPos);
			}catch(Exception e) {
				logger.error("insertMtMeishiDealBatch error {} ",e);
			}
		}
		
		
		Map<String,Object> photos = param.getMap("photos");
		List albumImgUrls = ObjectUtil.castListObj(photos.get("albumImgUrls"));
		List<MtImgPo> mtImgs = parseMtImg(albumImgUrls,cityId,MtConstant.MT_IMG_TYPE_MEISHI,poiId);
		if(ObjectUtil.getSize(mtImgs) > 0) {
			try {
				mtMeishiInfoPoMapper.insertMtImgBatch(mtImgs);
			}catch(Exception e) {
				logger.error("insertMtImgBatch {} ",e);
			}
		}
		
		
		result.msg = " is ok ";
		result.poiId = poiId;
		sendDefaultJson(response, result);
	}
	
	
	
	
    private List<MtMeishiTagPo> parseTag(List<Map<String,Object>> tags,long poiId){
    	ArrayList<MtMeishiTagPo> datas = new ArrayList<MtMeishiTagPo>();
    	if(null != tags && tags.size() > 0) {
    		for(Map<String,Object> tag :  tags) {
    			MtMeishiTagPo po = new MtMeishiTagPo();
    			po.countNum = ObjectUtil.getInt(tag,"count");
    			po.poiId = poiId;
    			po.tag = ObjectUtil.getStr(tag,"tag");
    			datas.add(po);
    		}
    	}
    	return datas;
    }
    
	@RequestMapping("/addCityMeishiCmt")
	public void addCityMeishiCmt(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam reqParam = getParams(request);
		Map<String,Object> dataMap = reqParam.getMap("data");
		int total = ObjectUtil.getInt(dataMap,"total");
		int cityId = reqParam.getInt("ci");
		long poiId = reqParam.getLong("poiId");
		int offset = reqParam.getInt("offset");
		int cmtNum = reqParam.getInt("cmtNum");
		mtManager.removeNextList(poiId);
		
		MtMeishiInfoPo info = mtMeishiInfoPoMapper.getMeishiInfoById(poiId);
		
		if(null == info) {
			logger.error("poiId={} is null ",poiId);
			return;
		}
		
		if(offset == 0) {
			MtMeishiInfoPo updateCmtNum = new MtMeishiInfoPo();
			updateCmtNum.id = poiId;
			updateCmtNum.cmtNum = cmtNum;
			mtMeishiInfoService.updateMeishiInfoSelective(updateCmtNum);
		}
		
	    System.out.println(ObjectUtil.parseInt(info.cmtGet)+"------"+ObjectUtil.parseInt(info.cmtNum));
		if(ObjectUtil.parseInt(info.cmtGet) >= ObjectUtil.parseInt(info.cmtNum)) {
			logger.error("poiId={} cmt is crawled over ",poiId);
			return;
		}
		
		cityId =  info.cityId;
		
		List<Map<String,Object>> comments = ObjectUtil.castListObj(dataMap.get("comments"));
		
		List<Map<String,Object>> tags = ObjectUtil.castListObj(dataMap.get("tags"));
		
		
		
		if(offset == 0) {
			List<MtMeishiTagPo> tagList = parseTag(tags, poiId);
			if(ObjectUtil.getSize(tagList) > 0) {
				mtMeishiInfoPoMapper.insertMtMeishiTagBatch(tagList);
			}
		}
		
		int cmtGetSize = mtMeishiInfoService.addMeishiCmts(comments, cityId, poiId);
		mtManager.updateCmtLog(poiId, ObjectUtil.getSize(comments)+offset,total);
		
		logger.info("cityId={} poiId={} total={} cmtGetSize={} offset={} ",cityId,poiId,total,cmtGetSize,offset);
		//mtManager.nextList.add(new MtNextDetailDto(poiId, System.currentTimeMillis(),0L));
	}
	
	
	@RequestMapping("/getTop100")
	public void getTop100(HttpServletRequest request,HttpServletResponse response) throws IOException {
		ReqParam param = getParams(request);
		Set<Long> setIds = new HashSet<>();
		setIds.add(-1L);
		setIds.add(param.getLong("id"));
		sendDefaultJson(response,mtManager.getNextMeishiCmt());
	}
	
	private MtCmtResult parseNextCmtResult(MtMeishiInfoPo po) {
		MtCmtResult cmt = new MtCmtResult();
		if(null == po ) {
			return cmt;
		}
		cmt.offset = ObjectUtil.parseInt(po.cmtGet);
		cmt.poiId = po.id;
		cmt.poiname = po.title;
		cmt.poiUrl = "http://www.meituan.com/meishi/"+po.id+"/?offset="+cmt.offset+"&cmtNum="+ObjectUtil.parseInt(po.cmtNum)+"&cityId="+ObjectUtil.parseInt(po.cityId)+"&poiid="+po.id;
		cmt.cmtNum = ObjectUtil.parseInt(po.cmtNum);
		cmt.cityId = po.cityId;
		return cmt;
	}
	
	@RequestMapping("/getNextCmtMeishi")
	public void getNextCmtMeishi(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam param = getParams(request);
		long poiid = param.getLong("poiid");
		MtCmtResult result = null ;
		if(poiid > 0 ) {
			result = parseNextCmtResult(mtMeishiInfoPoMapper.getMeishiInfoById(poiid));
		}else {
			result = parseNextCmtResult(mtManager.getNextMeishiCmt());
		}
		mtManager.nextList.add(new MtNextDetailDto(result.poiId,System.currentTimeMillis(),0L));
		sendDefaultJson(response,result);
	}
	
	@RequestMapping("/getNextL")
	public void getNextList(HttpServletResponse response) throws IOException{
		sendDefaultJson(response, mtManager.nextList);
	}
	@RequestMapping("/startCmtMeishi")
	public void startCmtMeishi(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<MtCmtResult> datas= new ArrayList<>();
		ReqParam param = getParams(request);
		int num = param.getInt("num");
		if(num == 0) {
			num = 1;
		}
		for(int i=0;i<num;i++) {
			datas.add(parseNextCmtResult(mtManager.getNextMeishiCmt()));
		}
		sendDefaultJson(response, datas);
	}
	
	@RequestMapping("/getCmtIds")
	public void getCmtIds(HttpServletRequest request,HttpServletResponse response) throws IOException{
		sendDefaultJson(response, mtManager.hasGetIds);
	}
	
	@RequestMapping("/openChrome")
	public void openChrome(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam param = getParams(request);
		String url = param.getStr("url");
		mtManager.reopenUrlInChrome(url);
		sendDefaultJson(response, "ok");
	}
	
	@RequestMapping("/checkMtCmtOver")
	public void checkMtCmtOver(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam param = getParams(request);
		List ids = param.getList("ids");
		List<Long> newIds = new ArrayList<>(15);
		for(Object id : ids) {
			newIds.add(ObjectUtil.parseLong(id));
		}
		//MtCmtLog log = mtManager.checkGetCmtOver(newIds);
		MtCmtLog log = mtMeishiInfoPoMapper.getSumCmtNum(newIds);
		sendDefaultJson(response, log);
	}
	
	@RequestMapping("/getCmtLog")
	public void getCmtLog(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam param = getParams(request);
		List ids = param.getList("ids");
		List<Long> newIds = new ArrayList<>(15);
		for(Object id : ids) {
			newIds.add(ObjectUtil.parseLong(id));
		}
		List<MtCmtLog> logs = mtMeishiInfoPoMapper.getPoiCmtNum(newIds);
		Map<String,MtCmtLog> map = new HashMap<>();
		if(logs != null && logs.size() > 0) {
			for(MtCmtLog log : logs ) {
				map.put(log.poiId+"",log);
			}
		}
		sendDefaultJson(response, map);
	}
	
	@RequestMapping("/setDown")
	public void setDownFlag(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam param = getParams(request);
		mtManager.downFlag = ObjectUtil.getBool(param,"down");
		sendDefaultJson(response, "ok");
	}
	
	@RequestMapping("/startDown")
	public void startDown(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam param = getParams(request);
		int cityId = param.getInt("cityId");
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				mtManager.downMeiDetail(cityId);
			}
		}).start();
		sendDefaultJson(response,"ok");
	}
	
	@Autowired
	private IdWorker idWorker;
	@Autowired
	private MtCityCookiePoMapper cookieMapper;
	@RequestMapping("/saveCookie")
	public void saveCookie(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam param = getParams(request);
		String url = param.getStr("url");
		
		boolean isMatch = Pattern.matches("http[s]*://www.meituan.com/meishi/[0-9]+/",url);
		System.out.println(isMatch+"---"+url);
		if(isMatch) {
			System.out.println(JSON.toJSONString(param));
			List<Map<String,Object>> headers = param.getList("requestHeaders");
			for(Map<String,Object> header : headers ) {
				String key = ObjectUtil.getStr(header,"name");
				if("Cookie".equals(key)) {
					MtCityCookiePo cookie = new MtCityCookiePo();
					cookie.cookie = ObjectUtil.getStr(header,"value");
					cookie.flag = 0;
					cookie.id = idWorker.nextId();
					cookieMapper.insert(cookie);
					mtManager.initCookie();
					break;
				}
			}
		}
		
	}
	
	@RequestMapping("/getFile")
	public void getFile(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam param = getParams(request);
		long poiId = param.getLong("id");
		MtMeishiInfoPo po = mtMeishiInfoPoMapper.getMeishiInfoById(poiId);
		if(po.down == 100) {
			logger.info("ok_poiid_dow has update {} ",poiId);
			sendHtml(response,"");
			return;
		}
		String path ="D:\\dxmtools\\myxm\\mytools\\meit\\"+poiId+".html";
		File file = new File(path);
		String code = new FileCharsetDetector().guessFileEncoding(file);
		String encoding = "UTF-8";
		if(!"UTF-8".equals(code)) {
			encoding = "GBK";
		}
		String str = IOUtils.toString(new FileInputStream(file),encoding);
		if(str.indexOf("window._appState") > 0) {
			logger.info("ok_poiid_dow {} ",poiId);
			sendHtml(response, new String(str.getBytes("UTF-8"),"UTF-8"));
		}else {
			logger.error("err_poiid_dow {} ",poiId);
			sendHtml(response,"");
		}
	}
	
	@RequestMapping("/testMeit011")
	public void testMeit01(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//mtManager.dealMeit01();
	}
	@RequestMapping("/testMeit021")
	public void testMeit02(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//mtManager.dealMeit02();
	}
	
	@RequestMapping("/downCmt")
	public void downCmt(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam param = getParams(request);
	    long id = param.getLong("id");
	    MtMeishiInfoPo po = mtMeishiInfoPoMapper.getMeishiInfoById(id);
	    mtManager.downCmt(po);
	    sendDefaultJson(response, "is ok");
	}
	
	public static int cmtFlag = 1;
	@RequestMapping("/downCmtAll")
	public void downCmtAll(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam param = getParams(request);
		int num = param.getInt("num");
		if(num == 0) {
			num = 1;
		}
		int cityId = param.getInt("cityId");
		mtCityInfoService.checkCmtTableExistsAndCreate(cityId);
		MtCityInfoPo city = mtCityInfoPoMapper.getCityInfoById(cityId);
		if(null == city) {
			sendDefaultJson(response,"cityId is error ");
			return;
		}
		for(int i = 0;i<num;i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					MtMeishiInfoPo po = mtManager.getNextMeishiCmt();
					while(po != null && cmtFlag==1) {
						int flag = mtManager.downCmt(po);
						if(flag == -1) {
							break;
						}
						po = mtManager.getNextCmt(cityId);
					}
				}
			}).start();
		}
		sendDefaultJson(response, "down all is ok ");
	}
	
	@RequestMapping("/setCmt")
	public void stopCmt(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam param = getParams(request);
		int flag = param.getInt("flag");
		cmtFlag = flag;
		sendDefaultJson(response, " setCmt is ok ");
	}
	
	@RequestMapping("/getNextCmt")
	public void getNextCmt(HttpServletRequest request,HttpServletResponse response) throws IOException {
		sendDefaultJson(response, mtManager.getNextCmt(1));
	}
	@RequestMapping("/getNextDetail")
	public void getNextDetail(HttpServletRequest request,HttpServletResponse response) throws IOException {
		sendDefaultJson(response, mtManager.getNextDetail(0));
	}
	
	@RequestMapping("/downAllDetail")
	public void downAllDetail(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam param = getParams(request);
		int num = param.getInt("num");
		if(num == 0) {
			num = 1;
		}
		int cityId = param.getInt("cityId");
		for(int j =0 ;j<num;j++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					logger.info("{} Start Thread {}",Thread.currentThread().getName(),cityId);
					MtMeishiInfoPo po = mtManager.getNextDetail(cityId);
					while(po != null && mtManager.downFlag) {
						mtManager.multiDownMeiDetail(po);
						po = mtManager.getNextDetail(cityId);
					}
				}
			}).start();
		}
		
	}
}
