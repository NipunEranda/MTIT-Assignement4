package com.sliit.abc.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	//private static final String CONFIRMLINK = Main.siteHost + "/SPMS_API/api/user/emailConfirmation/";
	
	public static void sendMail(String to, String content, String subject) {
		// Recipient's email ID needs to be mentioned.
		// String to = "namarasekarax@gmail.com";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// Get the Session object.// and pass username and password
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("spmsystemSL@gmail.com", "gpwdyfmkichabvth");

			}

		});

		// Used to debug SMTP issues
		session.setDebug(true);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress("spmsystemSL@gmail.com"));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject(subject.toString());

			// Now set the actual message
			message.setContent(content, "text/html");

			System.out.println("sending...");
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
			System.out.println("Something went wrong");
		}
	}

	public static void sendConfirmationCode(String to, String code, String subject) {
		try {
		String content = "<div style='margin:auto;text-align:center;left:50%;right:50%;float:none;border:0.5px;border-color:black;border-style: solid;width:50%;padding:20px;color:black;'>"
				+ "<h1>SPMS account confirmation code</h1>"
				+ "<h4>Please enter the following security code or Activation link to confirm your account.</h4><br/>"
				+ "<div><label style='font-size:25px'>" + code + "</label></div>" + "</div></br>";

		sendMail(to, content, subject.toString());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*public static void sendConfirmationCode(String to, String code, String subject) {
		String content = "<div style='margin:auto;text-align:center;left:50%;right:50%;float:none;border:0.5px;border-color:black;border-style: solid;width:50%;padding:20px;color:black;'>"
				+ "<h1>SPMS Password Reset Code</h1>"
				+ "<h4>Please enter the following security code and confirm your Account.</h4><br/>"
				+ "<div><label style='font-size:25px'>Confirmation Code : " + code + "</label></div>" + "</div>";

		sendMail(to, content, subject.toString());
	}*/
	
	public static void sendNewPassword(String to, String code, String subject) {
		String content = "<div style='margin:auto;text-align:center;left:50%;right:50%;float:none;border:0.5px;border-color:black;border-style: solid;width:50%;padding:20px;color:black;'>"
				+ "<h1>SPMS Password Reset Code</h1>"
				+ "<h4>Please use the following security password to login and reset to your new password.</h4><br/>"
				+ "<div><label style='font-size:25px'>" + code + "</label></div>" + "</div>";

		sendMail(to, content, subject.toString());
	}

}
