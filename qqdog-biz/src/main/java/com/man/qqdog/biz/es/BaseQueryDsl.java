package com.man.qqdog.biz.es;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.man.pageinfo.SortParams;
import com.man.utils.ObjectUtil;

public class BaseQueryDsl {
	protected static List<SortParams> parseSortParams(Map<String, Object> bizParams) {
		String sortField = ObjectUtil.toString(bizParams.get("sidx"));
		String sort = ObjectUtil.toString(bizParams.get("sord"));
		List<SortParams> sorts = new ArrayList<SortParams>();
		sorts.add(new SortParams(sortField, sort));
		return sorts;
	}
}
