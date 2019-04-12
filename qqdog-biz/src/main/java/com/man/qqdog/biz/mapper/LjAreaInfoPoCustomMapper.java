package com.man.qqdog.biz.mapper;

import org.apache.ibatis.annotations.Param;

import com.man.qqdog.client.po.LjAreaInfoPo;

public interface LjAreaInfoPoCustomMapper extends LjAreaInfoPoMapper {
	public  LjAreaInfoPo getByUrl(@Param("url") String url);
}
