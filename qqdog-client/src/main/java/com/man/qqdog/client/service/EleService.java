package com.man.qqdog.client.service;

import java.util.List;

import com.man.qqdog.client.po.EleMeishiInfoPo;
import com.man.qqdog.client.po.ElePoiInfoPo;
import com.man.utils.ReqParam;

public interface EleService {

	public int addCityInfos(ReqParam param);
	
	public int saveCityPoints(ReqParam params);
	
	public ElePoiInfoPo getNextPoi(int cityId);
	
	public int saveMeishiDatas(ReqParam params);
	
	public List<EleMeishiInfoPo> getTopK(ReqParam params);
}
