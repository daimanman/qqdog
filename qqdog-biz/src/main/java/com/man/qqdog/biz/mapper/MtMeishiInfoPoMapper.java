package com.man.qqdog.biz.mapper;

import java.util.List;

import com.man.qqdog.client.po.MtMeishiInfoPo;

public interface MtMeishiInfoPoMapper {

	
	public int insertMeishiInfoBatch(List<MtMeishiInfoPo> datas);
	
	
	public int updateMeishiInfoSelective(MtMeishiInfoPo data);
}
