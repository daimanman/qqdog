package com.man.qqdog.biz.mapper;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.man.qqdog.client.po.QuserInfoPo;

public interface QuserInfoPoMapper {
	
	public int addQuserInfo(QuserInfoPo userInfo);
	
	public int batchInsertUidsN(@Param("set") Set<Long> sets);
	
	public long getMaxUid();
	
	public List<Map<String,Object>> getEsData(@Param("tableName")String tableName,@Param("startId")long startId,@Param("endId")long endId);
}
