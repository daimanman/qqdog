package com.man.qqdog.biz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.man.qqdog.client.po.EleMeishiInfoPo;

public interface EleMeishiInfoPoCustomMapper extends EleMeishiInfoPoMapper {

	
	public int insertBatch(@Param("cityId")int cityId,@Param("datas")List<EleMeishiInfoPo> datas);
	
	public List<String> getExistIds(@Param("cityId")int cityId,@Param("ids")List<String> ids);
	
	public List<EleMeishiInfoPo> getTopK(@Param("cityId")int cityId,@Param("num") int num);
	
}
