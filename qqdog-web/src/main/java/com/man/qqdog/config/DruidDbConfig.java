package com.man.qqdog.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;
import com.man.utils.ObjectUtil;

@Configuration
public class DruidDbConfig {
	Logger logger = LoggerFactory.getLogger(DruidDbConfig.class);
	@Value("${spring.datasource.druid.url}")  
    private String dbUrl;  
      
    @Value("${spring.datasource.druid.username}")  
    private String username;  
      
    @Value("${spring.datasource.druid.password}")  
    private String password;  
      
    @Value("${spring.datasource.druid.driverClassName}")  
    private String driverClassName;  
      
    @Value("${spring.datasource.druid.initialSize}")  
    private int initialSize;  
      
    @Value("${spring.datasource.druid.minIdle}")  
    private int minIdle;  
      
    @Value("${spring.datasource.druid.maxActive}")  
    private int maxActive;  
      
    @Value("${spring.datasource.druid.maxWait}")  
    private int maxWait;  
      
    @Value("${spring.datasource.druid.timeBetweenEvictionRunsMillis}")  
    private int timeBetweenEvictionRunsMillis;  
      
    @Value("${spring.datasource.druid.minEvictableIdleTimeMillis}")  
    private int minEvictableIdleTimeMillis;  
      
    @Value("${spring.datasource.druid.validationQuery}")  
    private String validationQuery;  
      
    @Value("${spring.datasource.druid.testWhileIdle}")  
    private boolean testWhileIdle;  
      
    @Value("${spring.datasource.druid.testOnBorrow}")  
    private boolean testOnBorrow;  
      
    @Value("${spring.datasource.druid.testOnReturn}")  
    private boolean testOnReturn;  
      
    @Value("${spring.datasource.druid.poolPreparedStatements}")  
    private boolean poolPreparedStatements;  
      
    @Value("${spring.datasource.druid.maxPoolPreparedStatementPerConnectionSize}")  
    private int maxPoolPreparedStatementPerConnectionSize;  
      
    @Value("${spring.datasource.druid.filters}")  
    private String filters;  
      
    @Value("${spring.datasource.druid.connectionProperties}")  
    private String connectionProperties;  
    
    @Value("${spring.datasource.druid.dbname}") 
    private String dbname;
    
    @Value("${spring.datasource.druid.dbip}") 
    private String dbip;
    
    @Value("${spring.datasource.druid.dbport}") 
    private String dbport;
      
    @Bean     //声明其为Bean实例  
    @Primary  //在同样的DataSource中，首先使用被标注的DataSource  
    public DataSource dataSource(){  
        DruidDataSource datasource = new DruidDataSource();
        dbname = ObjectUtil.toString(System.getenv("dbname"),dbname);
        dbip = ObjectUtil.toString(System.getenv("dbip"),dbip);
        logger.info("System.getenv(\"dbname\")={}",System.getenv("dbname"));
        String mysqlurl = String.format("jdbc:mysql://%s:%s/%s",dbip,dbport,dbname);
        logger.info("dbip={},dbname={},dbport={} url={}",dbip,dbname,dbport,mysqlurl);
        datasource.setUrl(mysqlurl);  
        datasource.setUsername(username);  
        datasource.setPassword(password);  
        datasource.setDriverClassName(driverClassName);  
          
        //configuration  
        datasource.setInitialSize(initialSize);  
        datasource.setMinIdle(minIdle);  
        datasource.setMaxActive(maxActive);  
        datasource.setMaxWait(maxWait);  
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);  
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);  
        datasource.setValidationQuery(validationQuery);  
        datasource.setTestWhileIdle(testWhileIdle);  
        datasource.setTestOnBorrow(testOnBorrow);  
        datasource.setTestOnReturn(testOnReturn);  
        datasource.setPoolPreparedStatements(poolPreparedStatements);  
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);  
//        try {  
//            datasource.setFilters(filters);  
//        } catch (SQLException e) {  
//            logger.error("druid configuration initialization filter", e);  
//        }  
//        datasource.setConnectionProperties(connectionProperties);  
          
        return datasource;  
    } 
}
