package com.man.qqdog.biz.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.man.constants.IdxConstant;
import com.man.es.manager.ElasticSearchManager;
import com.man.pageinfo.PageResult;
import com.man.pageinfo.QueryParams;
import com.man.qqdog.biz.es.UserInfoQueryDsl;
import com.man.qqdog.biz.manager.ImportDataThread;
import com.man.qqdog.biz.mapper.QuserInfoPoMapper;
import com.man.qqdog.client.po.QuserInfoPo;
import com.man.qqdog.client.service.QUserService;
import com.man.utils.ObjectUtil;
import com.man.utils.ReqParam;


@Service
public class QUserServiceImpl extends BaseServiceImpl implements QUserService  {

	@Autowired
	private QuserInfoPoMapper quserInfoPoMapper;
	
	@Autowired
	private ElasticSearchManager esManager;
	
	Logger logger = LoggerFactory.getLogger(QUserServiceImpl.class);
	
	@Override
	public int addQuserInfo(QuserInfoPo userInfo) {
		try {
		return quserInfoPoMapper.addQuserInfo(userInfo);
		}catch(Exception e ) {
			logger.error("addQuserInfo err uid = {} {}",userInfo.uid,e);
		}
		return 0;
	}

	@Override
	public int batchInsertUidsN(Set<Long> sets) {
		if(null == sets || sets.size() == 0) {
			return 0;
		}
		return quserInfoPoMapper.batchInsertUidsN(sets);
	}

	@Override
	public long getMaxUid() {
		return quserInfoPoMapper.getMaxUid();
	}

	@Override
	public PageResult<Map<String, Object>> queryEsUserPage(ReqParam params) {
		QueryParams esQueryParams =	UserInfoQueryDsl.parseUserListDsl(params);
		return esManager.filterPage(IdxConstant.QUSER_INFO_IDX,IdxConstant.QUSER_INFO_TYPE, esQueryParams);
	}
	
	private List<String> getMysqlCols(String index,String tableName){
		Map<String,Object> esPropsMap = esManager.getIndexFields(index, tableName);
		Set<String> esPropsKeys = esPropsMap.keySet();
		List<String> fieldList = new ArrayList<>();
		List<String> mysqlList = quserInfoPoMapper.getAllColsByTableName(tableName);
		for(String key:esPropsKeys) {
			if(mysqlList.contains(key)) {
				fieldList.add(key);
			}
		}
		return fieldList;
	}

	@Override
	public void importEsData(ReqParam params) {
		String tableName = params.getStr("tableName");
		String idxName = tableName+"_idx";
		long indexTotal = params.getLong("indexTotal");
		long pageSize = params.getLong("pageSize");
		if(pageSize == 0 ) {
			pageSize = 5000;
		}
		long totalNum = 0 ;
		if(indexTotal > 0) {
			totalNum = indexTotal;
		}else {
			totalNum = quserInfoPoMapper.getTableNum(tableName);
		}
		long totalPage = (totalNum+pageSize-1)/pageSize;
		List<String> fields = getMysqlCols(idxName, tableName);
		for(int i = 1;i<=totalPage;i++) {
			long offset = (i-1)*pageSize;
			Map<String,Object> numMap = quserInfoPoMapper.getNumPage(tableName, offset, pageSize);
			long startId = ObjectUtil.parseLong(numMap.get("startId"));
			long endId = ObjectUtil.parseLong(numMap.get("endId"));
			
			List<Map<String,Object>> datas = quserInfoPoMapper.getEsData(tableName, startId, endId,fields);
			if(null != datas && datas.size()  > 0) {
				logger.info("{} get {} from to {} ",tableName,startId,endId);
				ImportDataThread importThread = new ImportDataThread(esManager, datas, idxName, tableName);
				new Thread(importThread).start();
				if(i%4 == 0) {
					try {
						Thread.sleep(1000*2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				//esManager.multiIndex(idxName, tableName, datas,false);
			}
		}
	}

//	public void importMySqlData02(String tableName){
//		int pageSize = 10000;
//		Map<String,Object> queryParams = new HashMap<String,Object>();
//		queryParams.put("tableName", tableName);
//		Map<String,Object> idMap = infoMapper.getMaxMinId(queryParams);
//		long minId = ObjectUtil.parseLong(idMap.get("minId"));
//		long maxId = ObjectUtil.parseLong(idMap.get("maxId"));
//		String indexName = tableName+"_idx";
//		
//		long startId = minId;
//		for(;startId <= maxId;){
//			queryParams.put("startId",startId);
//			long endId = startId+pageSize-1;
//			queryParams.put("endId", endId);
//			logger.info(tableName+" get data from "+startId+" to "+endId);
//			List<Map<String,Object>> datas  = infoMapper.queryWithIdRange(queryParams);
//			if(datas == null || datas.size() == 0){
//				break;
//			}else{
//				new Thread(new EsImportDataThread(elasticSearchService,indexName,tableName, datas)).start();
//			}
//			startId = endId+1;
//			
//			try {
//				Thread.sleep(3000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			
//		}
//	}
	
}
