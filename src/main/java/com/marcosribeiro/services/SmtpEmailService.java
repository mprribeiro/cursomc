package com.marcosribeiro.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpEmailService extends AbstractEmailService {

	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	private static final Logger log = LoggerFactory.getLogger(SmtpEmailService.class);
		
	@Override
	public void sendEmail(SimpleMailMessage sm) {
		log.info("Sending email... ");
		mailSender.send(sm);
		log.info("Email has been sent!");		
	}

	@Override
	public void sendHtmlEmail(MimeMessage mm) {
		log.info("Sending email... ");
		javaMailSender.send(mm);
		log.info("Email has been sent!");		
	}

}
