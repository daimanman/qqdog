package com.man.qqdog.utilbean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.man.qqscry.util.IdWorker;

@Configuration
public class CommonUtilsBeanConfig {

	@Bean
	public IdWorker makeIdWorker(){
		IdWorker idWorker = new IdWorker(1L);
		return idWorker;
	}
}
