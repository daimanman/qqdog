package com.man.qqdog.biz.mapper;

import java.util.List;

public interface MtBtPoCustomMapper extends MtBtPoMapper {

	
	public int getByHash(String hash);
	
	public List<String> getClearDatas();
	
	public int deleteBatch(List<Long> ids);
	
	
	
}
