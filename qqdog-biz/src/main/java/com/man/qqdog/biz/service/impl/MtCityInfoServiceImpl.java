package com.man.qqdog.biz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
