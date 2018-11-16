package com.man.qqdog.biz.mapper;

import java.util.List;

import com.man.qqdog.client.po.QsessionInfoPo;

public interface QsessionInfoPoMapper {

	public int addQsessionInfo(QsessionInfoPo info);
	
	public int deleteByUid(String uid);
	
	public List<QsessionInfoPo> getSessions(String uid);
	
	public int updateSessionInfoSelective(QsessionInfoPo info);
	
	
	
}
