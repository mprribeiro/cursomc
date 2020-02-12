package com.marcosribeiro.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.marcosribeiro.domain.Client;
import com.marcosribeiro.domain.Order;

public abstract class AbstractEmailService implements EmailService {
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail (Order order) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromOrder(order);
		sendEmail(sm);
	}
	
	protected SimpleMailMessage prepareSimpleMailMessageFromOrder (Order order) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(order.getClient().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Order confirmed! Code: " + order.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(order.toString());
		return sm;
	}
	
	protected String htmlFromTemplateOrder(Order order) {
		Context context = new Context();
		context.setVariable("order", order);
		return templateEngine.process("email/OrderConfirmation", context);
	}
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Order order) {
		try {
			MimeMessage mm = prepareMimeMessageFromOrder(order);
			sendHtmlEmail(mm);
		}
		catch (MessagingException e) {
			sendOrderConfirmationEmail(order);
		}
	}
	
	protected MimeMessage prepareMimeMessageFromOrder (Order order) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(order.getClient().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Congrats, your order has been confirmed! Code: " + order.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplateOrder(order), true);
		return mimeMessage;
	}
	
	@Override
	public void sendNewPasswordEmail(Client client, String newPassword) {
		SimpleMailMessage sm = prepareNewPasswordEmail(client, newPassword);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareNewPasswordEmail(Client client, String newPassword) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(client.getEmail());
		sm.setFrom(sender);
		sm.setSubject("New Password Solicitation");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("New Password: " + newPassword);
		return sm;
	}
	 

}
