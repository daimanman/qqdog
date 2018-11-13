package com.man.qqdog.biz.mapper;

import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.man.qqdog.client.po.QuserInfoPo;

public interface QuserInfoPoMapper {
	
	public int addQuserInfo(QuserInfoPo userInfo);
	
	public int batchInsertUidsN(@Param("set") Set<Long> sets);
	
	public long getMaxUid();
}
