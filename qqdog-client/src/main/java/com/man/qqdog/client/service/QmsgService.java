package com.man.qqdog.client.service;

import java.util.List;
import java.util.Map;

import com.man.dto.CountSingleDto;
import com.man.pageinfo.PageResult;
import com.man.qqdog.client.po.QmsgInfoPo;
import com.man.qqdog.client.po.QmsgInfoReplyPo;
import com.man.utils.ReqParam;

public interface QmsgService extends BaseService {

	public int insertQmsgBatch(List<QmsgInfoPo> list);

	public int insertMsgReplyBatch(List<QmsgInfoReplyPo> datas);
	//解析Map
	public int saveMsgList(Map<String,Object> dataMap,long uid);
	
	public PageResult<Map<String,Object>> queryEsMsgPage(ReqParam params);
	
	public long getMsgNum(String uid);
	
	public List<CountSingleDto> countMsgUin(ReqParam param);
	
	
	
}
