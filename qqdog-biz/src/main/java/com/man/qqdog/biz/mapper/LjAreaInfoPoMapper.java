package com.man.qqdog.biz.mapper;

import com.man.qqdog.client.po.LjAreaInfoPo;

public interface LjAreaInfoPoMapper {
    int insert(LjAreaInfoPo record);

    int insertSelective(LjAreaInfoPo record);

    LjAreaInfoPo selectByPrimaryKey(Long id);

    int updateSelective(LjAreaInfoPo record);
}