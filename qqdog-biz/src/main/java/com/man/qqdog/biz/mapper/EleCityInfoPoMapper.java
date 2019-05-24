package com.man.qqdog.biz.mapper;

import com.man.qqdog.client.po.EleCityInfoPo;

public interface EleCityInfoPoMapper {
    int insert(EleCityInfoPo record);

    int insertSelective(EleCityInfoPo record);

    EleCityInfoPo selectByPrimaryKey(Integer id);

    int updateSelective(EleCityInfoPo record);
}