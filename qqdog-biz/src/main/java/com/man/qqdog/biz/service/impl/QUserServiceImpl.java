package com.man.qqdog.biz.service.impl;

import java.util.Set;

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

	@Override
	public int batchInsertUidsN(Set<Long> sets) {
		if(null == sets || sets.size() == 0) {
			return 0;
		}
		return quserInfoPoMapper.batchInsertUidsN(sets);
	}

	@Override
	public long getMaxUid() {
		return quserInfoPoMapper.getMaxUid();
	}

	
}
