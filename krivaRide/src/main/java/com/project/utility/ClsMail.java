package com.project.utility;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class ClsMail {
	/*
	 * Creating javax.mail.Session object
	 * 
	 * Creating javax.mail.internet.MimeMessage object, we have to set different properties in this object such as recipient email address,
	 *  Email Subject, Reply-To email, email body, attachments etc.
	 *  
	 * Using javax.mail.Transport to send the email message.
	 */
	private static Session objSes;
	private static MimeMessage mimeMsg;
	private static boolean isMailSent=false;
	
	public static boolean mailSent(String mailTo,String mailSubject,String mailBody) {
		if(getSession()!=null)
		{
			mimeMsg=new MimeMessage(getSession());
			try {
				//add message headers
				mimeMsg.addHeader("Content-type", "text/HTML; charset=UTF-8");
				mimeMsg.addHeader("format", "flowed");
				mimeMsg.addHeader("Content-Transfer-Encoding", "8bit");
				
				//set 
				mimeMsg.setFrom(new InternetAddress("kriva1803@gmail.com"));

				mimeMsg.setReplyTo(InternetAddress.parse("kriva1803@gmail.com", false));

				mimeMsg.setSubject(mailSubject, "UTF-8");

				mimeMsg.setContent(mailBody, "text/html");

				mimeMsg.setSentDate(new Date());

				mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo, false));
			     System.out.println("Message is ready");
			     Transport.send(mimeMsg);  
			     isMailSent=true;
				
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return isMailSent;
	}
	
	private static Session getSession() {			
			System.out.println("TLSEmail Start");
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
			props.put("mail.smtp.port", "587"); //TLS Port
			props.put("mail.smtp.auth", "true"); //enable authentication
			props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
		
        //create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("kriva1803@gmail.com","pooja1803");
			}
		};
		 objSes = Session.getInstance(props, auth);
		 return objSes;
	}
	
}
