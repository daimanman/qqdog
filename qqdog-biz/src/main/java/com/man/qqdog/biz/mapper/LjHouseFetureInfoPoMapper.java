package com.man.qqdog.biz.mapper;

import com.man.qqdog.client.po.LjHouseFetureInfoPo;

public interface LjHouseFetureInfoPoMapper {
    int insert(LjHouseFetureInfoPo record);

    int insertSelective(LjHouseFetureInfoPo record);

    LjHouseFetureInfoPo selectByPrimaryKey(Long id);

    int updateSelective(LjHouseFetureInfoPo record);
}