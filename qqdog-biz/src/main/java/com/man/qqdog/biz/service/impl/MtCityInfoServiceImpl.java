package com.man.qqdog.biz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.man.qqdog.biz.mapper.DbMapper;
import com.man.qqdog.biz.mapper.MtCityInfoPoMapper;
import com.man.qqdog.client.po.MtCityInfoAreaPo;
import com.man.qqdog.client.po.MtCityInfoPo;
import com.man.qqdog.client.service.MtCityInfoService;
import com.man.utils.IdWorker;

@Service
public class MtCityInfoServiceImpl implements MtCityInfoService {

	@Autowired
	private MtCityInfoPoMapper mtCityInfoPoMapper;
	
	@Autowired
	public DbMapper dbMapper;
	
	@Autowired
	private IdWorker idWorker;
	
	@Override
	public int addCityInfoList(List<MtCityInfoPo> datas) {
		if(null == datas || datas.size() == 0) {
			return 0;
		}
		return mtCityInfoPoMapper.insertCityInfoBatch(datas);
	}

	@Override
	public int addCityInfoAreaList(List<MtCityInfoAreaPo> datas) {
		if(null == datas || datas.size() == 0) {
			return 0;
		}
		return mtCityInfoPoMapper.insertCityInfoAreaBatch(datas);
	}

	@Override
	public MtCityInfoPo getCityInfoById(int cityId) {
		return mtCityInfoPoMapper.getCityInfoById(cityId);
	}

	@Override
	public int updateMtCityInfoSelectiveById(MtCityInfoPo cityInfo) {
		return mtCityInfoPoMapper.updateMtCityInfoSelectiveById(cityInfo);
	}

	@Override
	public MtCityInfoAreaPo getNextCrawlArea() {
		return mtCityInfoPoMapper.getNextCrawlArea();
	}

	@Override
	public int updateMtCityInfoAreaSelectiveById(MtCityInfoAreaPo cityInfoArea) {
		if(null == cityInfoArea) {
			return 0;
		}
		return mtCityInfoPoMapper.updateCityAreaSelective(cityInfoArea);
	}

	@Override
	public MtCityInfoAreaPo getCityAreaById(int id) {
		return mtCityInfoPoMapper.getCityAreaById(id);
	}

	@Override
	public int checkCmtTableExistsAndCreate(int cityId) {
		String name = "mt_meishi_cmt_"+cityId;
		int num = dbMapper.checkTableExists(name);
		if(num == 0) {
			dbMapper.createMeiCmtTable(cityId);
		}
		return 1;
	}

}
