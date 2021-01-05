package email;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMail {

	public static void send(String subject, String body, String user, String pass, String fileName) throws AddressException, MessagingException, IOException { // inside parenthesis
		// TODO Auto-generated method stub
		System.out.println("Starting to send...");
		Properties properties = new Properties();
		
		//properties
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		// info
		String excelArrayNew[] = new String[ExcelSheetTransfer.send(fileName).length - 3];
		
		for (int i = 0; i < excelArrayNew.length; i++) {
			excelArrayNew[i] = ExcelSheetTransfer.send(fileName)[i + 3];
		}
		
		
		
		//logging in
		Session session = Session.getInstance(properties, new Authenticator () {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, pass);
			}
			
		});
		
		//gets all the recipients
		
		int j = 0; 
		
		for (int i = 0; i < excelArrayNew.length / 3; i++) {
			MimeMessage mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress(user));
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(excelArrayNew[j + 2]));
//			System.out.println("recipient email address:" + excelArrayNew[j + 2]);
//			System.out.println("recipient first name:" + excelArrayNew[j]);
//			System.out.println("recipient last name:" + excelArrayNew[j + 1]);
			subject = subject.replaceAll("FIRSTNAME", excelArrayNew[j]);
			subject = subject.replaceAll("LASTNAME", excelArrayNew[j + 1]);
			body = body.replaceAll("FIRSTNAME", excelArrayNew[j]);
			body = body.replaceAll("LASTNAME", excelArrayNew[j + 1]);
			if (i > 0) {
				subject = subject.replaceAll(excelArrayNew[j - 3], excelArrayNew[j]);
				subject = subject.replaceAll(excelArrayNew[j - 2], excelArrayNew[j + 1]);
				body = body.replaceAll(excelArrayNew[j - 3], excelArrayNew[j]);
				body = body.replaceAll(excelArrayNew[j - 2], excelArrayNew[j + 1]);
			}
			
			mimeMessage.setSubject(subject);
			mimeMessage.setText(body);
			
			Transport.send(mimeMessage);
			j+=3;

		}
		
		System.out.println("Email successfully sent.");
		
	}

}
