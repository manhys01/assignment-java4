package poly.manhnd.assignment.utils;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtilLocal {

	public static void sendMail(String to, String from, String subject, String body, boolean bodyIsHTML)
			throws MessagingException {
		// 1: get a mail session
		Properties properties = new Properties();
		properties.put("mail.transport.protocal", "smtp");
		properties.put("mail.smtp.host", "localhost");
		properties.put("mail.smtp.port", 25);
		Session session = Session.getDefaultInstance(properties);
		
		// 2: create a message
		MimeMessage messsage = new MimeMessage(session);
		messsage.setSubject(subject);
		if(bodyIsHTML) {
			messsage.setContent(body, "text/html");
		}else {
			messsage.setText(body);
		}
		
		// 3: address the message
		Address fromAdress = new InternetAddress(from);
		Address toAddress = new InternetAddress(to);
		messsage.setFrom(fromAdress);
		messsage.setRecipient(Message.RecipientType.TO, toAddress);
		
		// 4: send the message
		Transport.send(messsage);
	}
	
	public static void main(String[] args) {
		String to = "manhnd2261995@gmail.com";
		String from = "manhnd.695@gmail.com";
		String subject = "Test";
		String body = "Hello, I sent a email from java web app!";
		boolean bodyIsHTML = false;
		try {
			sendMail(to, from, subject, body, bodyIsHTML );
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
