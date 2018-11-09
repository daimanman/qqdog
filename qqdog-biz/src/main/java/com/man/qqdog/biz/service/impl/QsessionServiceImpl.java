package com.man.qqdog.biz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.man.qqdog.biz.mapper.QsessionInfoPoMapper;
import com.man.qqdog.client.po.QsessionInfoPo;
import com.man.qqdog.client.service.QsessionService;


@Service
public class QsessionServiceImpl implements QsessionService {

	Logger logger = LoggerFactory.getLogger(QsessionServiceImpl.class);
	
	@Autowired
	private QsessionInfoPoMapper qsessionInfoPoMapper;
	
	//缓存会话
	private Map<String,QsessionInfoPo> sessionMap = new HashMap<>();
	
	
	public int addQsessionInfo(QsessionInfoPo info) {
		qsessionInfoPoMapper.deleteByUid(info.uid);
		qsessionInfoPoMapper.addQsessionInfo(info);
		logger.info("add session uid={}",info.uid);
		return 1;
	}

	@Override
	public int deleteByUid(String uid) {
		return qsessionInfoPoMapper.deleteByUid(uid);
	}

	@Override
	public List<QsessionInfoPo> getAllSession() {
		List<QsessionInfoPo> datas = qsessionInfoPoMapper.getSessions(null);
		if(null != datas && datas.size() > 0) {
			for(QsessionInfoPo data:datas) {
				try {
				data.cookieMap = JSON.parseObject(data.cookie,new TypeReference<Map<String,String>>(){} );
				data.paramsMap = JSON.parseObject(data.params,new TypeReference<Map<String,Object>>(){});
				data.cookie=null;
				data.params= null;
				}catch(Exception e) {
					logger.info("transform session error {} ",e);
					continue;
				}
			}
		}
		return datas;
	}

	@Override
	public QsessionInfoPo getByUid(String uid) {
		List<QsessionInfoPo> datas = qsessionInfoPoMapper.getSessions(null);
		if(datas != null && datas.size() > 0) {
			return datas.get(0);
		}
		return null;
	}

	@Override
	public int updateQsessionInfo(QsessionInfoPo info) {
		return qsessionInfoPoMapper.updateSessionInfoSelective(info);
	}

}
