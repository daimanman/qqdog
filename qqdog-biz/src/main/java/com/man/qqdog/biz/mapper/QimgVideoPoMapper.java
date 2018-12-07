package com.man.qqdog.biz.mapper;

import java.util.List;

import com.man.qqdog.client.po.QimgVideoPo;

public interface QimgVideoPoMapper {
	public int addQimgVideoBatch(List<QimgVideoPo> datas);
}
