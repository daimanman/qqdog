package com.man.qqdog.biz.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.man.qqdog.client.dto.MtCmtLog;
import com.man.qqdog.client.po.MtImgPo;
import com.man.qqdog.client.po.MtMeishiCmtPo;
import com.man.qqdog.client.po.MtMeishiDealPo;
import com.man.qqdog.client.po.MtMeishiInfoPo;
import com.man.qqdog.client.po.MtMeishiTagPo;

public interface MtMeishiInfoPoMapper {

	
	public int insertMeishiInfoBatch(List<MtMeishiInfoPo> datas);
	
	
	public int updateMeishiInfoSelective(MtMeishiInfoPo data);
	
	public int insertMtImgBatch(List<MtImgPo> datas);
	
	public int insertCityMeiShiCmtBatch(@Param("datas")List<MtMeishiCmtPo> datas,@Param("cityId")int cityId);
	
	public MtMeishiInfoPo getMeishiInfoById(@Param("id") long id);
	
	public int insertMtMeishiTagBatch(List<MtMeishiTagPo> datas);
	
	public int insertMtMeishiDealBatch(List<MtMeishiDealPo> datas);
	
	public int addCmtNum(@Param("id")long id ,@Param("num")int num);
	
	public List<MtMeishiInfoPo> getNextCmtMeishiTop100(@Param("ids") Set<Long> ids);
	
	public List<MtMeishiInfoPo> getMeishiInfoByIds(@Param("ids") List<Long> ids);
	
	public List<Long> getExistsIds(@Param("ids") List<Long> ids);
	
	public MtCmtLog getSumCmtNum(@Param("ids") List<Long> ids);
	
	public List<MtCmtLog> getPoiCmtNum(@Param("ids") List<Long> ids);
	
	public MtMeishiInfoPo getNextMeishiDetail(@Param("cityId")int cityId);
	
	public MtMeishiInfoPo getNextCmt(@Param("ids") Set<Long> ids,@Param("cityId") int cityId);
	
	public MtMeishiInfoPo getNextDetail(@Param("ids") Set<Long> ids,@Param("cityId") int cityId);
	
}
