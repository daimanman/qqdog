package com.man.qqdog.biz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.man.qqdog.biz.mapper.QphotoInfoPoMapper;
import com.man.qqdog.client.po.QphotoImgPo;
import com.man.qqdog.client.po.QphotoInfoPo;
import com.man.qqdog.client.service.QphotoInfoService;

@Service
public class QphotoInfoServiceImpl extends BaseServiceImpl implements QphotoInfoService {


	@Autowired
	private QphotoInfoPoMapper photoMapper;
	
	@Override
	public int addPhotoImgBatch(List<QphotoImgPo> datas) {
		if(null == datas || datas.size() == 0) {
			return 0;
		}
		return photoMapper.addPhotoImgBatch(datas);
	}

	@Override
	public int addPhotoBatch(List<QphotoInfoPo> datas) {
		if(null == datas || datas.size() == 0) {
			return 0;
		}
		return photoMapper.addPhotoBatch(datas);
	}

	@Override
	public int updatePhotoSelective(QphotoInfoPo data) {
		return photoMapper.updatePhotoSelective(data);
	}

}
