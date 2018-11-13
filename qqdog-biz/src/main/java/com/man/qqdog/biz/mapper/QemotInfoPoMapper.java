package com.man.qqdog.biz.mapper;

import java.util.List;

import com.man.qqdog.client.po.QemotCommentPo;
import com.man.qqdog.client.po.QemotCommentReplyPo;
import com.man.qqdog.client.po.QemotInfoPo;
import com.man.qqdog.client.po.QemotPicPo;


public interface QemotInfoPoMapper {
	
	public  int  insertQemotInfoBatch(List<QemotInfoPo> qemotInfoList);
	
	public int insertBatchPicsByList(List<QemotPicPo> picList);
	
	public int insertQemotCommentBatch(List<QemotCommentPo> commentList);
	
	public int insertBatchCommentReplyByList(List<QemotCommentReplyPo> commentReplyList);
	
}
