package com.man.qqdog.biz.mapper;

import com.man.qqdog.client.po.MtBtPo;

public interface MtBtPoMapper {
    int insert(MtBtPo record);

    int insertSelective(MtBtPo record);

    MtBtPo selectByPrimaryKey(Long id);

    int updateSelective(MtBtPo record);
}