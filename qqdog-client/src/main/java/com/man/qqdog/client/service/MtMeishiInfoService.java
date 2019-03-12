package com.man.qqdog.client.service;

import java.util.List;

import com.man.qqdog.client.po.MtMeishiInfoPo;

public interface MtMeishiInfoService {
	
	public int addMeishiInfoBatch(List<MtMeishiInfoPo> datas);

	public int updateMeishiInfoSelective(MtMeishiInfoPo data);
}
