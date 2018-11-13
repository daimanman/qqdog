package com.man.qqdog.biz.mapper;

import java.util.List;

import com.man.qqdog.client.po.QmsgInfoPo;
import com.man.qqdog.client.po.QmsgInfoReplyPo;

public interface QmsgInfoPoMapper {

	public int insertQmsgBatch(List<QmsgInfoPo> list);
	
	public int insertMsgReplyBatch(List<QmsgInfoReplyPo> datas);
}
