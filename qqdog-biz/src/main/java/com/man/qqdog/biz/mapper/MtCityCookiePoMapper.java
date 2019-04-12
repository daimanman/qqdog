package com.man.qqdog.biz.mapper;

import java.util.List;

import com.man.qqdog.client.po.MtCityCookiePo;

public interface MtCityCookiePoMapper {

	
	public int insert(MtCityCookiePo data);
	
	public int updateSelective(MtCityCookiePo data);
	
	public List<MtCityCookiePo> getAllCookie();
	
	
}
