package com.man.qqdog.client.service;

import java.util.List;
import java.util.Map;

import com.man.utils.ReqParam;

public interface LjErShouFangService {

	public long saveAreaInfo(ReqParam params);
	
	public long saveErHouseInfo(ReqParam params);
	
	public List<Map<String,String>> getNextDetailHids(int num);
	
	public int addHouseDetail(ReqParam params);
	
}
