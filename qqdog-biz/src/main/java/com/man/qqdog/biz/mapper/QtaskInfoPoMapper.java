package com.man.qqdog.biz.mapper;

import com.man.qqdog.client.po.QtaskInfoPo;

public interface QtaskInfoPoMapper {

	public QtaskInfoPo getQtaskByUid(long uid);
	
	public int insertQtaskInfo(QtaskInfoPo data);
	
	public int updateByPrimaryKeySelective(QtaskInfoPo data);
}
