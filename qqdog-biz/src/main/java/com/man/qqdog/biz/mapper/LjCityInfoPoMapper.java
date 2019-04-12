package com.man.qqdog.biz.mapper;

import com.man.qqdog.client.po.LjCityInfoPo;

public interface LjCityInfoPoMapper {
    int insert(LjCityInfoPo record);

    int insertSelective(LjCityInfoPo record);

    LjCityInfoPo selectByPrimaryKey(Long id);

    int updateSelective(LjCityInfoPo record);
}