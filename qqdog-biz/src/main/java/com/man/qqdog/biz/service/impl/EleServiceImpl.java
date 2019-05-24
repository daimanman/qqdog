package com.man.qqdog.biz.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.man.qqdog.biz.mapper.DbMapper;
import com.man.qqdog.biz.mapper.EleCityInfoPoCustomMapper;
import com.man.qqdog.biz.mapper.EleMeishiInfoPoCustomMapper;
import com.man.qqdog.biz.mapper.ElePoiInfoPoCustomMapper;
import com.man.qqdog.client.po.EleCityInfoPo;
import com.man.qqdog.client.po.EleMeishiInfoPo;
import com.man.qqdog.client.po.ElePoiInfoPo;
import com.man.qqdog.client.service.EleService;
import com.man.utils.IdWorker;
import com.man.utils.ObjectUtil;
import com.man.utils.ReqParam;

@Service
public class EleServiceImpl implements EleService {

	Logger logger = LoggerFactory.getLogger(EleServiceImpl.class);
	@Autowired
	public EleCityInfoPoCustomMapper cityMapper;
	
	@Autowired
	public ElePoiInfoPoCustomMapper poiMapper;
	
	@Autowired
	public IdWorker idWorker;
	
	@Autowired
	public DbMapper dbMapper;
	
	@Autowired
	public EleMeishiInfoPoCustomMapper meishiMapper;
	
	
	public List<EleCityInfoPo> converCitys(List<Map<String,Object>> datas){
		List<EleCityInfoPo> citys = new ArrayList<EleCityInfoPo>();
		if(null == datas || datas.size() == 0) {
			return citys;
		}
		
		for(Map<String,Object> data : datas) {
			EleCityInfoPo city = new EleCityInfoPo();
			city.abbr = ObjectUtil.getStr(data,"abbr");
			city.id = ObjectUtil.getInt(data, "id");
			city.geohash = ObjectUtil.getStr(data, "geohash");
			city.latitude = ObjectUtil.getDouble(data, "latitude");
			city.longitude = ObjectUtil.getDouble(data, "longitude");
			city.name = ObjectUtil.getStr(data,"name");
			city.pinyin =  ObjectUtil.getStr(data,"pinyin");
			citys.add(city);
		}
		return citys;
	}
	@Override
	public int addCityInfos(ReqParam param) {
		List<EleCityInfoPo> citys = converCitys(param.getList("datas"));
		logger.info("{} ",JSON.toJSONString(param));
		if(citys.size() > 0) {
			return cityMapper.insertBatch(citys);
		}
		return 0;
	}
	public List<ElePoiInfoPo> converPois(ReqParam params){
		List<ElePoiInfoPo> datas = new ArrayList<>();
		int cityId = params.getInt("cityId");
		List<Map<String,Object>> pois = params.getList("pois");
		for(Map<String,Object> poi : pois ) {
			ElePoiInfoPo po = new ElePoiInfoPo();
			po.cityId = cityId;
			po.flag = 0;
			po.id = idWorker.nextId();
			po.lat = ObjectUtil.getDouble(poi,"lat");
			po.lng = ObjectUtil.getDouble(poi,"lng");
			po.num =0;
			po.offsetNum = 0;
			datas.add(po);
		}
		return datas;
	}
	@Override
	public int saveCityPoints(ReqParam params) {
		List<ElePoiInfoPo> datas = converPois(params);
		if(datas.size() > 0) {
			return poiMapper.insertBatch(datas);
		}
		return 0;
	}
	@Override
	public ElePoiInfoPo getNextPoi(int cityId) {
		return poiMapper.getNextPoi(cityId);
	}
	private void checkMeishiTableAndCreate(int cityId) {
		String tableName = "ele_meishi_info_"+cityId;
		int num = dbMapper.checkTableExists(tableName);
		if(num == 0) {
			dbMapper.createEleMeishiTable(cityId);
		}
	}
	private List<EleMeishiInfoPo> converMeishis(ReqParam params){
		List<Map<String,Object>> datas = params.getList("datas");
		int cityId = params.getInt("cityId");
		long pointId = params.getLong("pointId");
		List<EleMeishiInfoPo>  pos = new ArrayList<EleMeishiInfoPo>();
		Date nowDate = new Date();
		for(Map<String,Object> data : datas ) {
			EleMeishiInfoPo po = new EleMeishiInfoPo();
			po.id = ObjectUtil.getStr(data,"id");
			if(ObjectUtil.isNull(po.id)) {
				logger.error("null id pointId={} po={}",pointId,JSON.toJSONString(data));
				continue;
			}
			po.cityId= cityId;
			po.actTag = ObjectUtil.getInt(data,"act_tag");
			po.address = ObjectUtil.getStr(data,"address",200);
			po.authenticId = ObjectUtil.getLong(data,"authentic_id");
			po.createTime =  nowDate;
			po.deliveryFeeDiscount = ObjectUtil.getInt(data,"delivery_fee_discount");
			po.description = ObjectUtil.getStr(data,"description",200);
			po.flag = ObjectUtil.getInt(data,"flag");
			po.flavors = JSON.toJSONString(data.get("flavors"));
			po.floatDeliveryFee = ObjectUtil.getDouble(data,"float_delivery_fee");
			po.floatMinimumOrderAmount = ObjectUtil.getDouble(data,"float_minimum_order_amount");
			po.foldingRestaurantBrand = ObjectUtil.getStr(data,"folding_restaurant_brand",200);
			po.hasStory = ObjectUtil.getBool(data,"has_story");
			po.imagePath = ObjectUtil.getStr(data,"image_path");
			po.isNew = ObjectUtil.getBool(data,"is_new");
			po.isPremium = ObjectUtil.getBool(data,"is_premium");
			po.isStar = ObjectUtil.getBool(data,"is_star");
			po.isValid = ObjectUtil.getBool(data,"is_valid");
			po.latitude = ObjectUtil.getDouble(data,"latitude");
			po.longitude = ObjectUtil.getDouble(data,"longitude");
			po.maxAppliedQuantityPerOrder  = ObjectUtil.getInt(data,"max_applied_quantity_per_order");
			po.name = ObjectUtil.getStr(data,"name",200);
			po.nextBusinessTime = ObjectUtil.getStr(data,"next_business_time",200);
			po.openingHours = JSON.toJSONString(data.get("opening_hours"));
			po.orderLeadTime = ObjectUtil.getInt(data,"order_lead_time");
			po.phone = ObjectUtil.getStr(data,"phone",200);
			po.promotionInfo = ObjectUtil.getStr(data,"promotion_info",200);
			po.rating = ObjectUtil.getDouble(data,"rating");
			po.ratingCount = ObjectUtil.getInt(data,"rating_count");
			po.recentOrderNum = ObjectUtil.getInt(data,"recent_order_num");
			po.scheme = ObjectUtil.getStr(data,"scheme");
			po.status = ObjectUtil.getInt(data,"status");
			po.type = ObjectUtil.getInt(data,"type");
			po.pointId = pointId;
			pos.add(po);
			
		}
		return pos;
	}
	@Override
	public int saveMeishiDatas(ReqParam params) {
		int cityId = params.getInt("cityId");
		//checkMeishiTableAndCreate(cityId);
		List<EleMeishiInfoPo> pos = converMeishis(params);
		Map<String,EleMeishiInfoPo> poMap = new  HashMap<>();
		Set<String> ids = new HashSet<>();
		for(EleMeishiInfoPo po : pos) {
			ids.add(po.id);
			poMap.put(po.id,po);
		}
		List<String> existIds =  meishiMapper.getExistIds(cityId, new ArrayList<>(ids));
		int existIdsLength = ObjectUtil.getSize(existIds);
		logger.info("cityId={} posSize={} existIdsLength={}",cityId,pos.size(),existIdsLength);
		if(existIdsLength == 0 && pos.size() > 0 ) {
			try {
			return meishiMapper.insertBatch(cityId, pos);
			}catch(Exception e) {
				logger.error(e.getMessage());
				logger.error("POJSON {} ",JSON.toJSONString(pos));
			}
		}else {
			List<EleMeishiInfoPo> newPos = new ArrayList<>();
			for(String id : ids) {
				if(!existIds.contains(id)) {
					newPos.add(poMap.get(id));
				}
			}
			if(newPos.size() > 0) {
				try {
					return meishiMapper.insertBatch(cityId, newPos);
				}catch(Exception e) {
					logger.error(e.getMessage());
					logger.error("NewPOJSON {} ",JSON.toJSONString(newPos));
				}
			}
		}
		return 0;
	}
	@Override
	public List<EleMeishiInfoPo> getTopK(ReqParam params) {
		int cityId = params.getInt("cityId");
		int num = params.getInt("num");
		return meishiMapper.getTopK(cityId, num);
	}

}
