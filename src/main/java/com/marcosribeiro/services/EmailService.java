package com.marcosribeiro.services;

import org.springframework.mail.SimpleMailMessage;

import com.marcosribeiro.domain.Order;

public interface EmailService {

	void sendOrderConfirmationEmail (Order order);
	
	void sendEmail (SimpleMailMessage smg);
}
