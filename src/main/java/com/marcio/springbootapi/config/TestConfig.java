package com.marcio.springbootapi.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.marcio.springbootapi.services.DbTestService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DbTestService dbTestService;
	@Bean
	public boolean InstatianteBatabase() throws ParseException {
		dbTestService.instatiateTestDb();
		return true;
	}
}
