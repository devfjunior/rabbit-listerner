package br.com.fjunior.rabbitmq.payload;

import lombok.Data;

import java.util.List;

@Data
public class QueueEmailRequest {
	
	private List<String> adminEmails;
	
	private EmailRequest requestedEmail;
	
}
