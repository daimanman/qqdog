package com.man.qqdog.biz.mapper;

import java.util.List;

import com.man.qqdog.client.po.QphotoImgPo;
import com.man.qqdog.client.po.QphotoInfoPo;

public interface QphotoInfoPoMapper {

	
	public int addPhotoImgBatch(List<QphotoImgPo> datas);
	
	public int addPhotoBatch(List<QphotoInfoPo> datas);
	
	public int updatePhotoSelective(QphotoInfoPo data);
	
	
}
