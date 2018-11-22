package com.man.qqdog.biz.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StartCrawlThread implements Runnable {

	public QqManager qqManager;
	
	Logger logger = LoggerFactory.getLogger(StartCrawlThread.class);
	
	public static int zanTing = 0;
	
	public StartCrawlThread(QqManager qqManager) {
		this.qqManager = qqManager;
	}
	@Override
	public void run() {
		if(qqManager != null && qqManager.startUid > 0) {
			String threadName = Thread.currentThread().getName();
			while( qqManager.userInfoUidsList.size() > 0  && (zanTing==0)) {
				if(qqManager.msgUidsList.size() == 0 || qqManager.emotUidsList.size() == 0 || qqManager.photoUidsList.size() == 0){
					logger.error(" 操作频繁 sleep 15min ");
					try {
						Thread.sleep(1000*60*15);
						qqManager.initSession();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				long nextUid = qqManager.getNextUid();
				logger.info("Thread-Name {} #start down uid={} #",threadName,nextUid);
				try {
					qqManager.downAllQqInfo(nextUid);
				}catch(Exception e ) {
					logger.error("##### Thread={}  Error nextUid={} ",threadName,nextUid,e);
				}
				if(zanTing == 1) {
					logger.error("{} ************** zanTing ****************",threadName);
				}
			}
		}
	}

}
