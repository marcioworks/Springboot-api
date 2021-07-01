package com.marcio.springbootapi.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.marcio.springbootapi.services.DbTestService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DbTestService dbTestService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean InstatianteBatabase() throws ParseException {
		
		if(!"create".equals(strategy)) {
			return false;
		}
		dbTestService.instatiateTestDb();
		return true;
	}
}
