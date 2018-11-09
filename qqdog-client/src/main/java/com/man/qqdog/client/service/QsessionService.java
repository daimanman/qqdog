package com.man.qqdog.client.service;

import java.util.List;

import com.man.qqdog.client.po.QsessionInfoPo;

public interface QsessionService {

	public int addQsessionInfo(QsessionInfoPo info);

	public int deleteByUid(String uid);
	
	public List<QsessionInfoPo> getAllSession();
	
	public QsessionInfoPo getByUid(String uid);
	
	public int updateQsessionInfo(QsessionInfoPo info);
}
