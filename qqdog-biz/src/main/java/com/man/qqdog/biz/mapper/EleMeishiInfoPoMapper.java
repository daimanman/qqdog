package com.man.qqdog.biz.mapper;

import com.man.qqdog.client.po.EleMeishiInfoPo;

public interface EleMeishiInfoPoMapper {
    int insert(EleMeishiInfoPo record);

    int insertSelective(EleMeishiInfoPo record);

    EleMeishiInfoPo selectByPrimaryKey(String id);

    int updateSelective(EleMeishiInfoPo record);
}