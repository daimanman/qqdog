package com.man.qqdog.biz.mapper;

import com.man.qqdog.client.po.MtTagPo;

public interface MtTagPoMapper {
	
    int insert(MtTagPo record);

    int insertSelective(MtTagPo record);

    MtTagPo selectByPrimaryKey(Long id);

    int updateSelective(MtTagPo record);
}