package com.marcio.springbootapi.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.marcio.springbootapi.domain.Pedido;

public abstract class AbstractMailService implements MailService {
	
	
	@Value("${default.sender}")
	private String sender;

	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		SimpleMailMessage sm = PrepareSimpleMailMessageFromOrder(obj);
		sendMail(sm);
	}

	protected SimpleMailMessage PrepareSimpleMailMessageFromOrder(Pedido obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail());
		sm.setFrom(null);
		sm.setSubject("Order confirmed!, Cod: "+ obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		
		return sm;
	}
	
}
