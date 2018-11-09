package com.man.qqdog.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.man.utils.IdWorker;

public class BaseServiceImpl {

	@Autowired
	private IdWorker idWorker;
	
	public long getId() {
		return idWorker.nextId();
	}
}
