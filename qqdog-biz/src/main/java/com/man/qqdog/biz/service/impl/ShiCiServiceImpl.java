package com.man.qqdog.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.man.qqdog.biz.mapper.AuthorInfoPoCustomMapper;
import com.man.qqdog.biz.mapper.ShiInfoPoCustomMapper;
import com.man.qqdog.client.po.AuthorInfoPo;
import com.man.qqdog.client.po.ShiInfoPo;
import com.man.qqdog.client.service.ShiCiService;
import com.man.utils.IdWorker;
import com.man.utils.ReqParam;

@Service
public class ShiCiServiceImpl implements ShiCiService {

	@Autowired
	public ShiInfoPoCustomMapper shiInfoMapper;
	
	@Autowired
	public AuthorInfoPoCustomMapper authorInfoMapper;
	
	@Autowired
	public IdWorker idWorker;
	
	private AuthorInfoPo converAuthor(ReqParam reqParam) {
		AuthorInfoPo po = new AuthorInfoPo();
		po.js  = reqParam.getStr("js");
		po.name = reqParam.getStr("name");
		po.qs = reqParam.getStr("qs");
		po.tag = reqParam.getStr("tag");
		po.url = reqParam.getStr("url");
		po.urlMd5 = reqParam.getStr("urlMd5");
		po.chaoDai = reqParam.getStr("chaoDai");
		po.topicType = reqParam.getInt("topicType");
		return po;
	}
	@Override
	public int saveAuthor(ReqParam reqParam) {
		String urlMd5 = reqParam.getStr("urlMd5");
		AuthorInfoPo po = authorInfoMapper.getByMd5Code(urlMd5);
		if(null != po) {
			return 0;
		}
		po = converAuthor(reqParam);
		po.id = idWorker.nextId();
		return authorInfoMapper.insertSelective(po);
	}

	public ShiInfoPo converShiInfo(ReqParam reqParams) {
		ShiInfoPo po = new ShiInfoPo();
		String type = reqParams.getStr("type");
		po.urlMd5  = reqParams.getStr("urlMd5");
		po.topicType = reqParams.getInt("topicType");
		
		
		if("info".equals(type)) {
			po.authorMd5 = reqParams.getStr("authorMd5");
			po.authorName = reqParams.getStr("authorName");
			po.js = reqParams.getStr("js");
			po.ns = reqParams.getStr("ns");
			po.title = reqParams.getStr("title");
			po.url = reqParams.getStr("url");
			int totalNum = reqParams.getInt("totalNum");
			if(totalNum > 0) {
				authorInfoMapper.updateNumByMd5Code(totalNum,po.authorMd5);
			}
			String content = reqParams.getStr("content");
			if(content.trim().length() > 0) {
				po.content = content;
			}
		}
		
		if("detail".equals(type)) {
			po.content = reqParams.getStr("content");
		}
		
		return po;
	}
	
	@Override
	public int saveShiInfo(ReqParam reqParam) {
		String urlMd5 = reqParam.getStr("urlMd5");
		ShiInfoPo po = shiInfoMapper.getByMd5Code(urlMd5);
		ShiInfoPo newPo = converShiInfo(reqParam);
		if(po != null ) {
			newPo.id = po.id;
			shiInfoMapper.updateSelective(newPo);
		}else {
			newPo.id = idWorker.nextId();
			shiInfoMapper.insertSelective(newPo);
		}
		return 0;
	}

}
