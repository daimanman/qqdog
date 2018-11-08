package com.man.qqdog.biz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.man.qqscry.util.IdWorker;


@Service
public class QUserServiceImpl {

	@Autowired
	private IdWorker idWorker;
	
	public long getId(){
		return idWorker.nextId();
	}
}
