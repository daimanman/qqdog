package com.man.qqdog.biz.es;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.man.basequery.QueryItem;
import com.man.basequery.QueryTypeEnum;
import com.man.pageinfo.QueryParams;
import com.man.utils.ObjectUtil;
import com.man.utils.ReqParam;

/**
 * 查询解析 类似Mapper文件的地位 将前端的参数构造成符合Elasticsearch 规范的DSL查询语句
 * 
 * @author daixm
 *
 */
public class QphotoInfoQueryDsl extends BaseQueryDsl {

	static Map<Integer,String> likeMap = new HashMap<>();
	static {
		likeMap.put(0,QueryTypeEnum.LIKE.getType());
		likeMap.put(1,QueryTypeEnum.IN.getType());
	}
	/**
	 * Photo
	 * @param bizParams
	 * @return
	 */
	public static QueryParams parsePhotoListDsl(ReqParam bizParams) {
		QueryParams dslParams = new QueryParams();
		// 分页信息
		dslParams.setPage(ObjectUtil.parseInt(bizParams.get("page")));
		dslParams.setPageSize(ObjectUtil.parseInt(bizParams.get("pageSize")));

		List<QueryItem> queryItems = new ArrayList<QueryItem>();
		int likeFlag = bizParams.getInt("likeFlag");
		
		// 业务查询字段
		String desc = ObjectUtil.toString(bizParams.get("desc"));
		if (!ObjectUtil.isNull(desc)) {
			QueryItem descItem = new QueryItem("desc", desc, likeMap.get(likeFlag));
			queryItems.add(descItem);
		}

		String name = ObjectUtil.toString(bizParams.get("name"));
		if (!ObjectUtil.isNull(name)) {
			QueryItem nameItem = new QueryItem("name", name, likeMap.get(likeFlag));
			queryItems.add(nameItem);
		}

		String uid = ObjectUtil.toString(bizParams.get("uid"));
		if (!ObjectUtil.isNull(uid)) {
			QueryItem uidItem = new QueryItem("uid", uid, QueryTypeEnum.IN.getType());
			queryItems.add(uidItem);

		}


		int allowAccess = ObjectUtil.parseInt(bizParams.get("allowAccess"),-10);
		if(allowAccess >= 0 ){
			QueryItem sexItem = new QueryItem("allow_access", allowAccess, QueryTypeEnum.IN.getType());
			queryItems.add(sexItem);
		}
		dslParams.setQueryItems(queryItems);
		dslParams.setSorts(parseSortParams(bizParams));
		return dslParams;
	}
	
	public static QueryParams parsePhotoImgListDsl(Map<String, Object> bizParams) {
		QueryParams dslParams = new QueryParams();
		// 分页信息
		dslParams.setPage(ObjectUtil.parseInt(bizParams.get("page")));
		dslParams.setPageSize(ObjectUtil.parseInt(bizParams.get("pageSize")));

		List<QueryItem> queryItems = new ArrayList<QueryItem>();
		

		String uid = ObjectUtil.toString(bizParams.get("photoId"));
		if (!ObjectUtil.isNull(uid)) {
			QueryItem uidItem = new QueryItem("photo_id", uid, QueryTypeEnum.IN.getType());
			queryItems.add(uidItem);
		}

		dslParams.setQueryItems(queryItems);
		dslParams.setSorts(parseSortParams(bizParams));
		return dslParams;
	}
}
