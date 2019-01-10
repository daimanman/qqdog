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
import com.man.qqdog.biz.es.EmotInfoQueryDsl;
import com.man.qqdog.biz.es.MsgInfoQueryDsl;
import com.man.qqdog.biz.mapper.QmsgInfoPoMapper;
import com.man.qqdog.biz.utils.QqModelTransform;
import com.man.qqdog.client.po.QmsgInfoPo;
import com.man.qqdog.client.po.QmsgInfoReplyPo;
import com.man.qqdog.client.service.QmsgService;
import com.man.utils.ObjectUtil;
import com.man.utils.ReqParam;

@Service
@SuppressWarnings("rawtypes")
public class QmsgServiceImpl extends BaseServiceImpl implements QmsgService {

	@Autowired
	private QmsgInfoPoMapper msgInfoMapper;
	Logger logger = LoggerFactory.getLogger(QmsgServiceImpl.class);
	
	@Autowired
	public ElasticSearchManager esManager;
	
	@Override
	public int insertQmsgBatch(List<QmsgInfoPo> list) {
		if(null == list || list.size() == 0) {
			return 0;
		}
		return msgInfoMapper.insertQmsgBatch(list);
	}

	@Override
	public int insertMsgReplyBatch(List<QmsgInfoReplyPo> datas) {
		if(null == datas || datas.size() == 0) {
			return 0;
		}
		return msgInfoMapper.insertMsgReplyBatch(datas);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public int saveMsgList(Map<String, Object> dataMap,long uid) {
		List commentList = ObjectUtil.castListObj(dataMap.get("commentList"));
		if(null != commentList && commentList.size() > 0) {
			List<QmsgInfoPo> msgDatas = new ArrayList<>(commentList.size());
			List<QmsgInfoReplyPo> replyDatas = new ArrayList<>(commentList.size());
			for(Object comment:commentList) {
				Map<String,Object> msgMap = ObjectUtil.castMapObj(comment);
				String content = ObjectUtil.getStr(msgMap, "htmlContent");
				if(content.trim().length() == 0) {
					continue;
				}
				long msgId = getId();
				QmsgInfoPo infoPo = QqModelTransform.converQmsgInfo(msgMap);
				infoPo.id = msgId;
				infoPo.uid = uid+"";
				msgDatas.add(infoPo);
				
				//reply
				List replys = ObjectUtil.castListObj(msgMap.get("replyList"));
				for(Object reply:replys) {
					Map<String,Object> replyMap = ObjectUtil.castMapObj(reply);
					QmsgInfoReplyPo replyPo = QqModelTransform.converMsgReplyInfo(replyMap);
					replyPo.msgId = msgId;
					replyPo.id = getId();
					replyDatas.add(replyPo);
				}
			}
			try {
			insertQmsgBatch(msgDatas);
			}catch(Exception e) {
				logger.error("insertQmsgBatch Error {} {} ",uid,e);
			}
			try {
			insertMsgReplyBatch(replyDatas);
			}catch(Exception e) {
				logger.error("insertQmsgBatch Error {} {} ",uid,e);
			}
		}
		return 0;
	}

	@Override
	public PageResult<Map<String, Object>> queryEsMsgPage(ReqParam params) {
		QueryParams queryParams = MsgInfoQueryDsl.parseListDsl(params);
		return esManager.filterPage(IdxConstant.QMSG_INFO_IDX,IdxConstant.QMSG_INFO_TYPE, queryParams);
	}

	@Override
	public long getMsgNum(String uid) {
		ReqParam params = new ReqParam();
		params.put("uid",uid);
		params.put("page","1");
		params.put("pageSize","1");
		PageResult<Map<String,Object>> pageResult = queryEsMsgPage(params);
		return pageResult.getTotal();
	}

}
