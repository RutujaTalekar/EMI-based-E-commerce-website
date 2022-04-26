package com.lti.fg.services;

import javax.annotation.Resource;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class SendMailService {

	
	@Resource
	private MailSender mailSender;
	
	@Resource
	private SimpleMailMessage message;
	
	public void send(String emailId,String subject, String text)
	{
		
		message.setTo(emailId); //set a proper recipient of the mail
		message.setSubject(subject);
		message.setText(text);
		mailSender.send(message);
	}
}
