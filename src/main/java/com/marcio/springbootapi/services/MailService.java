package com.marcio.springbootapi.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.marcio.springbootapi.domain.Client;
import com.marcio.springbootapi.domain.Pedido;

public interface MailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendMail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Pedido obj);
	
	void sendHtmlEmail(MimeMessage msg);
	
	void sendNewPasswordEmail(Client client, String newPass);
}
