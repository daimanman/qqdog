package com.man.qqdog.client.service;

import java.util.List;

import com.man.qqdog.client.po.MtCityInfoAreaPo;
import com.man.qqdog.client.po.MtCityInfoPo;

public interface MtCityInfoService {

	public int addCityInfoList(List<MtCityInfoPo> datas);
	
	public int addCityInfoAreaList(List<MtCityInfoAreaPo> datas);
	
	public MtCityInfoPo getCityInfoById(int cityId);
	
	public int updateMtCityInfoSelectiveById(MtCityInfoPo cityInfo);
	
	public MtCityInfoAreaPo getNextCrawlArea();
	
	public int updateMtCityInfoAreaSelectiveById(MtCityInfoAreaPo cityInfoArea);
	
	public MtCityInfoAreaPo getCityAreaById(int id);
	
	public int checkCmtTableExistsAndCreate(int cityId);
	
}
