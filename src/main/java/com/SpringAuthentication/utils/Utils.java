package com.SpringAuthentication.utils;

import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author Ankit Khatri
 */

/* Utility class to provide functions to the applications */
@Service
public class Utils {

	@Value("${app.baseURL}")
	private String baseUrl;

	@Value("${sender.email.name}")
	private String senderEmail;

	@Value("${sender.email.password}")
	private String senderEmailPassword;

	private static Session emailSessionInstance;

	/* Function to generate Random Token */
	public String getRandomToken() {
		return UUID.randomUUID().toString();
	}

	/*
	 * Function to return Authentication object if user logged in otherwise return
	 * null
	 */
	public Authentication getAuthentication() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null || AnonymousAuthenticationToken.class.isAssignableFrom(auth.getClass()))
			return null;
		return auth;
	}

	/**
	 * @param email
	 * @param signupVerificationKey
	 */
	public boolean sendSignupVerificationEmail(String email, String signupVerificationKey) {
		String htmlContent = "<img src='https://picsum.photos/220' class='top_img' /><h2>Welcome to KHATRI's APP..! </h2> <h3>You have successfully signedup for SpringAuthentication App. Kindly verify in order to activate your account.</h3><a href='"
				+ baseUrl + "/verify/user?email=" + email + "&vkey=" + signupVerificationKey + "&timestamp="
				+ new Date().getTime() + "&token=" + getRandomToken() + "&securePath=true"
				+ "'>Verify Now</a><p>If you have not created an account, relax no further action is required.</p>'";

		try {
			Session emailSession = getEmailSessionInstance();
			Message msg = new MimeMessage(emailSession);
			msg.setFrom(new InternetAddress("KHATRI"));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
			msg.setSubject("Account verification mail"); // to set the subject (not mandatory)
			msg.setContent(htmlContent, "text/html");
			Transport.send(msg);
		} catch (MessagingException e) {
			return false;
		}
		return true;
	}

	/* Function to Set Email Properties */
	private Session getEmailSessionInstance() {
		if (emailSessionInstance != null)
			return emailSessionInstance;

		// Getting system properties
		Properties properties = System.getProperties();
		// Setting up mail server
		properties.put("mail.smtp.auth", "true"); // for username and password authentication
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com"); // here host is gmail.com
		properties.put("mail.smtp.port", "587");
		emailSessionInstance = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// pass your email id and password here
				return new PasswordAuthentication(senderEmail, senderEmailPassword);
			}
		});

		return emailSessionInstance;
	}

	/**
	 * @param email
	 * @param forgotPasswordVerificationkey
	 * @return
	 */
	public boolean sendForgotPasswordEmail(String email, String forgotPasswordVerificationkey) {
		String htmlContent = "<img src='https://picsum.photos/220' class='top_img' /><h2>Hello From KHATRI's APP..! </h2> <h3>Click on below link to reset your password.</h3><a href='"
				+ baseUrl + "/reset-password?email=" + email + "&vkey=" + forgotPasswordVerificationkey + "&timestamp="
				+ new Date().getTime() + "&token=" + getRandomToken() + "&securePath=true"
				+ "'>Reset Password</a><p>If you have not requested for password change, kindly change your password to stay safe.</p>'";

		try {
			Session emailSession = getEmailSessionInstance();
			Message msg = new MimeMessage(emailSession);
			msg.setFrom(new InternetAddress("KHATRI"));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
			msg.setSubject("Reset Your Password - SpringAuthentication"); // to set the subject (not mandatory)
			msg.setContent(htmlContent, "text/html");
			Transport.send(msg);
		} catch (MessagingException e) {
			return false;
		}
		return true;
	}

}
