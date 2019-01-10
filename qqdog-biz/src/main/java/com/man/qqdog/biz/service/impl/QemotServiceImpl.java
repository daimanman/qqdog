package com.man.qqdog.biz.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.man.constants.IdxConstant;
import com.man.es.manager.ElasticSearchManager;
import com.man.pageinfo.PageResult;
import com.man.pageinfo.QueryParams;
import com.man.qq.QqConfig;
import com.man.qqdog.biz.es.EmotInfoQueryDsl;
import com.man.qqdog.biz.mapper.QemotInfoPoMapper;
import com.man.qqdog.biz.utils.QqModelTransform;
import com.man.qqdog.client.po.QemotCommentPo;
import com.man.qqdog.client.po.QemotCommentReplyPo;
import com.man.qqdog.client.po.QemotInfoPo;
import com.man.qqdog.client.po.QemotPicPo;
import com.man.qqdog.client.service.QemotService;
import com.man.utils.ObjectUtil;
import com.man.utils.ReqParam;

@Service
public class QemotServiceImpl extends BaseServiceImpl implements QemotService {

	Logger logger = LoggerFactory.getLogger(QemotServiceImpl.class);
	
	@Autowired
	private QemotInfoPoMapper qemotMapper;
	
	@Autowired
	public ElasticSearchManager esManager;
	
	@Override
	public int insertQemotInfoBatch(List<QemotInfoPo> datas) {
		if(null == datas || datas.size() == 0) {
			return 0;
		}
		return qemotMapper.insertQemotInfoBatch(datas);
	}

	@Override
	public int insertBatchPicsByList(List<QemotPicPo> datas) {
		if(null == datas || datas.size() == 0) {
			return 0;
		}
		return qemotMapper.insertBatchPicsByList(datas);
	}

	@Override
	public int insertQemotCommentBatch(List<QemotCommentPo> datas) {
		if(null == datas || datas.size() == 0) {
			return 0;
		}
		return qemotMapper.insertQemotCommentBatch(datas);
	}

	@Override
	public int insertBatchCommentReplyByList(List<QemotCommentReplyPo> datas) {
		if(null == datas || datas.size() == 0) {
			return 0;
		}
		return qemotMapper.insertBatchCommentReplyByList(datas);
	}

	@Override
	public void saveEmotInfoMap(Map<String, Object> dataMap,long uid) {
		if(null == dataMap) {
			return;
		}
		List<QemotInfoPo> qemotDatas = new ArrayList<>(QqConfig.DEFAULT_EMOT_REAL_NUM);
		List<QemotPicPo> picDatas = new ArrayList<>(50);
		List<QemotCommentPo> commentDatas = new ArrayList<>(300);
		List<QemotCommentReplyPo> replyDatas = new ArrayList<>(100);
		String uidStr = uid+"";
		List msgLists = ObjectUtil.castListObj(dataMap.get("msglist"));
		for(Object msg:msgLists) {
			Map<String,Object> emotMap = ObjectUtil.castMapObj(msg);
			long  emotId = getId();
			//基本信信息
			QemotInfoPo emotPo = QqModelTransform.converQemotInfo(emotMap);
			emotPo.id = emotId;
			emotPo.uid = uidStr;
			qemotDatas.add(emotPo);
			
			//pic
			List picLists  = ObjectUtil.castListObj(emotMap.get("pic"));
			for(Object pic:picLists) {
				long picId = getId();
				Map picMap = ObjectUtil.castMapObj(pic);
				QemotPicPo picPo = QqModelTransform.converQemotPic(picMap);
				picPo.id = picId;
				picPo.emotId = emotId;
				picPo.uid = uidStr;
				picDatas.add(picPo);
			}
			
			List commentLists = ObjectUtil.castListObj(emotMap.get("commentlist"));
			for(Object comment:commentLists) {
				long commentId = getId();
				Map commentMap = ObjectUtil.castMapObj(comment);
				QemotCommentPo commentPo  = QqModelTransform.converQemotComment(commentMap);
				commentPo.emotId = emotId;
				commentPo.id = commentId;
				commentPo.uid = uidStr;
				commentDatas.add(commentPo);
				
				//replys
				List replyLists = ObjectUtil.castListObj(commentMap.get("list_3"));
				for(Object reply:replyLists) {
					long replyId = getId();
					Map replyMap = ObjectUtil.castMapObj(reply);
					QemotCommentReplyPo replyPo = QqModelTransform.converQemotCommentReply(replyMap);
					replyPo.commentId = commentId;
					replyPo.id = replyId;
					replyPo.uid = uidStr;
					replyDatas.add(replyPo);
				}
			}
		}
		
		logger.info("uid={} emotSize={} commentSize={} replySize={} picSize={} ",uid,qemotDatas.size(),commentDatas.size(),replyDatas.size(),picDatas.size());
		try {
			insertQemotInfoBatch(qemotDatas);
		}catch(Exception e ) {
			logger.info("Exception uid={}",uid);
			e.printStackTrace();
		}
		try {
		insertQemotCommentBatch(commentDatas);
		}catch(Exception e ) {
			logger.info("Exception uid={}",uid);
			logger.error("Error {} ",e);
		}
		try {
			insertBatchPicsByList(picDatas);
		}catch(Exception e ) {
			logger.info("Exception uid={}",uid);
			logger.error("Error {} ",e);
		}
		try {
		insertBatchCommentReplyByList(replyDatas);
		}catch(Exception e ) {
			logger.info("Exception uid={}",uid);
			logger.error("Error {} ",e);
		}
	}

	@Override
	public PageResult<Map<String, Object>> queryEsEmotPage(ReqParam params) {
		QueryParams queryParams = EmotInfoQueryDsl.parseListDsl(params);
		return esManager.filterPage(IdxConstant.QEMOT_INFO_IDX,IdxConstant.QEMOT_INFO_TYPE, queryParams);
	}

	@Override
	public long getEmotNum(String uid) {
		ReqParam params = new ReqParam();
		params.put("page","1");
		params.put("pageSize","1");
		params.put("uid",uid);
		PageResult<Map<String, Object>>  pageResult = queryEsEmotPage(params);
		return pageResult.total;
	}

}
