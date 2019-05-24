package com.man.qqdog.biz.mapper;

import com.man.qqdog.client.po.ShiInfoPo;

public interface ShiInfoPoMapper {
    int insert(ShiInfoPo record);

    int insertSelective(ShiInfoPo record);

    ShiInfoPo selectByPrimaryKey(Long id);

    int updateSelective(ShiInfoPo record);
}