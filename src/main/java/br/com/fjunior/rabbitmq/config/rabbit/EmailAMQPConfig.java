package br.com.fjunior.rabbitmq.config.rabbit;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Configuration
public class EmailAMQPConfig {
	
	@Value("${rabbit.username}")
	private String rabbitUsername;
	
	@Value("${rabbit.password}")
	private String rabbitPassword;
	
	@Value("${rabbit.host}")
	private String host;
	
	@Value("${rabbit.port}")
	private Integer port;
	
	public static final String QUEUE = "emails-to-send";
	public static final String EXCHANGE_NAME = "Emails";
	public static final String ROUTING_KEY = "";
	
	
	@Bean
	public Exchange declareExchange() {
		return ExchangeBuilder.directExchange( EXCHANGE_NAME )
			       .durable( true )
			       .build();
	}
	
	
	@Bean
	public Queue declareQueue() {
		return QueueBuilder.durable( QUEUE )
			       .build();
	}
	
	@Bean
	public Binding declareBinding(Exchange exchange, Queue queue) {
		return BindingBuilder.bind( queue )
			       .to( exchange )
			       .with( ROUTING_KEY )
			       .noargs();
	}
	
	@Bean
	public Connection connectionMQ() throws IOException, TimeoutException {
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUsername( this.rabbitUsername );
		factory.setPassword( this.rabbitPassword );
		factory.setHost( this.host );
		factory.setPort( this.port );
		
		return factory.newConnection();
		
	}
	
	
}
