package com.tolga.mail;

import java.io.IOException;

import javax.mail.Message;
import javax.mail.MessagingException;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tolga.initializer.SmtpClient;

/**
 * Servlet implementation class MailServlet
 */

public class MailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Session emailSession;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MailServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		emailSession = SmtpClient.getSession();
		Message message = new MimeMessage(emailSession);
		String userEmail = (String) request.getAttribute("userEmail");
		String jobTitle = (String) request.getAttribute("jobTitle");
		String companyUrl = (String) request.getAttribute("companyUrl");
		String appStatus = (String) request.getAttribute("appStatus");
		try {
			message.setFrom(new InternetAddress("e-mail"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
			message.setSubject("Testing Subject");
			message.setContent(
					"<img src='" + companyUrl + "'></img><br/><h3>" + jobTitle + "</h3><br/><p>" + appStatus + "</p>",
					"text/html; charset=utf-8");
			Transport.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
