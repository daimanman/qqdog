package com.man.qqdog.biz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.man.qqdog.client.po.LjErHouseInfoPo;

public interface LjErHouseInfoPoCustomMapper extends LjErHouseInfoPoMapper {

	public LjErHouseInfoPo getByHid(@Param("hid")long hid);
	
	public List<LjErHouseInfoPo> getNeedDetailHid(@Param("num")int num);
	
	public int updateDetailStatus(@Param("hids") List<Long> hids,@Param("dmlFlag") int dmlFlag);
	
}
