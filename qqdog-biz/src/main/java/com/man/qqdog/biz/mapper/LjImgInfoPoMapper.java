package com.man.qqdog.biz.mapper;

import com.man.qqdog.client.po.LjImgInfoPo;

public interface LjImgInfoPoMapper {
    int insert(LjImgInfoPo record);

    int insertSelective(LjImgInfoPo record);

    LjImgInfoPo selectByPrimaryKey(Long id);

    int updateSelective(LjImgInfoPo record);
}