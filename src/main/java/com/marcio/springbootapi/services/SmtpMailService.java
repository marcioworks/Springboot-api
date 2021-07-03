package com.marcio.springbootapi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SmtpMailService extends AbstractMailService{

	private static final Logger LOG = LoggerFactory.getLogger(SmtpMailService.class);
	
	@Autowired
	private MailSender mailSender;
	@Override
	public void sendMail(SimpleMailMessage msg) {
		LOG.info("Simulating  email sending...");
		mailSender.send(msg);
		LOG.info("Email sent");
		
	}

}
