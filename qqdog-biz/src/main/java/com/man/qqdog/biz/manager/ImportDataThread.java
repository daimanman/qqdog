package com.man.qqdog.biz.manager;

import java.util.List;
import java.util.Map;

import com.man.es.manager.ElasticSearchManager;

public class ImportDataThread implements Runnable{

	private ElasticSearchManager esManager;
	
	private List<Map<String,Object>> datas;
	
	private String idxName;
	
	private String typeName;
	
	
	
	
	public ImportDataThread(ElasticSearchManager esManager, List<Map<String, Object>> datas, String idxName,
			String typeName) {
		this.esManager = esManager;
		this.datas = datas;
		this.idxName = idxName;
		this.typeName = typeName;
	}

	
	@Override
	public void run() {
		esManager.multiIndex(idxName, typeName, datas, false);
	}

}
