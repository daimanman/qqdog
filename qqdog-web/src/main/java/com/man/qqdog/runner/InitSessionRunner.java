package com.man.qqdog.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.man.qqdog.biz.manager.QqManager;
import com.man.qqdog.client.service.QUserService;

@Component
@Order(1)
public class InitSessionRunner implements ApplicationRunner {

	Logger logger = LoggerFactory.getLogger(InitSessionRunner.class);
	
	@Autowired
	private QqManager qqManager;
	
	@Autowired
	private QUserService quserService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		logger.info("############### init session uids ###################");
		qqManager.initSession();
		long startUid = quserService.getMaxUid();
		if(0 == startUid) {
			logger.error("########未设置起始uid####,请在 quser_info 表中设置起始uid,或者调用接口设置");
		}
	}

}
