package com.man.qqdog.biz.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.man.constants.IdxConstant;
import com.man.es.manager.ElasticSearchManager;
import com.man.pageinfo.PageResult;
import com.man.pageinfo.QueryParams;
import com.man.qqdog.biz.es.QphotoInfoQueryDsl;
import com.man.qqdog.biz.mapper.QphotoInfoPoMapper;
import com.man.qqdog.client.po.QphotoImgPo;
import com.man.qqdog.client.po.QphotoInfoPo;
import com.man.qqdog.client.service.QphotoInfoService;
import com.man.utils.ReqParam;

@Service
public class QphotoInfoServiceImpl extends BaseServiceImpl implements QphotoInfoService {


	@Autowired
	private QphotoInfoPoMapper photoMapper;
	
	@Autowired
	private ElasticSearchManager esManager;
	
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

	@Override
	public PageResult<Map<String, Object>> queryEsPhotoPage(ReqParam params) {
		QueryParams queryParams = QphotoInfoQueryDsl.parsePhotoListDsl(params);
		return esManager.filterPage(IdxConstant.QPHOTO_INFO_IDX,IdxConstant.QPHOTO_INFO_TYPE, queryParams);
	}

	@Override
	public PageResult<Map<String, Object>> queryEsImgPage(ReqParam params) {
		QueryParams queryParams = QphotoInfoQueryDsl.parsePhotoImgListDsl(params);
		return esManager.filterPage(IdxConstant.QPHOTO_IMG_IDX,IdxConstant.QPHOTO_IMG_TYPE, queryParams);
	}
}
