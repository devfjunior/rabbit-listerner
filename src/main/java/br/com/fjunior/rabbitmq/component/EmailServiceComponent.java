package br.com.fjunior.rabbitmq.component;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Component
public class EmailServiceComponent {
	
	private final JavaMailSender emailSender;
	
	public EmailServiceComponent(JavaMailSender emailSender) {
		this.emailSender = emailSender;
	}
	
	
	public void sendEmail(String targetEmail, String subject, String message, List<String> ccs) throws MessagingException {
		
		MimeMessage mimeMessage = this.emailSender.createMimeMessage();
		
		mimeMessage.addRecipient( Message.RecipientType.TO, new InternetAddress( targetEmail ) );
		mimeMessage.addRecipients( Message.RecipientType.CC, String.join( ",", ccs ) );
		mimeMessage.setSubject( subject );
		mimeMessage.setText( message );
		
		this.emailSender.send( mimeMessage );
		
	}
	
	public void sendEmail(String targetEmail, String subject, String message) throws MessagingException {
		
		MimeMessage mimeMessage = this.emailSender.createMimeMessage();
		
		mimeMessage.addRecipient( Message.RecipientType.TO, new InternetAddress( targetEmail ) );
		mimeMessage.setSubject( subject );
		mimeMessage.setText( message );
		
		this.emailSender.send( mimeMessage );
		
	}
	
	
}
