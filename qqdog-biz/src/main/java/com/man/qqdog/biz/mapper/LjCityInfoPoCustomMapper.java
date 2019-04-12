package com.man.qqdog.biz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.man.qqdog.client.po.LjCityInfoPo;

public interface LjCityInfoPoCustomMapper extends LjCityInfoPoMapper {

	public List<LjCityInfoPo> getAll();
	
	public LjCityInfoPo getByCityName(@Param("cityName") String name);
}
