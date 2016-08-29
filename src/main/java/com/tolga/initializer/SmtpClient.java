package com.tolga.initializer;

import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.servlet.ServletContext;

public class SmtpClient {
	private Properties props = new Properties();
	private static Session session;

	public SmtpClient(ServletContext context) {
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		String smtpEmail = context.getInitParameter("SmtpEmail");
		String smtpPass = context.getInitParameter("smtpPass");
		SmtpClient.session = authenticate(smtpEmail, smtpPass);
	}

	private Session authenticate(String email, String password) {
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email, password);
			}
		});
		return session;
	}

	public void init() {

	}

	public static Session getSession() {
		return session;
	}

	public SmtpClient() {

	}
}
