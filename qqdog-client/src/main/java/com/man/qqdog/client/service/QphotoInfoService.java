package com.man.qqdog.client.service;

import java.util.List;
import java.util.Map;

import com.man.pageinfo.PageResult;
import com.man.qqdog.client.po.QimgVideoPo;
import com.man.qqdog.client.po.QphotoImgPo;
import com.man.qqdog.client.po.QphotoInfoPo;
import com.man.utils.ReqParam;

public interface QphotoInfoService extends BaseService {
public int addPhotoImgBatch(List<QphotoImgPo> datas);
	
	public int addPhotoBatch(List<QphotoInfoPo> datas);
	
	public int updatePhotoSelective(QphotoInfoPo data);
	
	public PageResult<Map<String,Object>> queryEsPhotoPage(ReqParam params);
	
	public PageResult<Map<String,Object>> queryEsImgPage(ReqParam params);
	
	public int addImgVideoBatch(List<QimgVideoPo> datas);
	
	public long getPhotoNum(String uid);
	
	
	
}
