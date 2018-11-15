package com.man.qqdog.biz.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StartCrawlThread implements Runnable {

	public QqManager qqManager;
	
	Logger logger = LoggerFactory.getLogger(StartCrawlThread.class);
	
	public StartCrawlThread(QqManager qqManager) {
		this.qqManager = qqManager;
	}
	@Override
	public void run() {
		if(qqManager != null && qqManager.startUid > 0) {
			while( qqManager.userInfoUidsList.size() > 0 ) {
				long nextUid = qqManager.getNextUid();
				logger.info("Thread-Name {} #start down uid={} #",Thread.currentThread().getName(),nextUid);
				qqManager.downAllQqInfo(nextUid);
			}
		}
	}

}
