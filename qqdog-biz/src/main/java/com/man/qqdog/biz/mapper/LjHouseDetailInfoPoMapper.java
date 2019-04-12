package com.man.qqdog.biz.mapper;

import com.man.qqdog.client.po.LjHouseDetailInfoPo;

public interface LjHouseDetailInfoPoMapper {
    int insert(LjHouseDetailInfoPo record);

    int insertSelective(LjHouseDetailInfoPo record);

    LjHouseDetailInfoPo selectByPrimaryKey(Long id);

    int updateSelective(LjHouseDetailInfoPo record);
}