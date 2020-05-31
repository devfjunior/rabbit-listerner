package br.com.fjunior.rabbitmq.config.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {
	
	@Value("${smtp.host}")
	private String smtpHost;
	
	@Value("${smtp.port}")
	private Integer smtpPort;
	
	@Value("${smtp.username}")
	private String smtpUsername;
	
	@Value("${smtp.password}")
	private String smtpPassword;
	
	@Bean
	public JavaMailSender getJavaMailSender() {
		
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost( this.smtpHost );
		javaMailSender.setPort( this.smtpPort );
		
		javaMailSender.setUsername( this.smtpUsername );
		javaMailSender.setPassword( this.smtpPassword );
		
		Properties props = javaMailSender.getJavaMailProperties();
		props.put( "mail.transport.protocol", "smtp" );
		props.put( "mail.smtp.auth", "true" );
		props.put( "mail.smtp.starttls.enable", "true" );
		
		return javaMailSender;
		
	}
	
}
