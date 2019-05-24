package com.man.qqdog.biz.mapper;

import java.util.List;

import com.man.qqdog.client.po.ElePoiInfoPo;

public interface ElePoiInfoPoCustomMapper extends ElePoiInfoPoMapper {

	public ElePoiInfoPo getNextPoi(int cityId);
	
	public int insertBatch(List<ElePoiInfoPo> datas);
}
