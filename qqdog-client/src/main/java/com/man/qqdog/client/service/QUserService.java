package com.man.qqdog.client.service;

import java.util.Map;
import java.util.Set;

import com.man.pageinfo.PageResult;
import com.man.qqdog.client.po.QuserInfoPo;
import com.man.utils.ReqParam;

public interface QUserService extends BaseService {

public int addQuserInfo(QuserInfoPo userInfo);
	
	public int batchInsertUidsN( Set<Long> sets);
	
	public long getMaxUid();
	
	public PageResult<Map<String,Object>> queryEsUserPage(ReqParam params);
	
	public void importEsData(ReqParam params);
	
	public Map<String,Object> getEsUserInfo(String uid);
	
}
