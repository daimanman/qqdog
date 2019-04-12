package com.man.qqdog.biz.mapper;

import java.util.List;

import com.man.qqdog.client.po.LjHouseDetailInfoPo;

public interface LjHouseDetailInfoPoCustomMapper extends LjHouseDetailInfoPoMapper {

	public int addDetailBatchs(List<LjHouseDetailInfoPo> datas);
}
