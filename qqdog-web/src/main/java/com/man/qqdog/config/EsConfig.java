package com.man.qqdog.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.man.es.manager.ElasticSearchManager;

@Configuration
@PropertySource(value = { "classpath:/es.properties"})
public class EsConfig {

	@Value("${cluster.hosts}")
	private String hosts;
	
	@Value("${cluster.name}")
	private String clusterName;
	
	Logger logger = LoggerFactory.getLogger(EsConfig.class);
	
	
	@Bean
	public ElasticSearchManager createEsManager() {
		ElasticSearchManager esManager = new ElasticSearchManager();
		esManager.setClusterName(clusterName);
		esManager.setHosts(hosts);
		//esManager.initClient();
		logger.info("ES HOSTS={} clusterName={} ",hosts,clusterName);
		return esManager;
	}
}
