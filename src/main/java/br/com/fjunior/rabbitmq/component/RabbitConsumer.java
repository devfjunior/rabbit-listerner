package br.com.fjunior.rabbitmq.component;

import br.com.fjunior.rabbitmq.config.rabbit.EmailAMQPConfig;
import br.com.fjunior.rabbitmq.payload.QueueEmailRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.nio.charset.StandardCharsets;


@Log4j2
@Component
public class RabbitConsumer {
	
	private final EmailServiceComponent emailServiceComponent;
	
	public RabbitConsumer(EmailServiceComponent emailServiceComponent) {
		this.emailServiceComponent = emailServiceComponent;
	}
	
	@RabbitListener(queues = EmailAMQPConfig.QUEUE)
	public void consumer(Message message) {
		
		try {
			String json = new String( message.getBody(), StandardCharsets.UTF_8 );
			ObjectMapper objectMapper = new ObjectMapper();
			QueueEmailRequest queueEmailRequest = objectMapper.readValue( json, QueueEmailRequest.class );
			
			this.emailServiceComponent.sendEmail( queueEmailRequest.getRequestedEmail().getTargetEmail(), "Teste de email",
				queueEmailRequest.getRequestedEmail().getMessage(), queueEmailRequest.getAdminEmails() );
			
			log.info( "Email envido!" );
		} catch (JsonProcessingException | MessagingException e) {
			log.fatal( e.getMessage() );
		}
	}
	
}
