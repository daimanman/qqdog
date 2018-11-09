package com.man.qqdog.biz.manager;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.man.qqdog.client.po.QsessionInfoPo;
import com.man.qqdog.client.service.QsessionService;

@Service
public class QqManager {
	
	@Autowired
	private QsessionService qsessionService;
	
	public Map<String,QsessionInfoPo> sessionMap = new HashMap<>();
	
	public LinkedList<String> msgUidsList = new LinkedList<>();
	
	public LinkedList<String> userInfoUidsList = new LinkedList<>();
	
	public LinkedList<String> photoUidsList = new LinkedList<>();
	
	public LinkedList<String> imgUidsList = new LinkedList<>();
	
	public LinkedList<String> emotUidsList = new LinkedList<>();
	
	public void addUids(String uid) {
		msgUidsList.remove(uid);
		msgUidsList.add(uid);
		
		userInfoUidsList.remove(uid);
		userInfoUidsList.add(uid);
		
		photoUidsList.remove(uid);
		photoUidsList.add(uid);
		
		imgUidsList.remove(uid);
		imgUidsList.add(uid);
		
		emotUidsList.remove(uid);
		emotUidsList.add(uid);
	}
	
	
	
	public void initSession() {
		List<QsessionInfoPo> datas = qsessionService.getAllSession();
		if(null != datas && datas.size() > 0) {
			for(QsessionInfoPo data:datas) {
				sessionMap.put(data.uid,data);
				addUids(data.uid);
			}
		}
	}
	
}
