package com.man.qqdog.biz.mapper;

import com.man.qqdog.client.po.ShiInfoPo;

public interface ShiInfoPoCustomMapper extends ShiInfoPoMapper {

	public ShiInfoPo getByMd5Code(String urlMd5);
	
}
