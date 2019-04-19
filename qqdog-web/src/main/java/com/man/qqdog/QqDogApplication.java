package com.man.qqdog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(
		{
			"com.man.qqdog.biz.mapper"
		}
		) 
@EnableScheduling
public class QqDogApplication {

	public static void main(String[] args){
		SpringApplication app = new SpringApplication(QqDogApplication.class);
		app.run(args);
	}
}
