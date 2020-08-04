package com.log;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMailer {  

	public static void sendMail(String tomail,String subject,String otp){  
	 
  
		final String uname="musicstreamer.india@gmail.com";
		final String pass="Bktktb@123";
		Properties props=new Properties();
		props.put("mail.smtp.auth",true);
		props.put("mail.smtp.starttls.enable",true);
		props.put("mail.smtp.host","smtp.gmail.com");
		props.put("mail.smtp.port","25");
		
		Session session=Session.getInstance(props,
				new javax.mail.Authenticator() {
			@Override
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(uname,pass);
			}
		});
		
		try {
			
			Message message=new MimeMessage(session);
			message.setFrom(new InternetAddress(tomail));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(tomail));
			MimeBodyPart textpart = new MimeBodyPart();
			Multipart multipart= new MimeMultipart();
			textpart.setText(otp);
			multipart.addBodyPart(textpart);
			message.setContent(multipart);
			message.setSubject(subject);
			Transport.send(message);
			
			
		}
		catch(Exception e) {
			System.out.print("Failed");
			e.printStackTrace();
		}
	
		
	}
}  