package com.marcosribeiro.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.marcosribeiro.domain.Client;
import com.marcosribeiro.domain.Order;

public interface EmailService {

	void sendOrderConfirmationEmail (Order order);
	
	void sendEmail (SimpleMailMessage smg);
	
	void sendOrderConfirmationHtmlEmail(Order order);
	
	void sendHtmlEmail(MimeMessage msg);
	
	void sendNewPasswordEmail(Client client, String newPassword);
}
