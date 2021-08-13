package com.db.cc.emailnotification;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class EmailSender {
	@Autowired
	private JavaMailSender javaMailSender;
	
	public EmailSender() {
		// TODO Auto-generated constructor stub
	}
	
	public void sendSimpleEmail(String to, String from, String subject, String text) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
		System.out.println("Sending Email");
		
		helper.setTo(to);
		helper.setFrom(from);
		helper.setSubject(subject);
		helper.setText(text);
		
		javaMailSender.send(message);
		
		System.out.println("Mail sent.");
		
	}
}
