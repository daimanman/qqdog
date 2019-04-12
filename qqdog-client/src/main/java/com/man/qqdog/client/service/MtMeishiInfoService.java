package com.man.qqdog.client.service;

import java.util.List;
import java.util.Map;

import com.man.qqdog.client.po.MtMeishiInfoPo;

public interface MtMeishiInfoService {
	
	public int addMeishiInfoBatch(List<MtMeishiInfoPo> datas);

	public int updateMeishiInfoSelective(MtMeishiInfoPo data);
	
	public int addCmtNum(long poiId,int cmtGet);
	
	public int addMeishiCmts(List<Map<String,Object>> datas,int cityId,long poiId);
	
	
}
