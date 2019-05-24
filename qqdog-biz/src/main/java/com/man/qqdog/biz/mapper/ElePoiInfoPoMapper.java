package com.man.qqdog.biz.mapper;

import com.man.qqdog.client.po.ElePoiInfoPo;

public interface ElePoiInfoPoMapper {
    int insert(ElePoiInfoPo record);

    int insertSelective(ElePoiInfoPo record);

    ElePoiInfoPo selectByPrimaryKey(Long id);

    int updateSelective(ElePoiInfoPo record);
}