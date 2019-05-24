package com.man.qqdog.biz.mapper;

import com.man.qqdog.client.po.AuthorInfoPo;

public interface AuthorInfoPoMapper {
    int insert(AuthorInfoPo record);

    int insertSelective(AuthorInfoPo record);

    AuthorInfoPo selectByPrimaryKey(Long id);

    int updateSelective(AuthorInfoPo record);
}