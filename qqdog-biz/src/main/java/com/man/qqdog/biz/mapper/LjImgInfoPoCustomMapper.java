package com.man.qqdog.biz.mapper;

import java.util.List;

import com.man.qqdog.client.po.LjImgInfoPo;

public interface LjImgInfoPoCustomMapper extends LjImgInfoPoMapper {
	public int addImgBatch(List<LjImgInfoPo> datas);
}
