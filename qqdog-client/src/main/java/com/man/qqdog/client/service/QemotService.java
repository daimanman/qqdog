package com.man.qqdog.client.service;

import java.util.List;
import java.util.Map;

import com.man.dto.CountSingleDto;
import com.man.pageinfo.PageResult;
import com.man.qqdog.client.po.QemotCommentPo;
import com.man.qqdog.client.po.QemotCommentReplyPo;
import com.man.qqdog.client.po.QemotInfoPo;
import com.man.qqdog.client.po.QemotPicPo;
import com.man.utils.ReqParam;

public interface QemotService extends BaseService {

public  int  insertQemotInfoBatch(List<QemotInfoPo> qemotInfoList);
	
	public int insertBatchPicsByList(List<QemotPicPo> picList);
	
	public int insertQemotCommentBatch(List<QemotCommentPo> commentList);
	
	public int insertBatchCommentReplyByList(List<QemotCommentReplyPo> commentReplyList);
	
	public void saveEmotInfoMap(Map<String,Object> dataMap,long uid);
	
	public PageResult<Map<String,Object>> queryEsEmotPage(ReqParam params);
	
	public long getEmotNum(String uid);
	
	public List<Map<String,Object>> getEmotCommentList(String uid,List<String> emotIds);
	
	public List<Map<String,Object>> getEmotPicList(String uid,List<String> emotIds);
	
	
	public PageResult<Map<String,Object>> queryEsEmotPageWithCommentAndPic(ReqParam params);
	
	
	public List<CountSingleDto> countCommentMuid(ReqParam params);
}
