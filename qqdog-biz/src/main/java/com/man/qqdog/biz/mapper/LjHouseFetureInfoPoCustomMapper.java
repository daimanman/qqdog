package com.man.qqdog.biz.mapper;

import java.util.List;

import com.man.qqdog.client.po.LjHouseFetureInfoPo;

public interface LjHouseFetureInfoPoCustomMapper extends LjHouseFetureInfoPoMapper {

	public int addFetureBatchs(List<LjHouseFetureInfoPo> datas);
}
