package com.man.qqdog.biz.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.man.qqdog.biz.mapper.MtMeishiInfoPoMapper;
import com.man.qqdog.client.po.MtMeishiCmtPo;
import com.man.qqdog.client.po.MtMeishiInfoPo;
import com.man.qqdog.client.service.MtMeishiInfoService;
import com.man.utils.IdWorker;
import com.man.utils.ObjectUtil;

@Service
public class MtMeishiInfoServiceImpl implements MtMeishiInfoService {

	@Autowired
	private MtMeishiInfoPoMapper mtMeishiInfoPoMapper;
	
	private MtMeishiCmtPo cmtLock = new MtMeishiCmtPo();
	
	Logger logger = LoggerFactory.getLogger(MtMeishiInfoServiceImpl.class);
	
	
	@Autowired
	private IdWorker idWorker;
	
	@Override
	public int addMeishiInfoBatch(List<MtMeishiInfoPo> datas) {
		if(null == datas || datas.size() == 0) {
			return 0;
		}
		
		List<Long> ids = new ArrayList<>(datas.size());
		Map<Long,MtMeishiInfoPo> infoMap = new HashMap<>();
		for(MtMeishiInfoPo po : datas) {
			ids.add(po.id);
			infoMap.put(po.id,po);
		}
		List<Long> oldIds = mtMeishiInfoPoMapper.getExistsIds(ids);
		ids.removeAll(oldIds);
		
		//只添加未存在的  meishi info 
		if(ids.size() > 0) {
			logger.info("add new {} meishi_ids not exists {} ",ids.size(),ids);
			List<MtMeishiInfoPo> newDatas = new ArrayList<>(ids.size());
			for(long id: ids) {
				newDatas.add(infoMap.get(id));
			}
			return mtMeishiInfoPoMapper.insertMeishiInfoBatch(newDatas);
		}
		return 0 ;
	}
	
	

	@Override
	public int updateMeishiInfoSelective(MtMeishiInfoPo data) {
		if(null == data || data.id == null || data.id == 0) {
			return 0;
		}
		return mtMeishiInfoPoMapper.updateMeishiInfoSelective(data);
	}

	@Override
	public int addCmtNum(long poiId, int cmtGet) {
		synchronized (cmtLock) {
			return mtMeishiInfoPoMapper.addCmtNum(poiId, cmtGet);
		}
	}

	private List<MtMeishiCmtPo> parseCmts(List<Map<String,Object>> datas,int cityId,long poiId){
		int size = ObjectUtil.getSize(datas);
		if(size == 0) {
			return null;
		}
		List<MtMeishiCmtPo> cmts = new ArrayList<>(size);
		for(Map<String,Object> data : datas) {
			MtMeishiCmtPo po = new MtMeishiCmtPo();
			po.alreadyZzz = ObjectUtil.getBool(data,"alreadyZzz");
			po.anonymous = ObjectUtil.getBool(data,"anonymous");
			po.avgPrice = ObjectUtil.getDouble(data,"avgPrice");
			po.cityId = cityId;
			po.comment = ObjectUtil.getStr(data,"comment");
			po.commentTime = ObjectUtil.getLong(data,"commentTime");
			po.dealEndtime = ObjectUtil.getLong(data,"dealEndtime");
			po.did = ObjectUtil.getLong(data,"did");
			po.hilignt = ObjectUtil.getStr(data,"hilignt");
			po.id = idWorker.nextId();
			po.menu = ObjectUtil.getStr(data,"menu");
			po.merchantComment = ObjectUtil.getStr(data,"merchantComment");
			po.picUrls = JSON.toJSONString(data.get("picUrls"));
			po.poiId = poiId;
			po.quality = ObjectUtil.getBool(data,"quality");
			po.readCnt = ObjectUtil.getInt(data,"readCnt");
			po.replyCnt = ObjectUtil.getInt(data,"replyCnt");
			po.reviewId = ObjectUtil.getLong(data,"reviewId");
			po.star = ObjectUtil.getInt(data,"star");
			po.userId = ObjectUtil.getLong(data,"userId");
			po.userLevel = ObjectUtil.getInt(data,"userLevel");
		    po.utype = ObjectUtil.getInt(data,"utype");
		    po.userName = ObjectUtil.getStr(data,"userName");
		    po.userUrl = ObjectUtil.getStr(data,"userUrl");
		    po.zanCnt = ObjectUtil.getInt(data,"zanCnt");
		    cmts.add(po);
		}
		return cmts;
	}
	@Override
	public int addMeishiCmts(List<Map<String, Object>> datas, int cityId, long poiId) {
		List<MtMeishiCmtPo> cmts = parseCmts(datas, cityId, poiId);
		int size = ObjectUtil.getSize(datas);
		if(size > 0 ) {
			mtMeishiInfoPoMapper.insertCityMeiShiCmtBatch(cmts, cityId);
			addCmtNum(poiId, size);
		}
		return size;
	}

}
