package com.saurabh;

import java.io.File;
import java.nio.file.Files;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class App 
{
    public static void main( String[] args )
    {
    	System.out.println("Preparing for sending email...");
    	
        String message = "Dear Saurabh, This is an inspection email. Please do not respond to this email";
        String subject = "Inspection Email : Confirmation";
        String to = "Provide here reciever email Id";
        String from = "Provide here sender email Id";
        
        sendEmail(message, subject, to, from);
        
        //sendAttach(message, subject, to, from);
    }

    //Method for sending the attachment to the email
    private static void sendAttach(String message, String subject, String to, String from) {
		// TODO Auto-generated method stub
    	
    	//variable for gmail
    			String host = "smtp.gmail.com";
    			
    			//get the system properties
    			Properties properties =  System.getProperties();
    			System.out.println("Properties: " +properties);
    			
    			//setting important information to properties object
    			
    			//host set
    			properties.put("mail.smtp.host", host);
    			properties.put("mail.smtp.port", "465");
    			properties.put("mail.smtp.ssl.enable", "true");
    			properties.put("mail.smtp.auth", "true");
    			
    			// Step 1: to get the session object
    			Session session = Session.getInstance(properties, new Authenticator() {

    				@Override
    				protected PasswordAuthentication getPasswordAuthentication() {
    					// TODO Auto-generated method stub
    					return new PasswordAuthentication("Provide here sender email Id", "Provide here email pass key for authorization");
    				}
    				
    			});
    			
    			session.setDebug(true);
    			
    			// Step 2: compose the message [text, multimedia]
    			MimeMessage m = new MimeMessage(session);
    			
    			try {
    				//from email
    				m.setFrom(from);
    				
    				//adding recipient to message
    				m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
    				
    				//adding subject to message
    				m.setSubject(subject);
    				
    				//set attachment 
    
    				//file path
    				String path = "C:/Users/hp/Desktop/My_Updated_Resume.pdf";
    				
    				MimeMultipart mimeMultipart = new MimeMultipart();
    				
    				//file
    				MimeBodyPart fileMime = new MimeBodyPart();
    				
    				//text
    				MimeBodyPart textMime = new MimeBodyPart();
    				
    				try {
    					textMime.setText(message);
    					
    					File file = new File(path);
    					fileMime.attachFile(file);
    					
    					mimeMultipart.addBodyPart(textMime);
    					mimeMultipart.addBodyPart(fileMime);
    					
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
    				
    				m.setContent(mimeMultipart);
    				
    				//send
    				//Step 3: send using trasport
    				Transport.send(m);
    				
    			} catch (MessagingException e) {
    				e.printStackTrace();
    			}
    			
    			System.out.println("Email sent successfully with attachment!!");
		
	}

	//Method which is responsible for sending the email...
	private static void sendEmail(String message, String subject, String to, String from) {
		
		//variable for gmail
		String host = "smtp.gmail.com";
		
		
		//get the system properties
		Properties properties =  System.getProperties();
		System.out.println("Properties: " +properties);
		
		//setting important information to properties object
		
		//host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		// Step 1: to get the session object
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("Provide here sender email Id", "Provide here email pass key for authorization");
			}
			
		});
		
		session.setDebug(true);
		
		// Step 2: compose the message [text, multimedia]
		MimeMessage m = new MimeMessage(session);
		
		try {
			//from email
			m.setFrom(from);
			
			//adding recipient to message
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			//adding subject to message
			m.setSubject(subject);
			
			//adding text to message
			m.setText(message);
			
			//send the email
			// Step 3: send the email using transport method
			Transport.send(m);
			
			System.out.println("Email sent successfully!!");
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
}
