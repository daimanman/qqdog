package com.man.qqdog.biz.mapper;

import java.util.List;

import com.man.qqdog.client.po.MtCityInfoAreaPo;
import com.man.qqdog.client.po.MtCityInfoPo;

public interface MtCityInfoPoMapper {

	public int insertCityInfoBatch(List<MtCityInfoPo> datas);
	
	public int insertCityInfoAreaBatch(List<MtCityInfoAreaPo> datas);
	
	public MtCityInfoPo getCityInfoById(int cityId);
	
	public int updateMtCityInfoSelectiveById(MtCityInfoPo cityInfo);
	
	public MtCityInfoPo getNextCityCrawl();
	
	
	public MtCityInfoAreaPo getCityAreaById(int areaId);
	
	public int updateCityAreaSelective(MtCityInfoAreaPo data);
	
	
}
