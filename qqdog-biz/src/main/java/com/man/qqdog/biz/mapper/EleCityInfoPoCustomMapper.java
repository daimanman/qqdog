package com.man.qqdog.biz.mapper;

import java.util.List;

import com.man.qqdog.client.po.EleCityInfoPo;

public interface EleCityInfoPoCustomMapper extends EleCityInfoPoMapper {

	
	public int insertBatch(List<EleCityInfoPo> datas);
	
	
	public EleCityInfoPo getCityByName(String cityName);
	
	
}
