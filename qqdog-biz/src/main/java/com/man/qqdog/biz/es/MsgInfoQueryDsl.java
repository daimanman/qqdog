package com.man.qqdog.biz.es;

import java.util.ArrayList;
import java.util.List;

import com.man.basequery.QueryItem;
import com.man.basequery.QueryTypeEnum;
import com.man.pageinfo.QueryParams;
import com.man.utils.ObjectUtil;
import com.man.utils.ReqParam;

public class MsgInfoQueryDsl extends BaseQueryDsl {

	
	public static QueryParams parseMsgListDsl(ReqParam params) {
		QueryParams dslParams = new QueryParams();
		// 分页信息
		dslParams.setPage(params.getInt("page"));
		dslParams.setPageSize(params.getInt("pageSize"));
		List<QueryItem> queryItems = new ArrayList<QueryItem>();

		List uids = params.getList("uids");
		if(uids.size() > 0) {
			QueryItem uidsItem = new QueryItem("uid",uids, QueryTypeEnum.IN.getType());
			queryItems.add(uidsItem);
		}
		
		// UID in 查询
		String uid = ObjectUtil.toString(params.get("uid"));
		if (!ObjectUtil.isNull(uid)) {
			QueryItem uidItem = new QueryItem("uid", uid, QueryTypeEnum.IN.getType());
			queryItems.add(uidItem);
		}
		
		String uin = params.getStr("uin");
		if(!ObjectUtil.isNull(uin)) {
			QueryItem uinItem = new QueryItem("uin",uin,QueryTypeEnum.IN.getType());
			queryItems.add(uinItem);
		}
		
		String content = params.getStr("content");
		if(!ObjectUtil.isNull(content)) {
			QueryItem contentItem = new QueryItem("html_content",content,QueryTypeEnum.LIKE.getType());
			queryItems.add(contentItem);
		}
		dslParams.setQueryItems(queryItems);
		return dslParams;
	}
}
