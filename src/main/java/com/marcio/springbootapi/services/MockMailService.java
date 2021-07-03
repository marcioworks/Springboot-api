package com.marcio.springbootapi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockMailService extends AbstractMailService{

	private static final Logger LOG = LoggerFactory.getLogger(MockMailService.class);
	
	@Override
	public void sendMail(SimpleMailMessage msg) {
		LOG.info("Simulating  email sending...");
		LOG.info(msg.toString());
		LOG.info("Email sent");
	}

}
