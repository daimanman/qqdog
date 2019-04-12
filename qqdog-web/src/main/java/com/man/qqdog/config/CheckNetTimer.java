package com.man.qqdog.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.man.qqdog.biz.manager.MtManager;
import com.man.qqdog.client.dto.MtNextDetailDto;
import com.man.qqdog.client.po.MtMeishiDealPo;

//@Component
public class CheckNetTimer {

	@Autowired
	public MtManager mtManager;
	
	Logger logger = LoggerFactory.getLogger(CheckNetTimer.class);
    //@Scheduled(cron = "0/30 * * * * ?")
    public void timerToNow(){
      long now = System.currentTimeMillis();
      long last = mtManager.urlLog.lasttime;
      long dur = now - last;
      
      logger.info("test dur is {} ms logurl={}",dur,JSON.toJSONString(mtManager.urlLog));
      if(dur >= 1000*60*10) {
    	  try {
    		  logger.error("time out 10min reopen url {} ",mtManager.urlLog.lasturl);
			mtManager.reopenUrlInChrome(mtManager.urlLog.lasturl);
		} catch (IOException e) {
			e.printStackTrace();
		}
      }
    }
    
    @Scheduled(cron = "0/13 * * * * ?")
    public void checkMeishiDetail() throws IOException{
      long now = System.currentTimeMillis();
      logger.info("check detail opened {}",JSON.toJSONString(mtManager.nextList));
      for(MtNextDetailDto detail : mtManager.nextList) {
    	  if(now - detail.start >= 10*1000) {
    		  logger.info("reopen poiId={} ",detail.poiId);
    		  mtManager.reopenMeishiDetail(detail.poiId);
    		  mtManager.nextList.remove(detail);
    	  }
      }
    }

}
