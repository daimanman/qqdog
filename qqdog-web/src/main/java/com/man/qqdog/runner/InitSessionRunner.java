package com.man.qqdog.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.man.qqdog.biz.manager.QqManager;

@Component
@Order(1)
public class InitSessionRunner implements ApplicationRunner {

	Logger logger = LoggerFactory.getLogger(InitSessionRunner.class);
	
	@Autowired
	private QqManager qqManager;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		logger.info("############### init session uids ###################");
	 qqManager.initSession();
	}

}
