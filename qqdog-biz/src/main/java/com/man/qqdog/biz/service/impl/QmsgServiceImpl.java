package com.man.qqdog.biz.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.man.qqdog.biz.mapper.QmsgInfoPoMapper;
import com.man.qqdog.biz.utils.QqModelTransform;
import com.man.qqdog.client.po.QmsgInfoPo;
import com.man.qqdog.client.po.QmsgInfoReplyPo;
import com.man.qqdog.client.service.QmsgService;
import com.man.utils.ObjectUtil;

@Service
@SuppressWarnings("rawtypes")
public class QmsgServiceImpl extends BaseServiceImpl implements QmsgService {

	@Autowired
	private QmsgInfoPoMapper msgInfoMapper;
	
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
			insertQmsgBatch(msgDatas);
			insertMsgReplyBatch(replyDatas);
		}
		return 0;
	}

}
