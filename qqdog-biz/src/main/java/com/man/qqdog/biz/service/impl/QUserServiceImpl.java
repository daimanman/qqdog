package com.man.qqdog.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.man.qqdog.biz.mapper.QuserInfoPoMapper;
import com.man.qqdog.client.po.QuserInfoPo;
import com.man.qqdog.client.service.QUserService;


@Service
public class QUserServiceImpl extends BaseServiceImpl implements QUserService  {

	@Autowired
	private QuserInfoPoMapper quserInfoPoMapper;
	
	@Override
	public int addQuserInfo(QuserInfoPo userInfo) {
		return quserInfoPoMapper.addQuserInfo(userInfo);
	}

	
}
