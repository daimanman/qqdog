package com.man.qqdog.client.service;

import com.man.qqdog.client.po.QtaskInfoPo;

public interface QtaskInfoService extends BaseService {

public QtaskInfoPo getQtaskByUid(long uid);
	
	public int insertQtaskInfo(QtaskInfoPo data);
	
	public int updateByPrimaryKeySelective(QtaskInfoPo data);
}
