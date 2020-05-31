package br.com.fjunior.rabbitmq.config.rabbit;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j2
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
	
	@Value("${exchange.name}")
	public String exchangeName;
	
	public static final String QUEUE = "emails-to-send";
	public static final String ROUTING_KEY = "";
	
	
	@Bean
	public Exchange declareExchange() {
		return ExchangeBuilder.directExchange( this.exchangeName )
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
	public CachingConnectionFactory connectionFactory() {
		
		log.info( "RabbitMQ host started at " + this.host );
		
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setUsername( this.rabbitUsername );
		connectionFactory.setPassword( this.rabbitPassword );
		connectionFactory.setHost( this.host );
		connectionFactory.setPort( this.port );
		
		return connectionFactory;
		
	}
	
	
}
