package br.com.fjunior.rabbitmq.payload;

import lombok.Data;

@Data
public class EmailRequest {
	
	private String message;
	private String targetEmail;
	
}
