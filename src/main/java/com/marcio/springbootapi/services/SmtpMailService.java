package com.marcio.springbootapi.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.marcio.springbootapi.domain.Pedido;

public class SmtpMailService extends AbstractMailService{

	private static final Logger LOG = LoggerFactory.getLogger(SmtpMailService.class);
	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private JavaMailSender javaMailSneder;
	
	@Override
	public void sendMail(SimpleMailMessage msg) {
		LOG.info("Simulating  email sending...");
		mailSender.send(msg);
		LOG.info("Email sent");
		
	}
	
	
	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("Simulating  email sending...");
		javaMailSneder.send(msg);
		LOG.info("Email sent");
		
	}

}
