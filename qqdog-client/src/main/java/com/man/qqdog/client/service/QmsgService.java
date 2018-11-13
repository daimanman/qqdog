package com.man.qqdog.client.service;

import java.util.List;
import java.util.Map;

import com.man.qqdog.client.po.QmsgInfoPo;
import com.man.qqdog.client.po.QmsgInfoReplyPo;

public interface QmsgService extends BaseService {

	public int insertQmsgBatch(List<QmsgInfoPo> list);

	public int insertMsgReplyBatch(List<QmsgInfoReplyPo> datas);
	//解析Map
	public int saveMsgList(Map<String,Object> dataMap,long uid);
	
}
