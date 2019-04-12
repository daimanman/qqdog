package com.man.qqdog.biz.mapper;

import org.apache.ibatis.annotations.Param;

public interface DbMapper {

	public int createMeiCmtTable(@Param("cityId") int cityId);
	
	public int checkTableExists(@Param("tableName") String tableName);
}
