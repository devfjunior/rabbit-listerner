package br.com.fjunior.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackageClasses = {RabbitmqApplication.class})
public class RabbitmqApplication {
	
	public static void main(String[] args) {
		SpringApplication.run( RabbitmqApplication.class, args );
	}
	
}
