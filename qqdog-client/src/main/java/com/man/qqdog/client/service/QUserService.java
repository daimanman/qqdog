package com.man.qqdog.client.service;

import java.util.Set;

import com.man.qqdog.client.po.QuserInfoPo;

public interface QUserService extends BaseService {

public int addQuserInfo(QuserInfoPo userInfo);
	
	public int batchInsertUidsN( Set<Long> sets);
	
	public long getMaxUid();
}
