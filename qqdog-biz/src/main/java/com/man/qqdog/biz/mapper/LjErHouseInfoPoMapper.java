package com.man.qqdog.biz.mapper;

import com.man.qqdog.client.po.LjErHouseInfoPo;

public interface LjErHouseInfoPoMapper {
    int insert(LjErHouseInfoPo record);

    int insertSelective(LjErHouseInfoPo record);

    LjErHouseInfoPo selectByPrimaryKey(Long id);

    int updateSelective(LjErHouseInfoPo record);
}