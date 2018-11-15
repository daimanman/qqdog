package com.man.qqdog.client.service;

import java.util.List;
import java.util.Map;

import com.man.qqdog.client.po.QemotCommentPo;
import com.man.qqdog.client.po.QemotCommentReplyPo;
import com.man.qqdog.client.po.QemotInfoPo;
import com.man.qqdog.client.po.QemotPicPo;

public interface QemotService extends BaseService {

public  int  insertQemotInfoBatch(List<QemotInfoPo> qemotInfoList);
	
	public int insertBatchPicsByList(List<QemotPicPo> picList);
	
	public int insertQemotCommentBatch(List<QemotCommentPo> commentList);
	
	public int insertBatchCommentReplyByList(List<QemotCommentReplyPo> commentReplyList);
	
	public void saveEmotInfoMap(Map<String,Object> dataMap,long uid);
}
