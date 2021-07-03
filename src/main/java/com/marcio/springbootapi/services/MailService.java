package com.marcio.springbootapi.services;

import org.springframework.mail.SimpleMailMessage;

import com.marcio.springbootapi.domain.Pedido;

public interface MailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendMail(SimpleMailMessage msg);
}
