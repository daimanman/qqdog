package com.man.qqdog.biz.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.man.qqdog.biz.mapper.LjAreaInfoPoCustomMapper;
import com.man.qqdog.biz.mapper.LjCityInfoPoCustomMapper;
import com.man.qqdog.biz.mapper.LjErHouseInfoPoCustomMapper;
import com.man.qqdog.biz.mapper.LjHouseDetailInfoPoCustomMapper;
import com.man.qqdog.biz.mapper.LjHouseFetureInfoPoCustomMapper;
import com.man.qqdog.biz.mapper.LjImgInfoPoCustomMapper;
import com.man.qqdog.client.po.LjAreaInfoPo;
import com.man.qqdog.client.po.LjCityInfoPo;
import com.man.qqdog.client.po.LjErHouseInfoPo;
import com.man.qqdog.client.po.LjHouseDetailInfoPo;
import com.man.qqdog.client.po.LjHouseFetureInfoPo;
import com.man.qqdog.client.po.LjImgInfoPo;
import com.man.qqdog.client.service.LjErShouFangService;
import com.man.utils.IdWorker;
import com.man.utils.ObjectUtil;
import com.man.utils.ReqParam;

@Service
public class LjErShouFangServiceImpl implements LjErShouFangService {

	Logger logger = LoggerFactory.getLogger(LjErShouFangServiceImpl.class);
	@Autowired
	public IdWorker idWorker;
	
	@Autowired
	public LjAreaInfoPoCustomMapper areaInfoMapper;
	
	@Autowired
	public LjCityInfoPoCustomMapper cityInfoMapper;
	
	@Autowired
	public LjErHouseInfoPoCustomMapper houseInfoMapper;
	
	@Autowired
	public LjHouseDetailInfoPoCustomMapper houseDetailInfoMapper;
	
	@Autowired
	public LjHouseFetureInfoPoCustomMapper fetureInfoMapper;
	
	@Autowired
	public LjImgInfoPoCustomMapper imgInfoMapper;
	
	public LjAreaInfoPo coverAreaInfoPo(ReqParam params) {
		LjAreaInfoPo po = new LjAreaInfoPo();
		po.cityName = params.getStr("cityName");
		po.level = params.getInt("level");
		po.name = params.getStr("name");
		po.num = params.getInt("num");
		po.parentName = params.getStr("parentName");
		po.pid = 0L;
		po.py = params.getStr("py");
		po.url = params.getStr("url");
		return po;
	}
	@Override
	public long saveAreaInfo(ReqParam params) {
		
		logger.info("{}",JSON.toJSONString(params));
		LjAreaInfoPo po = coverAreaInfoPo(params);
		LjAreaInfoPo oldPo = areaInfoMapper.getByUrl(po.url);
		if(oldPo != null) {
			//update
			po.id = oldPo.id;
			 areaInfoMapper.updateSelective(po);
		}else {
			po.id = idWorker.nextId();
			areaInfoMapper.insert(po);
		}
		
		return po.id;
	}

	public LjErHouseInfoPo converHouse(ReqParam params) {
		LjErHouseInfoPo po = new LjErHouseInfoPo();
		
		po.arient = params.getStr("arient");
		po.hid = params.getLong("hid");
		po.title = params.getStr("title");
		po.xiaoquName = params.getStr("xiaoquName");
		po.xiaoquId = params.getLong("xiaoquId");
		po.roomNum = params.getInt("roomNum");
		po.hallNum = params.getInt("hallNum");
		po.roomSize = params.getDouble("roomSize");
		po.decorate = params.getStr("decorate");
		po.floorTotal =params.getInt("floorTotal");
		po.floorPoi = params.getStr("floorPoi");
		po.floorType = params.getStr("floorType");
		po.cityName = params.getStr("cityName");
		po.areaName = params.getStr("areaName");
		po.areaPname = params.getStr("areaPname");
		po.areaNum = params.getInt("areaNum");
		po.totalPrice = params.getLong("totalPrice");
		po.unitPrice = params.getLong("unitPrice");
		po.guanzhuNum = params.getInt("guanzhuNum");
		po.daikanNum =params.getInt("daikanNum");
		po.fabuData = params.getStr("fabuDate");
		po.offsetIndex = params.getInt("offset");
		po.tags = params.getStr("tags");
		po.dmlDate = new Date();
		return po;
	}
	@Override
	public long saveErHouseInfo(ReqParam params) {
		logger.info("{}",JSON.toJSONString(params));
		LjErHouseInfoPo po = converHouse(params);
		LjErHouseInfoPo oldPo = houseInfoMapper.getByHid(po.hid);
		if(null != oldPo) {
			po.id = oldPo.id;
			houseInfoMapper.updateSelective(po);
		}else {
			po.id = idWorker.nextId();
			houseInfoMapper.insertSelective(po);
		}
		return po.id;
	}
	
	public Map<String,String> cityUrlMap = new HashMap<>();
	public LinkedList<Map<String,String>> cacheDetails = new LinkedList<Map<String,String>>();
	public void initBatchNextDetails(int num) {
		num =  num <= 0 ? 1 : num;
		List<LjErHouseInfoPo> pos = houseInfoMapper.getNeedDetailHid(num);
		List<Long> ids = pos.stream().map(po -> po.hid ).collect(Collectors.toList());
		houseInfoMapper.updateDetailStatus(ids,-1);
		for(LjErHouseInfoPo po :  pos) {
			
			String cityName = po.cityName;
			String url = cityUrlMap.get(cityName);
			if(null == url) {
				LjCityInfoPo city = cityInfoMapper.getByCityName(cityName);
				if(null == city) {
					continue;
				}
				url = city.url;
				cityUrlMap.put(cityName,url);
			}
			Map<String,String> data =  new HashMap<>();
			data.put("id",po.id+"");
			data.put("hid", po.hid+"");
			data.put("url",String.format("%sershoufang/%s.html",url,po.hid+""));
			data.put("rid",po.xiaoquId+"");
			data.put("rurl",String.format("%sershoufang/housestat?hid=%s&rid=%s",url,po.hid+"",po.xiaoquId+""));
			cacheDetails.add(data);
		}
	}
	@Override
	public synchronized List<Map<String,String>> getNextDetailHids(int num) {
		List<Map<String,String>> maps = new ArrayList<>();
		num =  num <= 0 ? 1 : num;
		if(cacheDetails.size() == 0) {
			initBatchNextDetails(20000);
		}
		for(int i = 0 ;i<num && cacheDetails.size() > 0;i++) {
			maps.add(cacheDetails.removeFirst());
		}
		return maps;
	}
	
	public LjErHouseInfoPo converBaseHouseInfo(ReqParam params) {
		LjErHouseInfoPo po = new LjErHouseInfoPo();
		po.chanquanNianxian = params.getInt("chanquanNianxianNum");
		po.chanquanSuoshu = params.getStr("chanquanSuoshu");
		po.chufangNum= params.getInt("chufangNum");
		po.decorate = params.getStr("decorate");
		po.diyaXinxi = params.getStr("diyaXinxi");
		po.dmlFlag = 10;
		po.fangbenBeijian = params.getStr("fangbenBeijian");
		po.fangwuChangxiang = params.getStr("fangwuChaoxiang");
		po.arient = po.fangwuChangxiang;
		po.fangwuNianxian = params.getStr("fangwuNianxian");
		po.fangwuYongtu = params.getStr("fangwuYongtu");
		po.floorPoi = params.getStr("floorPoi");
		po.floorTotal  = params.getInt("floorTotal");
		po.floorType = params.getStr("jianzhuLeixing");
		po.gongnuanFangshi = params.getStr("gongnuanFangshi");
		po.guapaiDate = params.getDate("guapaiShijian");
		po.hallNum = params.getInt("hallNum");
		po.huxingJiegou = params.getStr("huxingJiegou");
		po.jianzhuJiegou = params.getStr("jianzhuJiegou");
		po.jianzhuLeixing = params.getStr("jianzhuLeixing");
		po.jiaoyiQuanshu = params.getStr("jiaoyiQuanshu");
		po.lat = params.getDouble("lat");
		po.lng = params.getDouble("lng");
		po.peibeiDianti = params.getStr("peibeiDianti");
		po.roomNum = params.getInt("roomNum");
		po.roomSize = params.getDouble("roomSize");
		po.shangciJiaoyi = params.getStr("shangciJiaoyi");
		po.taoneiMianji = params.getStr("innerRoomSize");
		po.tihuBili = params.getStr("tihuBili");
		po.washroomNum = params.getInt("washNum");
		return po;
	}
	
	public List<LjHouseDetailInfoPo> converHouseDetail(ReqParam params,long hid){
		List<LjHouseDetailInfoPo> datas = new ArrayList<>();
		List<Map<String,Object>> maps = params.getList("details");
		if(null != maps && maps.size() > 0) {
			for(Map<String,Object> map : maps) {
				LjHouseDetailInfoPo po = new LjHouseDetailInfoPo();
				po.arient = ObjectUtil.getStr(map,"arient");
				po.hid = hid;
				po.id = idWorker.nextId();
				po.itemName = ObjectUtil.getStr(map,"itemName");
				po.itemSize = ObjectUtil.getStr(map,"itemSize");
				po.window = ObjectUtil.getStr(map,"window");
				po.orderNum = ObjectUtil.getInt(map,"orderNum");
				datas.add(po);
			}
		}
		return datas;
	}
	
	public List<LjHouseFetureInfoPo> converFetures(ReqParam params,long hid){
		List<LjHouseFetureInfoPo> datas = new ArrayList<>();
		List<Map<String,Object>>  maps = params.getList("fetures");
		for(Map<String,Object> map : maps ) {
			LjHouseFetureInfoPo po = new LjHouseFetureInfoPo();
			po.hid = hid;
			po.id = idWorker.nextId();
			po.orderNum = ObjectUtil.getInt(map,"OrderNum");
			po.title = ObjectUtil.getStr(map,"Title");
			po.contents = ObjectUtil.getStr(map,"Contents");
			datas.add(po);
		}
		
		return datas;
	}
	
	public List<LjImgInfoPo> converImgs(ReqParam params,long hid){
		List<LjImgInfoPo>  datas = new ArrayList<>();
		List<Map<String,Object>> maps = params.getList("Imgs");
		if(null != maps && maps.size() > 0 ) {
			for(Map<String,Object> map : maps ) {
				LjImgInfoPo po = new LjImgInfoPo();
				po.hid = hid;
				po.id = idWorker.nextId();
				po.orderNum = ObjectUtil.getInt(map,"OrderNum");
				po.url = ObjectUtil.getStr(map,"Url");
				po.alt = ObjectUtil.getStr(map,"Alt");
				datas.add(po);
			}
		}
		return datas;
	}
	@Override
	public int addHouseDetail(ReqParam params) {
		long hid = params.getLong("hid");
		long id = params.getLong("id");
		logger.info("hid={} id={} ",hid,id);
		if(hid <= 0 ) {
			logger.error("error detail {} ",JSON.toJSONString(params));
			return -1;
		}
		//base info
		LjErHouseInfoPo po = converBaseHouseInfo(params);
		po.id = id;
		houseInfoMapper.updateSelective(po);
		
		//detail info 
		List<LjHouseDetailInfoPo> details = converHouseDetail(params, hid);
		if(details != null && details.size() > 0) {
			houseDetailInfoMapper.addDetailBatchs(details);
		}
		
		//feture info
		List<LjHouseFetureInfoPo> fetures = converFetures(params, hid);
		if(fetures.size() >  0) {
			fetureInfoMapper.addFetureBatchs(fetures);
		}
		
		//imgs info
		List<LjImgInfoPo> imgs = converImgs(params, hid);
		if(imgs != null && imgs.size() > 0) 
		{
			imgInfoMapper.addImgBatch(imgs);
		}
		return 0;
	}
	

}
