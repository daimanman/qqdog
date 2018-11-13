package com.man.qqdog.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.man.qqdog.biz.mapper.QtaskInfoPoMapper;
import com.man.qqdog.client.po.QtaskInfoPo;
import com.man.qqdog.client.service.QtaskInfoService;

@Service
public class QtaskServiceImpl extends BaseServiceImpl implements QtaskInfoService {

	
	@Autowired
	private QtaskInfoPoMapper taskInfoMapper;
	
	@Override
	public QtaskInfoPo getQtaskByUid(long uid) {
		return taskInfoMapper.getQtaskByUid(uid);
	}

	@Override
	public int insertQtaskInfo(QtaskInfoPo data) {
		QtaskInfoPo po = getQtaskByUid(data.uid);
		if(po != null) {
			return 0;
		}
		return taskInfoMapper.insertQtaskInfo(data);
	}

	@Override
	public int updateByPrimaryKeySelective(QtaskInfoPo data) {
		return taskInfoMapper.updateByPrimaryKeySelective(data);
	}

}
