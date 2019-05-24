package com.man.qqdog.biz.mapper;

import org.apache.ibatis.annotations.Param;

import com.man.qqdog.client.po.AuthorInfoPo;

public interface AuthorInfoPoCustomMapper extends AuthorInfoPoMapper {

	public AuthorInfoPo getByMd5Code(String urlMd5);
	
	public int updateNumByMd5Code(@Param("num")int num,@Param("urlMd5")String urlMd5);
}
