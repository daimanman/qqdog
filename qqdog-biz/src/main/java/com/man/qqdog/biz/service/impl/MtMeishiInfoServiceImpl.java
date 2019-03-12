package com.man.qqdog.biz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.man.qqdog.biz.mapper.MtMeishiInfoPoMapper;
import com.man.qqdog.client.po.MtMeishiInfoPo;
import com.man.qqdog.client.service.MtMeishiInfoService;

@Service
public class MtMeishiInfoServiceImpl implements MtMeishiInfoService {

	@Autowired
	private MtMeishiInfoPoMapper mtMeishiInfoPoMapper;
	
	@Override
	public int addMeishiInfoBatch(List<MtMeishiInfoPo> datas) {
		if(null == datas || datas.size() == 0) {
			return 0;
		}
		return mtMeishiInfoPoMapper.insertMeishiInfoBatch(datas);
	}

	@Override
	public int updateMeishiInfoSelective(MtMeishiInfoPo data) {
		if(null == data || data.id == null || data.id == 0) {
			return 0;
		}
		return mtMeishiInfoPoMapper.updateMeishiInfoSelective(data);
	}

}
